package commons;

import javafx.stage.Stage;

import java.util.Observable;

/**
 * Primary stage notifier.
 */
public final class PrimaryStageNotifier extends Observable {

    private static PrimaryStageNotifier primaryStageNotifier;

    /**
     * Setting a singleton observable.
     *
     * @return singleton of notifier.
     */
    public static PrimaryStageNotifier getPrimaryStageNotifier() {
        if (primaryStageNotifier == null) {
            primaryStageNotifier = new PrimaryStageNotifier();
        }
        return primaryStageNotifier;
    }

    /**
     * Notify every one about primary stage.
     *
     * @param primaryStage primary stage.
     */
    public void notifyPrimaryStageChanged(final Stage primaryStage) {
        this.setChanged();
        this.notifyObservers(primaryStage);
    }
}
