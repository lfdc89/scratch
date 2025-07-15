package it.sogei.architetture.exporter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import it.sogei.architetture.models.KafkaEventModel;
import it.sogei.architetture.models.OutboxEntryModel;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JsonExporter {

    public static void exportToJson(List<OutboxEntryModel> documenti, String filePath) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        try {
            mapper.writeValue(new File(filePath), documenti);
            System.out.println("File JSON scritto con successo in: " + filePath);
        } catch (IOException e) {
            System.err.println("Errore durante la scrittura del file JSON: " + e.getMessage());
        }
    }

    public static void exportToKafkaJson(List<KafkaEventModel> documenti, String filePath) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        try {
            mapper.writeValue(new File(filePath), documenti);
            System.out.println("File JSON scritto con successo in: " + filePath);
        } catch (IOException e) {
            System.err.println("Errore durante la scrittura del file JSON: " + e.getMessage());
        }
    }

}
