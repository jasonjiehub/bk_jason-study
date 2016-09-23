package com.sogou.study.redis.jedisPool;

import org.junit.Test;

/**
 * Created by denglinjie on 2016/8/19.
 */
public class TestJedisPool {

    @Test
    public void testPool() {
        RedisClient redisClient = JedisFactory.getRedisClient();
        System.out.println(redisClient.hget("key", "field"));
        System.out.println(redisClient.exists("key"));
    }
}
