package ui;

import core.Utgift;
import core.UtgiftList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FxAppController {
    private String utgifterText;

    @FXML
    TextField textFieldKategori;
    @FXML
    Button btnNyUtgift;
    @FXML
    ListView<String> listViewUtgift;


    public String getUtgifterText(){
        return "hei";
    }
    @FXML
    public void leggTilUtgift(){
        //åpne opp FxAppLeggTilUtgift.fxml.
    }

}
