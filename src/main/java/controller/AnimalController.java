package controller;

import utils.HibernateUtil;
import gui.GuiLauncher;
import gui.ViewController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Animal;
import model.Feed;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.swing.*;
import java.util.List;

public class AnimalController extends ViewController {

    public static String URL_FXML = "/views/AnimalManage.fxml";

    private Session session = HibernateUtil.getSessionFactory().openSession();
    @FXML
    TextField nameField;
    @FXML
    TextField kindField;
    @FXML
    Label infoLabel;
    @FXML
    TextField feedField;
    @FXML
    TextField feedQuantityField;
    @FXML
    TextField departmentField;
    @FXML
    TextField idField;

    @FXML
    protected void addAnimal(ActionEvent event) {
        try {
            String kind = kindField.getText();
            String name = nameField.getText();
            String feed = feedField.getText();
            Integer feedQuantity = Integer.parseInt(feedQuantityField.getText());
            if (!(kind.isEmpty() && name.isEmpty())) {
                session.getTransaction().begin();
//                Query query = session.createQuery("from Feed where name = :name ");
//                query.setParameter("name", department);
//                List<Feed> list = query.list();
//                if (list.isEmpty()) {
//                    infoLabel.setText("INFO: Not Found!");
//                    session.getTransaction().commit();
//                    return;
//                }
                Animal animal = new Animal();
                animal.setKind(kind);
                animal.setDescription(name);
//                Animal.setPatronymic(patronymic);
//                Animal.setDepartment_iddepartment(list.get(0).getIdDepartment());
//                Animal.setPhoneNumber(phoneNumber);
                session.save(animal);
                session.getTransaction().commit();
                infoLabel.setText("INFO: OK!");
            } else {
                infoLabel.setText("ERROR: Empty input!");
            }
        } catch(Exception e) {
            infoLabel.setText("ERROR! " + e.getMessage());
            session.getTransaction().rollback();
        }
    }

    @FXML
    protected void deleteAnimal(ActionEvent event) {
        try {
            String id = idField.getText();
            if (!id.isEmpty()) {
                int id_Animal = Integer.parseInt(id);
                session.getTransaction().begin();
                session.delete(session.get(Animal.class, id_Animal));
                session.getTransaction().commit();
                infoLabel.setText("INFO: OK!");
            } else {
                infoLabel.setText("ERROR: Empty input!");
            }
        } catch(Exception e) {
            infoLabel.setText("ERROR! " + e.getMessage());
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
    protected void showDepartments(ActionEvent event) {
        System.out.println("24iy3");
//        session.getTransaction().begin();
//        Query query = session.createQuery("from Department");
//        List<Department> list = query.list();
//        StringBuilder result = new StringBuilder("Department: ");
//        for(Department department : list)
//        {
//            result.append(department.getIdDepartment() + "  ");
//        }
//        JOptionPane.showMessageDialog(null, result, "About", JOptionPane.INFORMATION_MESSAGE);
    }

    @FXML
    protected void showAnimals(ActionEvent event) {
        session.getTransaction().begin();
        Query query = session.createQuery("from Animal");
        List<Animal> list = query.list();
        StringBuilder result = new StringBuilder("Animal: ");
        for(Animal Animal : list) {
            result.append(Animal.getAnimal() + "  ");
        }
        JOptionPane.showMessageDialog(null, result, "About", JOptionPane.INFORMATION_MESSAGE);
    }

    @FXML
    protected void goBack(ActionEvent event) {
        GuiLauncher.getNavigation().load(MenuController.URL_FXML).show();
        session.close();
    }
}
