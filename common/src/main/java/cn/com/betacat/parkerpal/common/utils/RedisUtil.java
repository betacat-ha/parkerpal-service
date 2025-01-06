package cn.com.betacat.parkerpal.common.utils;

import cn.com.betacat.parkerpal.common.constants.RedisMessageConstant;
import java.util.concurrent.TimeUnit;
import org.springframework.data.redis.core.RedisTemplate;

public final class RedisUtil {

    private static RedisTemplate<String, Object> redisTemplate;

    // 私有构造函数防止实例化
    private RedisUtil() {}

    public static void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
        RedisUtil.redisTemplate = redisTemplate;
    }

    public static void set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public static void set(String key, Object value, long timeout) {
        redisTemplate.opsForValue().set(key, value, timeout);
    }

    public static void set(String key, Object value, long timeout, TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(key, value, timeout, timeUnit);
    }

    public static Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public static void delete(String key) {
        redisTemplate.delete(key);
    }

    public static void expire(String key, long timeout) {
        redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
    }

    public static void setResetPwdCode(String telephone, String authCode) {
        set(telephone + RedisMessageConstant.SENDTYPE_RESETPWD, authCode, 5, TimeUnit.MINUTES);
    }

    public static String getResetPwdCode(String telephone) {
        Object code = get(telephone + RedisMessageConstant.SENDTYPE_RESETPWD);
        return code == null ? null : (String) code;
    }
}