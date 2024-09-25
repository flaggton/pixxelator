package io.flaggton.pixxelator.views;

import io.flaggton.pixxelator.models.StandardDrawingPane;
import io.flaggton.pixxelator.services.JfxUiService;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class MainController implements Initializable {
    @Autowired
    private JfxUiService jfxUiService;
    @FXML
    private BorderPane borderPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        borderPane.setCenter(new StandardDrawingPane(400, 400));
    }

    public void onExitMenuItemClick() {
        Platform.exit();
        System.exit(0);
    }

    public void onNewCanvasButtonClick() throws IOException {
        jfxUiService.createAndShowFxmlDialog("New Canvas", true, false,
                getClass().getResource("/io/flaggton/pixxelator/views/canvas-creation.fxml"),
                null,
                c -> ((CanvasCreationController) c).init(zoomableScrollPane -> {
                    // machIrgendwas von Consumer wird hier gemacht
                    borderPane.setCenter(zoomableScrollPane);
                }));
    }


}

