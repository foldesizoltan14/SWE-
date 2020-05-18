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
import lombok.extern.slf4j.Slf4j;
import task.FTodoTask;
import task.taskDao;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;

@Slf4j
public class TasksController {

    private int userId;
    taskDao taskDao = task.taskDao.getInstance();
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button AddTaskButton;

    @FXML
    private Button previousTasks;

    @FXML
    private Button deleteButton;

    @FXML
    private TableView<FTodoTask> table;
    @FXML
    private TableColumn<FTodoTask, String> name;

    @FXML
    private TableColumn<FTodoTask, String> description;

    @FXML
    private TableColumn<FTodoTask, Date> created;


    public void getIdTasksC(int userId) {
        this.userId = userId;
    }


    @FXML
    void initialize() {
        Platform.runLater(() -> {
            List<FTodoTask> tasks = taskDao.findUsersTasks(String.valueOf(userId));

            name.setCellValueFactory(new PropertyValueFactory<>("name"));
            description.setCellValueFactory(new PropertyValueFactory<>("description"));
            created.setCellValueFactory(new PropertyValueFactory<>("created"));

            ObservableList<FTodoTask> observableList = FXCollections.observableArrayList();
            observableList.addAll(tasks);
            table.setItems(observableList);
        });


    }


    public void addTask(javafx.event.ActionEvent actionEvent) throws IOException {


        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/addTask.fxml"));
        Parent root = fxmlLoader.load();
        fxmlLoader.<addTaskController>getController().getIdTasksT(userId);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();

        log.info("Loading AddTask scene");
    }

    public void previousTask(javafx.event.ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/previousTasks.fxml"));
        Parent root = fxmlLoader.load();
        fxmlLoader.<previousTasksController>getController().getIdTasksC(userId);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
        log.info("Loading PreviousTasks scene");
    }

    public void deleteTask(javafx.event.ActionEvent actionEvent) throws IOException {
        deleteButton.setOnAction(e -> {
            FTodoTask selectedItem = table.getSelectionModel().getSelectedItem();
            table.getItems().remove(selectedItem);
            taskDao.remove(selectedItem);
            log.info("Task deleted");

        });
    }
}

