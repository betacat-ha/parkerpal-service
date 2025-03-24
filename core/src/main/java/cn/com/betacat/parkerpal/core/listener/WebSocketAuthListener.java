package cn.com.betacat.parkerpal.core.listener;

import cn.com.betacat.parkerpal.common.utils.websocket.WebSocketAuthEvent;
import cn.com.betacat.parkerpal.core.interceptor.JwtAuthInterceptor;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class WebSocketAuthListener implements ApplicationListener<WebSocketAuthEvent> {

    private final JwtAuthInterceptor jwtAuthInterceptor;

    public WebSocketAuthListener(JwtAuthInterceptor jwtAuthInterceptor) {
        this.jwtAuthInterceptor = jwtAuthInterceptor;
    }

    @Override
    public void onApplicationEvent(@NotNull WebSocketAuthEvent event) {
        try {
            jwtAuthInterceptor.loginInspect(event.getToken(), true, event.getAuthorityType());
            event.setAuthorized(true); // 验证成功
        } catch (Exception e) {
            event.setAuthorized(false); // 验证失败
        }
    }
}
