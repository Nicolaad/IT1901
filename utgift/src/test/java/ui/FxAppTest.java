package ui;

import core.UtgiftList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

public class FxAppTest extends ApplicationTest {
    @BeforeClass
    public static void headless(){

    }

    private FxAppController controller;
    private UtgiftList utgiftList;
    @Override
    public void start(final Stage stage) throws Exception{
        final FXMLLoader loader = new FXMLLoader(getClass().getResource("FxApp.fxml"));
        final Parent root = loader.load();
        final Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Test
    public void testController() {
        Assert.assertNotNull(this.controller);
    }

}
