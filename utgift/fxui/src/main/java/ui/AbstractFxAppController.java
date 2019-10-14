package ui;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import json.UtgiftListModule;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

public abstract class AbstractFxAppController {
    @FXML private ListView<Utgift> listViewUtgift;
    @FXML private PieChart pieChart;
    @FXML private Label labelHelse;
    @FXML private Label labelMat;
    @FXML private Label labelSkole;
    @FXML private Label labelTotal;
    @FXML TextField inputField;

    private UtgiftList utgiftList;
    private UtgiftListDataAccess dataAccess;
    private FxAppController controller;

    protected UtgiftListDataAccess getDataAccess() {
        return dataAccess;
    }

    public void setUtgiftList(UtgiftList utgiftList){
        setDataAccess(new LocalUtgiftListDataAccess(utgiftList));
      //  init2();
    }
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
     * @return the ObjectMapper used by this controller
     */
    public ObjectMapper getObjectMapper() {

            objectMapper = new ObjectMapper();
            objectMapper.registerModule(new UtgiftListModule());

        return objectMapper;
    }


    public void init2(){
        listViewUtgift.setItems(dataAccess.getUtgifter());
        pieChart.setData(dataAccess.getPieChart());
    }

    public UtgiftList getUtgiftList(){
        return utgiftList;
    }
    /**
     * Saves the data to save.json using a static method in the class Save
     */
    @FXML
    public void save() {
        Save.save(utgiftList.getUtgifter(), new File("src/main/resources/json/save.json"));
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
        for (Utgift data: dataAccess.getAllUtgifter()) {
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

}