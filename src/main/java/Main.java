import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;


public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
        System.out.println("Test");
    }

    @Override
    public void start(Stage stage) {
        Label label = new Label("Hello");
        Scene scene = new Scene( label,400, 200);

        stage.setTitle("Square Color");
        stage.setScene(scene);
        stage.show();
    }
}
