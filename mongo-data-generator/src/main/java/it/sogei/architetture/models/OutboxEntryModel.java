package it.sogei.architetture.models;

import java.util.UUID;
import lombok.Data;

@Data
public class OutboxEntryModel {

    private Id _id;
    private UUID idRichiedente;
    private String tipoRichiedente;
    private NomeDocumento nomeDocumento;
    private IdDoc idDoc;

    @Data
    public static class Id {
        private String $oid;

        public void set$oid(String oid) {
            this.$oid = oid;
        }
        public String get$oid() {
            return this.$oid;
        }
    }

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
