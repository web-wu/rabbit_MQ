package com.tabwu.MQ;

import com.rabbitmq.client.*;
import com.tabwu.MQ.provider.Topics_provider;
import com.tabwu.MQ.utils.ConnectionUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Topics_comsumer2 {
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();

        channel.basicConsume(Topics_provider.TOPICS_QUEUE_2,true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("topics_2-----" + new String(body));
            }
        });
    }
}
