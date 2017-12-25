package controller;

import model.Animal;
import model.Feed;
import model.Home;
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
import java.util.ArrayList;
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
    TableColumn feedQuantityColumn;
    @FXML
    TableColumn avgTempColumn;
    @FXML
    TableColumn avgHumColumn;
    @FXML
    TableColumn homeColumn;
    @FXML
    TableView mainTable;
    @FXML
    TableView feedTable;
    @FXML
    TableView homeTable;


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
                List<Animal> list = session.createQuery(criteriaQuery).getResultList();
                List<Feed> listFeed = new ArrayList<>();
                List<Home> listHome = new ArrayList<>();
                Integer id;
                for (Animal animal : list) {
                    id = animal.getAnimal();

                    CriteriaBuilder builderFeed = session.getCriteriaBuilder();
                    CriteriaQuery<Feed> criteriaQueryFeed = builderFeed.createQuery(Feed.class);
                    Root<Feed> rootFeed = criteriaQueryFeed.from(Feed.class);
                    criteriaQueryFeed.select(rootFeed);
                    criteriaQueryFeed.where(builderFeed.equal(rootFeed.get("feed"), id));
                    List<Feed> tempListFeed = session.createQuery(criteriaQueryFeed).getResultList();
                    listFeed.addAll(tempListFeed);

                    CriteriaBuilder builderHome = session.getCriteriaBuilder();
                    CriteriaQuery<Home> criteriaQueryHome = builderHome.createQuery(Home.class);
                    Root<Home> rootHome = criteriaQueryHome.from(Home.class);
                    criteriaQueryHome.select(rootHome);
                    criteriaQueryHome.where(builderHome.equal(rootHome.get("home"), id));
                    List<Home> tempListHome = session.createQuery(criteriaQueryHome).getResultList();
                    listHome.addAll(tempListHome);
                }
                ObservableList<Animal> data = FXCollections.observableArrayList(list);
                nameColumn.setCellValueFactory(new PropertyValueFactory<Animal, String>("description"));
                kindColumn.setCellValueFactory(new PropertyValueFactory<Animal, String>("kind"));
                mainTable.setItems(data);
                ObservableList<Feed> dataFeed = FXCollections.observableArrayList(listFeed);
                feedColumn.setCellValueFactory(new PropertyValueFactory<Feed, String>("feed"));
                feedQuantityColumn.setCellValueFactory(new PropertyValueFactory<Feed, String>("quantity"));
                feedTable.setItems(dataFeed);
                ObservableList<Home> dataHome = FXCollections.observableArrayList(listHome);
                avgTempColumn.setCellValueFactory(new PropertyValueFactory<Home, String>("averageTemp"));
                avgHumColumn.setCellValueFactory(new PropertyValueFactory<Home, String>("averageHumidity"));
                homeColumn.setCellValueFactory(new PropertyValueFactory<Home, String>("description"));
                homeTable.setItems(dataHome);
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
