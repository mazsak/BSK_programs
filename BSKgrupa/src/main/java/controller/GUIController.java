package controller;

import bskgrupa.CaesarCipher;
import bskgrupa.MatrixChanges;
import bskgrupa.RailFence;
import bskgrupa.Vigenere;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class GUIController implements Initializable {

    private ObservableList<String> listCoding = FXCollections.observableArrayList();

    @FXML
    private ChoiceBox<String> chosenCiphers;

    @FXML
    private Label resultText;

    @FXML
    private Label keyLabel;

    @FXML
    private TextField keyText;

    @FXML
    private TextField key2Text;

    @FXML
    private CheckBox chosenEcryption;

    @FXML
    private TextArea wordText;

    @FXML
    void codingActionListener(ActionEvent event) {
        if (!chosenCiphers.getSelectionModel().isEmpty() && !wordText.getText().isEmpty() && !keyText.getText().isEmpty()) {
            switch (chosenCiphers.getSelectionModel().getSelectedIndex()) {
                case 0:
                    RailFence rf = new RailFence();
                    if (chosenEcryption.isSelected()) {
                        resultText.setText(rf.decipher(wordText.getText(), Integer.parseInt(keyText.getText())));
                    } else {
                        resultText.setText(rf.encrypt(wordText.getText(), Integer.parseInt(keyText.getText())));
                    }
                    break;
                case 1:
                    MatrixChanges mcA = new MatrixChanges();
                    if (chosenEcryption.isSelected()) {
                        resultText.setText(mcA.decipherA(wordText.getText(), keyText.getText()));
                    } else {
                        resultText.setText(mcA.encryptA(wordText.getText(), keyText.getText()));
                    }
                    break;
                case 2:
                    MatrixChanges mcB = new MatrixChanges();
                    if (chosenEcryption.isSelected()) {
                        resultText.setText(mcB.decipherB(wordText.getText(), keyText.getText()));
                    } else {
                        resultText.setText(mcB.encryptB(wordText.getText(), keyText.getText()));
                    }
                    break;
                case 3:

                    MatrixChanges mcC = new MatrixChanges();
                    if (chosenEcryption.isSelected()) {
                        resultText.setText(mcC.decryptC(wordText.getText(), keyText.getText()));
                    } else {
                        resultText.setText(mcC.encryptC(wordText.getText(), keyText.getText()));
                    }
                    break;
                case 4:

                    CaesarCipher ccA = new CaesarCipher();
                    if (chosenEcryption.isSelected()) {
                        resultText.setText(ccA.decryptA(wordText.getText(), Integer.parseInt(keyText.getText())));
                    } else {
                        resultText.setText(ccA.encryptA(wordText.getText(), Integer.parseInt(keyText.getText())));
                    }
                    break;
                case 5:
                    CaesarCipher ccB = new CaesarCipher();
                    if (chosenEcryption.isSelected()) {
                        resultText.setText(ccB.decryptB(wordText.getText(), Integer.parseInt(keyText.getText()), Integer.parseInt(key2Text.getText())));
                    } else {
                        resultText.setText(ccB.encryptB(wordText.getText(), Integer.parseInt(keyText.getText()), Integer.parseInt(key2Text.getText())));
                    }
                    break;
                case 6:
                    Vigenere v = new Vigenere();
                    if (chosenEcryption.isSelected()) {
                        resultText.setText(v.decipher(wordText.getText(), keyText.getText()));
                    } else {
                        resultText.setText(v.encrypt(wordText.getText(), keyText.getText()));
                    }
                    break;
            }
        }
    }

    @FXML
    void preparationActionListener(KeyEvent event) {
        if (chosenCiphers.getSelectionModel().getSelectedIndex() == 5) {
            keyLabel.setVisible(true);
            key2Text.setVisible(true);
        } else {
            keyLabel.setVisible(false);
            key2Text.setVisible(false);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listCoding.add("Rail Fence");
        listCoding.add("Matrix Changes A");
        listCoding.add("Matrix Changes B");
        listCoding.add("Matrix Changes C");
        listCoding.add("Caesar Cipher A");
        listCoding.add("Caesar Cipher B");
        listCoding.add("Vigenere");

        chosenCiphers.setItems(listCoding);
    }
}

