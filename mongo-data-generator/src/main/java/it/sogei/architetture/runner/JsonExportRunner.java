package it.sogei.architetture.runner;

import it.sogei.architetture.exporter.JsonExporter;
import it.sogei.architetture.generator.KafkaEventGenerator;
import it.sogei.architetture.generator.OutboxEntryGenerator;
import it.sogei.architetture.models.KafkaEventModel;
import it.sogei.architetture.models.OutboxEntryModel;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JsonExportRunner implements CommandLineRunner {

    public void run(String... args) {

        List<OutboxEntryModel> entries = new ArrayList<>();
        List<KafkaEventModel> events = new ArrayList<>();
        int numeroElementi = 10;

        for(int i=0;i<numeroElementi;i++) {
            OutboxEntryModel entry = OutboxEntryGenerator.generate();
            entries.add(entry);
            KafkaEventModel event = KafkaEventGenerator.generate(entry);
            events.add(event);
        }

        JsonExporter.exportToJson(entries, "mongo_outbox_entries.json");
        JsonExporter.exportToKafkaJson(events, "kafka_events.json");
    }
}
