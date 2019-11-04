package ui;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

import core.Utgift;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.testfx.framework.junit.ApplicationTest;
import rest.api.UtgiftListService;
import rest.server.UtgiftListGrizzlyApp;



@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FxAppUsingRestControllerTest extends ApplicationTest {
    private FxAppUsingRestController controller;
    /* disabled as it is not used - spotbug warning
    private Thread thread;
    */
    private RestUtgiftListDataAccess dataAccess;
    private HttpServer currentServer;

    @Override
    public void start(final Stage stage) throws Exception {
        URL f = getClass().getResource("FxAppUsingRest.fxml");
        final String serverUrlString = "http://localhost:8080/";
        final String clientUrlString = serverUrlString + UtgiftListService.UTGIFT_LIST_SERVICE_PATH;
        final FXMLLoader loader = new FXMLLoader(f);
        final Parent root = loader.load();
        this.controller = loader.getController();
        dataAccess = new RestUtgiftListDataAccess(clientUrlString,controller.getObjectMapper());
        try {
            currentServer = UtgiftListGrizzlyApp.startServer(new String[]{serverUrlString},5);
        } catch (IOException e) {
            throw new IllegalStateException("could not setup server");
        }
        Utgift u1 =   new Utgift("Fisk","200.0","Mat");
        Utgift u2 =  new Utgift("Rotter","50.0","Mat");
        Utgift u3 = new Utgift("Penn","20.0","Skole");
        Utgift u4 =   new Utgift("Medisin","100.0","Helse");
        dataAccess.addUtgift(Arrays.asList(u1,u2,u3,u4));

        controller.setDataAccess(dataAccess);
        controller.init2();
        controller.labelsSetUp();
        final Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Stopper serveren etter testingen
     */
    @After
    public void stopServer() {
        currentServer.shutdownNow();
    }

    /**
     * sjekker om det eksisterer en kontroller
     */
    @Test
    public void aTestController() {
        assertNotNull(this.controller);
    }


    @Test
    public void cTestLabelsSetup() {
        final Label mat = lookup("#labelMat").query();
        final Label helse = lookup("#labelHelse").query();
        final Label skole = lookup("#labelSkole").query();
        final Label total = lookup("#labelTotal").query();
        assertEquals("250.0",mat.getText());
        assertEquals("20.0",skole.getText());
        assertEquals("100.0",helse.getText());
        assertEquals("370.0",total.getText());

    }

    /**
     * SJekker at piechart har riktig data. Det betyr at etter at den har blitt initializert
     * Den skal ikke ha flere data med Mat, hver kategori tilsvarer et data field og med summen av alle
     * utgiftene sin pris som value. og navnet på kategori som name.
     */
    @Test
    public void bTestPieChart() {
        final PieChart pieChart = lookup("#pieChart").query();
        ObservableList<PieChart.Data> temp = pieChart.getData();
        ObservableList<PieChart.Data> temp2 = controller.getDataAccess().getPieChart();
        for (int i = 0;i < pieChart.getData().size();i++) {
            assertEquals(temp.get(i).getPieValue(),temp2.get(i).getPieValue(),0.05);
            assertEquals(temp.get(i).getName(),temp2.get(i).getName());
        }
    }

    @Test
    public void dTestListView() {
        final ListView listView = lookup("#listViewUtgift").query();
        ObservableList<Utgift> temp  = listView.getItems();
        ObservableList<Utgift> temp2 = controller.getDataAccess().getUtgifter();
        for (int i = 0;i < temp.size();i++) {
            assertEquals(temp.get(i).getPris(),temp2.get(i).getPris(),0.05);
            assertEquals(temp.get(i).getKategori(),temp2.get(i).getKategori());
            assertEquals(temp.get(i).getNavn(),temp2.get(i).getNavn());
        }
    }

}