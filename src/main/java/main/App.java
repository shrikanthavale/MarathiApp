package main;

import form.FormView;
import javafx.application.Preloader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import login.LoginView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"database"}, basePackageClasses = {LoginView.class, FormView.class})
@EnableJpaRepositories(basePackages = {"database.repository"})
@EntityScan(basePackages = {"database.entity"})
public class App extends AbstractJavaFxApplicationSupport {

	/**
	 * Note that this is configured in application.properties
	 */
	@Value("${app.ui.title:Example App}")
	private String windowTitle;

	@Autowired
	private LoginView loginView;

	@Override
	public void start(Stage stage) {

		notifyPreloader(new Preloader.StateChangeNotification(Preloader.StateChangeNotification.Type.BEFORE_START));

		stage.setTitle(windowTitle);
		stage.setScene(new Scene(loginView.getView()));
		stage.setResizable(true);
		stage.centerOnScreen();
		stage.show();
		loginView.setStage(stage);
	}

	public static void main(String[] args) {
		launchApp(App.class, args);
	}

}
