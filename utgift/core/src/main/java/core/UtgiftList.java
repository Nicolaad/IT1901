package core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;

/**
 * UtgiftList inneholder to statiske observableList. Den ene inneholder Utgift, en liste av utgifter altsaa.
 * Den andre er en liste av PieChart.Data. Hver Piechart.Data har name =kategori og
 * pris = summen av alle utgiftene med samme kategori.
 * utgifter og pieChartData knyttes hendholdsvis til listview og piechart i javafx.
 */
public class UtgiftList {

    private ObservableList<Utgift> utgifter = FXCollections.observableArrayList();
    private ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

    /**
     * Tom konstruktoor
     */
    public UtgiftList() {

    }

    /**
     * konstruktoren fjerner foorst det som finnes it utgifter for aa ikke legge til dobbelt.
     * deretter legger den til alle utgiftene gjennom private metoden addUtgift()
     * @param ul tar inn en array i form av utgift1, utgift2 ...
     */
    public UtgiftList(Utgift... ul) {
        utgifter.clear();
        for (Utgift u : ul) {
            addUtgift(u);
        }

    }

    /**
     * konstruktoren fjerner foorst det som finnes it utgifter for aa ikke legge til dobbelt.
     * deretter legger den til alle utgiftene gjennom private metoden addUtgifter()
     * @param utgifter tar inn en collection av utgifter.
     */
    public UtgiftList(Collection<Utgift> utgifter) {
        //this.utgifter.addAll(utgifter);
        clear();
        addUtgifter(utgifter);
    }

    /**
     * returnere en ugift basert på index
     * @param num indexen til utgiften
     * @return utgiften med indexen num
     */
    public Utgift getUtgift(int num) {
        return utgifter.get(num);
    }

    /**
     * toommer utgifter og pieChartData.
     */
    public void clear() {
        utgifter.clear();
        pieChartData.clear();
    }

    /*
    public void removeKategori(String kategori){
        for(Utgift u: utgifter){
            if(u.getKategori().equals(kategori)){

            }
        }
    }

    */

    /**
     * static metode for aa legge til utgift objekt forst til listen av utgifter
     * deretter til piechart.
     * PieChart inneholder kun kategori og pris par. Derfor vil den sjekke om
     * kategori finnes fra foor. Hvis den gjoor det vil prisen bli lagt til fra ny
     * utgift objekt til eksisterende pris. ellers blir kategorien lagt til og prisen blir satt.
     * @param utgift tar inn utgift element som skal legges til
     */
    public void add(Utgift utgift) {
        utgifter.add(utgift);
        List<String> yes = new ArrayList<>();
        //lager forst en liste av strenger av all data som ligger i piechart allerede
        for (PieChart.Data op: pieChartData) {
            if (!yes.contains(op.getName())) {
                yes.add(op.getName());
            }
        }
        //hvis kategorien til utgift er ny så legges den til i piechart
        if (!yes.contains(utgift.getKategori())) {
            pieChartData.add(new PieChart.Data(utgift.getKategori(),utgift.getPris()));
        } else {  //hvis ikke travereserer vi dataen og gjor dataen storre med samme kategori.
            for (PieChart.Data t: pieChartData) {
                if (t.getName().equals(utgift.getKategori())) {
                    double tk = t.getPieValue() + utgift.getPris();
                    t.setPieValue(tk);
                }
            }
        }
    }

    /**
     * bruker iterator og gaar gjennom utgifter legger til via statisk metode UtgiftList.add(utgift)
     * soorger for at begge listene fylles opp.
     * @param utgifter collection av utgifter. brukes av konstruktoren.
     */
    public int addUtgifter(Collection<Utgift> utgifter) {
        final int pos = this.utgifter.size();
        for (Utgift u :utgifter) {
            this.add(u);
        }
        return pos;
    }

    /**
     * brukes for aa teste og for serializer / deserializer.
     * @param utgift tar inn et utgift objekt.
     * @return
     */
    public int addUtgift(Utgift utgift) {
        this.add(utgift);
        return 0;
    }

    /**
     *
     * @return en peker til utgifter.
     */
    public List<Utgift> toList() {
        return utgifter;
    }

    /**
     * oppdatere pieChartData ved bruke av updatePieChart brukes ogsaa til aa initialisere pieChart i javafx.
     * @param ut tar inn collection av utgifter.
     * @return en liste med PieChart.Data fra den oppdaterte listen.
     */
    public List<PieChart.Data> setPieChartData(Collection<Utgift> ut) {
        return updatePieChart(ut);
    }

    /**
     * brukes for aa teste.
     * @param utgift utgift objekt som skal fjernes fra listen
     */
    public void removeUtgift(Utgift utgift) {
        utgifter.remove(utgift);
    }


    /**
     * @return liste av utgifter
     */
    public ObservableList<Utgift> getUtgifter() {
        return utgifter;
    }

    /**
     *
     * @param kategori spesifiserer hvilken utgifter basert på kategori attributen
     * @return utgifter med kategorien kategori
     */
    public List<Utgift> getKategori(String kategori) {
        return utgifter.stream().filter(u -> u.getKategori().equals(kategori)).collect(Collectors.toList());
    }

    /**
     * @return liste av pieChart.Data
     */
    public ObservableList<PieChart.Data> getPieChart() {
        return pieChartData;
    }

    /**
     * metoden brukes av setPieChartData og brukes til aa teste klassen.
     * @param ut tar inn collection av utgift objekter
     * @return en liste med piechart.data som inneholder kategori og pris.
     */
    private List<PieChart.Data> updatePieChart(Collection<Utgift> ut) {
        Map<String,Double> pieChartData = new HashMap<>();
        List<PieChart.Data> pie = new ArrayList<>();
        for (Utgift utgift: ut) {
            if (!pieChartData.containsKey(utgift.getKategori())) {
                pieChartData.put(utgift.getKategori(), utgift.getPris());
            } else {
                Double d = pieChartData.get(utgift.getKategori());
                pieChartData.replace(utgift.getKategori(), d + utgift.getPris());
            }
        }
        for (Map.Entry<String,Double> k: pieChartData.entrySet()) {
            pie.add(new PieChart.Data(k.getKey(), k.getValue()));
        }
        return pie;
    }
}
