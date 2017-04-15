import java.awt.*;

/**
 * Write a description of class BLOCK here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public abstract class BLOCK
{
    public static final int SIZE = 20;

    // coordinates
    private int x;
    private int y;

    private Color color;
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }
    
    public int getPosX() {
        return x * GAME.BLOCK_SIZE;
    }
    
    public int getPosY() {
        return y * GAME.BLOCK_SIZE;
    }

    public Color getColor() { return color; }

    public BLOCK(int x, int y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }
}
