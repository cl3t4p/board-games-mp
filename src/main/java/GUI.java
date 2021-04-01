<<<<<<< HEAD
import javafx.application.Application;
import javafx.stage.Stage;

public class GUI extends Application{

    @Override
    public void start(Stage stage) throws Exception {
        public void start(Stage primaryStage){

            Group root = new Group();
            Scene scene = new Scene(root, 300, 120, Color.GREEN);

            primaryStage.setTitle("BoardGames");
            primaryStage.setScene(scene);
            primaryStage.show();
        }

        public static void main(String[] args){
          launch(args);

        }
    }
=======
public class GUI {
    int X;
>>>>>>> dev
}
