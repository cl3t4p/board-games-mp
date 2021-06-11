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

    public Button[][] btn = new Button[10][10];
    public Button[][] rbtn = new Button[10][10];

    public Random zufallszahl = new Random();

    protected int[] ShipsPos = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
    public int[] MyShipPos = new int[100];

    protected boolean wasShot;
    protected boolean shipOnPos;

    public int suchePosition(int[] feld) {
        int pos = -1;
        for (int i = 0; i < feld.length; i++) {
            if (feld[i] == -1) {
                pos = i;
                break;
            }

        }
        return pos;
    }

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

     public int[] placeShip(int[] feld, int pos, int x, int y) {
        feld[pos] = 10 * x + y;
        return feld;
    }

    public boolean Shoot(int[] feld, int x, int y) {
        boolean shoot = false;
        for (int k = 0; k < feld.length; k++) {
            if (feld[k] == 10 * x + y) {
                shoot = true;
            }
        }
        return shoot;
    }

    public boolean ComputerShoot(int[] feld1, int[] feld2) {
        boolean shoot = false;
        for (int k = 0; k < 11; k++) {
            if (feld1[k] == feld2[k]) {
                shoot = true;
            }
        }
        return shoot;
    }


        public boolean isNumberAvailable ( int[] feld, int zahl){
            boolean isAvailable = false;
            for (int j = 0; j < feld.length; j++) {
                if (feld[j] == zahl) isAvailable = true;
            }
            return isAvailable;
        }

    @Override
    public void start(Stage primaryStage) {

        Label info1 = new Label();
            Label info2 = new Label("You can place 10 Ships");
            Label Titel1 = new Label("Enemy");
            Label Titel2 = new Label("You");

            Titel1.setStyle("-fx-font-size: 50; -fx-translate-x: 140");
            Titel2.setStyle("-fx-font-size: 50; -fx-translate-x: 150");

            int[] AIsetShips = new int[10];

            int t = 0;

            do {
                int neueZahl = zufallszahl.nextInt(100);
                if (!isNumberAvailable(AIsetShips, neueZahl)) {
                    AIsetShips[t] = neueZahl;
                    t++;
                }

            } while (t < 10);

            int[] AIShoot = new int[50];

            do {
                int neueZahl = zufallszahl.nextInt(101) + 100;
                if (!isNumberAvailable(AIShoot, neueZahl)) {
                    AIShoot[t] = neueZahl;
                    t++;
                }

            } while (t < 50);

            for (int j = 0; j < 10; j++) {
                for (int i = 0; i < 10; i++) {

                    String buttonName = "(" + i + j + ")";
                    btn[i][j] = new Button(buttonName);
                    btn[i][j].setPrefSize(40, 40);
                    btn[i][j].setStyle("-fx-text-fill: rgba(0,0,0,0);");

                    wasShot = false;

                    int posI = i;
                    int posJ = j;

                    btn[i][j].setOnAction(new EventHandler<ActionEvent>() {
                        public void handle(ActionEvent e) {
                            if (Shoot(AIsetShips, posI, posJ)) {
                                info1.setText("Hit");
                                btn[posI][posJ].setStyle("-fx-background-color: red; -fx-text-fill: rgba(0,0,0,0)");
                            } else {
                                info1.setText("Miss");
                                btn[posI][posJ].setStyle("-fx-background-color: blue; -fx-text-fill: rgba(0,0,0,0)");
                            }
                            btn[posI][posJ].setDisable(true);// = false;
                            ComputerShoot(AIShoot,MyShipPos);
                        }
                    });
                }
            }

            for (int k = 0; k < 10; k++) {
                for (int p = 0; p < 10; p++) {
                    String butnName = "(" + (p + 10) + k + ")";
                    rbtn[k][p] = new Button(butnName);
                    rbtn[k][p].setPrefSize(40, 40);
                    rbtn[k][p].setStyle("-fx-text-fill: rgba(0,0,0,0);");

                    wasShot = false;
                    shipOnPos = false;

                    int posK = k;
                    int posP = p;

                    rbtn[k][p].setOnAction(new EventHandler<ActionEvent>() {
                        public void handle(ActionEvent e) {
                            int z = suchePosition(ShipsPos);
                            int ms = 0;
                            MyShipPos[ms]= posP*10;
                            MyShipPos[ms]= ms +posK;
                            if (z != -1) {
                                ShipsPos = placeShip(ShipsPos, z, posK, posP);
                                rbtn[posK][posP].setStyle("-fx-background-color: black; -fx-text-fill: rgba(0,0,0,0)");
                                rbtn[posK][posP].setDisable(true);
                            } else {
                                info2.setText("all Ships placed");
                            }
                        }
                    });
                }
            }

            GridPane leftboard = new GridPane();
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    leftboard.add(btn[i][j], i, j);
                }
            }

            GridPane rightboard = new GridPane();
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    rightboard.add(rbtn[i][j], i, j);
                }
            }

            GridPane board = new GridPane();
            board.add(leftboard, 1, 2);
            board.add(rightboard, 2, 2);
            board.add(Titel1, 1, 1);
            board.add(Titel2, 2, 1);
            board.setLayoutX(20);
            board.setLayoutY(10);
            board.setHgap(45);

            GridPane gpinfo = new GridPane();
            gpinfo.add(info1, 1, 1);
            gpinfo.add(info2, 2, 1);

            gpinfo.setStyle("-fx-font-size: 20; -fx-translate-x: 400; -fx-translate-y: 550");
            gpinfo.setHgap(50);

            Group root = new Group(board, gpinfo);
            Scene scene = new Scene(root, 1000, 700);

            primaryStage.setTitle("BattleShip");
            primaryStage.setScene(scene);
            primaryStage.show();
        }
