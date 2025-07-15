package it.sogei.architetture.generator;

import it.sogei.architetture.models.KafkaEventModel;
import it.sogei.architetture.models.OutboxEntryModel;

import java.util.Random;
import java.util.UUID;

public class KafkaEventGenerator {

    public static KafkaEventModel generate(OutboxEntryModel outboxEntry) {
        KafkaEventModel model = new KafkaEventModel();
        KafkaEventModel.Headers headers = generateHeader();
        model.setHeaders(headers);
        model.setValue(outboxEntry);
        model.setKey(UUID.randomUUID().toString());
        return model;
    }


    private static KafkaEventModel.Headers generateHeader() {
        KafkaEventModel.Headers header = new KafkaEventModel.Headers();
        header.setTraceparent(generateTraceparent());
        header.setId(new Random().nextInt(1000)); // oppure UUID.randomUUID().toString()
        header.setType("DocumentoSalvato");
        header.setAggregateType("GESTORE_DOCUMENTI");
        return header;
    }

    private static String generateTraceparent() {
        String traceId = UUID.randomUUID().toString().replace("-", "").substring(0, 32);
        String spanId = UUID.randomUUID().toString().replace("-", "").substring(0, 16);
        return "00-" + traceId + "-" + spanId + "-01";
    }

}
