package des;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/sample.fxml"));
        primaryStage.setTitle("BSK");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) throws IOException {
        KeyGenerator.loadKey();
        KeyGenerator.permutationPC1();
        KeyGenerator.generateKeys();
        Encoding.permutationIP(Encoding.loadMessage("idiot"));
        String result = Encoding.coding(false);
        Encoding.saveEncoded(result, "zacode");

        launch(args);
    }
}
