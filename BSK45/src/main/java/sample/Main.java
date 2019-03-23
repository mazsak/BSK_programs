package sample;

import com.google.inject.internal.util.Lists;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        LFSR cos = new LFSR();
        cos.calculate(Polynomial.read("x^3+x+1"), Lists.newArrayList(1, 0, 0));
        SSC cos1 = new SSC();
        cos1.encryptAndDecipher(Polynomial.read("x^3+x+1"), Lists.newArrayList(1, 0, 0), Lists.newArrayList(1, 0, 0, 0));
        cos1.encryptAndDecipher(Polynomial.read("x^3+x+1"), Lists.newArrayList(1, 0, 0), Lists.newArrayList(0, 1, 0, 1));
        CA cos2 = new CA();
        cos2.encrypt(Polynomial.read("x^3+x+1"), Lists.newArrayList(1, 0, 0), Lists.newArrayList(1, 0, 0, 0));
        Parent root = FXMLLoader.load(getClass().getResource("/sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
