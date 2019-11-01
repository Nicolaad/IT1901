package ui;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.glassfish.grizzly.http.server.HttpServer;
import rest.api.UtgiftListService;
import rest.server.UtgiftListGrizzlyApp;

import java.net.URI;
import java.util.List;


public class FxApp extends Application {
    private HttpServer restServer = null;
    /**
     * @param primaryStage takes in stage. starts the javafx and also the grizzlyserver.
     *                    Right now our app always runs with the restapi.
     * @throws Exception
     */

    @Override
    public void start(final Stage primaryStage) throws Exception {
        URI baseUri =  URI.create("http://localhost:8080/");
        List<String> args = getParameters().getRaw();
        String[]strings ={baseUri.toString()};
        UtgiftListGrizzlyApp.startServer(strings,2);
        final String fxml = "FxAppUsingRest.fxml";
        final FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxml));
        final Parent root = fxmlLoader.load();

            final FxAppUsingRestController controller = fxmlLoader.getController();
            controller.setDataAccess(new RestUtgiftListDataAccess(baseUri + UtgiftListService.UTGIFT_LIST_SERVICE_PATH, controller.getObjectMapper()));
            controller.load();



        final Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }



    public static void main(final String[] args) {
        Application.launch(args);
    }
}
