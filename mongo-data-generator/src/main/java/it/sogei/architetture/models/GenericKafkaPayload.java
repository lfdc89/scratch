package it.sogei.architetture.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GenericKafkaPayload {

    private String key;
    private String value;
}
