package io.flaggton.pixxelator.views;

import com.wedasoft.wedasoftFxCustomNodes.zoomableScrollPane.ZoomableScrollPane;
import com.wedasoft.wedasoftFxGuiCommons.jfxDialogs.JfxDialogUtil;
import io.flaggton.pixxelator.enums.DrawingMode;
import io.flaggton.pixxelator.models.DrawingPaneActions;
import io.flaggton.pixxelator.models.PixelDrawingPane;
import io.flaggton.pixxelator.services.HelperService;
import io.flaggton.pixxelator.services.JfxUiService;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
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
    private RadioButton unsetRadioButton;
    @FXML
    private RadioButton pencilRadioButton;
    @FXML
    private RadioButton fillAllRadioButton;
    @FXML
    private RadioButton replacePixelColorRadioButton;
    @FXML
    private ColorPicker colorpicker;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        borderPane.setCenter(new PixelDrawingPane(64, 64));

        ToggleGroup drawingActions = new ToggleGroup();
        unsetRadioButton.setToggleGroup(drawingActions);
        unsetRadioButton.setOnAction(actionEvent -> onDrawingModeSelected(DrawingMode.UNSET));
        pencilRadioButton.setToggleGroup(drawingActions);
        pencilRadioButton.setOnAction(actionEvent -> onDrawingModeSelected(DrawingMode.PENCIL));
        fillAllRadioButton.setToggleGroup(drawingActions);
        fillAllRadioButton.setOnAction(actionEvent -> onDrawingModeSelected(DrawingMode.FILL_ALL));
        replacePixelColorRadioButton.setToggleGroup(drawingActions);
        replacePixelColorRadioButton.setOnAction(actionEvent -> onDrawingModeSelected(DrawingMode.REPLACE_PIXEL_COLOR));
        drawingActions.selectToggle(unsetRadioButton);

        colorpicker.setOnAction(actionEvent -> onColorSelected(colorpicker.getValue()));

        Platform.runLater(() -> {
            borderPane.getScene().addEventFilter(MouseEvent.DRAG_DETECTED, e -> borderPane.getScene().startFullDrag()); // drag over tile with starting dragging from outside
        });
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

    private void onDrawingModeSelected(DrawingMode selectedDrawingMode) {
        Node anyDrawingPane = borderPane.getCenter();
        DrawingPaneActions drawingPaneActions = (DrawingPaneActions) anyDrawingPane;
        drawingPaneActions.setDrawingMode(selectedDrawingMode);
    }

    private void onColorSelected(Color selectedColor) {
        Node anyDrawingPane = borderPane.getCenter();
        DrawingPaneActions drawingPaneActions = (DrawingPaneActions) anyDrawingPane;
        drawingPaneActions.setColor(selectedColor);
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
