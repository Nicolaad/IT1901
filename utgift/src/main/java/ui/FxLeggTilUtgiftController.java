package ui;

import core.UtgiftList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

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
    public UtgiftList utgiftList = new UtgiftList();

    @FXML
    public void leggTilUtgift(){
     /*   utgiftList.addUtgift(new Utgift(textFieldNavn.getText(),textFieldPris.getText(),textFieldKategori.getText()));
        clearText();
        ObservableList<String> fiks = FXCollections.observableList(utgiftList.getUtgifter());
        listViewUtgift.setItems(fiks);
        */
      //logikken her må endres.
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
    }
    @FXML
    public void tilbake(){
        //gå tilbake til FxApp uten å legge til nytt utgifts element,
        // feedback til user om at ingenting ble gjort.
    }
}
