package com.tabwu.MQ.provider;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.tabwu.MQ.utils.ConnectionUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Topics_provider {
    public static final String TOPICS_EXCHANGE = "topics_exchange";
    public static final String TOPICS_QUEUE_1 = "topics_queue_1";
    public static final String TOPICS_QUEUE_2 = "topics_queue_2";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(TOPICS_EXCHANGE, BuiltinExchangeType.TOPIC);

        channel.queueDeclare(TOPICS_QUEUE_1,true,false,false,null);
        channel.queueDeclare(TOPICS_QUEUE_2,true,false,false,null);

        channel.queueBind(TOPICS_QUEUE_1,TOPICS_EXCHANGE,"insert.*");
        channel.queueBind(TOPICS_QUEUE_2,TOPICS_EXCHANGE,"#.query");

        String msg1 = "add a user";
        channel.basicPublish(TOPICS_EXCHANGE,"insert.add_user", null, msg1.getBytes());

        String msg2 = "select all users";
        channel.basicPublish(TOPICS_EXCHANGE,"select.all.user.query",null,msg2.getBytes());

        channel.close();
        connection.close();
    }
}
