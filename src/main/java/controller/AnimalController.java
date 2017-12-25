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
import javax.persistence.criteria.Root;
import java.util.List;

public class AnimalController extends ViewController {

    public static String URL_FXML = "/views/AnimalManage.fxml";

    private Session session = HibernateUtil.getSessionFactory().openSession();

    @FXML
    TextField idField;
    @FXML
    TextField nameField;
    @FXML
    TextField kindField;
    @FXML
    TextField feedField;
    @FXML
    TextField feedQuantityField;
    @FXML
    TextField avTempField;
    @FXML
    TextField avHumField;
    @FXML
    TextField homeField;
    @FXML
    TextField idPersonnelField;
    @FXML
    Label infoLabel;

    @FXML
    protected void addAnimal(ActionEvent event) {
        try {
            Integer id;
            String name = nameField.getText();
            String kind = kindField.getText();
            String feedName = feedField.getText();
            Integer feedQuantity;
            Integer avTemp;
            Integer avHum;
            String homeName = homeField.getText();
            Integer idPersonnel;
            try {
                id = Integer.parseInt(idField.getText());
                feedQuantity = Integer.parseInt(feedQuantityField.getText());
                avTemp = Integer.parseInt(avTempField.getText());
                avHum = Integer.parseInt(avHumField.getText());
                idPersonnel = Integer.parseInt(idPersonnelField.getText());
            } catch (Exception e) {
                id = -1;
                feedQuantity = -1;
                avTemp = -1;
                avHum = -1;
                idPersonnel = -1;
            }
            if (!(id != -1 && name.isEmpty() && kind.isEmpty() && feedName.isEmpty()
                && feedQuantity != -1 && avTemp != -1 && avHum != -1 && homeName.isEmpty() && idPersonnel != -1)) {
                session.getTransaction().begin();

                CriteriaBuilder builder = session.getCriteriaBuilder();
                CriteriaQuery<Animal> criteriaQuery = builder.createQuery(Animal.class);
                Root<Animal> root = criteriaQuery.from(Animal.class);
                criteriaQuery.select(root);
                criteriaQuery.where(builder.equal(root.get("animal"), id));
                List<Animal> list = session.createQuery(criteriaQuery).getResultList();

                if (!list.isEmpty()) {
                    infoLabel.setText("Already exist!");
                    session.getTransaction().commit();
                    return;
                }
                Animal animal = new Animal(id, id, id, kind, name);
                session.save(animal);

                Feed feed = new Feed(id, feedName, feedQuantity);
                session.save(feed);

                Home home = new Home(id, avTemp, avHum, homeName);
                session.save(home);

                AnimalPersonnel animalPersonnel = new AnimalPersonnel(id+idPersonnel, id, idPersonnel);
                session.save(animalPersonnel);

                session.getTransaction().commit();
                infoLabel.setText("All right!");
            } else {
                infoLabel.setText("Wrong input!");
            }
        } catch(Exception e) {
            infoLabel.setText("Error: " + e.getMessage());
            session.getTransaction().rollback();
        }
    }

    @FXML
    protected void deleteAnimal(ActionEvent event) {
        try {
            String id = idField.getText();
            if (!id.isEmpty()) {
                int idAnimal = Integer.parseInt(id);
                session.getTransaction().begin();
                session.delete(session.get(Animal.class, idAnimal));
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
    protected void editAnimal(ActionEvent event) {
//        try {
//            String surname = surnameField.getText();
//            String name = nameField.getText();
//            String patronymic = patronymicField.getText();
//            String phoneNumber = phoneNumberField.getText();
//            String department = departmentField.getText();
//            String id = idField.getText();
//            if (!(name.isEmpty() && surname.isEmpty() && patronymic.isEmpty() && department.isEmpty() && id.isEmpty())) {
//                int id_Animal = Integer.parseInt(id);
//                session.getTransaction().begin();
//                // проверка по кафедре
//                // проверка по ид
//                Query query = session.createQuery("from Department where name = :name ");
//                query.setParameter("name", department);
//                List<Department> list = query.list();
//                if (list.isEmpty()) {
//                    infoLabel.setText("INFO: Not Found!");
//                    session.getTransaction().commit();
//                    return;
//                }
//                Animal Animal = new Animal();
//                Animal.setSurname(surname);
//                Animal.setName(name);
//                Animal.setPatronymic(patronymic);
//                Animal.setDepartment_iddepartment(list.get(0).getIdDepartment());
//                Animal.setPhoneNumber(phoneNumber);
//                session.save(Animal);
//                session.getTransaction().commit();
//                infoLabel.setText("INFO: OK!");
//            } else {
//                infoLabel.setText("ERROR: Empty input!");
//            }
//        } catch(Exception e) {
//            infoLabel.setText("ERROR! " + e.getMessage());
//            session.getTransaction().rollback();
//        }
        System.out.println("asdf");
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
