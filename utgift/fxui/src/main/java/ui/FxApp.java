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
        UtgiftListGrizzlyApp a = new UtgiftListGrizzlyApp();
        String []strings = {};
        primaryStage.show();
        UtgiftListGrizzlyApp.startServer(strings,0);
    }

    public static void main(final String[] args) {
        Application.launch(args);
    }
}
