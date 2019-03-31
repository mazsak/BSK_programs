package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import sample.Use;

import java.io.File;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class GUIController implements Initializable {

    private File fileCoding;
    private File file = null;
    private File file1 = null;

    private Stage stage;

    @FXML
    private ChoiceBox<String> orders;

    @FXML
    private Label pathFileCoding;

    @FXML
    private TextField nameFileOutput;

    @FXML
    private TextField seed;

    @FXML
    private TextField polynomial;

    @FXML
    private ChoiceBox<String> type;

    @FXML
    private Label pathFile;

    @FXML
    private Label pathFile1;

    @FXML
    private TextArea showFile;

    @FXML
    private TextArea showFile1;

    @FXML
    void saveActionListener(ActionEvent event) {
        if (file != null) {
            Use.saveFile(file, Arrays.stream(showFile.getText().split(" ")).map(e -> Integer.parseInt(e, 16)).collect(Collectors.toList()));
            file = null;
            showFile.setText("");
            pathFile.setText("Select file");
        }
    }

    @FXML
    void save1ActionListener(ActionEvent event) {
        if (file1 != null) {
            Use.saveFile(file1, Arrays.stream(showFile1.getText().split(" ")).map(e -> Integer.parseInt(e, 16)).collect(Collectors.toList()));
            file1 = null;
            showFile1.setText("");
            pathFile1.setText("Select file");
        }
    }

    @FXML
    void selectFile1ActionListener(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        file1 = fileChooser.showOpenDialog(stage);
        if (file1 != null) {
            pathFile1.setText(file1.getPath());
            showFile1.setText(Use.readFile(file1));
        }
    }

    @FXML
    void selectFileActionListener(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            pathFile.setText(file.getPath());
            showFile.setText(Use.readFile(file));
        }
    }

    @FXML
    void selectFileCodingActionListener(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileCoding = fileChooser.showOpenDialog(stage);
        if (fileCoding != null) {
            pathFileCoding.setText(fileCoding.getPath());
        }
    }

    @FXML
    void codingActionListener(ActionEvent event) {
        boolean clear = false;
        if (!pathFileCoding.getText().equals("Select file") && !nameFileOutput.getText().isEmpty()) {
            Use.setSeed(Arrays.stream(seed.getText().split(", ")).map(Integer::parseInt).collect(Collectors.toList()));
            Use.setPolynomial(polynomial.getText());
            if (orders.getSelectionModel().getSelectedItem().equals("Order 2")) {
                Use.codeFile(fileCoding, nameFileOutput.getText(), Use.ACTIVITY.SSC.ordinal());
                clear = true;
            } else {
                if (type.getSelectionModel().getSelectedItem().equals("Coding")) {
                    Use.codeFile(fileCoding, nameFileOutput.getText(), Use.ACTIVITY.CACODING.ordinal());
                    clear = true;
                } else {
                    Use.codeFile(fileCoding, nameFileOutput.getText(), Use.ACTIVITY.CADECODING.ordinal());
                    clear = true;
                }
            }
        }

        if (clear) {
            nameFileOutput.clear();
            pathFileCoding.setText("Select file");
            fileCoding = null;
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        orders.getItems().addAll("Order 2", "Order 3");
        type.getItems().addAll("Coding", "Decryption");
        orders.getSelectionModel().select(0);
        type.getSelectionModel().select(0);
    }
}
