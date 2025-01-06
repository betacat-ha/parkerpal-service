package cn.com.betacat.parkerpal.domain.exception;

/**
 * @Author: 
 * @Date: 2024/1/4
 * @Time: 上午9:42
 * @Describe:
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
