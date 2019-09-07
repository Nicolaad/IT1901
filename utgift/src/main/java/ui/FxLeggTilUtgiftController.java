package ui;

import core.Utgift;
import core.UtgiftList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class FxLeggTilUtgiftController {
    @FXML
    TextField utgifter;
    @FXML
    TextField textFieldNavn;
    @FXML
    TextField textFieldPris;
    @FXML
    TextField textFieldKategori;
    @FXML
    Button btnBack;
    @FXML
    Button btnFerdig;



    @FXML
    public void leggTilUtgift(){
        UtgiftList.add(new Utgift(textFieldNavn.getText(),textFieldPris.getText(),textFieldKategori.getText()));
        clearText();
    }
    public void clearText(){
        textFieldNavn.setText("");
        textFieldPris.setText("");
        textFieldKategori.setText("");
    }
    @FXML
    public void ferdigUtgift(){
        //gå tilbake til FxApp og legg til nytt utgift element til listview.
        //feedback til user om at nytt utgifts element ble lagt til.
        Stage stage = (Stage) btnBack.getScene().getWindow();
        leggTilUtgift();
        stage.close();
    }
    @FXML
    public void tilbake(){
        //gå tilbake til FxApp uten å legge til nytt utgifts element,
        // feedback til user om at ingenting ble gjort.
        Stage stage = (Stage) btnBack.getScene().getWindow();

        stage.close();

    }
}