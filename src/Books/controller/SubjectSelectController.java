package Books.controller;

import Book.Main;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SubjectSelectController implements Initializable {

        @FXML
        private SplitPane root;
        public static String t;
        @FXML
        private JFXComboBox<String> subject;
        private URL url;
        private ResourceBundle rb;
    @FXML
    private JFXButton select;


        public void initialize(URL url, ResourceBundle rb) {
           /* this.url = url;
            this.rb = rb;*/
            subject.getItems().add("Arts");
            subject.getItems().add("Science");
            subject.getItems().add("Commerce");
            subject.getItems().add("Novels");
            subject.getItems().add("Comics");
        }
    @FXML
    void onselect(ActionEvent event) {
            t =subject.getValue();
        try {
            Stage stage = Main.stage;
            Parent root = FXMLLoader.load(getClass().getResource("/Book/fxmls/Buy1.fxml"));
            stage.setTitle("Buy");
            stage.setResizable(false);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
