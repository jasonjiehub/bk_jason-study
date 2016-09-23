package com.sogou.study.redis.testShiWu;

import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

import java.util.List;

/**
 * Created by denglinjie on 2016/6/29.
 */
public class TestRuntine {

    @Test
    public void test() {
        Jedis jedis = new Jedis("localhost");
        try {
            Transaction transaction = jedis.multi();
            transaction.lpush("key", "11");
            transaction.lpush("key", "22");
            int a = 6 / 0;
            transaction.lpush("key", "33");
            transaction.lpush("key", "546");
            List<Object> list = transaction.exec();
        } catch (Exception e) {

        }
//        System.out.println(jedis.lrange("key",0,10));
    }
}
