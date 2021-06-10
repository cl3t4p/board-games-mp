import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class BattelShipGUI extends Application {

    public static void startAPP(String[] args) {
        launch(args);
    }

    public Button[][] btn = new Button[20][10];

    protected boolean wasShot;
    protected boolean schipOnPos;

    public boolean isHit() {
        return wasShot;
    }

    public boolean isShip() {
        return schipOnPos;
    }

    public void placeShip(Button button) {
        schipOnPos = true;
    }

    public void hitShip() {
        schipOnPos = false;
    }

    public boolean Shoot(Button btn) {
        wasShot = true;
        if (schipOnPos) {
            hitShip();
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void start(Stage primaryStage) {

        primaryStage.setTitle("BattleShip");

        Label info = new Label();
        Label info2 = new Label();


        for(int j=0;j<10;j++){
            for(int i=0;i<10;i++) {
                String buttonName = "(" + i + j + ")" ;
                btn[i][j] = new Button(buttonName);
                btn[i][j].setPrefSize(50, 50);


                wasShot = false;
                schipOnPos = false;

                int finalI = i;
                int finalJ = j;
                btn[i][j].setOnAction(new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent e) {
                        if(Shoot(btn[finalI][finalJ])) {
                            info.setText("Treffer");
                        } else {
                            info.setText("Daneben");
                        }

                        btn[finalI][finalJ].setDisable(true);// = false;
                    }
                });
            }
        }

        for(int j=0;j<10;j++){
            for(int i=10;i<20;i++) {
                String btnName = "(" + i + j + ")" ;
                btn[i][j] = new Button(btnName);
                btn[i][j].setPrefSize(50, 50);

                wasShot = false;
                schipOnPos = false;

                int finalI = i;
                int finalJ = j;
                btn[i][j].setOnAction(new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent e) {
                        placeShip(btn[finalI][finalJ]);
                        btn[finalI][finalJ].setDisable(true);// = false;
                    }
                });
            }
        }

        Button exit = new Button("Exit");
        exit.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                System.exit(0);
            }
        });


        GridPane board1 = new GridPane();
        for(int i=0;i<10;i++){
            for (int j = 0; j < 10; j++) {
                board1.add(btn[i][j], i, j);
            }
        }

        GridPane board2 = new GridPane();
        for(int i=10;i<20;i++){
            for (int j = 0; j < 10; j++) {
                board2.add(btn[i][j], i, j);
            }

        }

        board1.add(info, 10,2);
        board1.setLayoutX(20);

        board2.add(exit, 20,1);
        board2.add(info2, 100, 2);
        board2.setLayoutX(600);

        Group root = new Group(board1, board2);
        Scene scene = new Scene(root, 1500, 1080);
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
