package ui;

import core.Utgift;
import core.UtgiftList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import static junit.framework.TestCase.assertEquals;

public class FxAppTest extends ApplicationTest {
    @BeforeClass
    public static void headless(){

    }

    private FxAppController controller;
    // field is not used
//    private UtgiftList utgiftList;
    @Override
    public void start(final Stage stage) throws Exception{
        final FXMLLoader loader = new FXMLLoader(getClass().getResource("FxApp.fxml"));
        final Parent root = loader.load();
        this.controller = loader.getController();
        final Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Test
    public void testController() {
        Assert.assertNotNull(this.controller);
    }

    @Test
    public void voidTestUtgiftListView(){
        //henter foorst utgiftListView deretter sjekker om man kan legge til items til utgiftList og de vises i listview.
        final ListView<Utgift> utgiftListView = lookup("#listViewUtgift").query();
        UtgiftList utgiftL = new UtgiftList();
        utgiftListView.setItems(utgiftL.getUtgifter());
        assertEquals(utgiftL.getUtgifter(),utgiftListView.getItems());
        utgiftL.addUtgift(new Utgift("Mat","200","matvarer"));
        assertEquals(utgiftL.getUtgifter(),utgiftListView.getItems());
    }

}
