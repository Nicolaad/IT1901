package ui;

import com.fasterxml.jackson.databind.ObjectMapper;
import core.Utgift;
import java.io.File;
import java.io.IOException;
import java.util.List;
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
import json.UtgiftListModule;


public  class FxAppUsingRestController {
    @FXML private ListView<Utgift> listViewUtgift;
    @FXML private PieChart pieChart;
    @FXML private Label labelHelse;
    @FXML private Label labelMat;
    @FXML private Label labelSkole;
    @FXML private Label labelTotal;
    @FXML private Label indexLabel;
    @FXML TextField inputField;

    /* disabled as it is not in use - spotbug warning
    private UtgiftList utgiftList;
    */
    private UtgiftListDataAccess dataAccess;

    /* disabled and moved inside the delete function, as it is not used elsewhere - spotbug
        (might be activated for later features)
    private javafx.scene.input.MouseEvent mouseEvent;
    */
        public FxAppUsingRestController() {
        }

    public UtgiftListDataAccess getDataAccess() {
        return dataAccess;
    }

    /*public void setUtgiftList(UtgiftList utgiftList){
      setDataAccess(new LocalUtgiftListDataAccess(utgiftList));
      //  init2();
    }*/
    protected void setDataAccess(final UtgiftListDataAccess dataAccess) {
        this.dataAccess = dataAccess;
    }

    /*public AbstractFxAppController(){
        setDataAccess(new LocalUtgiftListDataAccess(new UtgiftList()));
    }
    */

    /**
     * Sets up the listview and piechart to initial data.
     */
    /*
    public void initialize() {
        listViewUtgift.setItems(dataAccess.getUtgifter());
        pieChart.setData(dataAccess.getPieChart());
    }

     */
    private ObjectMapper objectMapper;

    /**
     * Gets the ObjectMapper used by this controller.
     *
     * @return the ObjectMapper used by this controller
     */
    public ObjectMapper getObjectMapper() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new UtgiftListModule());
        return objectMapper;
    }

    /**
     * oppdaterer listviewutgift og piechart
     */
    public void init2() {
        listViewUtgift.setItems(dataAccess.getUtgifter());
        pieChart.setData(dataAccess.getPieChart());
    }

    /* neer used, commented out due to spotbug warning
    public UtgiftList getUtgiftList(){
        return utgiftList;
    }
*/

    /**
     * Saves the data to save.json using a static method in the class Save
     */


    public void save() {
        Save.save(dataAccess.getAllUtgifter(), new File("../core/src/main/resources/json/save.json"));
    }

    /**
     * metoden aapner fxappleggtilutgift.fxml, altsaa faar vi en slags popup og kan ikke
     * endre paa de forrige elementene lenger.
     */
    @FXML
    public void leggTilUtgift() {
        try {
            /*this line causes spotbug warning:
            UI_INHERITANCE_UNSAFE_GETRESOURCE: Usage of GetResource may be unsafe if class is extended
            Calling this.getClass().getResource(...) could give results other than expected
             if this class is extended by a class in another package.
            */
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FxAppLeggTilUtgift.fxml"));

            Parent confirmation;
            confirmation = fxmlLoader.load();
            FxLeggTilUtgiftController uc = fxmlLoader.getController();
            uc.setDataAccess(this.getDataAccess());
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("Legg til utgift");
            stage.setScene(new Scene(confirmation));
            stage.showAndWait();
            labelsSetUp();
            init2();

        } catch (IOException e) {
            System.out.println(e);
        }

    }

    /**
     * traveres the piechart and sets up the kategori labels with the responding values.
     */
    public void labelsSetUp() {
        //getUtgiftList().clear();
        double mat = 0.0;
        double helse = 0.0;
        double skole = 0.0;
        for (Utgift data : dataAccess.getAllUtgifter()) {
            System.out.println(data.getKategori());
            if (data.getKategori().equals("Mat")) {
                mat += data.getPris();
            } else if (data.getKategori().equals("Skole")) {
                skole += data.getPris();
            } else if (data.getKategori().equals("Helse")) {
                helse += data.getPris();
            }
        }
        labelMat.setText("" + mat);
        labelHelse.setText("" + helse);
        labelSkole.setText("" + skole);
        labelTotal.setText(mat + helse + skole + "");
    }

    /**
     * loads data from save.json to the listview, piechart and sets up the labels properly.
     */
    public void load() {
        List<Utgift> ut = (List<Utgift>) Load.retrieve(new File("../core/src/main/resources/json/save.json"));
        dataAccess.addUtgift(ut);
        init2();
        labelsSetUp();
    }

    /**
     * sletter utgiften som er klikket på
     */
    @FXML
    public void deleteUtgift() {
        try {
            javafx.scene.input.MouseEvent mouseEvent = null;
            dataAccess.deleteUtgift(getSelectedUtgift(mouseEvent), "Mat");
            save();
            init2();
            labelsSetUp();
        } catch (Exception e) {
            System.out.println("Kan ikke slette");
        }
    }

    /**
     * returnerer indexen til utgiften som er clicket på
     *
     * @param mouseEvent museventet som brukes til å hente utgiften
     * @return
     */
    @FXML
    public int getSelectedUtgift(javafx.scene.input.MouseEvent mouseEvent) {
        if (listViewUtgift.getSelectionModel().getSelectedItem() == null) {
            return -1;
        }
        Utgift selected = listViewUtgift.getSelectionModel().getSelectedItem();
        indexLabel.setText(selected.toString() + " index: " + listViewUtgift.getItems().indexOf(selected));
        return listViewUtgift.getItems().indexOf(selected);
    }

}
