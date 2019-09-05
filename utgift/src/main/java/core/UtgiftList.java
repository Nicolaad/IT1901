package core;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UtgiftList {
    private List<Utgift> utgifter = new ArrayList<>();

        public void addUtgift(Utgift utgift){
            utgifter.add(utgift);
        }
        public List<String> getUtgifter(){
            return utgifter.stream().map(c -> c.toString()).collect(Collectors.toList());
        }
}
