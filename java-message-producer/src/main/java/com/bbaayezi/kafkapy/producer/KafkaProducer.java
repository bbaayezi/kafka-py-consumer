package com.bbaayezi.kafkapy.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Component
public class KafkaProducer {
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProducer.class);

    @Value("${kafka.topic-name}")
    private String topicName;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String msg) {
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topicName, msg);

        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onFailure(Throwable ex) {
                LOGGER.error("Unable to send message=["
                        + msg + "] due to : " + ex.getMessage());
            }

            @Override
            public void onSuccess(SendResult<String, String> result) {
                LOGGER.info("Sent message=[" + msg +
                        "] with offset=[" + result.getRecordMetadata().offset() + "]");
            }
        });
    }
}
