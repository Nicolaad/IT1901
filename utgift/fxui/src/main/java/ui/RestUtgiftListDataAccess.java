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


/*
@startuml
class FxAppController
class LatLongs
class BorderPane
class "ListView<LatLong>" as ListView
class "fxmapcontrol.MapBase" as MapBase

BorderPane *--> ListView: "left"
BorderPane *--> MapBase: "center"

FxAppController --> LatLongs: "latLongs"
FxAppController --> MapBase: "mapView"
FxAppController --> ListView: "locationListView"
@enduml
 */

/**
 * Data access object using rest.
 * @author hal
 *
 */
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



    @Override
    public void setUtgift(final int index, final Utgift utgift) {
        try {
            final HttpRequest request = HttpRequest.newBuilder(getRequestUri("/" + index))
                    .header("Content-Type", "application/json")
                    .header("Accept", "application/json")
                    .PUT(BodyPublishers.ofString(getObjectMapper().writeValueAsString(utgift)))
                    .build();
            final HttpResponse<InputStream> response = HttpClient.newBuilder()
                    .build()
                    .send(request, HttpResponse.BodyHandlers.ofInputStream());
            final int realIndex = getObjectMapper().readValue(response.body(), Integer.class);
            if (realIndex < 0) {
                throw new IndexOutOfBoundsException("realIndex");
            }
        } catch (final JsonProcessingException e) {
            throw new RuntimeException(e);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addUtgift(final Utgift utgift) {
        try {
            final UtgiftList utgiftList = new UtgiftList(Arrays.asList(utgift));
            final HttpRequest request = HttpRequest.newBuilder(getRequestUri(""))
                    .header("Content-Type", "application/json")
                    .header("Accept", "application/json")
                    .POST(BodyPublishers.ofString(getObjectMapper().writeValueAsString(utgiftList)))
                    .build();
            final HttpResponse<InputStream> response = HttpClient.newBuilder()
                    .build()
                    .send(request, HttpResponse.BodyHandlers.ofInputStream());
            final int realIndex = getObjectMapper().readValue(response.body(), Integer.class);
            if (realIndex < 0) {
                throw new IndexOutOfBoundsException("realIndex");
            }
        } catch (final JsonProcessingException e) {
            throw new RuntimeException(e);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public ObservableList<Utgift> getUtgifter() {
        return FXCollections.observableArrayList(getAllUtgifter());
    }
}
