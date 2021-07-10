package com.tabwu.MQ;

import com.rabbitmq.client.*;
import com.tabwu.MQ.provider.Work_provider;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Work_comsumer1 {
    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();

        factory.setHost("www.tabwu.com");
        factory.setPort(5672);
        factory.setVirtualHost("tabwu");
        factory.setUsername("admin");
        factory.setPassword("admin");

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.basicQos(1);

        channel.basicConsume(Work_provider.QUEUE_NAME, true, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("work1----" + new String(body));
            }
        });
    }
}
