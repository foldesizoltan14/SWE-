package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import task.FTodoTask;
import task.taskDao;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class addTaskController {

    private taskDao taskDao;
    private int userIdT;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField taskName;

    @FXML
    private TextField taskDescription;

    @FXML
    private Button addTaskButton;

    public void getIdTasksT(int userIdT){
        this.userIdT = userIdT;
        System.out.println(userIdT);
    }

    @FXML
    void initialize() {
        taskDao = taskDao.getInstance();


    }
    private FTodoTask getTask(){
        FTodoTask task =FTodoTask.builder().userId(String.valueOf(userIdT)).name(taskName.getText()).description(taskDescription.getText()).created(new Date()).build();
        System.out.println(userIdT);
            return task;

    }
    public void addingTask(javafx.event.ActionEvent actionEvent) throws IOException {


        taskDao.persist(getTask());
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/Tasks.fxml"));
        Parent root = fxmlLoader.load();
        fxmlLoader.<TasksController>getController().getIdTasksC(userIdT);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

}
