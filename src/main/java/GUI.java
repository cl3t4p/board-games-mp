
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class GUI extends Application{


    public static void startAPP(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        Line destra = new Line(450, 400, 450, 0);
        Line sinsitra = new Line(150, 400, 150, 0);
        Line ceiling= new Line(10, 400, 10, 10);
        Line bottom= new Line(20, 10, 10, 10);

        Group root = new Group(destra, sinsitra, ceiling, bottom);
        Scene scene = new Scene(root, 600, 500);

        primaryStage.setTitle("BoardGames");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
