
package login;

import main.AbstractFxmlView;
import org.springframework.stereotype.Component;

@Component
public class LoginView extends AbstractFxmlView {

    @Override
    public String getStyleSheetName() {
        return "login.css";
    }
}
