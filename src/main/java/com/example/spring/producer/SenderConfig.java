package com.example.spring.producer;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.ConnectionFactory;


@Configuration
@ComponentScan
@PropertySource("application.properties")  //to read properties file
public class SenderConfig {

    @Value("${activemq.broker-url}")
    private String brokerUrl;

    @Value("${activemq.default-destination}")
    private String dest;

    @Bean
    public ConnectionFactory senderActiveMQConnectionFactory() {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();
        //activeMQConnectionFactory.setBrokerURL("tcp://localhost:61616");
        activeMQConnectionFactory.setBrokerURL(brokerUrl);
        //activeMQConnectionFactory.setTrustedPackages(Arrays.asList("com.example.spring"));

        return activeMQConnectionFactory;
    }

    /*@Bean
    public CachingConnectionFactory cachingConnectionFactory(){  //CachingConnectionFactory for caching of session, connections, and
        return new CachingConnectionFactory(senderActiveMQConnectionFactory());  //producers as well as automatic connection recovery.
    }*/


    //used for sending messages
    @Bean
    public JmsTemplate jmsTemplate() {
        JmsTemplate template = new JmsTemplate();
        template.setConnectionFactory(senderActiveMQConnectionFactory());
        //template.setDefaultDestinationName(messageQueue);
        template.setDefaultDestinationName(dest);
        template.setPubSubDomain(true);         //to send the message to Topic

        return template;
    }

   /* @Bean
    public Sender sender(){
        return new Sender();
    }*/
}
