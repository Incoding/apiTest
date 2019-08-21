package com.javaapi.test.buisness.pattern.ext.listener;

/**
 * Created by user on 2018/12/30
 */
public class OrderStatusEventListener implements OrderStatusChangeListener<OrderStatusEvent<OrderStatus>>  {
    @Override
    public void change(OrderStatusEvent<OrderStatus> event) {
        if (event == null) {
            System.out.println("event 为空");
            return;
        }
        System.out.println("event = " + event);

    }
}
