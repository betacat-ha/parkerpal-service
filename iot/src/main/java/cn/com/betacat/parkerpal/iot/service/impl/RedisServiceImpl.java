package cn.com.betacat.parkerpal.iot.service.impl;

import cn.com.betacat.parkerpal.common.constants.RedisMessageConstant;
import cn.com.betacat.parkerpal.iot.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class RedisServiceImpl implements RedisService {
    @Autowired
    private RedisTemplate redisTemplate;

    public void set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public void set(String key, Object value, long timeout) {
        redisTemplate.opsForValue().set(key, value, timeout);
    }

    public void set(String key, Object value, long timeout, TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(key, value, timeout, timeUnit);
    }

    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public void delete(String key) {
        redisTemplate.delete(key);
    }

    public void expire(String key, long timeout) {
        redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
    }

    public void setResetPwdCode(String telephone, String authCode) {
        set(telephone + RedisMessageConstant.SENDTYPE_RESETPWD, authCode, 5, TimeUnit.MINUTES);
    }

    public String getResetPwdCode(String telephone) {
        Object code = get(telephone + RedisMessageConstant.SENDTYPE_RESETPWD);
        return code == null ? null : (String) code;
    }
}
