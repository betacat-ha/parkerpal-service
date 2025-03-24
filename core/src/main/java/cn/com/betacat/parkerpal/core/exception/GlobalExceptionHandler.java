package cn.com.betacat.parkerpal.core.exception;

import cn.com.betacat.parkerpal.domain.base.ResResult;
import cn.com.betacat.parkerpal.domain.enums.RespEnum;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 处理自定义的业务异常
     *
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = BizException.class)
    public ResResult bizExceptionHandler(HttpServletRequest req, BizException e) {
        e.printStackTrace();
        logger.error(("发生业务异常！原因是：{}"), e.getErrorMsg());
        return ResResult.error(e.getErrorCode(), e.getErrorMsg());
    }

    /**
     * 处理异常
     *
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    public ResResult bizExceptionHandler(HttpServletRequest req, Exception e) {
        e.printStackTrace();
        logger.error(("发生异常！原因是：{}"), e.getMessage());
        return ResResult.error(RespEnum.FAILURE.getCode(), e.getMessage());
    }
}
