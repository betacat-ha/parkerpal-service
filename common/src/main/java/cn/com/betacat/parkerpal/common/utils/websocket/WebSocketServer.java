/*
 * Copyright 2023 BetaCat_HA
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cn.com.betacat.parkerpal.common.utils.websocket;


import cn.com.betacat.parkerpal.common.constants.WebSocketConstant;
import cn.com.betacat.parkerpal.common.utils.AuthorityType;
import cn.com.betacat.parkerpal.common.utils.JwtUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;


@ServerEndpoint(value = "/ws/{userId}", configurator = WebSocketConfigurator.class)
@Api(tags = "WebSocketServer", value = "WebSocketServer")
@Component
@Slf4j
public class WebSocketServer {

    /**
     * 注入ApplicationEventPublisher
     * @param publisher
     */
    private static ApplicationEventPublisher eventPublisher;


    @Autowired
    public void setEventPublisher(ApplicationEventPublisher publisher) {
        WebSocketServer.eventPublisher = publisher;
    }

    /**
     * 静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
     */
    private static int onlineCount = 0;
    /**
     * concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
     */
    private static ConcurrentHashMap<String, WebSocketServer> webSocketMap = new ConcurrentHashMap<>();
    /**
     * 与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    private Session session;

    /**
     * 接收userId
     */
    private String userId = "";

    /**
     * 过期时间
     */
    private long expireTime = 0;

    /**
     * 订阅主题
     */
    private String topic = WebSocketConstant.TOPIC_SPACE_STATUS;



    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("userId") String userId, EndpointConfig config) {
        this.session = session;
        this.userId = userId;

        HandshakeRequest request = (HandshakeRequest) config.getUserProperties().get(HandshakeRequest.class.getName());
        String token = null;
        boolean authPass = false;

        try {
            token = request.getHeaders().get("Sec-WebSocket-Protocol").get(0);

            // 校验用户ID是否匹配
            if (!Objects.equals(JwtUtil.getAccount(token), userId)) {
                log.error("用户ID不匹配，用户：" + userId);
                throw new Exception("用户ID不匹配");
            }

            // 鉴权
            WebSocketAuthEvent event = new WebSocketAuthEvent(this, token, AuthorityType.READ);
            eventPublisher.publishEvent(event);
            authPass = event.isAuthorized();

            // 获取token中的过期时间
            expireTime = JwtUtil.getExpireTime(token);

        } catch (Exception e) {
            log.error("鉴权失败，用户：" + userId);
        }


        if (!authPass || expireTime < System.currentTimeMillis()) {
            try {
                session.close();
            } catch (IOException e) {
                log.error("关闭连接失败，用户：" + userId);
            }
            return;
        }


        if (webSocketMap.containsKey(userId)) {
            webSocketMap.remove(userId);
            webSocketMap.put(userId, this);
            // 加入set中
        } else {
            webSocketMap.put(userId, this);
            // 加入set中
            addOnlineCount();
            // 在线数加1
        }

        log.info("用户连接:" + userId + ",当前在线人数为:" + getOnlineCount());

        try {
            sendMessage(WebSocketResult.success());
        } catch (IOException e) {
            log.error("用户:" + userId + ",网络异常!!!!!!");
        }
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        if (webSocketMap.containsKey(userId)) {
            webSocketMap.remove(userId);
            //从set中删除
            subOnlineCount();
        }
        log.info("用户退出:" + userId + ",当前在线人数为:" + getOnlineCount());
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("用户消息:" + userId + ",报文:" + message);
        // 可以群发消息
        // 消息保存到数据库、redis
        if (StringUtils.isNotBlank(message)) {
            try {
                // 解析发送的报文
                JSONObject jsonObject = JSON.parseObject(message);
                // 追加发送人(防止串改)
                jsonObject.put("fromUserId", this.userId);
            } catch (JSONException e) {
                log.error("用户消息:" + userId + ",报文:" + message + "，无法解析为JSON");
            } catch (Exception e) {
                log.error("用户消息:" + userId + ",报文:" + message + "，异常:" + e);
            }
        }
    }

    /**
     * 出现错误
     *
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        log.error("用户错误:" + this.userId + ",原因:" + error.getMessage());
        error.printStackTrace();
    }

    /**
     * 实现服务器主动推送
     */
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }


    /**
     * 发送消息给指定用户
     * @param message 消息内容
     * @param userId 用户ID
     */
    public static void sendMessage(String message, @PathParam("userId") String userId) throws IOException {
        log.info("发送消息到:" + userId + "，报文:" + message);
        if (StringUtils.isNotBlank(userId) && webSocketMap.containsKey(userId)) {
            webSocketMap.get(userId).sendMessage(message);
        } else {
            log.error("用户" + userId + ",不在线！");
        }
    }

    /**
     * 群发消息给指定用户
     */
    public static void sendMessage(String message, List<String> userId) throws IOException {
        log.info("群发消息:" + message + "，共" + userId.size() + "人");
        for (String id : userId) {
            sendMessage(message, id);
        }
        log.info("群发消息结束");
    }


    /**
     * 发送消息
     * @param message 消息内容
     * @param topic 主题
     */
    public static void sendMessageByTopic(String message, String topic) {
        log.info("开始向指定主题的用户发送消息");
        webSocketMap.forEachValue(1, webSocketServer -> {
            if (webSocketServer.topic.equals(topic)) {
                try {
                    webSocketServer.sendMessage(message);
                } catch (IOException e) {
                    log.error("发送消息失败：", e);
                }
            }
        });
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebSocketServer.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebSocketServer.onlineCount--;
    }
}