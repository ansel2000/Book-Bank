package Book.controllers;

import Book.DBconnect1;
import Book.Main;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

import static java.lang.Thread.sleep;

public class LoginController {

    public static String u;
    @FXML
    private AnchorPane root;

    @FXML
    public JFXTextField username;

    @FXML
    private JFXPasswordField password;

    @FXML
    private JFXButton signupbutton;
    @FXML
    private JFXButton loginbutton;

    @FXML
    void onlogin(ActionEvent event) {
        u = username.getText();
        System.out.println(u);
        String p = password.getText();
        if (u.isEmpty()) {
            status.setText("Username cannot be empty");
        } else if (p.isEmpty()) {
            status.setText("Password cannot be empty");
        } else {
            try {
                String query = "SELECT * FROM `username` WHERE `Username`='%s' AND `Password`='%s'";
                boolean found = DBconnect1.getConnection().createStatement().executeQuery(String.format(query, u, p)).next();
                if (found) {
                    status.setText("Logged in successfully");
                    try {
                        Stage stage = Main.stage;
                        Parent root = FXMLLoader.load(getClass().getResource("/Book/fxmls/Homie.fxml"));
                        stage.setTitle("Home");
                        stage.setResizable(false);
                        stage.setScene(new Scene(root));
                        stage.show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    status.setText("Incorrect username/password");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    ;

    @FXML
    private Label status;

    @FXML
    public void onsignUp(ActionEvent event) {
        {
            try {
                Stage stage = Main.stage;
                Parent root = FXMLLoader.load(getClass().getResource("/Book/fxmls/incomplete register.fxml"));
                stage.setTitle("Add User");
                stage.setResizable(false);
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
