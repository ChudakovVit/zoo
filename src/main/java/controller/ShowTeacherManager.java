//package controller;
//
//import app.HibernateUtil;
//import gui.GuiLauncher;
//import gui.ViewController;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.scene.control.Label;
//import javafx.scene.control.TableColumn;
//import javafx.scene.control.TableView;
//import javafx.scene.control.TextField;
//import javafx.scene.control.cell.PropertyValueFactory;
//import model.Administrator;
//import model.Teacher;
//import model.TeacherAndCoursesLink;
//import model.TeacherHasCourse;
//import org.hibernate.Session;
//import org.hibernate.query.Query;
//
//import java.lang.reflect.Array;
//import java.util.ArrayList;
//import java.util.List;
//
//public class ShowTeacherManager extends ViewController {
//    /** URL of the main menu manager view FXML file. */
//    public static String URL_FXML = "/fxml/ShowTeacherManager.fxml";
//
//    private Session session = HibernateUtil.getSessionFactory().openSession();
//
//    @FXML
//    TextField inputField;
//    @FXML
//    Label infoLabel;
//    @FXML
//    TableColumn surnameColumn;
//    @FXML
//    TableColumn nameColumn;
//    @FXML
//    TableColumn patronymicColumn;
//    @FXML
//    TableColumn coursesColumn;
//    @FXML
//    TableView mainTable;
//
//    /**
//     * Opening of the edit manager view.
//     * @param event
//     */
//    @FXML
//    protected void search(ActionEvent event) {
//        try {
//            String facultyName = inputField.getText();
//            List<Teacher> list;
//            session.getTransaction().begin();
//            Query query = session.createQuery("from Teacher");
//            list = query.list();
//            if (!list.isEmpty()) {
//                infoLabel.setText("ERROR: Not Found!");
//            }
//            query = session.createQuery("from TeacherHasCourse");
//            List<TeacherHasCourse> list1 = query.list();
//            System.out.println(list1);
//            List<TeacherAndCoursesLink> res_list = new ArrayList<>();
//            for(Teacher teacher : list)
//            {
//                TeacherAndCoursesLink t = new TeacherAndCoursesLink();
//                t.setName(teacher.getName());
//                t.setSurname(teacher.getSurname());
//                t.setPatronymic(teacher.getPatronymic());
//                t.setCourses("");
//                for(TeacherHasCourse teacherHasCourse : list1)
//                {
//                    if (teacherHasCourse.getTeacher().getIdTeacher() == teacher.getIdTeacher()){
//                        t.setCourses(t.getCourses() + "  " + teacherHasCourse.getCourse().getName());
//                    }
//                }
//                res_list.add(t);
//            }
//            ObservableList<TeacherAndCoursesLink> data =
//                    FXCollections.observableArrayList(res_list);
//            surnameColumn.setCellValueFactory(
//                    new PropertyValueFactory<TeacherAndCoursesLink, String>("surname"));
//            nameColumn.setCellValueFactory(
//                    new PropertyValueFactory<TeacherAndCoursesLink, String>("name"));
//            patronymicColumn.setCellValueFactory(
//                    new PropertyValueFactory<TeacherAndCoursesLink, String>("patronymic"));
//            coursesColumn.setCellValueFactory(
//                    new PropertyValueFactory<TeacherAndCoursesLink, String>("courses"));
//            mainTable.setItems(data);
//            infoLabel.setText("INFO: OK!");
//            session.getTransaction().commit();
//
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
//    }
//}
