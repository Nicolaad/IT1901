package ui;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import rest.server.UtgiftListGrizzlyApp;


public class FxApp extends Application{

    @Override
    public void start(final Stage primaryStage) throws Exception {
        primaryStage.setTitle("Utgift");
        primaryStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("FxApp.fxml"))));
        //rest.server.UtgiftListGrizzlyApp a = new rest.server.UtgiftListGrizzlyApp();
        String []strings = {};
        primaryStage.show();
        UtgiftListGrizzlyApp.main(strings);
    }

    public static void main(final String[] args) {
        Application.launch(args);
    }
}
