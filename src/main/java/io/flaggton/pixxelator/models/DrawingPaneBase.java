package io.flaggton.pixxelator.models;

import com.wedasoft.wedasoftFxCustomNodes.zoomableScrollPane.ZoomableScrollPane;
import io.flaggton.pixxelator.enums.DrawingMode;
import javafx.scene.Node;
import javafx.scene.paint.Color;

import java.util.List;

@SuppressWarnings({"LombokSetterMayBeUsed", "LombokGetterMayBeUsed"})
public abstract class DrawingPaneBase extends ZoomableScrollPane {

    protected Color selectedColor = Color.WHITE;
    protected DrawingMode drawingMode;

    public DrawingPaneBase(Node contentNode) {
        super(contentNode);
    }


    public void setColor(Color selectedColor) {
        this.selectedColor = selectedColor;
    }

    public Color getColor() {
        return selectedColor;
    }

    public void setDrawingMode(DrawingMode drawingMode) {
        this.drawingMode = drawingMode;
    }

    public DrawingMode getDrawingMode() {
        return drawingMode;
    }

    public abstract List<DrawingMode> getAvailableDrawingModes();
}