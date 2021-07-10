package com.tabwu.MQ.provider;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class MQ_provider {
    public static final String QUEUE_NAME = "queue_simple";

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();

        factory.setHost("www.tabwu.com");
        factory.setPort(5672);
        factory.setVirtualHost("tabwu");
        factory.setUsername("admin");
        factory.setPassword("admin");

        Connection connection = factory.newConnection();

        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, true, false, false, null);

        String message = "holle world";

        channel.basicPublish("", QUEUE_NAME, null, message.getBytes());

        System.out.println("message alreadly send");

        channel.close();

        connection.close();
    }
}
