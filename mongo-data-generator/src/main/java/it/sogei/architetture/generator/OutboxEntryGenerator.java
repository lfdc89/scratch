package it.sogei.architetture.generator;

import it.sogei.architetture.models.OutboxEntryModel;
import org.bson.types.ObjectId;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.util.UUID;

public class OutboxEntryGenerator {

        private static final Random random = new Random();

        public static OutboxEntryModel generate() {
            OutboxEntryModel outboxEntry = new OutboxEntryModel();
            OutboxEntryModel.Id id = new OutboxEntryModel.Id();
            id.set$oid(new ObjectId().toHexString());

            outboxEntry.set_id(id);
            outboxEntry.setIdRichiedente(UUID.randomUUID());
            outboxEntry.setTipoRichiedente("SUPPORTO_INGRESSO");

            // NomeDocumento
            OutboxEntryModel.NomeDocumento nomeDoc = new OutboxEntryModel.NomeDocumento();
            String nomeFile = "IT" + (10000000000L + random.nextInt(999999999)) + "_54N10.xml";
            nomeDoc.setNome(nomeFile);
            nomeDoc.setTipologia(randomTipologia());
            nomeDoc.setHash(sha1Hex(nomeFile));
            outboxEntry.setNomeDocumento(nomeDoc);

            // IdDoc
            OutboxEntryModel.IdDoc idDoc = new OutboxEntryModel.IdDoc();
            idDoc.setStorage("ECS");
            idDoc.setId("atmos-ecs/ecsrm1_scambio_svi/" + UUID.randomUUID() + "-" + UUID.randomUUID());
            idDoc.setDimensione(100 + random.nextInt(10000));
            outboxEntry.setIdDoc(idDoc);

            return outboxEntry;
        }

        private static String randomTipologia() {
            String[] tipi = {"FA", "NC", "DDT"};
            return tipi[random.nextInt(tipi.length)];
        }

        private static String sha1Hex(String input) {
            try {
                MessageDigest md = MessageDigest.getInstance("SHA-1");
                byte[] result = md.digest(input.getBytes());
                StringBuilder sb = new StringBuilder();
                for (byte b : result) {
                    sb.append(String.format("%02x", b));
                }
                return sb.toString();
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
        }
}