package com.tabwu.MQ.provider;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.tabwu.MQ.utils.ConnectionUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Direct_provider {
    public static final String DIRECT_EXCHANGE = "direct_exchange";
    public static final String DIRECT_QUEUE_1 = "direct_queue_1";
    public static final String DIRECT_QUEUE_2 = "direct_queue_2";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(DIRECT_EXCHANGE, BuiltinExchangeType.DIRECT);
        channel.queueDeclare(DIRECT_QUEUE_1,true,false,false,null);
        channel.queueDeclare(DIRECT_QUEUE_2,true,false,false,null);

        channel.queueBind(DIRECT_QUEUE_1,DIRECT_EXCHANGE,"insert");
        channel.queueBind(DIRECT_QUEUE_2,DIRECT_EXCHANGE,"update");

        String msg1 = "add a new data";
        channel.basicPublish(DIRECT_EXCHANGE,"insert",null,msg1.getBytes());

        String msg2 = "update a data";
        channel.basicPublish(DIRECT_EXCHANGE,"update",null,msg2.getBytes());

        channel.close();
        connection.close();
    }
}
