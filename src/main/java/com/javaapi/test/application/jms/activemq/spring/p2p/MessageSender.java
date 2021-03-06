package com.javaapi.test.application.jms.activemq.spring.p2p;

import org.springframework.jms.core.JmsTemplate;

public class MessageSender {

    private final JmsTemplate jmsTemplate;

    public MessageSender(final JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void send(final String text) {
        jmsTemplate.convertAndSend(text);
    }
}