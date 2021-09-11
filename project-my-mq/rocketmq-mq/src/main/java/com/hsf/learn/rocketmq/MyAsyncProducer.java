package com.hsf.learn.rocketmq;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.io.UnsupportedEncodingException;

public class MyAsyncProducer {
    public static void main(String[] args) throws InterruptedException, MQClientException, UnsupportedEncodingException, RemotingException {
        DefaultMQProducer producer = new DefaultMQProducer("myproducer_group01");
        producer.setNamesrvAddr("node01:9876");

        //初始化生产者
        producer.start();

        for(int i = 0; i < 100; i++){
            Message message = new Message("topic_02", "hello,rocket".getBytes(RemotingHelper.DEFAULT_CHARSET));

            producer.send(message, new SendCallback() {
                @Override
                public void onSuccess(SendResult sendResult) {
                    System.out.println("发送成功=="+sendResult);
                }

                @Override
                public void onException(Throwable throwable) {
                    System.out.println("发送失败=="+throwable.getMessage());

                }
            });

        }

        Thread.sleep(10_000);

        producer.shutdown();
    }
}
