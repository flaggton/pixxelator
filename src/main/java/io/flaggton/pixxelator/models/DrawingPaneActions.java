package io.flaggton.pixxelator.models;

import io.flaggton.pixxelator.enums.DrawingMode;
import javafx.scene.paint.Color;

public interface DrawingPaneActions {
    void setDrawingMode(DrawingMode drawingMode);

    void setColor(Color selectedColor);
}