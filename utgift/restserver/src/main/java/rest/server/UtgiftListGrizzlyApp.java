package rest.server;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import rest.api.UtgiftListService;

/**
 * Denne klassen kjører en grizzly server på BASE_URI URI kan ikke endres og har ikke noe å si ellers.
 * det blir baseURI +/utgiftlist
 * legger til et corsfilter på resourceconfig.
 * og ellers er det kode for å gjøre det mulig å teste serveren.
 */
public class UtgiftListGrizzlyApp {

    private static URI BASE_URI = URI.create("http://localhost:8080/");

    public static HttpServer startServer(final String[] args, int waitSecondsForServer) throws IOException {


        final URI baseUri = BASE_URI;

        final ResourceConfig resourceConfig = new UtgiftListConfig();
        resourceConfig.register(new CORSFilter());
        final HttpServer httpServer = GrizzlyHttpServerFactory.createHttpServer(baseUri, resourceConfig);
        if (waitSecondsForServer < 0) {
            return httpServer;
        }
        while (waitSecondsForServer > 0) {
            try {
                final URL clientUrl = new URL(baseUri + UtgiftListService.UTGIFT_LIST_SERVICE_PATH);
                final HttpURLConnection connection = (HttpURLConnection) clientUrl.openConnection();
                final int responseCode = connection.getResponseCode();
                System.out.println("Trying " + clientUrl + ": " + responseCode);
                connection.disconnect();
                if (responseCode == 200) {
                    return httpServer;
                }
            } catch (RuntimeException e) {
                System.out.println(e);
            } catch (Exception e) {
                System.out.println(e);
            }
            try {
                Thread.sleep(1000);
                waitSecondsForServer -= 1;
            } catch (final InterruptedException e) {
                return null;
            }
        }
        return null;
    }

    public static void stopServer(final HttpServer server) throws IOException {
        server.shutdown();
    }

    public static void main(final String[] args) throws IOException {
        try {
            final HttpServer server = startServer(args, -1);
            Runtime.getRuntime().addShutdownHook(new Thread(server::shutdownNow));
            Thread.currentThread().join();
        } catch (final InterruptedException ex) {
            Logger.getLogger(UtgiftListGrizzlyApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
