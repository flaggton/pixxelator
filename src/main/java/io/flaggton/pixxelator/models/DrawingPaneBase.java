package io.flaggton.pixxelator.models;

import com.wedasoft.wedasoftFxCustomNodes.zoomableScrollPane.ZoomableScrollPane;
import io.flaggton.pixxelator.enums.DrawingMode;
import javafx.scene.Node;
import javafx.scene.paint.Color;

public abstract class DrawingPaneBase extends ZoomableScrollPane {

    public DrawingPaneBase(Node contentNode) {
        super(contentNode);
    }

    public abstract void setDrawingMode(DrawingMode drawingMode);

    public abstract void setColor(Color selectedColor);
}