package it.sogei.architetture.producer;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import it.sogei.architetture.models.KafkaEventModel;

import org.springframework.beans.factory.annotation.Value;

@Service
public class KafkaEventProducer {

    private final KafkaTemplate<String, KafkaEventModel> kafkaTemplate;

    @Value("${kafka.topic.name:test-topic}")
    private String topicName;

    public KafkaEventProducer(KafkaTemplate<String, KafkaEventModel> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(KafkaEventModel event) {
        kafkaTemplate.send(topicName, event);
    }

}
