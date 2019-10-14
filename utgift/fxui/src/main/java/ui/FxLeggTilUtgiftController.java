package ui;

import core.Utgift;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FxLeggTilUtgiftController {
    @FXML TextField textFieldNavn;
    @FXML TextField textFieldPris;
    @FXML Button btnBack;
    @FXML ComboBox<String> comboBoxKategori;
    @FXML Button btnFerdigUtgift;

    private ObservableList<String> kategorier = FXCollections.observableArrayList();
    private List<String> presetKategori = new ArrayList<>(Arrays.asList("Mat","Skole","Helse"));
    private FxLeggTilUtgiftController controller;
    private FxAppController controller2;
    private UtgiftListDataAccess dataAccess;

    public void setDataAccess(UtgiftListDataAccess dataAccess) {
        this.dataAccess = dataAccess;
    }

    /**
     * Fyller opp combobox med presetta kategorier og setter verdien til aa starte paa Mat.
     */
    public void initialize() {
        kategorier.addAll(presetKategori);
        comboBoxKategori.setItems(kategorier);
        comboBoxKategori.setValue("Mat");
    }

    /**
     * denne metoden validerer forst input. kun tall i pris.  Deretter henter den navn fra textFieldNavn, pris fra
     * textFieldPris og kategori fra comboBoxKategori i javafx. Denne bruker statisk metode i Utgiflist til aa legge dem til saa de vises
     * paa den forrige fxml siden.
     */
    public void leggTilUtgift() {
        if (textFieldPris.getText().matches("[0-9]+") && textFieldPris.getText().length() > 0) {
            dataAccess.addUtgift(new Utgift(textFieldNavn.getText(), textFieldPris.getText(), comboBoxKategori.getValue()));
            clearText();
        } else {
            textFieldPris.setText("");
        }
    }

    /**
     * Rydder opp slik at klassen er klar til aa brukes igjen. Fjerner det som er skrevet inn i navn og pris.
     */
    public void clearText() {
        textFieldNavn.setText("");
        textFieldPris.setText("");
    }

    /**
     * gaa tilbake til fxapp og legg til et nytt utgift element til listview og piechart.
     * //TODO feedback til user om at nytt utgifts element ble lagt til.
     * kalles fra btnBack.
     */
    @FXML
    public void ferdigUtgift() {
        Stage stage = (Stage) btnBack.getScene().getWindow();
        leggTilUtgift();

        stage.close();
    }

    /**
     * Gaa tilbake til FxApp uten å legge til nytt utgifts element,
     * TODO feedback til user om at ingenting ble gjort.
     */
    @FXML
    public void tilbake() {
        Stage stage = (Stage) btnBack.getScene().getWindow();
        clearText();
        stage.close();

    }
}
