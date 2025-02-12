package lk.ijse.gdse.professionalsoftwareproject.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;


public class LoginController {

    //lk.ijse.gdse.professionalsoftwareproject.controller.LoginController

    @FXML
    private Button btnLogin;

    @FXML
    private AnchorPane loginContent;

    @FXML
    private TextField lblUserName;

    @FXML
    private PasswordField passLogin;



    @FXML
    void loginOnAction(ActionEvent event) throws IOException {

            String userName = lblUserName.getText();
            String password = passLogin.getText();

            String userNameField = "admin";
            String passwordField = "password";

            if (userNameField.equals(userName) && passwordField.equals(password)) {

                Stage stage1 = (Stage) loginContent.getScene().getWindow();

                Stage stage = new Stage();

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/MainLayout.fxml"));

                //Parent root = loader.load();

                Scene scene = new Scene(loader.load(), 960, 720);
                stage.setTitle("Main Layout!");
                stage.setScene(scene);
                stage.show();

                stage1.close();

            } else {
                new Alert(Alert.AlertType.ERROR, "Invalid Username or Password").show();
            }



        }


    }



