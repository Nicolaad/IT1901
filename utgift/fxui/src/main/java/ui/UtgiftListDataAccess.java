package ui;

import core.Utgift;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;

import java.util.Collection;

public interface UtgiftListDataAccess {


    Collection<Utgift> getAllUtgifter();
    ObservableList<PieChart.Data> getPieChart();

    Utgift getUtgift(int num);


    void setUtgift(int index, Utgift utgift);


    void addUtgift(Utgift utgift);

    ObservableList<Utgift> getUtgifter();
}
