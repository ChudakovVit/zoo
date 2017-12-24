package controller;

import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.AnimalPersonnel;
import model.Personnel;
import model.Animal;
import utils.HibernateUtil;
import gui.GuiLauncher;
import gui.ViewController;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class ShowAnimalPersonnel extends ViewController {
    
    public static String URL_FXML = "/views/ShowAnimalPersonnel.fxml";

    private Session session = HibernateUtil.getSessionFactory().openSession();

    @FXML
    ComboBox<Integer> comboBox;
    @FXML
    Label infoLabel;
    @FXML
    TableColumn fullNameColumn;
    @FXML
    TableColumn phoneNumberColumn;
    @FXML
    TableView mainTable;

    @FXML
    public void initialize() {
        session.getTransaction().begin();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Animal> criteriaQuery = builder.createQuery(Animal.class);
        Root<Animal> root = criteriaQuery.from(Animal.class);
        criteriaQuery.select(root);
        List list = session.createQuery(criteriaQuery).getResultList();
        ObservableList<Integer> data = FXCollections.observableArrayList(list);

        comboBox.setItems(data);
        comboBox.getSelectionModel().selectFirst();
        session.getTransaction().commit();
    }
    
    @FXML
    protected void search(ActionEvent event) {
        try {
            Integer value = comboBox.getSelectionModel().getSelectedIndex() + 1;
            session.getTransaction().begin();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<AnimalPersonnel> criteriaQuery = builder.createQuery(AnimalPersonnel.class);
            Root<AnimalPersonnel> root = criteriaQuery.from(AnimalPersonnel.class);
            criteriaQuery.select(root);
            criteriaQuery.where(builder.equal(root.get("animal"), value));
            List list = session.createQuery(criteriaQuery).getResultList();
            ObservableList<AnimalPersonnel> data = FXCollections.observableArrayList(list);
            Integer personnelId = data.get(0).getPersonnel();
            CriteriaBuilder builderPersonnel = session.getCriteriaBuilder();
            CriteriaQuery<Personnel> criteriaQueryPersonnel = builderPersonnel.createQuery(Personnel.class);
            Root<Personnel> rootPersonnel = criteriaQueryPersonnel.from(Personnel.class);
            criteriaQueryPersonnel.select(rootPersonnel);
            criteriaQueryPersonnel.where(builderPersonnel.equal(root.get("personnel"), personnelId));
            List listPersonnel = session.createQuery(criteriaQueryPersonnel).getResultList();
            ObservableList<Personnel> dataPersonnel = FXCollections.observableArrayList(listPersonnel);
            fullNameColumn.setCellValueFactory(new PropertyValueFactory<Personnel, String>("fullName"));
            phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<Personnel, String>("phoneNumber"));
            mainTable.setItems(dataPersonnel);
            infoLabel.setText("All right!");
        } catch(Exception e) {
            infoLabel.setText("Error: " + e.getMessage());
            session.getTransaction().rollback();
        } finally {
            session.getTransaction().commit();
        }
    }

    @FXML
    protected void goBack(ActionEvent event) {
        GuiLauncher.getNavigation().load(MenuController.URL_FXML).show();
    }
}
