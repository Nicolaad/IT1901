package ui;

import core.Utgift;
import java.util.Collection;
import java.util.List;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;

public interface UtgiftListDataAccess {

    /**
     * returnerer alle utgifter som en collection avutgifter
     *
     * @return collection av type utgifter
     */
    Collection<Utgift> getAllUtgifter();

    /**
     * returnerer piechartet
     *
     * @return en observable list av type PieChart.Data
     */
    ObservableList<PieChart.Data> getPieChart();

    /**
     * henter utgift basert på bare indeks
     *
     * @param num indeksen
     * @return utgiften me indeks num
     */
    Utgift getUtgift(int num);

    /**
     * sletter en spesifik utgift basert på kategori og indeks
     *
     * @param index    indeksen til utgiften etter filtreringen
     * @param kategori kategorien utgiftene skal bli filtrert til
     */
    void deleteUtgift(int index, String kategori);

    /**
     * legger til utgifter fra en liste
     *
     * @param utgifter listen med utgifter som skal legges til
     */
    void addUtgift(List<Utgift> utgifter);

    /**
     * henter utgifter
     *
     * @return ObservableList av typen utgift
     */
    ObservableList<Utgift> getUtgifter();
}
