package ui;


import core.Utgift;
import core.UtgiftList;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.glassfish.grizzly.http.server.HttpServer;
import rest.api.UtgiftListService;
import rest.server.UtgiftListGrizzlyApp;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class FxApp extends Application {
    private HttpServer restServer = null;
    /**
     * @param primaryStage takes in stage. starts the javafx and also the grizzlyserver.
     * @throws Exception
     */

    @Override
    public void start(final Stage primaryStage) throws Exception {
        URI baseUri = null;
        List<String> args = getParameters().getRaw();
        //args.add("https://localhost:8080");
       // args.add("[]");
       // baseUri =
        System.out.println(args);
        if(args.size()>=1){
            final List<String> serverArgs = new ArrayList<>();
            baseUri = URI.create(args.get(0));
            serverArgs.add(baseUri.toString());
            if (args.size() >= 2) {
                // json of initial data
                serverArgs.add(args.get(1));
            }
            System.out.println(serverArgs);
            System.out.println("Hei");
            restServer = UtgiftListGrizzlyApp.startServer(serverArgs.toArray(new String[serverArgs.size()]), 5);
        }
        final String fxml = (baseUri != null ? "FxAppUsingRest.fxml" : "FxApp.fxml");
        final FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxml));
        final Parent root = fxmlLoader.load();
        if (baseUri == null) {
            // set initial data manually
            final FxAppController controller = fxmlLoader.getController();
            controller.setUtgiftList(
                    new UtgiftList(
                            Arrays.asList(
                                    new Utgift("Fisk","200.0","Mat"),
                                    new Utgift("Rotter","50.0","Mat"),
                                    new Utgift("Penn","20.0","Skole"),
                                    new Utgift("Medisin","100.0","Helse"))));
            controller.init2();
            controller.labelsSetUp();
        } else {
            final FxAppUsingRestController controller = fxmlLoader.getController();
            controller.setDataAccess(new RestUtgiftListDataAccess(baseUri + UtgiftListService.UTGIFT_LIST_SERVICE_PATH, controller.getObjectMapper()));

        }
        final Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
        /*
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
        /*
        String []strings = {};
        UtgiftListGrizzlyApp.startServer(strings,0);
    }

         */

    public static void main(final String[] args) {
        Application.launch(args);
    }
}
