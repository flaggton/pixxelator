package io.flaggton.pixxelator.views;

import io.flaggton.pixxelator.models.PixelDrawingPane;
import io.flaggton.pixxelator.models.StandardDrawingPane;
import io.flaggton.pixxelator.services.JfxUiService;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
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
    @SuppressWarnings("unused")
    @Autowired
    private JfxUiService jfxUiService;
    @FXML
    private BorderPane borderPane;
    @FXML
    private RadioButton unsetRadioButton;
    @FXML
    private RadioButton pencilRadioButton;
    @FXML
    private RadioButton bucketRadioButton;
    @FXML
    private ColorPicker colorpicker;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        borderPane.setCenter(new StandardDrawingPane(400, 400));
        ToggleGroup drawingActions = new ToggleGroup();
        unsetRadioButton.setToggleGroup(drawingActions);
        pencilRadioButton.setToggleGroup(drawingActions);
        bucketRadioButton.setToggleGroup(drawingActions);
        drawingActions.selectToggle(unsetRadioButton);
    }

    public void onExitMenuItemClick() {
        Platform.exit();
        System.exit(0);
    }

    public void onNewStandardCanvasButtonClick() throws IOException {
        jfxUiService.createAndShowFxmlDialog("New Canvas", true, false,
                getClass().getResource("/io/flaggton/pixxelator/views/canvas-creation.fxml"),
                null,
                c -> ((CanvasCreationController) c).init(zoomableScrollPane -> {
                    // machIrgendwas von Consumer wird hier gemacht
                    borderPane.setCenter(zoomableScrollPane);
                }));
    }

    public void onNewPixelCanvasButtonClick() {
        borderPane.setCenter(new PixelDrawingPane(5, 10));
    }
}

