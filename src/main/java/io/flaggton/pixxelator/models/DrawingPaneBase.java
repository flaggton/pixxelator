package io.flaggton.pixxelator.models;

import com.wedasoft.wedasoftFxCustomNodes.zoomableScrollPane.ZoomableScrollPane;
import io.flaggton.pixxelator.enums.DrawingMode;
import javafx.scene.Node;
import javafx.scene.paint.Color;

@SuppressWarnings("LombokSetterMayBeUsed")
public abstract class DrawingPaneBase extends ZoomableScrollPane {

    protected Color selectedColor = Color.WHITE;
    protected DrawingMode drawingMode;

    public DrawingPaneBase(Node contentNode) {
        super(contentNode);
    }


    public void setColor(Color selectedColor) {
        this.selectedColor = selectedColor;
    }

    public void setDrawingMode(DrawingMode drawingMode) {
        this.drawingMode = drawingMode;
    }
}