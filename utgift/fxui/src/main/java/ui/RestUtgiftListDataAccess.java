package ui;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import core.Utgift;
import core.UtgiftList;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import json.Save;

public class RestUtgiftListDataAccess implements UtgiftListDataAccess {

    private final String baseUrlString;

    private final ObjectMapper objectMapper;

    /**
     * konstruerer RestUtgiftListDataAccess med en baseurl og en objectmapper
     * @param baseUrlString base urlen, eg baseurl/skole/53
     * @param objectMapper brukes til å konvertere utgifter til og fra json
     */
    public RestUtgiftListDataAccess(final String baseUrlString, final ObjectMapper objectMapper) {
        this.baseUrlString = baseUrlString;
        this.objectMapper = objectMapper;
    }

    protected ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    private URI getRequestUri(final String path) {
        try {
            return new URI(baseUrlString + path);
        } catch (final URISyntaxException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public Collection<Utgift> getAllUtgifter() {
        System.out.println("getAllRestUtgift");
        final URI requestUri = getRequestUri("");
        final HttpRequest request = HttpRequest.newBuilder(requestUri)
                .header("Accept", "application/json")
                .GET()
                .build();
        try {
            final HttpResponse<String> response = HttpClient.newBuilder()
                    .build()
                    .send(request, HttpResponse.BodyHandlers.ofString());
            final String responseString = response.body();
            final UtgiftList readValue = getObjectMapper().readValue(responseString, UtgiftList.class);
            return readValue.toList();
        } catch (JsonParseException | JsonMappingException e) {
            System.out.println(e);
        } catch (IOException | InterruptedException e) {
            System.out.println(e);
        }
        return Collections.emptyList();
    }


    @Override
    public ObservableList<PieChart.Data> getPieChart() {
        final URI requestUri = getRequestUri("");
        final HttpRequest request = HttpRequest.newBuilder(requestUri)
                .header("Accept", "application/json")
                .GET()
                .build();
        try {
            final HttpResponse<String> response = HttpClient.newBuilder()
                    .build()
                    .send(request, HttpResponse.BodyHandlers.ofString());
            final String responseString = response.body();
            final UtgiftList readValue = getObjectMapper().readValue(responseString, UtgiftList.class);
            return readValue.getPieChart();
        } catch (JsonParseException | JsonMappingException e) {
            System.out.println(e);
        } catch (IOException | InterruptedException e) {
            System.out.println(e);
        }
        return FXCollections.emptyObservableList();
    }

    @Override
    public Utgift getUtgift(final int num) {
        System.out.println("get");
        final HttpRequest request = HttpRequest.newBuilder(getRequestUri("/" + num))
                .header("Accept", "application/json")
                .GET()
                .build();
        try {
            final HttpResponse<InputStream> response = HttpClient.newBuilder()
                    .build()
                    .send(request, HttpResponse.BodyHandlers.ofInputStream());
            return getObjectMapper().readValue(response.body(), Utgift.class);
        } catch (JsonParseException | JsonMappingException e) {
            System.out.println(e);
        } catch (IOException | InterruptedException e) {
            System.out.println(e);
        }
        return null;
    }

    /**
     * sletter en utgift basert på kategori, og derreter indexen
     * @param index indeksen til utgiften iht kategori
     * @param kategori kategorien til utgiften
     */
    public void deleteUtgift(final int index, String kategori) {
        try {
            final HttpRequest request = HttpRequest.newBuilder(getRequestUri("/" + kategori + "/" + index))
                    .DELETE()
                    .build();
            final HttpResponse<InputStream> response =
                    HttpClient.newBuilder()
                    .build()
                    .send(request, HttpResponse.BodyHandlers.ofInputStream());
            System.out.println("RestUtgiftListDataAccess.deleteUtgift() response: " + response);
        } catch (final IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addUtgift(final List<Utgift> utgifter) {
        try {
            final UtgiftList ul = new UtgiftList(utgifter);
            final HttpRequest request = HttpRequest.newBuilder(getRequestUri(""))
                    .header("Content-Type", "application/json")
                    .header("Accept", "application/json")
                    //.POST(BodyPublishers.ofString("{\"kake\":\"2.0\"}"))
                    .POST(BodyPublishers.ofString(getObjectMapper().writeValueAsString(ul)))
                    .build();
            System.out.println(request);
            final HttpResponse<InputStream> response = HttpClient.newBuilder()
                    .build()
                    .send(request, HttpResponse.BodyHandlers.ofInputStream());
            System.out.println("Dataaccess.addUtgift() response: " + response);
            System.out.println("saving");
            Save.save(getAllUtgifter(),new File("../core/src/main/resources/json/save.json"));

            //Load.retrieve(new File("save.json"));
            //System.out.println("feilen er her");
            // feilen er fiksa tror jeg. - mvh Nico
        } catch (final JsonProcessingException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public ObservableList<Utgift> getUtgifter() {
        return FXCollections.observableArrayList(getAllUtgifter());
    }
}
