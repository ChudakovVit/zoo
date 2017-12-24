package gui;

import controller.MenuController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class GuiLauncher extends Application {

    private static Navigation navigation;

    public static Navigation getNavigation() {
        return navigation;
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            navigation = new Navigation(primaryStage);
            primaryStage.setTitle("Zoo Park");
            primaryStage.show();
            GuiLauncher.getNavigation().load(MenuController.URL_FXML).show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void launch() {
        launch((String) null);
    }
}