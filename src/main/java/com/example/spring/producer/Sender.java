package com.example.spring.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;

@Component
public class Sender {

    private static final Logger LOGGER = LoggerFactory.getLogger(Sender.class);

    @Autowired
    private JmsTemplate jmsTemplate;

    public void sendMessage(final Product product){

        jmsTemplate.send(new MessageCreator() {

            public Message createMessage(Session session) throws JMSException {

                ObjectMessage objectMessage = session.createObjectMessage(product);
                LOGGER.info("sending message='{}'",product);
                return objectMessage;
            }
        });


    }

   /* public void sendMessage(final String message){

        jmsTemplate.send(new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                ObjectMessage objectMessage = session.createObjectMessage(message);
                LOGGER.info("sending message='{}'", message);
                return objectMessage;
            }
        });
    }*/

}
