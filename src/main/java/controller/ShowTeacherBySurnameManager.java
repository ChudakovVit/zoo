//package controller;
//
//import app.HibernateUtil;
//import gui.GuiLauncher;
//import gui.ViewController;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.scene.control.Label;
//import javafx.scene.control.TableColumn;
//import javafx.scene.control.TableView;
//import javafx.scene.control.TextField;
//import javafx.scene.control.cell.PropertyValueFactory;
//import model.Administrator;
//import model.Department;
//import model.Teacher;
//import org.hibernate.Session;
//import org.hibernate.query.Query;
//
//import java.util.List;
//
//public class ShowTeacherBySurnameManager extends ViewController {
//    /** URL of the main menu manager view FXML file. */
//    public static String URL_FXML = "/fxml/ShowTeacherBySurnameManager.fxml";
//
//    private Session session = HibernateUtil.getSessionFactory().openSession();
//
//    @FXML
//    TextField inputField;
//    @FXML
//    Label infoLabel;
//    @FXML
//    TableColumn surnameColumn;
//    @FXML
//    TableColumn nameColumn;
//    @FXML
//    TableColumn patronymicColumn;
//    @FXML
//    TableColumn phoneNumberColumn;
//    @FXML
//    TableView mainTable;
//
//    /**
//     * Opening of the edit manager view.
//     * @param event
//     */
//    @FXML
//    protected void search(ActionEvent event) {
//        try {
//            String facultyName = inputField.getText();
//            List<Teacher> list;
//            session.getTransaction().begin();
//            if (!facultyName.isEmpty()) {
//                Query query = session.createQuery("from Teacher where surname like :name ");
//                query.setParameter("name", facultyName+"%");
//                list = query.list();
//                if (!list.isEmpty()) {
//                    infoLabel.setText("ERROR: Not Found!");
//                }
//            } else {
//                Query query = session.createQuery("from Teacher");
//                list = query.list();
//                if (!list.isEmpty()) {
//                    infoLabel.setText("ERROR: Not Found!");
//                }
//            }
//            ObservableList<Teacher> data =
//                    FXCollections.observableArrayList(list);
//            surnameColumn.setCellValueFactory(
//                    new PropertyValueFactory<Administrator, String>("surname"));
//            nameColumn.setCellValueFactory(
//                    new PropertyValueFactory<Administrator, String>("name"));
//            patronymicColumn.setCellValueFactory(
//                    new PropertyValueFactory<Administrator, String>("patronymic"));
//            phoneNumberColumn.setCellValueFactory(
//                    new PropertyValueFactory<Administrator, String>("phoneNumber"));
//            mainTable.setItems(data);
//            infoLabel.setText("INFO: OK!");
//            session.getTransaction().commit();
//
//        } catch(Exception e) {
//            infoLabel.setText("ERROR! " + e.getMessage());
//            session.getTransaction().rollback();
//        }
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
