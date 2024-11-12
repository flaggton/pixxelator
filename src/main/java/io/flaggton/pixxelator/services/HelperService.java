package io.flaggton.pixxelator.services;

import javafx.scene.SnapshotParameters;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;

import java.awt.image.BufferedImage;

public class HelperService {

    public static WritableImage createWritableImageFromPane(Region region) {
        final int width = (int) region.getBoundsInLocal().getWidth();
        final int height = (int) region.getBoundsInLocal().getHeight();

        WritableImage image = new WritableImage(width, height);
        SnapshotParameters params = new SnapshotParameters();
        params.setFill(Color.TRANSPARENT);
        region.snapshot(params, image);
        return image;
    }

    public static BufferedImage createBufferedImageFromWritableImage(WritableImage writableImage) {
        final int width = (int) writableImage.getWidth();
        final int height = (int) writableImage.getHeight();

        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        PixelReader pixelReader = writableImage.getPixelReader();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                bufferedImage.setRGB(x, y, pixelReader.getArgb(x, y));
            }
        }
        return bufferedImage;
    }

}