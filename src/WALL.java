import java.awt.*;

/**
 * Write a description of class WAND here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class WALL extends BLOCK
{
    
    public WALL(int x, int y, Color color)
    {
        super(x, y, color);
    }

    public WALL(int x, int y) {
        super(x, y, Color.BLUE);
    }

}
