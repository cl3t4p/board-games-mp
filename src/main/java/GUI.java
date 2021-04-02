
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

        Line destra = new Line(110, 130, 2, 130);
        Line sinsitra = new Line(50, 130, 0, 160);
        Line ceiling= new Line(10, 0, 0, 0);
        Line bottom= new Line(20, 10, 0, 0);
        
        Group root = new Group(destra, sinsitra, ceiling, bottom);
        Scene scene = new Scene(root, 500, 350, Color.BLACK);

        primaryStage.setTitle("BoardGames");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
