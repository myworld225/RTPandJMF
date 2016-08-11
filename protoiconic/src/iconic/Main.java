package iconic;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    private static double xOffset = 0;
    private static double yOffset = 0;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
//        primaryStage.setTitle("Hello World");
//        root.setOnMousePressed(event -> {
//            xOffset = primaryStage.getX() - event.getScreenX();
//            yOffset = primaryStage.getY() - event.getScreenY();
//        });
//        root.setOnMouseDragged(event -> {
//            primaryStage.setX(event.getSceneX() + xOffset);
//            primaryStage.setY(event.getSceneY() + yOffset);
//        });

        primaryStage.initStyle(StageStyle.DECORATED);//원래 없는것!
        primaryStage.setScene(new Scene(root, 362, 594));
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
