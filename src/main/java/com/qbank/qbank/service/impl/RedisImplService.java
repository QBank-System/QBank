package com.qbank.qbank.service.impl;

import com.qbank.qbank.service.inf.IRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.concurrent.TimeUnit;

/**
 * @author 王宇杰
 * @date 2020/1/18 14:05
 */
@Service
public class RedisImplService implements IRedisService {
    @Autowired
    private RedisTemplate<String, String> template;

    @Override
    public void expire(String key, long time) throws Exception {
        if (time > 0) {
            template.expire(key, time, TimeUnit.SECONDS);
        }
    }

    @Override
    public long getExpire(String key) throws Exception {
        return template.getExpire(key, TimeUnit.SECONDS);
    }

    @Override
    public boolean hasKey(String key) throws Exception {
        return template.hasKey(key);
    }

    @Override
    public void del(String... key) throws Exception {
        if (key != null && key.length > 0) {
            if (key.length == 1) {
                template.delete(key[0]);
            } else {
                template.delete(CollectionUtils.arrayToList(key));
            }
        }
    }

    @Override
    public Object get(String key) throws Exception {
        return key == null ? null : template.opsForValue().get(key);
    }

    @Override
    public boolean set(String key, Object value) throws Exception {
        template.opsForValue().set(key, String.valueOf(value));
        return true;
    }

    @Override
    public boolean set(String key, Object value, long time) throws Exception {
        if (time > 0) {
            template.opsForValue().set(key, String.valueOf(value), time, TimeUnit.SECONDS);
        } else {
            set(key, value);
        }
        return true;
    }

}
