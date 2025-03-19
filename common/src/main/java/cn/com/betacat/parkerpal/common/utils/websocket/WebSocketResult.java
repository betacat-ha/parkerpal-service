package cn.com.betacat.parkerpal.common.utils.websocket;

import com.alibaba.fastjson.JSON;
import lombok.Data;

@Data
public class WebSocketResult {
    enum Code {
        SUCCESS(200, "成功"),
        ERROR(500, "失败"),
        UNAUTHORIZED(401, "未授权"),
        FORBIDDEN(403, "禁止访问"),
        NOT_FOUND(404, "未找到"),
        METHOD_NOT_ALLOWED(405, "方法不允许"),
        INTERNAL_SERVER_ERROR(500, "服务器内部错误"),
        BAD_GATEWAY(502, "错误的网关"),
        SERVICE_UNAVAILABLE(503, "服务不可用"),
        GATEWAY_TIMEOUT(504, "网关超时");

        private final int code;
        private final String message;

        Code(int code, String message) {
            this.code = code;
            this.message = message;
        }
    }
    private String code;
    private String message;
    private Object data;

    public WebSocketResult(Code code) {
        this.code = String.valueOf(code.code);
        this.message = code.message;
    }

    public WebSocketResult(Code code, String message) {
        this.code = String.valueOf(code.code);
        this.message = message;
    }

    public WebSocketResult(Code code, Object data) {
        this.code = String.valueOf(code.code);
        this.message = code.message;
        this.data = data;
    }

    public WebSocketResult(Code code, String message, Object data) {
        this.code = String.valueOf(code.code);
        this.message = message;
        this.data = data;
    }

    public static String success() {
        return new WebSocketResult(Code.SUCCESS).toJson();
    }

    public static String success(Object data) {
        return new WebSocketResult(Code.SUCCESS, data).toJson();
    }

    public static String error() {
        return new WebSocketResult(Code.ERROR).toJson();
    }

    public static String unauthorized() {
        return new WebSocketResult(Code.UNAUTHORIZED).toJson();
    }



    private String toJson() {
        return JSON.toJSONString(this);
    }
}
