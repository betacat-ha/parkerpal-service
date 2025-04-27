package cn.com.betacat.parkerpal.domain.exception;

/**
 * @author
 */
public interface CustomError {

    /**
     * 错误码
     *
     * @return
     */
    Integer getResultCode();

    /**
     * 错误描述
     *
     * @return
     */
    String getResultMsg();
}
