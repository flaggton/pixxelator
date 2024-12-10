package io.flaggton.pixxelator.models;

import com.wedasoft.wedasoftFxCustomNodes.zoomableScrollPane.ZoomableScrollPane;
import io.flaggton.pixxelator.enums.DrawingMode;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@SuppressWarnings({"LombokSetterMayBeUsed", "LombokGetterMayBeUsed"})
public abstract class DrawingPaneBase extends ZoomableScrollPane {
    @Getter
    @Setter
    protected Color primaryColor = Color.WHITE;
    @Getter
    @Setter
    protected Color secondaryColor = Color.WHITE;
    @Getter
    @Setter
    protected DrawingMode drawingMode;

    public DrawingPaneBase(Node contentNode) {
        super(contentNode);
    }

    public abstract List<DrawingMode> getAvailableDrawingModes();
}