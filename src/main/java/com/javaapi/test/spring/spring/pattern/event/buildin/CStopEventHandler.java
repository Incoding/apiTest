package com.javaapi.test.spring.spring.pattern.event.buildin;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextStoppedEvent;

public class CStopEventHandler implements ApplicationListener<ContextStoppedEvent> {

    @Override
    public void onApplicationEvent(ContextStoppedEvent arg0) {
        System.out.println("ContextStoppedEvent received");
    }

}