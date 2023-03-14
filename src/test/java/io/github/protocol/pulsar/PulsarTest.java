package io.github.protocol.pulsar;

import io.github.embedded.pulsar.core.EmbeddedPulsarServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

public class PulsarTest {

    protected static final EmbeddedPulsarServer SERVER = new EmbeddedPulsarServer();

    protected static PulsarAdmin pulsarAdmin;

    protected static String CLUSTER_STANDALONE = "standalone";

    @BeforeAll
    public static void setup() throws Exception {
        SERVER.start();
        pulsarAdmin = PulsarAdmin.builder().port(SERVER.getWebPort()).build();
    }

    @AfterAll
    public static void teardown() throws Exception {
        SERVER.close();
    }

}
