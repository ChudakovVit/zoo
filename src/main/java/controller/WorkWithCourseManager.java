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
//import org.hibernate.Session;
//import org.hibernate.query.Query;
//
//import java.util.List;
//
//public class WorkWithCourseManager extends ViewController {
//    /** URL of the main menu manager view FXML file. */
//    public static String URL_FXML = "/fxml/WorkWithCourseManager.fxml";
//
//    private Session session = HibernateUtil.getSessionFactory().openSession();
//
//    @FXML
//    TextField nameField;
//    @FXML
//    TextField newNameField;
//    @FXML
//    Label infoLabel;
//
//    /**
//     * Opening of the edit manager view.
//     * @param event
//     */
//    @FXML
//    protected void addCourse(ActionEvent event) {
//        try {
//            String name = nameField.getText();
//            if (!name.isEmpty()) {
//                session.getTransaction().begin();
//                Course course = new Course();
//                course.setName(name);
//                session.save(course);
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
//    protected void deleteCourse(ActionEvent event) {
//        try {
//            String name = nameField.getText();
//            if (!name.isEmpty()) {
//                session.getTransaction().begin();
//                Course course = new Course();
//                course.setName(name);
//                session.delete(course);
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
//    protected void editCourse(ActionEvent event) {
//        try {
//            String name = nameField.getText();
//            String newName = newNameField.getText();
//            if (!(name.isEmpty() && newName.isEmpty())) {
//                session.getTransaction().begin();
//                Query query = session.createQuery("from Course where name = :name ");
//                query.setParameter("name", name);
//                List<Course> list = query.list();
//                if (list.isEmpty()) {
//                    infoLabel.setText("ERROR: Not Found!");
//                    session.getTransaction().commit();
//                    return;
//                }
//                Course course = new Course();
//                course.setIdCourse(list.get(0).getIdCourse());
//                course.setName(newName);
//                session.evict(list.get(0));
//                session.update(course);
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
//     * Opening of the print manager view.
//     * @param event
//     */
//    @FXML
//    protected void goBack(ActionEvent event) {
//        GuiLauncher.getNavigation().load(MenuController.URL_FXML).show();
//        session.close();
//    }
//}
