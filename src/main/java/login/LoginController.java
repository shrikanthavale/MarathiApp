package login;

import commons.ViewController;
import form.FormView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class LoginController extends ViewController {
    @Autowired
    private FormView formView;

    @Autowired
    private LoginView loginView;

    @Value("${app.ui.title:Example App}")
    private String windowTitle;

    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Label errorMessage;

    @FXML
    protected void handleSubmitButtonAction(ActionEvent event) {

        if(!("admin".equals(username.getText()) && "admin".equals(password.getText()))) {
            errorMessage.setText("Invalid username or password");
            return;
        }

        final Stage stage = getPrimaryStage();
        stage.setScene(new Scene(formView.getView()));
        stage.centerOnScreen();
        stage.show();
    }

}
