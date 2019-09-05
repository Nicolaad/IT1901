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
    public UtgiftList utgiftList = new UtgiftList();
    @FXML
    TextField utgifter;
    @FXML
    TextField textFieldNavn;
    @FXML
    TextField textFieldPris;
    @FXML
    TextField textFieldKategori;
    @FXML
    Button btnNyUtgift;
    @FXML
    ListView<String> listViewUtgift;

    public void clearText(){
        textFieldNavn.setText("");
        textFieldPris.setText("");
        textFieldKategori.setText("");
    }
    public String getUtgifterText(){
        return "hei";
    }

    @FXML
    public void leggTilUtgift(){
       utgiftList.addUtgift(new Utgift(textFieldNavn.getText(),textFieldPris.getText(),textFieldKategori.getText()));
        clearText();
        ObservableList<String> fiks = FXCollections.observableList(utgiftList.getUtgifter());
         listViewUtgift.setItems(fiks);
    }
}
