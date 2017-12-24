//package controller;
//
//import app.HibernateUtil;
//import gui.GuiLauncher;
//import gui.ViewController;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.scene.control.Label;
//import javafx.scene.control.TextField;
//import model.Course;
//import model.Department;
//import model.Teacher;
//import org.hibernate.Session;
//import org.hibernate.query.Query;
//
//import javax.swing.*;
//import java.util.List;
//
//public class WorkWithTeacherManager extends ViewController {
//    /** URL of the main menu manager view FXML file. */
//    public static String URL_FXML = "/fxml/WorkWithTeacherManager.fxml";
//
//    private Session session = HibernateUtil.getSessionFactory().openSession();
//    @FXML
//    TextField nameField;
//    @FXML
//    TextField surnameField;
//    @FXML
//    Label infoLabel;
//    @FXML
//    TextField patronymicField;
//    @FXML
//    TextField phoneNumberField;
//    @FXML
//    TextField departmentField;
//    @FXML
//    TextField idField;
//
//    /**
//     * Opening of the edit manager view.
//     * @param event
//     */
//    @FXML
//    protected void addTeacher(ActionEvent event) {
//        try {
//            String surname = surnameField.getText();
//            String name = nameField.getText();
//            String patronymic = patronymicField.getText();
//            String phoneNumber = phoneNumberField.getText();
//            String department = departmentField.getText();
//            if (!(name.isEmpty() && surname.isEmpty() && patronymic.isEmpty() && department.isEmpty())) {
//                session.getTransaction().begin();
//                Query query = session.createQuery("from Department where name = :name ");
//                query.setParameter("name", department);
//                List<Department> list = query.list();
//                if (list.isEmpty()) {
//                    infoLabel.setText("INFO: Not Found!");
//                    session.getTransaction().commit();
//                    return;
//                }
//                Teacher teacher = new Teacher();
//                teacher.setSurname(surname);
//                teacher.setName(name);
//                teacher.setPatronymic(patronymic);
//                teacher.setDepartment_iddepartment(list.get(0).getIdDepartment());
//                teacher.setPhoneNumber(phoneNumber);
//                session.save(teacher);
//                session.getTransaction().commit();
//                infoLabel.setText("INFO: OK!");
//            } else {
//                infoLabel.setText("ERROR: Empty input!");
//            }
//        } catch(Exception e) {
//            infoLabel.setText("ERROR! " + e.getMessage());
//            session.getTransaction().rollback();
//        }
//    }
//
//    /**
//     * Opening of the edit manager view.
//     * @param event
//     */
//    @FXML
//    protected void deleteTeacher(ActionEvent event) {
//        try {
//            String id = idField.getText();
//            if (!id.isEmpty()) {
//                int id_teacher = Integer.parseInt(id);
//                session.getTransaction().begin();
//                session.delete(session.get(Teacher.class, id_teacher));
//                session.getTransaction().commit();
//                infoLabel.setText("INFO: OK!");
//            } else {
//                infoLabel.setText("ERROR: Empty input!");
//            }
//        } catch(Exception e) {
//            infoLabel.setText("ERROR! " + e.getMessage());
//            session.getTransaction().rollback();
//        }
//    }
//
//    /**
//     * Opening of the edit manager view.
//     * @param event
//     */
//    @FXML
//    protected void editTeacher(ActionEvent event) {
//        try {
//            String surname = surnameField.getText();
//            String name = nameField.getText();
//            String patronymic = patronymicField.getText();
//            String phoneNumber = phoneNumberField.getText();
//            String department = departmentField.getText();
//            String id = idField.getText();
//            if (!(name.isEmpty() && surname.isEmpty() && patronymic.isEmpty() && department.isEmpty() && id.isEmpty())) {
//                int id_teacher = Integer.parseInt(id);
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
//                Teacher teacher = new Teacher();
//                teacher.setSurname(surname);
//                teacher.setName(name);
//                teacher.setPatronymic(patronymic);
//                teacher.setDepartment_iddepartment(list.get(0).getIdDepartment());
//                teacher.setPhoneNumber(phoneNumber);
//                session.save(teacher);
//                session.getTransaction().commit();
//                infoLabel.setText("INFO: OK!");
//            } else {
//                infoLabel.setText("ERROR: Empty input!");
//            }
//        } catch(Exception e) {
//            infoLabel.setText("ERROR! " + e.getMessage());
//            session.getTransaction().rollback();
//        }
//    }
//
//    /**
//     * Opening of the edit manager view.
//     * @param event
//     */
//    @FXML
//    protected void showDepartments(ActionEvent event) {
//        session.getTransaction().begin();
//        Query query = session.createQuery("from Department");
//        List<Department> list = query.list();
//        StringBuilder result = new StringBuilder("Department: ");
//        for(Department department : list)
//        {
//            result.append(department.getIdDepartment() + "  ");
//        }
//        JOptionPane.showMessageDialog(null, result, "About", JOptionPane.INFORMATION_MESSAGE);
//    }
//
//    /**
//     * Opening of the edit manager view.
//     * @param event
//     */
//    @FXML
//    protected void showTeachers(ActionEvent event) {
//        session.getTransaction().begin();
//        Query query = session.createQuery("from Teacher");
//        List<Teacher> list = query.list();
//        StringBuilder result = new StringBuilder("Teacher: ");
//        for(Teacher teacher : list)
//        {
//            result.append(teacher.getIdTeacher() + "  ");
//        }
//        JOptionPane.showMessageDialog(null, result, "About", JOptionPane.INFORMATION_MESSAGE);
//    }
//
//    /**
//     * Opening of the print manager view.
//     * @param event
//     */
//    @FXML
//    protected void goBack(ActionEvent event) {
//        GuiLauncher.getNavigation().load(MenuController.URL_FXML).show();
//    }
//}
