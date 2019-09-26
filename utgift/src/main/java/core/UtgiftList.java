package core;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class UtgiftList {

    private static ObservableList<Utgift> utgifter = FXCollections.observableArrayList();
    private static ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

        public void addUtgift(Utgift utgift){
            utgifter.add(utgift);
        }

        public static void clear(){
            utgifter.clear();
        }

        public void removeUtgift(Utgift utgift){utgifter.remove(utgift);}

        public ObservableList<Utgift> getUtgifter(){
            return utgifter;
        }
        public ObservableList<PieChart.Data> getNoob() {
            return pieChartData;
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
