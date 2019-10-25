package Book.controllers;

import Book.DBconnect1;
import Book.Main;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddBookController implements Initializable {

    @FXML
    private SplitPane root;

    @FXML
    private JFXComboBox<String> subject;
    private URL url;
    private ResourceBundle rb;

    public void initialize(URL url, ResourceBundle rb) {
        this.url = url;
        this.rb = rb;
        subject.getItems().add("Arts");
        subject.getItems().add("Science");
        subject.getItems().add("Commerce");
        subject.getItems().add("Novels");
        subject.getItems().add("Comics");
    }

    @FXML
    private JFXTextField author;

    @FXML
    private JFXTextField title;

    @FXML
    private JFXTextField edition;

    @FXML
    private JFXTextField publisher;

    @FXML
    private JFXTextField mrp;

    @FXML
    private JFXCheckBox tornpages;

    @FXML
    private JFXCheckBox missingpages;

    @FXML
    private JFXCheckBox yellowpage;

    @FXML
    private JFXCheckBox penmark;

    @FXML
    private JFXCheckBox sell;

    @FXML
    private JFXCheckBox donate;

    @FXML
    public JFXButton postadd;
    @FXML
    private ImageView hey;
    @FXML
    private JFXButton reset;
    @FXML
    private Label status;
    public void copy()
    {
        String u;
    }
    @FXML
    void onPostAdd(ActionEvent event) {
        String sub = subject.getValue().toString();
        String aut = author.getText();
        String ti = title.getText();
        String ed = edition.getText();
        String pub = publisher.getText();
        String MRP = mrp.getText();
        String msg = "";
        String purpose = "";
        if (tornpages.isSelected())
            msg += "Torn Pages\n";
        if (yellowpage.isSelected())
            msg += "Yellow Pages\n";
        if (penmark.isSelected())
            msg += "Pen/Pencil marks present\n";
        if (missingpages.isSelected())
            msg += "Pages missing";
        if (sub.isEmpty() || aut.isEmpty() || ti.isEmpty() || ed.isEmpty() || pub.isEmpty() || MRP.isEmpty())
            postadd.setDisable(true);
        if (sell.isSelected() && donate.isSelected())
            status.setText("Select either DONATE or SELL");

        else {
             if (sell.isSelected()) {
                purpose = "SELL";
                donate.setSelected(false);
            } else if (donate.isSelected()) {
                purpose = "DONATE";
                sell.setSelected(false);

            }
            try {

                String carryuser = LoginController.u;
                System.out.println(carryuser);

                String query = "INSERT INTO `ads`( `username`,`subject`, `author`, `title`, `edition`, `publisher`, `mrp`, `condi`, `purpose`) " +
                        "VALUES ('%s','%s','%s','%s','%s','%s','%s','%s','%s')";
                DBconnect1.getConnection().createStatement().executeUpdate(String.format(query,carryuser, sub, aut, ti, ed, pub, MRP, msg, purpose));
                String query2="UPDATE ads a INNER JOIN username b ON a.username=b.Username SET  a.phone=b.Phone,a.city=b.City";
                DBconnect1.getConnection().createStatement().executeUpdate(String.format(query2,carryuser));
                status.setText("AD successfully posted");

            } catch (SQLException e) {
                status.setText("Error occurred");
                e.printStackTrace();
            }
        }
    }
        @FXML
        public void onReset (ActionEvent event){
            try {
                Stage stage = Main.stage;
                Parent root = FXMLLoader.load(getClass().getResource("/Book/fxmls/Books.fxml"));
                stage.setTitle("Add Books");
                stage.setResizable(false);
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    @FXML
    private JFXButton Home;

    @FXML
    void onHome(ActionEvent event) {
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
    }
    }
