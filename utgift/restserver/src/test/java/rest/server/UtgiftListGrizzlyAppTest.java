package rest.server;

import java.io.IOException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import org.glassfish.grizzly.http.server.HttpServer;


public class UtgiftListGrizzlyAppTest {
    private HttpServer server;

    /**
     * Starter serveren før testene
     *
     * @throws Exception om den ikke greier å starte serveren
     */
    @Before
    public void setUp() throws Exception {
        server = UtgiftListGrizzlyApp.startServer(null, -1);
    }

    /**
     * Stopper serveren når testen er ferdig
     *
     * @throws Exception om den ikke greier å stoppe serveren
     */
    @After
    public void tearDown() throws Exception {
        UtgiftListGrizzlyApp.stopServer(server);
    }

    /**
     * tester om serveren kan starte og stoppe
     *
     * @throws IOException om serveren ikke kan lese inn startsverdiene
     */
    @Test
    public void startStopServer() throws IOException {
        UtgiftListGrizzlyApp.stopServer(server);
        HttpServer s = UtgiftListGrizzlyApp.startServer(null, 2);
        UtgiftListGrizzlyApp.stopServer(s);
    }

}