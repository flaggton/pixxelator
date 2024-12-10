package io.flaggton.pixxelator.views;

import io.flaggton.pixxelator.enums.DrawingPaneType;
import io.flaggton.pixxelator.models.DrawingPaneBase;
import io.flaggton.pixxelator.models.ExperimentalDrawingPane;
import io.flaggton.pixxelator.models.PixelDrawingPane;
import io.flaggton.pixxelator.models.StandardDrawingPane;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class CreateNewDrawingPaneController {
    @FXML
    public TextField newCanvasHeight;
    @FXML
    public TextField newCanvasWidth;
    @FXML
    private ChoiceBox<DrawingPaneType> drawingPaneTypeChoiceBox;
    @FXML
    private ColorPicker backgroundColorPicker;
    private Consumer<DrawingPaneBase> onConfirmButtonClickAction;

    public void init(Consumer<DrawingPaneBase> onConfirmButtonClickAction) {
        this.onConfirmButtonClickAction = onConfirmButtonClickAction;
        drawingPaneTypeChoiceBox.setItems(FXCollections.observableArrayList(DrawingPaneType.values()));
        drawingPaneTypeChoiceBox.getSelectionModel().selectFirst();
    }

    public void onConfirmButtonClick() {
        int height;
        try {
            height = Integer.parseInt(newCanvasHeight.getText());
        } catch (NumberFormatException e) {
            System.out.println("Ungültige Höhe.");
            return;
        }
        int width;
        try {
            width = Integer.parseInt(newCanvasWidth.getText());
        } catch (NumberFormatException e) {
            System.out.println("Ungültige Breite");
            return;
        }
        DrawingPaneBase drawingPaneBase;
        if (drawingPaneTypeChoiceBox.getSelectionModel().getSelectedItem() == DrawingPaneType.STANDARD_DRAWING_PANE) {
            drawingPaneBase = new StandardDrawingPane(width, height, backgroundColorPicker.getValue());
        } else if (drawingPaneTypeChoiceBox.getSelectionModel().getSelectedItem() == DrawingPaneType.PIXEL_DRAWING_PANE) {
            drawingPaneBase = new PixelDrawingPane(width, height, backgroundColorPicker.getValue());
        } else if (drawingPaneTypeChoiceBox.getSelectionModel().getSelectedItem() == DrawingPaneType.EXPERIMENTAL_DRAWING_PANE) {
            drawingPaneBase = new ExperimentalDrawingPane(width, height, backgroundColorPicker.getValue());
        } else {
            throw new RuntimeException("Drawing pane type not supported.");
        }
        onConfirmButtonClickAction.accept(drawingPaneBase);
        Stage stage = (Stage) newCanvasHeight.getScene().getWindow();
        stage.close();
    }

    public void onCancelButtonClick() {
        Stage stage = (Stage) newCanvasHeight.getScene().getWindow();
        stage.close();
    }
}
