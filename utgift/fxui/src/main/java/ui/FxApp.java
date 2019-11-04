package ui;

import java.net.URI;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import rest.api.UtgiftListService;
import rest.server.UtgiftListGrizzlyApp;





public class FxApp extends Application {
    /* disabled since it isn't used, spotbugs warning
     private HttpServer restServer = null;

     */
    /**
     * @param primaryStage takes in stage. starts the javafx and also the grizzlyserver.
     *                    Right now our app always runs with the restapi.
     * @throws Exception om den misslykkes i å starte
     */

    @Override
    public void start(final Stage primaryStage) throws Exception {
        URI baseUri = URI.create("http://localhost:8080/");

        /* disabled because never used - spotbug warning
        List<String> args = getParameters().getRaw();
        */

        String[]strings = {baseUri.toString()};
        UtgiftListGrizzlyApp.startServer(strings,2);
        final String fxml = "FxAppUsingRest.fxml";
        final FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxml));
        final Parent root = fxmlLoader.load();

        final FxAppUsingRestController controller = fxmlLoader.getController();
        controller.setDataAccess(new RestUtgiftListDataAccess(baseUri
                + UtgiftListService.UTGIFT_LIST_SERVICE_PATH, controller.getObjectMapper()));
        controller.load();



        final Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    /**
     * hovedmetode, starter lokalui delen av programmet
     * @param args argumenter som kan taes inn når man starter programmet
     */
    public static void main(final String[] args) {
        Application.launch(args);
    }
}
