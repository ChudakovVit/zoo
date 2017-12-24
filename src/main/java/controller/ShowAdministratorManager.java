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
//import javafx.scene.control.cell.PropertyValueFactory;
//import model.Administrator;
//import org.hibernate.Session;
//import javafx.scene.control.TextField;
//import org.hibernate.query.Query;
//import javafx.scene.control.TableView;
//import java.util.List;
//
//
//public class ShowAdministratorManager extends ViewController {
//    /** URL of the main menu manager view FXML file. */
//    public static String URL_FXML = "/fxml/ShowAdministratorManager.fxml";
//
//    private Session session = HibernateUtil.getSessionFactory().openSession();
//
//    /** Text field to input animal id. */
//    @FXML
//    TextField inputField;
//    @FXML
//    Label inputLabel;
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
//            if (!facultyName.isEmpty()) {
//                inputLabel.setText(facultyName);
//                session.getTransaction().begin();
//                Query query = session.createQuery("select a from Administrator as a " +
//                                                            "left join a.faculty as b " +
//                                                            "where b.name = :name ");
//                query.setParameter("name", facultyName);
//                List<Administrator> list = query.list();
//                ObservableList<Administrator> data =
//                        FXCollections.observableArrayList(list);
//                surnameColumn.setCellValueFactory(
//                        new PropertyValueFactory<Administrator, String>("surname"));
//                nameColumn.setCellValueFactory(
//                        new PropertyValueFactory<Administrator, String>("name"));
//                patronymicColumn.setCellValueFactory(
//                        new PropertyValueFactory<Administrator, String>("patronymic"));
//                phoneNumberColumn.setCellValueFactory(
//                        new PropertyValueFactory<Administrator, String>("phoneNumber"));
//                mainTable.setItems(data);
//                inputLabel.setText("INFO: OK!");
//
//            } else {
//                inputLabel.setText("ERROR: Empty input!");
//            }
//        } catch(Exception e) {
//            inputLabel.setText("ERROR! " +e.getMessage());
//        } finally {
//            session.getTransaction().commit();
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
//        session.close();
//    }
//}
