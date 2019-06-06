package login;

import database.entity.User;
import form.FormView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class LoginController {
    @Autowired
    private FormView formView;

    @Autowired
    private LoginView loginView;

    @Value("${app.ui.title:Example App}")
    private String windowTitle;

    @FXML
    protected void handleSubmitButtonAction(ActionEvent event) {
        Stage stage = new Stage();
        stage.setTitle(windowTitle);
        stage.setScene(new Scene(formView.getView()));
        stage.setResizable(true);
        stage.centerOnScreen();
        loginView.getStage().close();
        stage.show();
        formView.setStage(stage);
    }
}
