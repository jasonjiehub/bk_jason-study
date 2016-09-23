package com.sogou.study.redis.testSubscribe;

import org.junit.Test;
import redis.clients.jedis.Jedis;

/**
 * Created by denglinjie on 2016/6/29.
 */
public class TestSubscribe {
    @Test
    public void testSubscribe() throws Exception {
        Jedis jedis = new Jedis("localhost");
        RedisMsgPubSubListener listener = new RedisMsgPubSubListener();
        jedis.subscribe(listener, "redisChatTest");
        System.out.println("overle");
    }

    @Test
    public void testPublish() {
        Jedis jedis = new Jedis("localhost");
        jedis.publish("redisChatTest", "haha");
    }

}
