package Book.controllers;
import Book.Main;
import Books.controller.SubjectSelectController;
import com.jfoenix.controls.JFXButton;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.ScatterChart;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.awt.print.Book;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Observable;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import Book.DBconnect1;
import javafx.stage.Stage;

public class DisplayBooksController implements Initializable {

    @FXML
    private TableView<TableController> table;

    @FXML
    private TableColumn<TableController, String> titleCol;

    @FXML
    private TableColumn<TableController, String> authorCol;

    @FXML
    private TableColumn<TableController, String> editionCol;

    @FXML
    private TableColumn<TableController, String> pubCol;

    @FXML
    private TableColumn<TableController, String> condCol;

    @FXML
    private TableColumn<TableController, String> priceCol;

    @FXML
    private TableColumn<TableController, String> phoneCol;

    @FXML
    private TableColumn<TableController, String> cityCol;

    @FXML
    private MenuButton subject;
    @FXML
    ObservableList<TableController> oblist = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resources) {

        try {
            String hey= SubjectSelectController.t;
            Connection con = DBconnect1.getConnection();
            String query="SELECT * FROM `ads` WHERE `subject`='%s'";
            ResultSet rb = con.createStatement().executeQuery(String.format(query,hey));

            while (rb.next()) {
                oblist.add(new TableController(rb.getString("title"), rb.getString("author"), rb.getString("edition"), rb.getString("condi"),
                        rb.getString("publisher"), rb.getString("mrp"), rb.getString("city"), rb.getString("phone")));
            }

        } catch (SQLException e) {
            Logger.getLogger(TableController.class.getName()).log(Level.SEVERE,null,e);
        }
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        authorCol.setCellValueFactory(new PropertyValueFactory<>("author"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
        condCol.setCellValueFactory(new PropertyValueFactory<>("condition"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        cityCol.setCellValueFactory(new PropertyValueFactory<>("city"));
        editionCol.setCellValueFactory(new PropertyValueFactory<>("edition"));
        pubCol.setCellValueFactory(new PropertyValueFactory<>("publisher"));

        table.setItems(oblist);
    }
    @FXML
    private JFXButton returnbtn;

    @FXML
    private JFXButton logout;

    @FXML
    void onLogout(ActionEvent event) {
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

    @FXML
    void onReturntoSubject(ActionEvent event) {
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
}