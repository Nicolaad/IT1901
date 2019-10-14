package core;

import javafx.scene.chart.PieChart;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class UtgiftListTest {
    private UtgiftList utgiftList;
    private List<Utgift> utgifter = new ArrayList<>();
    private Utgift utgift1 = new Utgift("Fisk","200","Mat");
    private Utgift utgift2 = new Utgift("Nugatti","20","Mat");
    private Utgift utgift3 = new Utgift("Penn","10","Skole");
    private Utgift utgift4 = new Utgift("Medisin","50","Helse");

    @Before
    public void setUp(){
        utgifter.addAll(Arrays.asList(utgift1,utgift2,utgift3,utgift4));
        utgiftList = new UtgiftList(utgifter);
    }

    @Test
    public void utgiftListConstructorTest(){
        UtgiftList ul = new UtgiftList();
        for(int i = 0;i<utgifter.size();i++){
            assertEquals(utgifter.get(i),ul.getUtgifter().get(i));
        }

    }
    @Test
    public void testSetPieChartData(){
        UtgiftList ul = new UtgiftList();
        ul.clear();
        ul.addUtgift(utgift1);
        List<PieChart.Data> pie = new ArrayList<>();
        pie.add(new PieChart.Data(utgift1.getKategori(), utgift1.fetchPrisDoubleVersion()));
        List<PieChart.Data> op = ul.setPieChartData(Arrays.asList(utgift1));
        try {
            for (int i = 0; i < pie.size(); i++) {
                assertEquals(pie.get(i).getName(), op.get(i).getName());
                assertEquals(pie.get(i).getPieValue(), op.get(i).getPieValue(), 0.01);
            }
        }
        catch(Exception e){
            fail("The piechart did not work properly");
        }
        pie.add(new PieChart.Data(utgift2.getKategori(),utgift2.fetchPrisDoubleVersion()));
        ul.setPieChartData(Arrays.asList(utgift2));
    }

    @Test
    public void testSetPieChartDataSameKategori(){
        UtgiftList ul = new UtgiftList();
        ul.clear();
        UtgiftList.add(utgift1);
        UtgiftList.add(utgift2);
        List<PieChart.Data> pie = new ArrayList<>();
        pie.add(new PieChart.Data(utgift1.getKategori(), utgift1.fetchPrisDoubleVersion()+utgift2.fetchPrisDoubleVersion()));
        List<PieChart.Data> op = ul.getPieChart();
       // assertEquals(op.size(), pie.size());
        try {
                assertEquals(pie.get(0).getName(), op.get(0).getName());
                assertEquals(pie.get(0).getPieValue(), op.get(0).getPieValue(), 0.01);
        }
        catch(Exception e){
            fail("The piechart did not work properly");
        }

    }


    @Test
    public void utgiftListAddTest(){
        Utgift utgift5 = new Utgift("Paracet","100","Helse");
        UtgiftList.add(utgift5);
        assertTrue(utgiftList.toList().contains(utgift5));
        utgiftList.removeUtgift(utgift5);
        assertFalse(utgiftList.toList().contains(utgift5));
        utgiftList.addUtgift(utgift5);
        assertTrue(utgiftList.toList().contains(utgift5));
    }

    @Test
    public void testClear(){
        System.out.println(utgiftList.getUtgifter());
        UtgiftList.clear();
        assertTrue(utgiftList.getUtgifter().isEmpty());
        assertTrue(utgiftList.getPieChart().isEmpty());

    }

}
