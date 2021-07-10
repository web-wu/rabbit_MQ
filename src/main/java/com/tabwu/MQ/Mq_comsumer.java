package com.tabwu.MQ;

import com.rabbitmq.client.*;
import com.tabwu.MQ.provider.MQ_provider;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Mq_comsumer {
    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("www.tabwu.com");
        factory.setPort(5672);
        factory.setVirtualHost("tabwu");
        factory.setUsername("admin");
        factory.setPassword("admin");

        Connection connection = factory.newConnection();

        Channel channel = connection.createChannel();

        channel.queueDeclare(MQ_provider.QUEUE_NAME, true, false, false, null);

        DefaultConsumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println(consumerTag);
                System.out.println(envelope);
                System.out.println(properties);
                System.out.println(new String(body));
            }
        };

        channel.basicConsume(MQ_provider.QUEUE_NAME, true, consumer);
    }
}
