package it.sogei.architetture.models;

import java.util.UUID;

import org.bson.types.ObjectId;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import it.sogei.architetture.serializer.ObjectIdSerializer;
import lombok.Data;

@Data
public class OutboxEntryModel {

    @JsonSerialize(using = ObjectIdSerializer.class)
    private ObjectId _id;
    private UUID idRichiedente;
    private String tipoRichiedente;
    private NomeDocumento nomeDocumento;
    private IdDoc idDoc;

    @Data
    public static class NomeDocumento {
        private String nome;
        private String tipologia;
        private String hash;
    }

    @Data
    public static class IdDoc {
        private String storage;
        private String id;
        private int dimensione;
    }

}
