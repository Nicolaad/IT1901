package core;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;

import java.util.*;

public class UtgiftList {

    private static ObservableList<Utgift> utgifter = FXCollections.observableArrayList();
    private static ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

        public void addUtgift(Utgift utgift){
            utgifter.add(utgift);
        }
        public List<Utgift> toList(){
            return utgifter;
        }
        public static void clear(){
            utgifter.clear();
            pieChartData.clear();
        }
        public List<PieChart.Data> setPieChartData(Collection<Utgift> ut){
            return updatePieChart(ut);
        }
        public void removeUtgift(Utgift utgift){utgifter.remove(utgift);}

        public ObservableList<Utgift> getUtgifter(){
            return utgifter;
        }
        public ObservableList<PieChart.Data> getPieChart() {
            return pieChartData;
        }

        private List<PieChart.Data> updatePieChart(Collection<Utgift> ut){
            Map<String,Double> pieChartData = new HashMap<>();
            List<PieChart.Data> pie = new ArrayList<>();
            for(Utgift utgift: ut){
                if(!pieChartData.containsKey(utgift.getKategori())){
                    pieChartData.put(utgift.getKategori(), utgift.getPris());
                }
                else{
                    Double d = pieChartData.get(utgift.getKategori());
                    pieChartData.replace(utgift.getKategori(), d+utgift.getPris());
                }
            }
            for(String k: pieChartData.keySet()){
                pie.add(new PieChart.Data(k, pieChartData.get(k)));
            }
            return pie;
        }

        public static void add(Utgift utgift){
            utgifter.add(utgift);
            List<String> yes = new ArrayList<>();
            //lager forst en liste av strenger av all data som ligger i piechart allerede
            for(PieChart.Data op: pieChartData){
                if(!yes.contains(op.getName())){
                    yes.add(op.getName());
                }
            }
            //hvis kategorien til utgift er ny så legges den til i piechart
            if(!yes.contains(utgift.getKategori())){
                pieChartData.add(new PieChart.Data(utgift.getKategori(),utgift.getPris()));

            }
            //hvis ikke travereserer vi dataen og gjor dataen storre med samme kategori.
            else{
                for(PieChart.Data t: pieChartData){
                    if (t.getName().equals(utgift.getKategori())) {
                        double tk = t.getPieValue() + utgift.getPris();
                        t.setPieValue(tk);
                    }
                }
            }
        }
}
