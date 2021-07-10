package com.tabwu.MQ;

import com.rabbitmq.client.*;
import com.tabwu.MQ.provider.Direct_provider;
import com.tabwu.MQ.utils.ConnectionUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Direct_comsumer1 {
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();

        channel.basicConsume(Direct_provider.DIRECT_QUEUE_1,true, new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("queue_1_insert----" + new String(body));
            }
        });
    }
}
