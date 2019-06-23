package main;

import commons.ViewController;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import util.UTF8Control;

/**
 * This class represents abstract fxml view, which is responsible for pre-loading of the view.
 */
public abstract class AbstractFxmlView implements ApplicationContextAware {

    private static final String LANGUAGE_FILE = "uiresources";
    private static final String LANGUAGE = "mr";
    private ObjectProperty<ViewController> presenterProperty;
    private FXMLLoader fxmlLoader;
    private ResourceBundle bundle;
    private URL resource;
    private ApplicationContext applicationContext;

    public AbstractFxmlView() {
        this.presenterProperty = new SimpleObjectProperty<>();
        this.resource = getClass().getResource(getConventionalName());
        this.bundle = getResourceBundle(LANGUAGE_FILE);
    }

    @PostConstruct
    public void loadViewInAdvance() {
        this.fxmlLoader = loadSynchronously(resource, bundle);
        this.presenterProperty.set(this.fxmlLoader.getController());
        addCssIfAvailable(this.fxmlLoader.getRoot());
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        if (this.applicationContext != null) {
            return;
        }
        this.applicationContext = applicationContext;
    }

    /**
     * Initializes the view by loading the FXML (if not happened yet) and returns the top Node (parent) specified in the
     * FXML file.
     *
     * @return root view.
     */
    public Parent getView() {
        return fxmlLoader.getRoot();
    }

    /**
     * Initializes the view synchronously and invokes the consumer with the created parent Node within the FX UI thread.
     *
     * @param consumer - an object interested in received the {@link Parent} as callback
     */
    public void getView(Consumer<Parent> consumer) {
        CompletableFuture.supplyAsync(this::getView, Platform::runLater).thenAccept(consumer);
    }

    /**
     * Scene Builder creates for each FXML document a root container. This method omits the root container (e.g.
     * {@link AnchorPane}) and gives you the access to its first child.
     *
     * @return the first child of the {@link AnchorPane}
     */
    public Node getViewWithoutRootContainer() {

        ObservableList<Node> children = getView().getChildrenUnmodifiable();
        if (children.isEmpty()) {
            return null;
        }

        return children.listIterator().next();
    }

    /**
     * Child class must implement this method providing style sheet name.
     *
     * @return style sheet name.
     */
    public abstract String getStyleSheetName();

    /**
     * Returns the presenter.
     *
     * @return presenter
     */
    public ViewController getPresenter() {
        return this.presenterProperty.get();
    }

    /**
     * Does not initialize the view. Only registers the Consumer and waits until the the view is going to be created / the
     * method FXMLView#getView or FXMLView#getViewAsync invoked.
     *
     * @param presenterConsumer listener for the presenter construction
     */
    public void getPresenter(Consumer<Object> presenterConsumer) {
        this.presenterProperty.addListener(
                (ObservableValue<? extends Object> o, Object oldValue, Object newValue)
                        -> presenterConsumer.accept(newValue)
        );
    }

    private FXMLLoader loadSynchronously(URL resource, ResourceBundle bundle) {

        FXMLLoader loader = new FXMLLoader(resource, bundle);
        loader.setControllerFactory(this::createControllerForType);

        try {
            loader.load();
        } catch (IOException ex) {
            throw new IllegalStateException("Cannot load " + getConventionalName(), ex);
        }

        return loader;
    }

    private void addCssIfAvailable(Parent parent) {

        URL uri = getClass().getResource(getStyleSheetName());
        if (uri == null) {
            return;
        }

        String uriToCss = uri.toExternalForm();
        parent.getStylesheets().add(uriToCss);
    }

    private Object createControllerForType(Class<?> type) {
        return this.applicationContext.getBean(type);
    }

    private String getConventionalName() {
        return stripEnding(getClass().getSimpleName().toLowerCase()) + ".fxml";
    }

    private String stripEnding(String clazz) {

        if (!clazz.endsWith("view")) {
            return clazz;
        }

        return clazz.substring(0, clazz.lastIndexOf("view"));
    }

    private ResourceBundle getResourceBundle(String name) {
        try {
            return ResourceBundle.getBundle(name, new Locale(LANGUAGE), new UTF8Control());
        } catch (MissingResourceException ex) {
            return null;
        }
    }
}
