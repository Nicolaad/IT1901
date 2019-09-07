package ui;

import core.Utgift;
import core.UtgiftList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FxAppController {
    private String utgifterText;

    @FXML
    private TextField textFieldKategori;
    @FXML
    private Button btnNyUtgift;

    @FXML
    private ListView<String> listViewUtgift = new ListView<>();


    private UtgiftList utgiftList = new UtgiftList();


    public String getUtgifterText(){
        return "hei";
    }
    @FXML
    public void leggTilUtgift(){
        //åpne opp FxAppLeggTilUtgift.fxml.
        try {
            listViewUtgift.setItems(utgiftList.getUtgifter());
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FxAppLeggTilUtgift.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("Legg til utgift");
            stage.setScene(new Scene(root1));
            stage.showAndWait();

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
