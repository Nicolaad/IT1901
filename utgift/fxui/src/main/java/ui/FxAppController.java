package ui;

import core.Utgift;
import core.UtgiftList;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
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
    @FXML private TextField textFieldKategori;
    @FXML private Button btnNyUtgift;
    @FXML private ListView<Utgift> listViewUtgift = new ListView<>();
    @FXML private PieChart pieChart;
    @FXML private Label labelHelse,labelMat,labelSkole,labelTotal;

    private String utgifterText;
    private UtgiftList utgiftList = new UtgiftList();


    public void initialize(){
        listViewUtgift.setItems(utgiftList.getUtgifter());
        pieChart.setData(utgiftList.getPieChart());
    }

    @FXML
    public void save(){
        try {
            Save.save(utgiftList.getUtgifter(), new File("src/main/resources/json/save.json"));
        }
        catch(Exception e){

        }

    }

    /**
     * loads data from save.json to the listview, piechart and sets up the labels properly.
     */
    @FXML
   public void load(){
        // henter først de to observable arraylistene, den ene som er koblet til listview, den andre til piechart.
        ObservableList<Utgift> utgifterListView = utgiftList.getUtgifter();
        ObservableList<PieChart.Data> utgifterPieChart = utgiftList.getPieChart();
        //fjerner alt som ligger i dem fra før
        utgifterListView.clear();
        utgifterPieChart.clear();
        //henter fra fil, save.json via statisk retrieve metode til Load klassen.
        Collection<Utgift> ut = Load.retrieve(new File("src/main/resources/json/save.json"));
        //legger deretter til alle utgiftene. Listview er straightforward men for piechart bruker vi en metode i UtgiftList
        //set piechartdata som tar inn en collection av utgifter.
        utgifterListView.addAll(ut);
        utgifterPieChart.addAll(utgiftList.setPieChartData(ut));
        labelsSetUp();

    }


    /**
     * @leggTilUtgift() opens up FxAppLeggTilUtgift
     */
    @FXML
    public void leggTilUtgift(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FxAppLeggTilUtgift.fxml"));
            Parent confirmation;
            confirmation = fxmlLoader.load();
            FxLeggTilUtgiftController controller = (FxLeggTilUtgiftController) fxmlLoader.getController();

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("Legg til utgift");
            stage.setScene(new Scene(confirmation));
            stage.showAndWait();
            labelsSetUp();

        } catch (IOException e) {

        }

    }

    /**
     * traveres the piechart and sets up the kategori labels with the responding values.
     */
    private void labelsSetUp() {
        double mat = 0.0;
        double helse = 0.0;
        double skole = 0.0;
        for(PieChart.Data t: utgiftList.getPieChart()){

            System.out.println(t.getName());
            if(t.getName().equals("Mat")){
                mat+=t.getPieValue();
            }
            if(t.getName().equals("Skole")){
                skole+=t.getPieValue();
            }
            if(t.getName().equals("Helse")){
                helse+=t.getPieValue();
            }
        }
        labelMat.setText(""+mat);
        labelHelse.setText(""+helse);
        labelSkole.setText(""+skole);
        labelTotal.setText(mat+helse+skole+"");
    }

    public void add(Utgift utgift){
        utgiftList.add(utgift);
    }

}
