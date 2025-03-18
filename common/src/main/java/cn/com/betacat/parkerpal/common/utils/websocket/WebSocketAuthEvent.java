package cn.com.betacat.parkerpal.common.utils.websocket;

import cn.com.betacat.parkerpal.common.utils.AuthorityType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;


@Getter
public class WebSocketAuthEvent extends ApplicationEvent {
    private final String token;
    private final String authorityType;
    @Setter
    private boolean authorized = false;

    public WebSocketAuthEvent(Object source, String token, String authorityType) {
        super(source);
        this.token = token;
        this.authorityType = authorityType;
    }
}
