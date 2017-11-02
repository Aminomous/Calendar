package client; /**
 * Thanadon Pakawatthippoyom 5810405037
 */
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/mainProgram.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root));
//        primaryStage.setOpacity(0.8);
        primaryStage.show();
        primaryStage.setResizable(false);


    }


    public static void main(String[] args) {
        launch(args);
    }
}
