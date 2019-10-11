package ui;

import core.Utgift;
import core.UtgiftList;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import json.Load;
import json.Save;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

public class FxAppController {
    @FXML private ListView<Utgift> listViewUtgift = new ListView<>();
    @FXML private PieChart pieChart;
    @FXML private Label labelHelse;
    @FXML private Label labelMat;
    @FXML private Label labelSkole;
    @FXML private Label labelTotal;
    @FXML TextField inputField;

    private UtgiftList utgiftList = new UtgiftList();

    /**
     * Sets up the listview and piechart to initial data.
     */
    public void initialize() {
        listViewUtgift.setItems(utgiftList.getUtgifter());
        pieChart.setData(utgiftList.getPieChart());
    }

    /**
     * Saves the data to save.json using a static method in the calss Save
     */
    @FXML
    public void save() {
        Save.save(utgiftList.getUtgifter(), new File("src/main/resources/json/save.json"));
    }

    /**
     * loads data from save.json to the listview, piechart and sets up the labels properly.
     */
    @FXML
    public void load() {

        ObservableList<Utgift> utgifterListView = utgiftList.getUtgifter();
        ObservableList<PieChart.Data> utgifterPieChart = utgiftList.getPieChart();
        utgifterListView.clear();
        utgifterPieChart.clear();
        Collection<Utgift> ut = Load.retrieve(new File("src/main/resources/json/save.json"));
        utgifterListView.addAll(ut);
        utgifterPieChart.addAll(utgiftList.setPieChartData(ut));
        labelsSetUp();
    }


    /**
     * metoden aapner fxappleggtilutgift.fxml, altsaa faar vi en slags popup og kan ikke
     * endre paa de forrige elementene lenger.
     */
    @FXML
    public void leggTilUtgift() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FxAppLeggTilUtgift.fxml"));
            Parent confirmation;
            confirmation = fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("Legg til utgift");
            stage.setScene(new Scene(confirmation));
            stage.showAndWait();
            labelsSetUp();

        } catch (IOException e) {
            System.out.println(e);
        }

    }

    /**
     * traveres the piechart and sets up the kategori labels with the responding values.
     */
    private void labelsSetUp() {
        double mat = 0.0;
        double helse = 0.0;
        double skole = 0.0;
        for (PieChart.Data data: utgiftList.getPieChart()) {
            System.out.println(data.getName());
            if (data.getName().equals("Mat")) {
                mat += data.getPieValue();
            } else if (data.getName().equals("Skole")) {
                skole += data.getPieValue();
            } else if (data.getName().equals("Helse")) {
                helse += data.getPieValue();
            }
        }
        labelMat.setText("" + mat);
        labelHelse.setText("" + helse);
        labelSkole.setText("" + skole);
        labelTotal.setText(mat + helse + skole + "");
    }
}
