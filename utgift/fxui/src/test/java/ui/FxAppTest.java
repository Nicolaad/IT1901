package ui;

import core.Utgift;
import core.UtgiftList;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import java.net.URL;
import java.util.Arrays;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;


public class FxAppTest extends ApplicationTest {
    private FxAppController controller;
    private Thread thread;

    @Override
    public void start(final Stage stage) throws Exception {
        URL f =getClass().getResource("FxApp.fxml");
        final FXMLLoader loader = new FXMLLoader(f);
        final Parent root = loader.load();
        this.controller = loader.getController();
        controller.setUtgiftList(
                new UtgiftList(
                        Arrays.asList(
                                new Utgift("Fisk","200.0","Mat"),
                                new Utgift("Rotter","50.0","Mat"),
                                new Utgift("Penn","20.0","Skole"),
                                new Utgift("Medisin","100.0","Helse"))));
        controller.labelsSetUp();
        final Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }



    @Test
    public void testController(){
        assertNotNull(this.controller);
    }

    /* @Test
    public void testUtgiftListView(){
        final ListView<Utgift> utgiftListView = lookup("#listViewUtgift").query();
        ObservableList<Utgift> list2 = FXCollections.observableArrayList();
        Utgift u1 = new Utgift("Fisk","200.0","Mat");
        Utgift u2 = new Utgift("Melk","20.0","Mat");
        list2.addAll(Arrays.asList(u1,u2));
        ObservableList<Utgift> temp = utgiftListView.getItems();
        temp.add(u1);
        temp.add(u2);
        assertEquals(list2.get(0),temp.get(0));
        assertEquals(list2.get(0),temp.get(0));
}*/

    @Test
    public void testLabelsSetup(){
        final Label mat = lookup("#labelMat").query();
        final Label helse = lookup("#labelHelse").query();
        final Label skole = lookup("#labelSkole").query();
        final Label total = lookup("#labelTotal").query();
        assertEquals("250.0",mat.getText());
        assertEquals("20.0",skole.getText());
        assertEquals("100.0",helse.getText());
        assertEquals("370.0",total.getText());
     /*   for(Utgift u: temp){
            controller.getDataAccess().addUtgift(u);
        }*/
        //controller.labelsSetUp();
      //  Platform.runLater(() -> controller.labelsSetUp());
}

    @Test
    public void testPieChart(){
        final PieChart pieChart = lookup("#pieChart").query();
        ObservableList<PieChart.Data> temp = pieChart.getData();
        ObservableList<PieChart.Data> temp2 = controller.getDataAccess().getPieChart();
        System.out.println("fitte");

        for (int i = 0;i<pieChart.getData().size();i++){
            System.out.println(temp.get(i));
            System.out.println(temp2.get(i));
            assertEquals(temp.get(i),temp2.get(i));
        }
    }

}