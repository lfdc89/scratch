package it.sogei.architetture.models;


import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class KafkaEventModel {

    private Headers headers;
    private OutboxEntryModel value;
    private String key;
    
    @Data
    public static class Headers {
        private String traceparent;
        private int id;
        private String type;
        private String aggregateType;

        @JsonProperty("outbox-id")
        private String outboxId;
    }

}
