import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * This class is designed to load {@link Sprite} objects
 * @author Benno
 */
public class Spritesheet {
    private String path;
    private final int WIDTH, HEIGHT;
    private final int[] pixels;

    /**
     * @param path : the file path of the png picture of this {@link Spritesheet}
     * @param w : the width of this {@link Spritesheet}
     * @param h : the height of this {@link Spritesheet}
     */
    public Spritesheet(String path, int w, int h) {
        this.path = path;
        WIDTH = w;
        HEIGHT = h;
        pixels = new int[w * h];
        load();
    }

    /**
     * This loads the {@link Spritesheet} into its pixel-array
     */
    private void load() {
        try {
            BufferedImage image = ImageIO.read(Spritesheet.class.getResource(path));
            int w = image.getWidth(), h = image.getHeight();
            image.getRGB(0, 0, w, h, pixels, 0, w);
            for (int i = 0; i < pixels.length; i++)
                pixels[i] += (new Color(pixels[i])).getAlpha() * 0x1000000;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is used to get the pixels array values
     * @param i : the index
     * @return the value of pixels[i]
     */
    public int getPixel(int i) {
        return pixels[i];
    }

    /**
     * @return the height of this {@link Spritesheet}
     */
    public int getHeight() {
        return HEIGHT;
    }

    /**
     * @return the width of this {@link Spritesheet}
     */
    public int getWidth() {
        return WIDTH;
    }
}