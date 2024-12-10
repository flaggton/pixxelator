package io.flaggton.pixxelator.models;

import io.flaggton.pixxelator.enums.DrawingMode;
import javafx.scene.paint.Color;

import java.util.List;

import static io.flaggton.pixxelator.enums.DrawingMode.*;


public class ExperimentalDrawingPane extends PixelDrawingPane {
    public ExperimentalDrawingPane(int widthInPx, int heightInPx, Color backgroundColor) {
        super(widthInPx, heightInPx, backgroundColor);
    }


    @Override
    public List<DrawingMode> getAvailableDrawingModes() {
        return List.of(UNSET, PENCIL, SPILL_PAINT);
    }
}
