package com.example.spring;

import com.example.spring.producer.Product;
import com.example.spring.producer.Sender;
import com.example.spring.producer.SenderConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

public class MessageProducerApp {

    /*@Autowired
    static Sender sender;*/

    public static void main(String[] args){

        AbstractApplicationContext context = new AnnotationConfigApplicationContext(SenderConfig.class);
        Sender sender = context.getBean(Sender.class);

        Product product = new Product();
        product.setProductId(1);
        product.setName("oil");
        product.setQuantity(5);

        sender.sendMessage(product);
        System.out.println("message sent successfully");

        context.close();

    }

}
