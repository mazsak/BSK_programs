package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class GUIController implements Initializable {

    @FXML
    private ChoiceBox<String> chosenCiphers;

    @FXML
    private Label resultText;

    @FXML
    private TextField keyText;

    @FXML
    private CheckBox chosenEcryption;

    @FXML
    private TextArea wordText;

    @FXML
    void codingActionListener(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}

