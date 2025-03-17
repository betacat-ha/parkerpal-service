package cn.com.betacat.parkerpal.common.utils.websocket;

import cn.com.betacat.parkerpal.common.utils.AuthorityType;
import org.springframework.context.ApplicationEvent;

public class WebSocketAuthEvent extends ApplicationEvent {
    private final String token;
    private final String authorityType;
    private boolean authorized = false;

    public WebSocketAuthEvent(Object source, String token, String authorityType) {
        super(source);
        this.token = token;
        this.authorityType = authorityType;
    }

    public String getToken() {
        return token;
    }

    public void setAuthorized(boolean authorized) {
        this.authorized = authorized;
    }

    public boolean isAuthorized() {
        return authorized;
    }

    public String getAuthorityType() {
        return authorityType;
    }
}
