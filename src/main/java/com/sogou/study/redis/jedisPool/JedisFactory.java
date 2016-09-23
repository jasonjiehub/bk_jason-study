package com.sogou.study.redis.jedisPool;

/**
 * Created by denglinjie on 2016/8/19.
 */
public class JedisFactory {
    public static RedisClient getRedisClient() {
        String ip = "10.10.10.10";
        int port = 10006;
        int timeout = 3000;
        String auth = "123456";

        return new RedisClient(ip, port, timeout, auth);
    }
}
