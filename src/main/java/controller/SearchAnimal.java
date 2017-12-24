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

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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
            String name = inputField.getText();
            if (!name.isEmpty()) {
                inputLabel.setText(name);
                session.getTransaction().begin();
                CriteriaBuilder builder = session.getCriteriaBuilder();
                CriteriaQuery<Animal> criteriaQuery = builder.createQuery(Animal.class);
                Root<Animal> root = criteriaQuery.from(Animal.class);
                criteriaQuery.select(root);
                criteriaQuery.where(builder.like(root.get("description"), "%"+name+"%"));
                List list = session.createQuery(criteriaQuery).getResultList();
                ObservableList<Animal> data = FXCollections.observableArrayList(list);
                nameColumn.setCellValueFactory(new PropertyValueFactory<Animal, String>("description"));
                kindColumn.setCellValueFactory(new PropertyValueFactory<Animal, String>("kind"));
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
