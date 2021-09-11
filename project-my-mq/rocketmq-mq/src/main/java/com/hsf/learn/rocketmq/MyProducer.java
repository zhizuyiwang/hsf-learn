package com.hsf.learn.rocketmq;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;
import java.io.UnsupportedEncodingException;

public class MyProducer {
    public static void main(String[] args) throws MQClientException, UnsupportedEncodingException, RemotingException, InterruptedException, MQBrokerException {
        DefaultMQProducer producer = new DefaultMQProducer("myproducer_group01");
        producer.setNamesrvAddr("node01:9876");
        producer.start();

        Message message = new Message("topic_01", "hello,rocket".getBytes(RemotingHelper.DEFAULT_CHARSET));

        SendResult result = producer.send(message);

        System.out.println(result);

        producer.shutdown();

    }
}
