package it.sogei.architetture.runner;

import it.sogei.architetture.exporter.JsonExporter;
import it.sogei.architetture.generator.KafkaEventGenerator;
import it.sogei.architetture.generator.OutboxEntryGenerator;
import it.sogei.architetture.models.KafkaEventModel;
import it.sogei.architetture.models.OutboxEntryModel;
import it.sogei.architetture.producer.KafkaEventProducer;

import org.springframework.boot.CommandLineRunner;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ApplicationRunner implements CommandLineRunner {
    
    private final KafkaEventProducer kafkaEventProducer;

    public ApplicationRunner(KafkaEventProducer kafkaEventProducer) {
        this.kafkaEventProducer = kafkaEventProducer;
    }

    public void run(String... args) {

        List<OutboxEntryModel> entries = new ArrayList<>();
        List<KafkaEventModel> events = new ArrayList<>();
        int numeroElementi = 10;

        for(int i=0;i<numeroElementi;i++) {
            OutboxEntryModel entry = OutboxEntryGenerator.generate();
            entries.add(entry);
            KafkaEventModel event = KafkaEventGenerator.generate(entry);
            events.add(event);
            this.kafkaEventProducer.sendMessage(event);
        }

        JsonExporter.exportToJson(entries, "mongo_outbox_entries.json");
        JsonExporter.exportToKafkaJson(events, "kafka_events.json");
    }
}
