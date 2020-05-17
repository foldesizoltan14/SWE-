package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import user.FTodoUser;
import user.userDao;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class mainScreenController {

    private userDao userDao;
    private FTodoUser user2;
    private FTodoUser usernew;
    private int a;
    private int b;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField nameText;

    @FXML
    private Button enterButton;


    @FXML
    void initialize() {

        userDao=userDao.getInstance();


    }


    private FTodoUser getUser(){
        FTodoUser user = FTodoUser.builder().name(nameText.getText()).build();
        return user;
    }


    public void enterUser(javafx.event.ActionEvent actionEvent) throws IOException {
        if(nameText.getText().isEmpty()){
            System.out.println("error");
        }

if (userDao.findUser(nameText.getText())!=null && userDao.findUserId(nameText.getText())!=null){
    System.out.println("This works");
    user2=(userDao.findUserId(nameText.getText()));
     a = user2.getUserId();

    System.out.println("A:"+a);
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/Tasks.fxml"));
    Parent root = fxmlLoader.load();
    fxmlLoader.<TasksController>getController().getIdTasksC(a);

    Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
    stage.setScene(new Scene(root));
    stage.show();
}

 if ((userDao.findUser(nameText.getText())!=null && userDao.findUserId(nameText.getText())==null)){


    userDao.persist(getUser());

            usernew=(userDao.findUserId(nameText.getText()));
            b = usernew.getUserId();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/Tasks.fxml"));
            Parent root = fxmlLoader.load();
            fxmlLoader.<TasksController>getController().getIdTasksC(b);
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        }
    }
}
