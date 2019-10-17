package ui;

import core.Utgift;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;

import java.util.Collection;
import java.util.List;

public interface UtgiftListDataAccess {


    Collection<Utgift> getAllUtgifter();
    ObservableList<PieChart.Data> getPieChart();

    Utgift getUtgift(int num);

    void deleteUtgift(int index, String kategori);

    void addUtgift(List<Utgift> utgifter);

    ObservableList<Utgift> getUtgifter();
}
