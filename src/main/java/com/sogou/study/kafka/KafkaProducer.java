package com.sogou.study.kafka;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

import java.util.Properties;

/**
 * Created by denglinjie on 2016/7/5.
 */
public class KafkaProducer extends Thread {
    private final Producer<String, String> producer;
    private final String topic;
    private final Properties props = new Properties();

    public KafkaProducer(String topic) {
        props.put("zookeeper.connect", "10.134.113.75:2183");
        props.put("metadata.broker.list", "10.134.113.75:9092");
        props.put("serializer.class", "kafka.serializer.StringEncoder");
        producer = new Producer<String, String>(new ProducerConfig(props));
        this.topic = topic;
    }

    @Override
    public void run() {
        int messageNo = 1;
        while(true) {
            ++messageNo;
            String message = "who are you " + messageNo;
            KeyedMessage<String, String> messageContent = new KeyedMessage<String, String>("jason-test-topic", message);
            producer.send(messageContent);
            try {
                sleep(3000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
