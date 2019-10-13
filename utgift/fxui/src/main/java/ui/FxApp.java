package ui;


import core.Utgift;
import core.UtgiftList;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import rest.server.UtgiftListGrizzlyApp;

import java.net.URL;
import java.util.Arrays;


public class FxApp extends Application {

    /**
     * @param primaryStage takes in stage. starts the javafx and also the grizzlyserver.
     * @throws Exception
     */

    @Override
    public void start(final Stage primaryStage) throws Exception {
        URL f =getClass().getResource("FxApp.fxml");
        final FXMLLoader loader = new FXMLLoader(f);
        final Parent root = loader.load();
        FxAppController controller = loader.getController();
        controller.setUtgiftList(
                new UtgiftList(
                        Arrays.asList(
                                new Utgift("Fisk","200.0","Mat"),
                                new Utgift("Rotter","50.0","Mat"),
                                new Utgift("Penn","20.0","Skole"),
                                new Utgift("Medisin","100.0","Helse"))));
        controller.init2();
        controller.labelsSetUp();
        final Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.show();
        /*
        primaryStage.setTitle("Utgift");
        primaryStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("FxApp.fxml"))));
        FxAppController controller = loader
        primaryStage.show();
        */
        String []strings = {};
        UtgiftListGrizzlyApp.startServer(strings,0);
    }

    public static void main(final String[] args) {
        Application.launch(args);
    }
}
