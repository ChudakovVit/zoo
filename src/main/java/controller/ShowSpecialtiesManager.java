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
//import org.hibernate.Session;
//import org.hibernate.query.Query;
//
//import java.util.List;
//
//public class ShowSpecialtiesManager extends ViewController {
//    /** URL of the main menu manager view FXML file. */
//    public static String URL_FXML = "/fxml/ShowSpecialtiesManager.fxml";
//
//    private Session session = HibernateUtil.getSessionFactory().openSession();
//
//    @FXML
//    TextField inputField;
//    @FXML
//    Label inputLabel;
//    @FXML
//    TableColumn departmentColumn;
//    @FXML
//    TableColumn specialityColumn;
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
//                session.getTransaction().begin();
//                Query query = session.createQuery("select a from Department as a " +
//                                                            "left join a.faculty as b " +
//                                                            "where b.name = :name ");
//                query.setParameter("name", facultyName);
//                List<Department> list = query.list();
//                ObservableList<Department> data =
//                        FXCollections.observableArrayList(list);
//                departmentColumn.setCellValueFactory(
//                        new PropertyValueFactory<Administrator, String>("name"));
//                specialityColumn.setCellValueFactory(
//                        new PropertyValueFactory<Administrator, String>("speciality"));
//                mainTable.setItems(data);
//                inputLabel.setText("INFO: OK!");
//                session.getTransaction().commit();
//
//            } else {
//                inputLabel.setText("ERROR: Empty input!");
//            }
//        } catch(Exception e) {
//            inputLabel.setText("ERROR! " + e.getMessage());
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
//        session.close();
//    }
//}
