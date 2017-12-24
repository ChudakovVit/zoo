package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Personnel;
import utils.HibernateUtil;
import gui.GuiLauncher;
import gui.ViewController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import org.hibernate.Session;
import javafx.scene.control.TextField;
import javafx.scene.control.TableView;
import javax.persistence.criteria.*;
import java.util.List;


public class SearchPersonnel extends ViewController {

    public static String URL_FXML = "/views/SearchPersonnel.fxml";

    private Session session = HibernateUtil.getSessionFactory().openSession();

    @FXML
    TextField inputField;
    @FXML
    Label inputLabel;
    @FXML
    TableColumn phoneNumberColumn;
    @FXML
    TableColumn fullNameColumn;
    @FXML
    TableView mainTable;

    @FXML
    protected void search(ActionEvent event) {
        try {
            String fullName = inputField.getText();
            if (!fullName.isEmpty()) {
                inputLabel.setText(fullName);
                session.getTransaction().begin();
                CriteriaBuilder builder = session.getCriteriaBuilder();
                CriteriaQuery<Personnel> criteriaQuery = builder.createQuery(Personnel.class);
                Root<Personnel> root = criteriaQuery.from(Personnel.class);
                criteriaQuery.select(root);
                criteriaQuery.where(builder.like(root.get("fullName"), "%"+fullName+"%"));
                List list = session.createQuery(criteriaQuery).getResultList();
                ObservableList<Personnel> data = FXCollections.observableArrayList(list);
                fullNameColumn.setCellValueFactory(new PropertyValueFactory<Personnel, String>("fullName"));
                phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<Personnel, String>("phoneNumber"));
                mainTable.setItems(data);
                inputLabel.setText("All right!");
            } else {
                inputLabel.setText("Empty input!");
            }
        } catch(Exception e) {
            inputLabel.setText("Error: " +e.getMessage());
        } finally {
            session.getTransaction().commit();
        }
    }

    @FXML
    protected void goBack(ActionEvent event) {
        GuiLauncher.getNavigation().load(MenuController.URL_FXML).show();
        session.close();
    }
}
