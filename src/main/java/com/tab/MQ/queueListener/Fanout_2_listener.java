package com.tab.MQ.queueListener;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

public class Fanout_2_listener implements MessageListener {
    @Override
    public void onMessage(Message message) {
        System.out.println("fanout_2_queue"+ new String(message.getBody()));
    }
}
