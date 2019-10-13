package ui;

import core.Utgift;
import core.UtgiftList;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertNotNull;


public class FxAppTest extends ApplicationTest {
    private FxAppController controller;


    @Override
    public void start(final Stage stage) throws Exception {
        URL f =getClass().getResource("FxApp.fxml");
        final FXMLLoader loader = new FXMLLoader(f);
        final Parent root = loader.load();
        this.controller = loader.getController();
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
        final PieChart pieChart = lookup("#pieChart").query();
        final Label mat = lookup("#labelMat").query();
        final Label helse = lookup("#labelHelse").query();
        final Label skole = lookup("#labelSkole").query();
        final Label total = lookup("#labelTotal").query();
        Utgift u1 = new Utgift("Fisk","200.0","Mat");
        Utgift u2 = new Utgift("Melk","20.0","Mat");
        Utgift u3 = new Utgift("Penn","10.0","Skole");
        Utgift u4 = new Utgift("Medisin","100.0","Helse");
        List<Utgift> temp = new ArrayList<>(Arrays.asList(u1,u2,u3,u4));
      // controller.labelsSetUp();
        Platform.setImplicitExit(false);
        Platform.runLater(() -> UtgiftList.add(u1));

        for(Utgift u: temp){

        }


        //Platform.runLater(() -> controller.labelsSetUp());


}

}