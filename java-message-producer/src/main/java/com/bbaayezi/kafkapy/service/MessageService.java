package com.bbaayezi.kafkapy.service;

import com.bbaayezi.kafkapy.producer.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    @Autowired
    private KafkaProducer kafkaProducer;

    public void sendMessage(String msg) {
        kafkaProducer.sendMessage(msg);
    }
}
