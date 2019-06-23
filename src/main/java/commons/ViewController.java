package commons;

import javafx.stage.Stage;

import java.util.Observable;
import java.util.Observer;

public abstract class ViewController implements Observer {

    private Stage primaryStage;
    private PrimaryStageNotifier primaryStageNotifier = PrimaryStageNotifier.getPrimaryStageNotifier();

    protected ViewController() {
        primaryStageNotifier.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        primaryStage = (Stage) arg;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }
}
