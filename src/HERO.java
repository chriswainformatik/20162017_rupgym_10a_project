import java.awt.*;

/**
 * Write a description of class HERO here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class HERO
{
    // coordinates
    private int x;
    private int y;

    // "real" positions
    private int posX;
    private int posY;

    // color
    private Color color;

    // indicates whether hero is moving or not
    private boolean isMoving;

    // indicates the direction the hero is moving to
    private char movingDirection;

    /**
     * Get the x coordinate
     * @return
     */
    public int getX() {
        return x;
    }

    /**
     * Get the real x position
     * @return
     */
    public int getPosX() {
        return posX;
    }

    /**
     * Set the x coordinate;
     * @param x
     */
    public void setX(int x) {
        this.x = x;
    }
    /**
     * Set the real x position
     * @param posX
     */
    public void setPosX(int posX) {
        this.posX = posX;
    }

    /**
     * Get the y coordinate
     * @return
     */
    public int getY() {
        return y;
    }

    /**
     * Get the real y position
     * @return
     */
    public int getPosY() {
        return posY;
    }

    /**
     * Set the y coordinate;
     * @param y
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Set the real y position
     * @param posY
     */
    public void setPosY(int posY) {
        this.posY = posY;
    }

    /**
     * Get the current color
     * @return
     */
    public Color getColor() {
        return color;
    }

    public void startMoving(char direction) {
        this.isMoving = true;
        this.movingDirection = direction;
    }

    public void stopMoving() {
        this.isMoving = false;
        this.movingDirection = 'X';
    }

    public boolean isMoving() {
        return isMoving;
    }

    public char getMovingDirection () {
        return movingDirection;
    }

    /**
     * Generates a new HERO with spawn coordinates
     * @param spawnX x coordinate of spawn block
     * @param spawnY y coordinate of spawn block
     */
    public HERO(int spawnX, int spawnY) {
        this.x = spawnX; //* GAME.BLOCK_SIZE;
        this.y = spawnY; //* GAME.BLOCK_SIZE;
        this.posX = GAME.BLOCK_SIZE * x;
        this.posY = GAME.BLOCK_SIZE * y;
        this.color = Color.YELLOW;
        this.isMoving = false;
        this.movingDirection = 'X';
    }

}
