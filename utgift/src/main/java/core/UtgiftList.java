package core;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class UtgiftList {
    private List<Utgift> utgifter = new ArrayList<>();

        public void addUtgift(Utgift utgift){
            utgifter.add(utgift);
        }
        public void removeUtgift(Utgift utgift){utgifter.remove(utgift);}
        public List<String> getUtgifter(){
            return utgifter.stream().map(c -> c.toString()).collect(Collectors.toList());
        }

        public boolean getUtgift(Utgift utgift){
            return utgifter.contains(utgift);
        }

        public boolean getUtgift(Predicate<Utgift> pred){
            return utgifter.stream().filter(p -> pred.test(p)).collect(Collectors.toList()).size()>0;
        }

        public boolean getUtgiftNavn(String navn){
            return true;
        }

        public boolean getUtgiftKategori(String kategori){
            return true;
        }
}
