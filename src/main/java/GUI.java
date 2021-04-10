
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

        Line left = new Line(400, 450, 400, 0);
        Line right = new Line(200, 450, 200, 0);
        Line top= new Line(50, 150, 550, 150);
        Line bottom= new Line(50, 300, 550, 300);

        Group root = new Group(left, right, top, bottom);
        Scene scene = new Scene(root, 600, 500);

        primaryStage.setTitle("TicTacToe");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
