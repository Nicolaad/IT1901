package ui;

import core.Utgift;
import core.UtgiftList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Side;
import javafx.scene.Node;
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
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class FxAppController {
    private String utgifterText;

    @FXML
    private TextField textFieldKategori;
    @FXML
    private Button btnNyUtgift;

    @FXML
    private ListView<Utgift> listViewUtgift = new ListView<>();

    @FXML
    private PieChart pieChart;

    @FXML
    private Label labelHelse;private Label labelMat;private Label labelSkole;

    private UtgiftList utgiftList = new UtgiftList();

    private int fisk = 200;

    public String getUtgifterText(){
        return "hei";
    }

    public void initialize(){
        listViewUtgift.setItems(utgiftList.getUtgifter());
        pieChart.setData(utgiftList.getNoob());

    }


    @FXML
    public void save(){
        try {
            Save.save(utgiftList.getUtgifter(), new File("src/main/resources/json/save.json"));
        }
        catch(Exception e){

        }

    }
    @FXML
   public void load(){
        ObservableList<Utgift> temp = utgiftList.getUtgifter();
        temp.clear();
        temp.addAll(Load.retrieve(new File("src/main/resources/json/save.json")));
    }


    @FXML
    public void leggTilUtgift(){
        //åpne opp FxAppLeggTilUtgift.fxml.
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FxAppLeggTilUtgift.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("Legg til utgift");
            stage.setScene(new Scene(root1));
            stage.showAndWait();
            double mat = 0.0;
            double helse = 0.0;
            double skole = 0.0;
            for(PieChart.Data t: utgiftList.getNoob()){
                if(t.getName().equals("Mat")){
                    mat+=t.getPieValue();
                }
            }

        } catch (IOException e) {

        }

    }
    public String toString(){
        return "hei";
    }
    public void add(Utgift utgift){
        utgiftList.add(utgift);
    }

}
