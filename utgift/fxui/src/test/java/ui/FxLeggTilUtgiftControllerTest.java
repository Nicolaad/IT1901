package ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import java.net.URL;

import static junit.framework.TestCase.assertNotNull;

public class FxLeggTilUtgiftControllerTest extends ApplicationTest {
    private FxLeggTilUtgiftController controller;
    @Override
    public void start(final Stage stage) throws Exception {
        URL f =getClass().getResource("FxAppLeggTilUtgift.fxml");
        final FXMLLoader loader = new FXMLLoader(f);
        final Parent root = loader.load();
        this.controller = loader.getController();
        final Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Test
    public void aTestController(){
        assertNotNull(this.controller);
    }

}