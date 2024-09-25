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
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
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
    private Path drawingPath;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        borderPane.setCenter(createDrawingPane(400, 400));
    }

    public void onExitMenuItemClick() {
        Platform.exit();
        System.exit(0);
    }

    public void onNewCanvasButtonClick() {
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
        pane.setBackground(new Background(new BackgroundFill(Color.SALMON, null, null)));
        drawingPane = pane;
        enableDrawingOnPane(drawingPane);
        return new ZoomableScrollPane(pane);
    }

    private void enableDrawingOnPane(Pane pane) {
        pane.setOnMousePressed(e -> {
            if (e.isPrimaryButtonDown()) {
                drawingPath = new Path();
                drawingPath.setStroke(Color.BLACK);
                drawingPath.setStrokeWidth(3);
                drawingPath.getElements().add(new MoveTo(e.getX(), e.getY()));
                pane.getChildren().add(drawingPath);
            }
        });

        pane.setOnMouseDragged(e -> {
            if (e.isPrimaryButtonDown() && isWithinBounds(e.getX(), e.getY(), pane)) {
                // Bei Ziehen der Maus die Linie weiterzeichnen
                drawingPath.getElements().add(new LineTo(e.getX(), e.getY()));
            }
        });
    }
    private boolean isWithinBounds(double x, double y, Pane pane) {
        return x >= 0 && x <= pane.getWidth() && y >= 0 && y <= pane.getHeight();
    }
}

