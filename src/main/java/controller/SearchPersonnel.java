package controller;

import com.sun.scenario.effect.impl.prism.PrTexture;
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
import org.hibernate.query.Query;
import javafx.scene.control.TableView;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
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
                Query query = session.createQuery("SELECT full_name, phone_number FROM Personnel p");
//                query.setParameter("name", "%Иванов%");
//                WHERE p.full_name LIKE :name


//                CriteriaBuilder cb = session.getCriteriaBuilder();
//                CriteriaQuery<Personnel> q = cb.createQuery(Personnel.class);
//                Root<Personnel> c = q.from(Personnel.class);
//                ParameterExpression<String> p = cb.parameter(String.class);
//                q.select(c).where(cb.gt(c.get("phone_number"), p));

                List pers = new ArrayList<Personnel>();
                CriteriaBuilder builder = session.getCriteriaBuilder();
                CriteriaQuery<Personnel> criteriaQuery = builder.createQuery(Personnel.class);
                Root<Personnel> root = criteriaQuery.from(Personnel.class);
                ParameterExpression<String> p = builder.parameter(String.class);
                criteriaQuery.select(root);
                pers = session.createQuery(criteriaQuery).getResultList();



                List<Personnel> list = query.list();
                ObservableList<Personnel> data = FXCollections.observableArrayList(list);
                fullNameColumn.setCellValueFactory(new PropertyValueFactory<Personnel, String>("full_name"));
                phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<Personnel, String>("phone_number"));
                mainTable.setItems(data);
                inputLabel.setText("INFO: vOK!");
            } else {
                inputLabel.setText("ERROR: Empty input!");
            }
        } catch(Exception e) {
            inputLabel.setText("ERROR! " +e.getMessage());
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
