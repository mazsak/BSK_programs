package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.FileNotFoundException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/sample.fxml"));
        primaryStage.setTitle("BSK");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {
        try {
            KeyGenerator.loadKey();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        KeyGenerator.permutationPC1();
        KeyGenerator.divide();

        launch(args);
    }
}
