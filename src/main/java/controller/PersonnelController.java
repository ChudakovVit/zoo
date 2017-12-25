package controller;

import javafx.scene.control.Alert;
import model.*;
import utils.HibernateUtil;
import gui.GuiLauncher;
import gui.ViewController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.hibernate.Session;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;
import java.util.List;


public class PersonnelController extends ViewController {

    public static String URL_FXML = "/views/PersonnelManage.fxml";

    private Session session = HibernateUtil.getSessionFactory().openSession();

    @FXML
    TextField idField;
    @FXML
    TextField fullNameField;
    @FXML
    TextField phoneNumberField;
    @FXML
    TextField idAnimalField;
    @FXML
    Label infoLabel;

    @FXML
    protected void addPersonnel(ActionEvent event) {
        try {
            Integer id;
            String name = fullNameField.getText();
            String phone = phoneNumberField.getText();
            Integer idAnimal;
            try {
                id = Integer.parseInt(idField.getText());
                idAnimal = Integer.parseInt(idAnimalField.getText());
            } catch (Exception e) {
                id = -1;
                idAnimal = -1;
            }
            if (!(id != -1 && name.isEmpty() && phone.isEmpty() && idAnimal != -1)) {
                session.getTransaction().begin();

                CriteriaBuilder builder = session.getCriteriaBuilder();
                CriteriaQuery<Personnel> criteriaQuery = builder.createQuery(Personnel.class);
                Root<Personnel> root = criteriaQuery.from(Personnel.class);
                criteriaQuery.select(root);
                criteriaQuery.where(builder.equal(root.get("personnel"), id));
                List<Personnel> list = session.createQuery(criteriaQuery).getResultList();

                if (!list.isEmpty()) {
                    infoLabel.setText("Already exist!");
                    return;
                }
                Personnel personnel = new Personnel(id, name, phone);
                session.save(personnel);

                AnimalPersonnel animalPersonnel = new AnimalPersonnel(id+idAnimal, idAnimal, id);
                session.save(animalPersonnel);

                infoLabel.setText("All right!");
            } else {
                infoLabel.setText("Wrong input!");
            }
        } catch(Exception e) {
            infoLabel.setText("Error: " + e.getMessage());
            session.getTransaction().rollback();
        } finally {
            session.getTransaction().commit();
        }
    }

    @FXML
    protected void editPersonnel(ActionEvent event) {
        try {
            Integer id;
            String name = fullNameField.getText();
            String phone = phoneNumberField.getText();

            try {
                id = Integer.parseInt(idField.getText());
            } catch (Exception e) {
                id = -1;
            }
            session.getTransaction().begin();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaUpdate<Personnel> criteriaUpdate = builder.createCriteriaUpdate(Personnel.class);
            Root<Personnel> root = criteriaUpdate.from(Personnel.class);
            criteriaUpdate.set("phoneNumber", phone);
            criteriaUpdate.set("fullName", name);
            criteriaUpdate.where(builder.equal(root.get("personnel"), id));
            session.createQuery(criteriaUpdate).executeUpdate();
            infoLabel.setText("All right!");
        } catch(Exception e) {
            infoLabel.setText("Error: " + e.getMessage());
            session.getTransaction().rollback();
        } finally {
            session.getTransaction().commit();
        }
    }

    @FXML
    protected void deletePersonnel(ActionEvent event) {
        try {
            Integer id;
            try {
                id = Integer.parseInt(idField.getText());
            } catch (Exception e) {
                id = -1;
            }
            if (id != -1) {
                session.getTransaction().begin();
                session.delete(session.get(Personnel.class, id));
                session.getTransaction().commit();
                infoLabel.setText("All right!");
            } else {
                infoLabel.setText("Empty input!");
            }
        } catch(Exception e) {
            infoLabel.setText("Error: " + e.getMessage());
            session.getTransaction().rollback();
        }
    }


    @FXML
    protected void showPersonnel(ActionEvent event) {
        session.getTransaction().begin();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Personnel> criteriaQuery = builder.createQuery(Personnel.class);
        Root<Personnel> root = criteriaQuery.from(Personnel.class);
        criteriaQuery.select(root).orderBy(builder.asc(root.get("personnel")));
        List<Personnel> list = session.createQuery(criteriaQuery).getResultList();
        session.getTransaction().commit();
        StringBuilder result = new StringBuilder();
        for(Personnel personnel : list) {
            result.append(personnel.toString() + "\n");
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Сотрудники:");
        alert.setHeaderText(null);
        alert.setContentText(result.toString());
        alert.showAndWait();
    }

    @FXML
    protected void showAnimal(ActionEvent event) {
        session.getTransaction().begin();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Animal> criteriaQuery = builder.createQuery(Animal.class);
        Root<Animal> root = criteriaQuery.from(Animal.class);
        criteriaQuery.select(root).orderBy(builder.asc(root.get("animal")));
        List<Animal> list = session.createQuery(criteriaQuery).getResultList();
        session.getTransaction().commit();
        StringBuilder result = new StringBuilder();
        for(Animal animal : list) {
            result.append(animal.toString() + "\n");
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Животные:");
        alert.setHeaderText(null);
        alert.setContentText(result.toString());
        alert.showAndWait();
    }

    @FXML
    protected void goBack(ActionEvent event) {
        GuiLauncher.getNavigation().load(MenuController.URL_FXML).show();
        session.close();
    }
}
