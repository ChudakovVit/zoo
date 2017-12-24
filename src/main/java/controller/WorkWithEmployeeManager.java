//package controller;
//
//import app.HibernateUtil;
//import gui.GuiLauncher;
//import gui.ViewController;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import model.Department;
//import model.Faculty;
//import org.hibernate.Session;
//import org.hibernate.query.Query;
//
//import javax.swing.*;
//import java.util.List;
//
//public class WorkWithEmployeeManager extends ViewController {
//    /** URL of the main menu manager view FXML file. */
//    public static String URL_FXML = "/fxml/WorkWithEmployeeManager.fxml";
//
//    private Session session = HibernateUtil.getSessionFactory().openSession();
//
//    /**
//     * Opening of the edit manager view.
//     * @param event
//     */
//    @FXML
//    protected void addEmployee(ActionEvent event) {
//    }
//
//    /**
//     * Opening of the edit manager view.
//     * @param event
//     */
//    @FXML
//    protected void deleteEmployee(ActionEvent event) {
//    }
//
//    /**
//     * Opening of the edit manager view.
//     * @param event
//     */
//    @FXML
//    protected void editEmployee(ActionEvent event) {
//    }
//
//    /**
//     * Opening of the edit manager view.
//     * @param event
//     */
//    @FXML
//    protected void showFaculties(ActionEvent event) {
//        session.getTransaction().begin();
//        Query query = session.createQuery("from Faculty");
//        List<Faculty> list = query.list();
//        StringBuilder result = new StringBuilder("Department: ");
//        for(Faculty faculty : list)
//        {
//            result.append(faculty.getName() + "  ");
//        }
//        JOptionPane.showMessageDialog(null, result, "About", JOptionPane.INFORMATION_MESSAGE);
//    }
//
//    /**
//     * Opening of the edit manager view.
//     * @param event
//     */
//    @FXML
//    protected void showEmployees(ActionEvent event) {
//        // убрать
//    }
//
//    /**
//     * Opening of the print manager view.
//     * @param event
//     */
//    @FXML
//    protected void goBack(ActionEvent event) {
//        GuiLauncher.getNavigation().load(MenuController.URL_FXML).show();
//    }
//}
