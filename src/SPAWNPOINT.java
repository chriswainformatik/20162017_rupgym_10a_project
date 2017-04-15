import java.awt.*;

/**
 * Write a description of class SPAWNPOINT here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class SPAWNPOINT extends AIR
{
    
    public SPAWNPOINT(int x, int y, Color color)
    {
        super(x, y, color);
    }

    public SPAWNPOINT(int x, int y) {
        super(x, y, GAME.AIR_COLOR);
    }

}
