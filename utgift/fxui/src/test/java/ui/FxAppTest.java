package ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;

public class FxAppTest extends ApplicationTest {
    @Override
    public void start (Stage stage) throws Exception {
        final FXMLLoader loader = new FXMLLoader(getClass().getResource("FxApp.fxml"));
        Parent mainNode = loader.load();
        stage.setScene(new Scene(mainNode));
        stage.show();
        stage.toFront();
    }

    @Before
    public void setUp () throws Exception {
    }

    @After
    public void tearDown () throws Exception {
        FxToolkit.hideStage();

    }
    @Test
    public void testEnglishInput () {
        clickOn("#inputField");
        write("This is a test!");
    }
}