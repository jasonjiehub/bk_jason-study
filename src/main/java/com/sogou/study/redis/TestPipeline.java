package com.sogou.study.redis;

import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

import java.util.List;

/**
 * Created by denglinjie on 2016/6/29.
 */
public class TestPipeline {
    @Test
    public void test() {
        Jedis jedis = new Jedis("localhost");
        jedis.auth("123");
//        jedis.slaveof("", 6379);
//        ClientOne client = new ClientOne("localhost");
//        System.out.println(jedis.info());
//        client.clientSetname("jaosn");
//        System.out.println(client.clientGetname());
//        System.out.println(jedis.keys("*"));
//        jedis.flushAll();
//        System.out.println(jedis.keys("*"));
//        jedis.set("key", "11");
//        jedis.save();
//        jedis.flushAll();

//        jedis.configGet("D:\\redis\\64bit");

//        System.out.println(jedis.get("key"));
//        System.out.println(jedis.configGet("dir"));
//        System.out.println(jedis.configGet("requirepass"));
//        jedis.configSet("requirepass","123");
//        System.out.println(jedis.configGet("maxclients"));

        long start = System.currentTimeMillis();
        for(int i=0; i<100000; i++) {
            jedis.lpush("key", ""+i);
        }
        long end = System.currentTimeMillis();
        System.out.println(end-start);
        jedis.flushAll();

        long start2 = System.currentTimeMillis();
        Pipeline pipeline = jedis.pipelined();
        for(int i=0; i<100000; i++) {
            pipeline.lpush("key", ""+i);
        }
        List<Object> list = pipeline.syncAndReturnAll();
        long end2 = System.currentTimeMillis();
        System.out.println(end2-start2);
        jedis.flushAll();

        jedis.disconnect();

    }
}
