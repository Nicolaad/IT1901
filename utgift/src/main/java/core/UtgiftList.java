package core;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class UtgiftList {
    private static ObservableList<String> utgifter = FXCollections.observableArrayList();

        public void addUtgift(Utgift utgift){
            utgifter.add(utgift.toString());
        }
        public static void clear(){
            utgifter.clear();
        }
        public void removeUtgift(Utgift utgift){utgifter.remove(utgift);}

        public ObservableList<String> getUtgifter(){
           // return utgifter.stream().map(c -> c.toString()).collect(Collectors.toList());
            return utgifter;
        }
        public static void add(Utgift utgift){
            utgifter.add(utgift.toString());
        }
        public boolean getUtgift(Utgift utgift){
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
}
