package Util;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class MainGUI extends Application {


    public static void startAPP(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        Button ChooseButton = new Button("Choose game");
        Button StatsButton = new Button("Statistics");
        Button QuitButton = new Button("Quit");

        GridPane gp1 = new GridPane();

        gp1.setPadding(new Insets(80, 0, 0, 80));

        gp1.add(ChooseButton, 0, 1 );
        gp1.add(StatsButton, 1, 2);
        gp1.add(QuitButton, 0, 2);

        Group root = new Group(gp1);
        Scene scene = new Scene(root, 600, 500);

        primaryStage.setTitle("Board Games");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}