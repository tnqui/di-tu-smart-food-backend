package com.tranngocqui.ditusmartfoodbackend.service.redis;


import java.time.Duration;

public interface RedisService {

    void save(String key, Object value, Duration timeout);

    Object get(String key);

    void delete(String key);

}
