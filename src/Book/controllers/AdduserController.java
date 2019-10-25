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

public class AdduserController {

    @FXML
    private AnchorPane root;

    @FXML
    private JFXTextField fname;

    @FXML
    private JFXTextField lname;

    @FXML
    private JFXTextField phone;

    @FXML
    private JFXTextField city;

    @FXML
    private JFXTextField username;

    @FXML
    private JFXPasswordField password;

    @FXML
    private JFXPasswordField cpassword;

    @FXML
    private Label status;

    @FXML
    private JFXButton adduser;

    @FXML
    private JFXButton b2login;

    @FXML
    void onreturn(ActionEvent event) {
        try {
            Stage stage = Main.stage;
            Parent root = FXMLLoader.load(getClass().getResource("/Book/fxmls/login (1).fxml"));
            stage.setTitle("Login");
            stage.setResizable(false);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onClickadduser(ActionEvent event) {
        String u = username.getText();
        String p = password.getText();
        String f = fname.getText();
        String l = lname.getText();
        String pn = phone.getText();
        String ad = city.getText();
        if (u.isEmpty()) {
            status.setText("Username cannot be empty");
        } else if (p.isEmpty()) {
            status.setText("Password cannot be empty");
        } else if (!p.equals(cpassword.getText())) {
            status.setText("Passwords not same");
        } else if (f.isEmpty() || l.isEmpty() || pn.isEmpty() || ad.isEmpty()) {
            status.setText("Please fill All Compulsory(*) fields");
        }
        else if(pn.length()!=10)
        {
            status.setText("Please enter a valid phone number");
        }
        else if(p.length()<6)
        {
            status.setText("Password should be at least 6 characters");
        }
        else {
            try{
                String query = "INSERT INTO `username` (`Firstname`, `Lastname`, `Phone`, `City`, `Username`, `Password`) VALUES ( '%s', '%s', '%s', '%s', '%s', '%s');";
                DBconnect1.getConnection().createStatement().executeUpdate(String.format(query, f, l, pn, ad, u, p));
                status.setText("Registration successful");
            } catch (SQLException e) {
                status.setText("Error occurred");
                e.printStackTrace();
            }
        }
    }

}