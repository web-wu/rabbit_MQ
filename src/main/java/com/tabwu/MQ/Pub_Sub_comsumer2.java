package com.tabwu.MQ;

import com.rabbitmq.client.*;
import com.tabwu.MQ.provider.Pub_Sub_provider;
import com.tabwu.MQ.utils.ConnectionUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Pub_Sub_comsumer2 {
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();

        channel.basicConsume(Pub_Sub_provider.FANOUT_QUEUE_2,true, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("handle fanout_queue_2----" + new String(body));
            }
        });
    }
}
