package gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class Navigation {

    private final Stage stage;

    private final Scene scene;

    public Navigation(Stage stage) {
        this.stage = stage;
        scene = new Scene(new Pane());
        stage.setScene(scene);
        stage.setMinWidth(800);
        stage.setMinHeight(800);
    }

    public ViewController load (String urlString) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(urlString));
            Node root = fxmlLoader.load();
            ViewController viewController = fxmlLoader.getController();
            viewController.setView(root);
            return viewController;
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void show(ViewController viewController) {
        try {
            scene.setRoot((Parent) viewController.getView());
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
}