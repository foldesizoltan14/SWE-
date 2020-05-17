package controller;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import task.FTodoTask;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class previousTasksController {
    private int userIdP;
    task.taskDao taskDao = task.taskDao.getInstance();
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button backButton;

    @FXML
    private TableView<FTodoTask> previousTable;

    @FXML
    private TableColumn<FTodoTask, String> name;

    @FXML
    private TableColumn<FTodoTask, String> description;

    @FXML
    private TableColumn<FTodoTask, Date> created;



    public void getIdTasksC(int userIdP){
        this.userIdP = userIdP;}

    @FXML
    void initialize() {
        Platform.runLater(() -> {
            ;


            //String s = String.valueOf(userId);
            //System.out.println(userId);

            // List<FTodoTask> tasks = taskDao.findUsersTasks(s);
            //System.out.println("userId as string:"+String.valueOf(userId));


            List<FTodoTask> tasks = taskDao.findUsersPreviousTasks(String.valueOf(userIdP));

            name.setCellValueFactory(new PropertyValueFactory<>("name"));
            description.setCellValueFactory(new PropertyValueFactory<>("description"));
            created.setCellValueFactory(new PropertyValueFactory<>("created"));

            ObservableList<FTodoTask> observableList = FXCollections.observableArrayList();
            observableList.addAll(tasks);
            previousTable.setItems(observableList);


        });


    }
    @FXML
    void addTask(javafx.event.ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/Tasks.fxml"));
        Parent root = fxmlLoader.load();
        fxmlLoader.<TasksController>getController().getIdTasksC(userIdP);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();

    }
}