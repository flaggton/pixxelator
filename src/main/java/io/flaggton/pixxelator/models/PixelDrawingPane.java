package io.flaggton.pixxelator.models;

import com.wedasoft.wedasoftFxCustomNodes.zoomableScrollPane.ZoomableScrollPane;
import io.flaggton.pixxelator.enums.DrawingMode;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Random;

public class PixelDrawingPane extends ZoomableScrollPane implements DrawingPaneActions {
    Random random = new Random();

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
        rectangle.setWidth(1);
        rectangle.setHeight(1);
        rectangle.setFill(Color.color(random.nextDouble(), random.nextDouble(), random.nextDouble()));
        return rectangle;
    }

    @Override
    public void setDrawingMode(DrawingMode drawingMode) {
        System.out.println("PixelDrawingPane drawingMode = " + drawingMode);
    }

    @Override
    public void setColor(Color selectedColor) {
        System.out.println("PixelDrawingPane selectedColor = " + selectedColor.toString());
    }
}