package cn.com.betacat.parkerpal.common.utils.websocket;

import javax.servlet.http.HttpSession;
import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;

public class WebSocketConfigurator extends ServerEndpointConfig.Configurator {
    @Override
    public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response) {
        // 将 HandshakeRequest 存入 UserProperties
        sec.getUserProperties().put(HandshakeRequest.class.getName(), request);
    }
}