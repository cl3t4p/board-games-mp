
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class TicTacToGUI extends Application{


    public static void startAPP(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        Button[] btn = new Button [9];

        for(int i=0;i<9;i++) {
            String buttonName = "btn" + (i + 1);
            btn[i] = new Button(buttonName);
            btn[i].setPrefSize(80, 80);
            int finalI = i;
            btn[i].setOnAction(new EventHandler<ActionEvent>() {
                public void handle(ActionEvent e) {
                    button_click(btn[finalI]);
                }
            });
        }

        HBox firstRow = new HBox();
        firstRow.setSpacing(120);
        firstRow.getChildren().addAll(btn[0], btn[1], btn[2]);

        HBox secondRow = new HBox();
        secondRow.setSpacing(120);
        secondRow.getChildren().addAll(btn[3], btn[4], btn[5]);

        HBox thirdRow = new HBox();
        thirdRow.setSpacing(120);
        thirdRow.getChildren().addAll(btn[6], btn[7], btn[8]);

        VBox board = new VBox();
        board.setSpacing(10);
        board.setPadding(new Insets(10));


        board.getChildren().addAll(ersteZeile, zweiteZeile, dritteZeile);
        BorderPane background = new BorderPane();
        background.setPadding(new Insets(10));

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
