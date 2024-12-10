package io.flaggton.pixxelator.models;

import io.flaggton.pixxelator.enums.DrawingMode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;

import java.util.List;

import static io.flaggton.pixxelator.enums.DrawingMode.PENCIL;
import static io.flaggton.pixxelator.enums.DrawingMode.UNSET;

public class StandardDrawingPane extends DrawingPaneBase {
    private Path drawingPath;

    public StandardDrawingPane(int width, int height, Color backgroundColor) {
        super(new Pane());
        Pane pane = (Pane) getContentNode();
        pane.setPrefWidth(width);
        pane.setMaxWidth(width);
        pane.setMinWidth(width);
        pane.setPrefHeight(height);
        pane.setMaxHeight(height);
        pane.setMinHeight(height);
        pane.setBackground(new Background(new BackgroundFill(backgroundColor, null, null)));
        pane.setOnMousePressed(e -> {
            if (e.isPrimaryButtonDown()) {
                drawingPath = new Path();
                drawingPath.setStroke(super.selectedColor);
                drawingPath.setStrokeWidth(2);
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

    @Override
    public List<DrawingMode> getAvailableDrawingModes() {
        return List.of(UNSET, PENCIL);
    }
}
