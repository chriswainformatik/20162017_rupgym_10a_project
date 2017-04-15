/**
 * A class of a picture which can be displayed by {@link Screen}
 * @author Benno
 *
 */
public class Sprite {

    private final int WIDTH, HEIGHT;
    private final int[] pixels;
    private int x, y;
    private Spritesheet sheet;
    private int rx, ry;
    private int color;

    /**
     * Used to create a {@link Sprite} from a {@link Spritesheet}
     * @param w : the width of the sprite
     * @param h : the height of the sprite
     * @param x : the x position of the sprite on the spritesheet (WATCH OUT it is mutiplied by the width)
     * @param y : the y position of the sprite on the spritesheet (WATCH OUT it is mutiplied by the height)
     * @param sheet : the spritesheet of this sprite
     */
    public Sprite(int w, int h, int x, int y, Spritesheet sheet) {
        WIDTH = w;
        HEIGHT = h;
        pixels = new int[getWidth() * getHeight()];
        this.x = (int) (x * w);
        this.y = (int) (y * h);
        this.sheet = sheet;
        load();
    }

    /**
     * Used to create a one color Sprite
     * @param w : the width in pixels
     * @param h : the height in pixels
     * @param color : the color
     */
    public Sprite(int w, int h, int color) {
        WIDTH = w;
        HEIGHT = h;
        pixels = new int[WIDTH * HEIGHT];
        setColor(color);
    }

    /**
     * Gets the value of the width of this Sprite
     * @return WIDTH
     */
    public int getWidth() {
        return WIDTH;
    }

    /**
     * Gets the value of the height of this Sprite
     * @return HEIGHT
     */
    public int getHeight() {
        return HEIGHT;
    }

    /**
     * Gets the value of the pixel at i
     * @param i : the index
     * @return the value of pixel at i
     */
    public int getPixel(int i) {
        return pixels[i];
    }

    /**
     * Sets the whole sprite to the color
     * @param color : the color you want to set it to
     */
    private void setColor(int color) {
        this.color = color;
        for (int x = 0; x < getWidth(); x++)
            for (int y = 0; y < getHeight(); y++) {
                pixels[x + y * getWidth()] = color;
            }
    }

    /**
     * Loads this Sprite from its sheet ({@link Spritesheet})
     */
    private void load() {
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                pixels[x + y * WIDTH] = sheet.getPixel((x + this.x) + (y + this.y) * sheet.getHeight());
            }
        }
    }

    public int getRx() {
        return rx;
    }

    public void setRx(int rx) {
        this.rx = rx;
    }

    public int getRy() {
        return ry;
    }

    public void setRy(int ry) {
        this.ry = ry;
    }

    /**
     * @return the color
     */
    public int getColor() {
        return color;
    }


    /**
     * Used to display {@link Sprite}s
     * @author Benno Lossin
     *
     */
    private class Screen {
        private final int width, height;
        private int[] pixels;
        private int xoff, yoff;

        public Screen(int width, int height) {
            this.width = width;
            this.height = height;
            pixels = new int[width * height];
        }

        public void clear() {
            for (int i = 0; i < pixels.length; i++) {
                pixels[i] = 0xff000000;
            }
        }

        /**
         * This method is used to render a specific {@link Sprite}
         * @param xp : the x position of the render position (left upper corner)
         * @param yp : the y position of the render position (left upper corner)
         * @param sprite : the sprite you want to display
         */
        public void renderSprite(int xp, int yp, Sprite sprite) {
            sprite.setRx(xp);
            sprite.setRy(yp);
            xp -= xoff;
            yp -= yoff;
            for (int y = 0; y < sprite.getHeight(); y++) {
                int ya = yp + y;
                for (int x = 0; x < sprite.getWidth(); x++) {
                    int xa = x + xp;
                    if (xa < -sprite.getWidth() || xa >= width || ya < 0 || ya >= height)
                        continue;
                    if (xa < 0)
                        xa = 0;
                    int col = sprite.getPixel(x + y * sprite.getWidth());
                    if (col != 0xffff00ff)
                        pixels[xa + ya * width] = col;
                }
            }
        }

        public int getXoff() {
            return xoff;
        }

        public void setXoff(int xoff) {
            this.xoff = xoff;
        }

        public int getYoff() {
            return yoff;
        }

        public void setYoff(int yoff) {
            this.yoff = yoff;
        }

        public int getPixel(int i) {
            return pixels[i];
        }
    }
}