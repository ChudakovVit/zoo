package controller;

import model.Animal;
import utils.HibernateUtil;
import gui.GuiLauncher;
import gui.ViewController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;


public class SearchAnimal extends ViewController {
    public static String URL_FXML = "/views/SearchAnimal.fxml";

    private Session session = HibernateUtil.getSessionFactory().openSession();

    @FXML
    TextField inputField;
    @FXML
    Label inputLabel;
    @FXML
    TableColumn nameColumn;
    @FXML
    TableColumn kindColumn;
    @FXML
    TableColumn feedColumn;
    @FXML
    TableView mainTable;

    @FXML
    protected void search(ActionEvent event) {
        try {
            String animalName = inputField.getText();
            if (!animalName.isEmpty()) {
                session.getTransaction().begin();
                Query query = session.createQuery("select description, kind from Animal where description = :name ");
                query.setParameter("name", animalName);
                List<Animal> list = query.list();
                ObservableList<Animal> data = FXCollections.observableArrayList(list);
                nameColumn.setCellValueFactory(new PropertyValueFactory<Animal, String>("description"));
                kindColumn.setCellValueFactory(new PropertyValueFactory<Animal, String>("kind"));
//                feedColumn.setCellValueFactory();
                mainTable.setItems(data);
                inputLabel.setText("INFO: OK!");
                session.getTransaction().commit();

            } else {
                inputLabel.setText("ERROR: Empty input!");
            }
        } catch(Exception e) {
            inputLabel.setText("ERROR! " + e.getMessage());
            session.getTransaction().rollback();
        }
    }

    @FXML
    protected void goBack(ActionEvent event) {
        GuiLauncher.getNavigation().load(MenuController.URL_FXML).show();
        session.close();
    }
}
