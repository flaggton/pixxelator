package io.flaggton.pixxelator.models;

import io.flaggton.pixxelator.enums.DrawingMode;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static io.flaggton.pixxelator.enums.DrawingMode.*;

public class PixelDrawingPane extends DrawingPaneBase {
    private final GridPane gridPane;

    public PixelDrawingPane(int widthInPx, int heightInPx, Color backgroundColor) {
        super(new GridPane());
        gridPane = (GridPane) getContentNode();
        for (int x = 0; x < widthInPx; x++) {
            for (int y = 0; y < heightInPx; y++) {
                gridPane.add(createPixelTile(), x, y);
            }
        }
        fillAllPixelsWithColor(backgroundColor);
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
        Color selectedColor = e.isPrimaryButtonDown() ? primaryColor : secondaryColor;
        if (drawingMode == DrawingMode.PENCIL) {
            pixel.setFill(selectedColor);
        }
        if (drawingMode == DrawingMode.FILL_ALL) {
            fillAllPixelsWithColor(selectedColor);
        }
        if (drawingMode == DrawingMode.REPLACE_PIXEL_COLOR) {
            replaceColorOfPixels(pixel.getFill(), selectedColor);
        }
        if (drawingMode == DrawingMode.STANDARD_BUCKET) {
            fillAllAdjacentPixels(pixel, selectedColor);
        }
    }

    private void fillAllPixelsWithColor(Color color) {
        for (Node node : gridPane.getChildren()) {
            if (node instanceof Rectangle) { // <- überprüft ob alle "node" wirklich Rectangle sind, bevor gecastet wird -> Program geht nicht kabumm
                Rectangle pixel = (Rectangle) node;
                pixel.setFill(color);
            }
        }
    }

    private void replaceColorOfPixels(Paint paintToReplace, Color newColor) {
        for (Node node : gridPane.getChildren()) {
            if (node instanceof Rectangle) {
                Rectangle pixel = (Rectangle) node;
                if (pixel.getFill().equals(paintToReplace)) {
                    pixel.setFill(newColor);
                }
            }
        }
    }

    private void fillAllAdjacentPixels(Rectangle pixel, Color newColor) {
        Rectangle[][] xyMatrix = new Rectangle[gridPane.getColumnCount()][gridPane.getRowCount()];
        for (Node node : gridPane.getChildren()) {
            xyMatrix[GridPane.getColumnIndex(node)][GridPane.getRowIndex(node)] = (Rectangle) node;
        }

        final Integer pixelX = GridPane.getColumnIndex(pixel);
        final Integer pixelY = GridPane.getRowIndex(pixel);

        if (pixelX == null || pixelY == null) {
            System.out.println("Pixel-Koordinaten konnten nicht ermittelt werden.");
            return;
        }

        Color originalColor = (Color) pixel.getFill();

        // Wenn die ursprüngliche Farbe gleich der neuen Farbe ist, breche ab
        if (originalColor.equals(newColor)) {
            System.out.println("Die neue Farbe entspricht bereits der Originalfarbe. Keine Änderungen erforderlich.");
            return;
        }

        // Verwende eine Queue für die Flood-Fill-Logik (Breitensuche)
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{pixelX, pixelY});

        // Start Flood-Fill
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0];
            int y = current[1];

            // Prüfe, ob die aktuellen Koordinaten innerhalb des Bereichs liegen
            if (x < 0 || y < 0 || x >= xyMatrix.length || y >= xyMatrix[0].length) {
                continue;
            }

            Rectangle currentPixel = xyMatrix[x][y];
            if (currentPixel == null) {
                continue;
            }

            // Hole die Farbe des aktuellen Pixels
            Color currentColor = (Color) currentPixel.getFill();

            // Überspringe Pixel, die nicht die ursprüngliche Farbe haben
            if (!currentColor.equals(originalColor)) {
                continue;
            }

            // Färbe das Pixel mit der neuen Farbe
            currentPixel.setFill(newColor);

            // Füge benachbarte Pixel in die Queue hinzu
            queue.add(new int[]{x - 1, y}); // Links
            queue.add(new int[]{x + 1, y}); // Rechts
            queue.add(new int[]{x, y - 1}); // Oben
            queue.add(new int[]{x, y + 1}); // Unten
        }
    }

    @Override
    public List<DrawingMode> getAvailableDrawingModes() {
        return List.of(UNSET, PENCIL, FILL_ALL, REPLACE_PIXEL_COLOR, STANDARD_BUCKET);
    }
}