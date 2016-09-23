package com.sogou.study.kafka;

import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Created by denglinjie on 2016/7/5.
 */
public class KafkaConsumer extends Thread {
    private final ConsumerConnector consumer;
    private final String topic;

    public KafkaConsumer(String groupId, String topic) {
        consumer = Consumer.createJavaConsumerConnector(createConsumerConfig(groupId));
        this.topic = topic;
    }

    private static ConsumerConfig createConsumerConfig(String groupId) {
        Properties props = new Properties();
        props.put("zookeeper.connect", "10.134.113.75:2183");
        props.put("group.id", groupId);
        props.put("zookeeper.session.timeout.ms", "400");
        props.put("zookeeper.sync.time.ms", "200");
        props.put("auto.commit.interval.ms", "1000");
        return new ConsumerConfig(props);
    }

    @Override
    public void run() {
        Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
        topicCountMap.put(topic, new Integer(1));

        //每调用一次createMessageStreams都会将consumer注册到topic上
        Map<String, List<KafkaStream<byte[], byte[]>>> consumerMap = consumer.createMessageStreams(topicCountMap);
        KafkaStream<byte[], byte[]> stream = consumerMap.get(topic).get(0);
        ConsumerIterator<byte[], byte[]> iterator = stream.iterator();
        while(iterator.hasNext()) {
            System.out.println("receiver message:" + iterator.next().message());
            try {
                sleep(3000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
