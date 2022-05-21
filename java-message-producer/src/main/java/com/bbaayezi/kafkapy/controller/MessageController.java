package com.bbaayezi.kafkapy.controller;

import com.bbaayezi.kafkapy.consumer.KafkaConsumer;
import com.bbaayezi.kafkapy.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api")
public class MessageController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageController.class);

    private final MessageService messageService;

    private final KafkaConsumer kafkaConsumer;

    @Autowired
    public MessageController(MessageService messageService, KafkaConsumer kafkaConsumer) {
        this.messageService = messageService;
        this.kafkaConsumer = kafkaConsumer;
    }

    @PostMapping("/sendMessage")
    public void sendMessage(@RequestBody String message) {
        LOGGER.info("Sending kafka message");
        messageService.sendMessage(message);
    }

}
