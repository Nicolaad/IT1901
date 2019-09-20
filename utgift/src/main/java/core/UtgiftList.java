package core;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;

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
           // return utgifter.stream().map(c -> c.toString()).collect(Collectors.toList());
            return utgifter;
        }
        public ObservableList<PieChart.Data> getNoob() {
            return pieChartData;
        }
        public static void add(Utgift utgift){
            utgifter.add(utgift);
            pieChartData.add(new PieChart.Data(utgift.getKategori(),200));
        }
       /* public boolean getUtgift(Utgift utgift){
            return utgifter.contains(utgift.toString());
        }

        public boolean getUtgift(Predicate<Utgift> pred){
         //   return utgifter.stream().filter(p -> pred.test(p)).collect(Collectors.toList()).size()>0;
            return true;
        }



        public boolean getUtgiftNavn(String navn){
            return true;
        }

        public boolean getUtgiftKategori(String kategori){
            return true;
        }

        */
}
