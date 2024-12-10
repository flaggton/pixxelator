package io.flaggton.pixxelator.views;

import com.wedasoft.wedasoftFxCustomNodes.zoomableScrollPane.ZoomableScrollPane;
import com.wedasoft.wedasoftFxGuiCommons.jfxDialogs.JfxDialogUtil;
import io.flaggton.pixxelator.enums.DrawingMode;
import io.flaggton.pixxelator.models.DrawingPaneBase;
import io.flaggton.pixxelator.models.PixelDrawingPane;
import io.flaggton.pixxelator.services.HelperService;
import io.flaggton.pixxelator.services.JfxUiService;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
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
    private VBox radioButtonsVBox;
    @FXML
    private ColorPicker primaryColorPicker;
    @FXML
    private ColorPicker secondaryColorPicker;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setDrawingPaneIntoBorderPaneCenterAndConfigureRadioButtons(new PixelDrawingPane(64, 64, Color.WHITE));

        primaryColorPicker.setOnAction(actionEvent -> getCurrentDrawingPane().setPrimaryColor(primaryColorPicker.getValue()));

        secondaryColorPicker.setOnAction(actionEvent -> getCurrentDrawingPane().setSecondaryColor(secondaryColorPicker.getValue()));

        Platform.runLater(() -> {
            borderPane.getScene().addEventFilter(MouseEvent.DRAG_DETECTED, e -> borderPane.getScene().startFullDrag()); // drag over tile with starting dragging from outside
        });
    }

    private void setDrawingPaneIntoBorderPaneCenterAndConfigureRadioButtons(DrawingPaneBase drawingPane) {
        borderPane.setCenter(drawingPane);

        ToggleGroup toggleGroup = new ToggleGroup();
        radioButtonsVBox.getChildren().clear();
        for (DrawingMode drawingMode : drawingPane.getAvailableDrawingModes()) {
            RadioButton radioButton = new RadioButton(drawingMode.getUiText());
            radioButton.setToggleGroup(toggleGroup);
            radioButton.setOnAction(actionEvent -> onDrawingModeSelected(drawingMode));
            radioButtonsVBox.getChildren().add(radioButton);
        }
        toggleGroup.selectToggle(toggleGroup.getToggles().get(0));
    }

    public void onExitMenuItemClick() {
        Platform.exit();
        System.exit(0);
    }

    public void onNewDrawingPaneButtonClick() throws IOException {
        jfxUiService.createAndShowFxmlDialog("New drawing pane", true, false,
                getClass().getResource("/io/flaggton/pixxelator/views/create-new-drawing-pane.fxml"),
                null,
                c -> ((CreateNewDrawingPaneController) c).init(drawingPaneBase -> {
                    Color selectedColor = getCurrentDrawingPane().getPrimaryColor();
                    DrawingMode drawingMode = getCurrentDrawingPane().getDrawingMode();
                    setDrawingPaneIntoBorderPaneCenterAndConfigureRadioButtons(drawingPaneBase);
                    getCurrentDrawingPane().setPrimaryColor(selectedColor);
                    getCurrentDrawingPane().setDrawingMode(drawingMode);
                }));
    }

    private void onDrawingModeSelected(DrawingMode selectedDrawingMode) {
        getCurrentDrawingPane().setDrawingMode(selectedDrawingMode);
    }

    private DrawingPaneBase getCurrentDrawingPane() {
        return (DrawingPaneBase) borderPane.getCenter();
    }

    public void onSaveAsButtonClick() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save image");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PNG Files", "*.png"));
        File file = fileChooser.showSaveDialog(null);

        if (file != null) {
            try {
                ZoomableScrollPane drawPane = (ZoomableScrollPane) borderPane.getCenter();
                drawPane.resetZoom();
                WritableImage writableImage = HelperService.createWritableImageFromPane((Pane) drawPane.getContentNode());
                BufferedImage bufferedImage = HelperService.createBufferedImageFromWritableImage(writableImage);
                ImageIO.write(bufferedImage, "png", file);
            } catch (Exception e) {
                JfxDialogUtil.createErrorDialog("Image couldn't be saved.", e).showAndWait();
            }
        }
    }
}
// IMPLEMENTIERE Fill all !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
