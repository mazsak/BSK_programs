package des;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/sample.fxml"));
        primaryStage.setTitle("BSK");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {
        KeyGenerator.loadKey();
        KeyGenerator.permutationPC1();
        KeyGenerator.generateKeys();
        Encoding.loadMessage();
        Encoding.permutationIP();

        launch(args);
    }
}
