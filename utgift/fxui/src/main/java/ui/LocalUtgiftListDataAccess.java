package ui;

import core.Utgift;
import core.UtgiftList;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;

import java.util.Collection;

/**
 * Data access methods used by the controller.
 * @author hal
 *
 */
public class LocalUtgiftListDataAccess implements UtgiftListDataAccess {
    private UtgiftList utgiftList;
    public LocalUtgiftListDataAccess(UtgiftList utgiftList){
        this.utgiftList = utgiftList;
    }

    public LocalUtgiftListDataAccess(){
        this(new UtgiftList());
    }

    public UtgiftList getUtgiftList(){
        return utgiftList;
    }

    /**
     * Gets all the (internal) LatLong objects.
     * @return the (internal) LatLong objects
     */
    public Collection<Utgift> getAllUtgifter(){
        return getUtgiftList().toList();
    }
    public ObservableList<PieChart.Data> getPieChart(){
      return getUtgiftList().getPieChart();
    }

    /**
     * Gets a specific LatLong object by index.
     * @param num the index of the LatLong object to get
     * @return the LatLong object at the specified index
     */
    public Utgift getUtgift(int num){
        return getUtgiftList().getUtgift(num);
    }


    /**
     * Sets a the LatLong object at a specific index.
     * @param index the index of the LatLong object to set
     * @param utgift the new LatLong object
     */
    public void setUtgift(int index, Utgift utgift){

    }

    /**
     * Adds a LatLong object
     * @param utgift the LatLong object to add
     * @return the index where the LatLong object was added
     */
    public void addUtgift(Utgift utgift){
        getUtgiftList().addUtgift(utgift);
    }

    @Override
    public ObservableList<Utgift> getUtgifter() {
        return getUtgiftList().getUtgifter();
    }


}
