package com.tabwu.MQ.utils;


import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class ConnectionUtil {
    public static Connection getConnection() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();

        factory.setHost("www.tabwu.com");
        factory.setPort(5672);
        factory.setVirtualHost("tabwu");
        factory.setUsername("admin");
        factory.setPassword("admin");

        return  factory.newConnection();
    }
}
