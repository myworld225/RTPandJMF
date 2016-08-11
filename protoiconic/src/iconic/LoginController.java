package iconic;/**
 * Created by user on 2016-08-11.
 *
 * 로그인 페이지를 컨트롤하는 클래스 login.fxml과 쌍이다.
 *
 */

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;


public class LoginController extends StackPane implements Initializable {

    @FXML
    TextField userID;
    @FXML
    PasswordField password;
    @FXML
    Button login;
    @FXML
    Label errorMessage;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        errorMessage.setText("");
        userID.setPromptText("demo");
        password.setPromptText("demo");
    }
}
