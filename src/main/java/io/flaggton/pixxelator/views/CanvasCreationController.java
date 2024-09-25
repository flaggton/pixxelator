package io.flaggton.pixxelator.views;

import com.wedasoft.wedasoftFxCustomNodes.zoomableScrollPane.ZoomableScrollPane;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class CanvasCreationController {
    @FXML
    public TextField newCanvasHeight;
    @FXML
    public TextField newCanvasWidth;

    public ZoomableScrollPane onConfirmButtonClick(){
        Pane pane = new Pane();
        try {
            int height = Integer.parseInt(newCanvasHeight.getText());
            pane.setPrefWidth(height);
            pane.setMaxWidth(height);
            pane.setMinWidth(height);
        } catch (NumberFormatException e) {
            System.out.println("Ungültige Höhe.");
        }
        try {
            int width = Integer.parseInt(newCanvasWidth.getText());
            pane.setPrefHeight(width);
            pane.setMaxHeight(width);
            pane.setMinHeight(width);
        } catch (NumberFormatException e) {
            System.out.println("Ungültige Breite");
        }
        pane.setBackground(new Background(new BackgroundFill(Color.SALMON, null, null)));
//        drawingPane = pane;
//        enableDrawingOnPane(drawingPane);
        return new ZoomableScrollPane(pane);
    }

    public void onCancelButtonClick(){
        Stage stage = (Stage) newCanvasHeight.getScene().getWindow();
        stage.close();
    }

}
