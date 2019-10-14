package ui;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import core.Utgift;
import core.UtgiftList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import json.Save;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;



public class RestUtgiftListDataAccess implements UtgiftListDataAccess {

    private final String baseUrlString;

    private final ObjectMapper objectMapper;

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
        } catch (IOException | InterruptedException e) {
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
        } catch (IOException | InterruptedException e) {
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
        } catch (IOException | InterruptedException e) {
        }
        return null;
    }
    public void deleteUtgift(final int index, String kategori) {
        try {
            final HttpRequest request = HttpRequest.newBuilder(getRequestUri("/" + kategori + "/" + index))
                    .DELETE()
                    .build();
            System.out.println(request.toString());
            System.out.println(request.headers());
            final HttpResponse<InputStream> response =
                    HttpClient.newBuilder()
                    .build()
                    .send(request, HttpResponse.BodyHandlers.ofInputStream());

        } catch (final IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addUtgift(final Utgift utgift) {
        try {
            final UtgiftList ul = new UtgiftList(Arrays.asList(utgift));
            final HttpRequest request = HttpRequest.newBuilder(getRequestUri(""))
                    .header("Content-Type", "application/json")
                    .header("Accept", "application/json")
                   // .POST(BodyPublishers.ofString("{\"kake\":\"2.0\"}"))
                    .POST(BodyPublishers.ofString(getObjectMapper().writeValueAsString(ul)))
                    .build();
            System.out.println(request);
            final HttpResponse<InputStream> response = HttpClient.newBuilder()
                    .build()
                    .send(request, HttpResponse.BodyHandlers.ofInputStream());
            System.out.println("saving");
            Save.save(getAllUtgifter(),new File("../save.json"));

            //Load.retrieve(new File("save.json"));
            System.out.println("feilen er her");
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
