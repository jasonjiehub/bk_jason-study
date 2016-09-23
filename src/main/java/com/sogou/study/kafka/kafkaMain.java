package com.sogou.study.kafka;

/**
 * Created by denglinjie on 2016/7/5.
 */
public class kafkaMain {
    public static void main(String[] args) {
        String groupId = "group-1";
        String topic = "jason-test-topic";

        KafkaProducer kafkaProducer = new KafkaProducer(topic);
        kafkaProducer.start();

        //消息发送方没有组的概念，消费方才有组的概念
        //发送方只是将消息发送到一个主题，一个组中的消费方只有一个会收到该消息
        KafkaConsumer kafkaConsumer = new KafkaConsumer(groupId, topic);
        kafkaConsumer.start();
    }
}
