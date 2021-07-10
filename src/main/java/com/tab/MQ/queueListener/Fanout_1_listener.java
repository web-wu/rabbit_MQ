package com.tab.MQ.queueListener;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

public class Fanout_1_listener implements MessageListener {
    @Override
    public void onMessage(Message message) {
        System.out.println("fanout_1_queue"+ new String(message.getBody()));
    }
}
