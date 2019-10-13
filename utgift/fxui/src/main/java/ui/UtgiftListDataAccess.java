package ui;

import core.Utgift;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;

import java.util.Collection;

/**
 * Data access methods used by the controller.
 * @author hal
 *
 */
public interface UtgiftListDataAccess {

    /**
     * Gets all the (internal) LatLong objects.
     * @return the (internal) LatLong objects
     */
    Collection<Utgift> getAllUtgifter();
    ObservableList<PieChart.Data> getPieChart();
    /**
     * Gets a specific LatLong object by index.
     * @param num the index of the LatLong object to get
     * @return the LatLong object at the specified index
     */
    Utgift getUtgift(int num);

    /**
     * Sets a the LatLong object at a specific index.
     * @param index the index of the LatLong object to set
     * @param utgift the new LatLong object
     */
    void setUtgift(int index, Utgift utgift);

    /**
     * Adds a LatLong object
     * @param utgift the LatLong object to add
     * @return the index where the LatLong object was added
     */
    void addUtgift(Utgift utgift);

    ObservableList<Utgift> getUtgifter();
}
