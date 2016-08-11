package iconic;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main extends Application {

    private static double xOffset = 0;
    private static double yOffset = 0;
    private User loggedUser;
    private Group root = new Group();
    private Stage window;


    @Override
    public void start(Stage primaryStage) throws Exception{
//        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
//        primaryStage.setTitle("Hello World");
//        root.setOnMousePressed(event -> {
//            xOffset = primaryStage.getX() - event.getScreenX();
//            yOffset = primaryStage.getY() - event.getScreenY();
//        });
//        root.setOnMouseDragged(event -> {
//            primaryStage.setX(event.getSceneX() + xOffset);
//            primaryStage.setY(event.getSceneY() + yOffset);

//        });

        primaryStage.initStyle(StageStyle.UNDECORATED);//원래 없는것!
        window = primaryStage;
        primaryStage.setScene(new Scene(createContent(), 362, 594));
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    public Parent createContent(){
        gotoLogin();
        return root;
    }

    public void gotoLogin(){
        try {
            LoginController login = (LoginController) replaceSceneContent("login.fxml");
            login.setApp(this);
        } catch (Exception ex){
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null ,ex);
        }
    }

    private Initializable replaceSceneContent(String fxml) throws Exception{

        FXMLLoader loader = new FXMLLoader();
        InputStream in = Main.class.getResourceAsStream(fxml);

        loader.setLocation(Main.class.getResource(fxml));
        StackPane page;

        try {
            page = (StackPane) loader.load(in);
        } finally {
            in.close();
        }
        root.getChildren().removeAll();
        root.getChildren().addAll(page);
        return (Initializable)loader.getController();
    }

    public boolean userLogging(String userId, String password){

        if(Authenticator.validate(userId, password)){
            loggedUser = User.of(userId);//db에서 유저정보 획득하여 loggedUser를 초기화 한다.

            //그리고 로그인 성공했으니 여기서 scene이 전환되는 코드가 필요하다
            System.err.println("login!!");
            return true;
        } else {
            return false;
        }
    }
}
