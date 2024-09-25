package io.flaggton.pixxelator.views;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;

public class DrawingController {
    private Path drawingPath;
    public void setDrawingPane(Pane drawingPane) {
        drawingPane.setOnMousePressed(e -> {
            drawingPath = new Path();
            drawingPath.setStroke(Color.BLACK);
            drawingPath.setStrokeWidth(3);
            drawingPath.getElements().add(new MoveTo(e.getX(), e.getY()));
            drawingPane.getChildren().add(drawingPath);
        });

        drawingPane.setOnMouseDragged(e -> {
            drawingPath.getElements().add(new LineTo(e.getX(), e.getY()));
        });
    }
}
