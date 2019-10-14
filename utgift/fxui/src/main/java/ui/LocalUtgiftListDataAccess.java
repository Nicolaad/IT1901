package ui;

import core.Utgift;
import core.UtgiftList;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;

import java.util.Collection;


public class LocalUtgiftListDataAccess implements UtgiftListDataAccess {
    private UtgiftList utgiftList;
    public LocalUtgiftListDataAccess(UtgiftList utgiftList){
        this.utgiftList = utgiftList;
    }

    public LocalUtgiftListDataAccess(){
        this(new UtgiftList());
    }

    public UtgiftList getUtgiftList(){
        return utgiftList;
    }


    public Collection<Utgift> getAllUtgifter(){
        return getUtgiftList().toList();
    }
    public ObservableList<PieChart.Data> getPieChart(){
      return getUtgiftList().getPieChart();
    }


    public Utgift getUtgift(int num){
        return getUtgiftList().getUtgift(num);
    }

    @Override
    public void deleteUtgift(int index, String kategori) {

    }


    public void setUtgift(int index, Utgift utgift){

    }


    public void addUtgift(Utgift utgift){
        getUtgiftList().addUtgift(utgift);
    }

    @Override
    public ObservableList<Utgift> getUtgifter() {
        return getUtgiftList().getUtgifter();
    }


}
