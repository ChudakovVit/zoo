package controller;

import gui.GuiLauncher;
import gui.ViewController;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;


public class MenuController extends ViewController {

    public static String URL_FXML = "/views/MenuManager.fxml";
//
//    @FXML
//    protected void showTeachers(ActionEvent event) {
//
//        GuiLauncher.getNavigation().load(ShowTeacherManager.URL_FXML).show();
//    }
//
//    @FXML
//    protected void showSpecialties(ActionEvent event) {
//        GuiLauncher.getNavigation().load(ShowSpecialtiesManager.URL_FXML).show();
//    }
//
//    @FXML
//    protected void showAdministrators(ActionEvent event) {
//        GuiLauncher.getNavigation().load(ShowAdministratorManager.URL_FXML).show();
//    }
//
//    @FXML
//    protected void showTeachersBySurname(ActionEvent event) {
//        GuiLauncher.getNavigation().load(ShowTeacherBySurnameManager.URL_FXML).show();
//    }
//
//    @FXML
//    protected void workWithCourses(ActionEvent event) {
//        GuiLauncher.getNavigation().load(WorkWithCourseManager.URL_FXML).show();
//    }
//
//    @FXML
//    protected void workWithEmployees(ActionEvent event) {
//        GuiLauncher.getNavigation().load(WorkWithEmployeeManager.URL_FXML).show();
//    }

    @FXML
    protected void animalsManage(ActionEvent event) {
        GuiLauncher.getNavigation().load(AnimalController.URL_FXML).show();
    }

    @FXML
    protected void exit(ActionEvent event) {
        Platform.exit();
    }
}