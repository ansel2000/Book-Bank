package Book.controllers;
import Book.Main;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeController {

    @FXML
    private JFXButton sellbtn;

    @FXML
    private JFXButton buybtn;

    @FXML
    private JFXButton logoutbtn;

    @FXML
    void onBuy(ActionEvent event) {
        try {
            Stage stage = Main.stage;
            Parent root = FXMLLoader.load(getClass().getResource("/Book/fxmls/Subject.fxml"));
            stage.setTitle("Select Subject");
            stage.setResizable(false);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onLogout(ActionEvent event) {
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
    void onSell(ActionEvent event) {
        try {
            Stage stage = Main.stage;
            Parent root = FXMLLoader.load(getClass().getResource("/Book/fxmls/Books.fxml"));
            stage.setTitle("Post AD");
            stage.setResizable(false);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
