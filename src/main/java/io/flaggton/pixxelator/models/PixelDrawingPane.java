package io.flaggton.pixxelator.models;

import com.wedasoft.wedasoftFxCustomNodes.zoomableScrollPane.ZoomableScrollPane;
import io.flaggton.pixxelator.enums.DrawingMode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class PixelDrawingPane extends ZoomableScrollPane implements DrawingPaneActions {
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
        rectangle.setPickOnBounds(true);
        rectangle.setOnMouseClicked(e -> onPixelClickOrDragOver(e, rectangle));
        rectangle.setOnMouseDragEntered(e -> onPixelClickOrDragOver(e, rectangle));
        return rectangle;
    }

    private void onPixelClickOrDragOver(MouseEvent e, Rectangle rectangle) {
        if (drawingMode == DrawingMode.PENCIL) {
            rectangle.setFill(selectedColor);
        }
    }

    @Override
    public void setDrawingMode(DrawingMode drawingMode) {
        this.drawingMode = drawingMode;
    }

    @Override
    public void setColor(Color selectedColor) {
        this.selectedColor = selectedColor;
    }
}