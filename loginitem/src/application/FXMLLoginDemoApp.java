package application;

import application.scene.*;

import java.io.InputStream;

import java.util.logging.Level;

import java.util.logging.Logger;

import javafx.application.Application;

import javafx.fxml.FXMLLoader;

import javafx.fxml.Initializable;

import javafx.fxml.JavaFXBuilderFactory;

import javafx.scene.Scene;

import javafx.scene.Group;

import javafx.scene.layout.AnchorPane;

import javafx.stage.Stage;

import javafx.scene.Parent;

/**
 * 
 * FXML-based Login screen sample
 * 
 */

public class FXMLLoginDemoApp extends Application {
	
	private Stage window; // for switching scenes

	private Group root = new Group();

	private User loggedUser;

	private final double MINIMUM_WINDOW_WIDTH = 390.0;

	private final double MINIMUM_WINDOW_HEIGHT = 500.0;

	public Parent createContent() {

		gotoLogin();

		return root;

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		window = primaryStage;
		
		primaryStage.setResizable(false);

		primaryStage.setScene(new Scene(createContent()));

		primaryStage.show();

	}

	public static void main(String[] args) {

		Application.launch(args);

	}

	public User getLoggedUser() {

		return loggedUser;

	}

	public boolean userLogging(String userId, String password) {

		if (Authenticator.validate(userId, password)) {

			loggedUser = User.of(userId);

			gotoProfile();
//			gotoWeb();
			
			return true;

		} else {

			return false;

		}

	}

	void userLogout() {

		loggedUser = null;

		gotoLogin();

	}

	private void gotoProfile() {

		try {

			ProfileController profile = (ProfileController) replaceSceneContent("Profile.fxml");

			profile.setApp(this);

		} catch (Exception ex) {

			Logger.getLogger(FXMLLoginDemoApp.class.getName()).log(Level.SEVERE, null, ex);

		}

	}

	private void gotoLogin() {

		try {

			LoginController login = (LoginController) replaceSceneContent("Login.fxml");

			login.setApp(this);

		} catch (Exception ex) {

			Logger.getLogger(FXMLLoginDemoApp.class.getName()).log(Level.SEVERE, null, ex);

		}

	}

	private Initializable replaceSceneContent(String fxml) throws Exception {
		//FXML 파일을 로딩하기 위한 FXMLLoader
		FXMLLoader loader = new FXMLLoader();

		InputStream in = FXMLLoginDemoApp.class.getResourceAsStream(fxml);
		
		//builderfactory도 언제 쓰이는지 모르겠다 (주석처리해도 돌아간다)
		loader.setBuilderFactory(new JavaFXBuilderFactory());

		//new FXMLLoader(FXMLLoginDemoApp.class.getResource(fxml));
		loader.setLocation(FXMLLoginDemoApp.class.getResource(fxml));

		AnchorPane page;

		try {
			//사실 여기서 위에서 선언한 inputstream 은 필요가 없다, 그 이유는 setLocation으로 
			page = (AnchorPane) loader.load(in);//fxml파일에서 루트태그로 선언된 컨테이너를 반환 따라서 AnchorPane으로 캐스팅 가능

		} finally {

			in.close();

		}

		root.getChildren().removeAll();

		root.getChildren().addAll(page);

		return (Initializable) loader.getController();

	}
	
	/**
	 * 
	 */
	private void gotoWeb(){
		WebViewApp web = new WebViewApp();

		window.setResizable(true);
		window.setScene(new Scene(web.createContent()));
		window.show();

	}
}
