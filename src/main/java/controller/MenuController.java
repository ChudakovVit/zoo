package controller;

import gui.GuiLauncher;
import gui.ViewController;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;


public class MenuController extends ViewController {

    public static String URL_FXML = "/views/MenuManager.fxml";

    @FXML
    protected void employeeManage(ActionEvent event) {
//        GuiLauncher.getNavigation().load(ShowTeacherManager.URL_FXML).show();
    }

    @FXML
    protected void animalManage(ActionEvent event) {
        GuiLauncher.getNavigation().load(AnimalController.URL_FXML).show();
    }

    @FXML
    protected void searchAnimal(ActionEvent event) {
        GuiLauncher.getNavigation().load(SearchAnimal.URL_FXML).show();
    }

    @FXML
    protected void searchPersonnel(ActionEvent event) {
//        GuiLauncher.getNavigation().load(ShowAdministratorManager.URL_FXML).show();
    }

    @FXML
    protected void showAnimalPersonnel(ActionEvent event) {
//        GuiLauncher.getNavigation().load(ShowTeacherBySurnameManager.URL_FXML).show();
    }

    @FXML
    protected void exit(ActionEvent event) {
        Platform.exit();
    }
}