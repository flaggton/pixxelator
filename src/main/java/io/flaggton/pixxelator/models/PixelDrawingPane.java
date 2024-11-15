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
                gridPane.add(createPixelTile(), x, y);
            }
        }
    }

    private Rectangle createPixelTile() {
        Rectangle pixel = new Rectangle();
        pixel.setWidth(1);
        pixel.setHeight(1);
        pixel.setPickOnBounds(true);
        pixel.setOnMouseClicked(e -> onPixelClickOrDragOver(e, pixel));
        pixel.setOnMouseDragEntered(e -> onPixelClickOrDragOver(e, pixel));
        return pixel;
    }

    private void onPixelClickOrDragOver(MouseEvent e, Rectangle pixel) {
        if (drawingMode == DrawingMode.PENCIL) {
            pixel.setFill(selectedColor);
        }
        if (drawingMode == DrawingMode.FILL_ALL) {
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