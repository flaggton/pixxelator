package io.flaggton.pixxelator.models;

import com.wedasoft.wedasoftFxCustomNodes.zoomableScrollPane.ZoomableScrollPane;
import io.flaggton.pixxelator.enums.DrawingMode;
import javafx.application.Platform;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Random;

public class PixelDrawingPane extends ZoomableScrollPane implements DrawingPaneActions {
    Random random = new Random();
    private Color selectedColor = Color.WHITE;
    private DrawingMode drawingMode;

    public PixelDrawingPane(int widthInPx, int heightInPx) {
        super(new GridPane());
        GridPane gridPane = (GridPane) getContentNode();
        for (int x = 0; x < widthInPx; x++) {
            for (int y = 0; y < heightInPx; y++) {
                gridPane.add(createRectangle(), x, y);
            }
        }
    }

    private Rectangle createRectangle() {
        Rectangle rectangle = new Rectangle();
        rectangle.setWidth(10);
        rectangle.setHeight(10);
//        rectangle.setFill(Color.color(random.nextDouble(), random.nextDouble(), random.nextDouble()));
        rectangle.setOnMouseMoved(mouseEvent -> onMouseDragged(rectangle));

        return rectangle;
    }

    @Override
    public void setDrawingMode(DrawingMode drawingMode) {
        System.out.println("PixelDrawingPane drawingMode = " + drawingMode);
        this.drawingMode = drawingMode;
    }

    @Override
    public void setColor(Color selectedColor) {
        System.out.println("PixelDrawingPane selectedColor = " + selectedColor.toString());
        this.selectedColor = selectedColor;
    }

    public void onMouseDragged(Rectangle rectangle) {
        System.out.println("x");
        Platform.runLater(() -> {
            rectangle.setFill(selectedColor);
        });
    }
}