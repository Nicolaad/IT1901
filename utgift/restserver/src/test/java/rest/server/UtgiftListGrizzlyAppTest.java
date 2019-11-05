package rest.server;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class UtgiftListGrizzlyAppTest {
    private HttpServer server;

    @Before
    public void setUp() throws Exception {
        server = UtgiftListGrizzlyApp.startServer(null,-1);
    }

    @After
    public void tearDown() throws Exception {
        UtgiftListGrizzlyApp.stopServer(server);
    }

    @Test
    public void startStopServer() throws IOException {
        UtgiftListGrizzlyApp.stopServer(server);
        HttpServer s = UtgiftListGrizzlyApp.startServer(null,2);
        UtgiftListGrizzlyApp.stopServer(s);
    }

}