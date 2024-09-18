package io.flaggton.pixxelator.views;

import io.flaggton.pixxelator.services.JfxUiService;
import javafx.application.Platform;
import javafx.fxml.Initializable;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@RequiredArgsConstructor
public class MainController implements Initializable {

    private final JfxUiService jfxUiService;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void onExitMenuItemClick() {
        Platform.exit();
        System.exit(0);
    }
}
