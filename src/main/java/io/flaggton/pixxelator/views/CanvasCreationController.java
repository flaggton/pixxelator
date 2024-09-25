package io.flaggton.pixxelator.views;

import com.wedasoft.wedasoftFxCustomNodes.zoomableScrollPane.ZoomableScrollPane;
import io.flaggton.pixxelator.models.StandardDrawingPane;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class CanvasCreationController {
    @FXML
    public TextField newCanvasHeight;
    @FXML
    public TextField newCanvasWidth;
    private Consumer<ZoomableScrollPane> onConfirmButtonClickAction;

    public void init(Consumer<ZoomableScrollPane> onConfirmButtonClickAction) {
        this.onConfirmButtonClickAction = onConfirmButtonClickAction;
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
        StandardDrawingPane standardDrawingPane = new StandardDrawingPane(width, height);
        // machIrgendwas.mit(diesemObjekt);
        onConfirmButtonClickAction.accept(standardDrawingPane);
        Stage stage = (Stage) newCanvasHeight.getScene().getWindow();
        stage.close();
    }

    public void onCancelButtonClick() {
        Stage stage = (Stage) newCanvasHeight.getScene().getWindow();
        stage.close();
    }
}
