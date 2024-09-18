package io.flaggton.pixxelator.views;

import com.wedasoft.wedasoftFxCustomNodes.zoomableScrollPane.ZoomableScrollPane;
import io.flaggton.pixxelator.services.JfxUiService;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
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
    @FXML
    private BorderPane borderPane;

    private final JfxUiService jfxUiService;

    private Pane drawingPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void onExitMenuItemClick() {
        Platform.exit();
        System.exit(0);
    }

    public void onNewCanvasButtonClick() {
        System.out.println("onNewCanvasButtonClick()");
        borderPane.setCenter(createDrawingPane(400, 400));
    }

    @SuppressWarnings("SameParameterValue")
    private ZoomableScrollPane createDrawingPane(int width, int height) {
        Pane pane = new Pane();
        pane.setPrefWidth(width);
        pane.setMaxWidth(width);
        pane.setMinWidth(width);
        pane.setPrefHeight(height);
        pane.setMaxHeight(height);
        pane.setMinHeight(height);
        pane.setBackground(new Background(new BackgroundFill(Color.RED, null, null)));
        drawingPane = pane;
        return new ZoomableScrollPane(pane);
    }
}

