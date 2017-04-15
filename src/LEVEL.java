import java.awt.Graphics;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

/**
 * Write a description of class LEVEL here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class LEVEL extends JFrame
{
    /* ########## REMOVE LATER ########## */
    private int blockCount;
    private SPAWNPOINT theSpawnPoint;
    private GOAL theGoal;
    private HERO theHero;
    private boolean levelOver;
    private JLabel gameInfo;

    private GAMECANVAS theCanvas;
    private BLOCK[][] theField;
    private BLOCK[] theBlocks;
    private Rectangle[] theRectangles;

    /**
     * Creates a new instance of LEVEL and sets everything up:
     *  - frame dimensions
     *  - key listeners
     *  - initialize block array
     *  - initialize hero
     *  - fill in surrounding walls (remove later)
     * @param frameTitle
     */
    public LEVEL(String frameTitle, SPAWNPOINT spawnpoint, GOAL goal)
    {  
        super(frameTitle);

        /* ### CHANGE LATER ### */
        int frameWidth = GAME.BLOCK_SIZE * GAME.GAME_WIDTH;
        int frameHeight = GAME.BLOCK_SIZE * GAME.GAME_HEIGHT;
        theCanvas = new GAMECANVAS(frameWidth, frameHeight, Color.LIGHT_GRAY);

        /* ########## REMOVE LATER ########## */
        this.addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {

                }
                @Override
                public void keyPressed(KeyEvent e) {
                    char direction = 'X';
                    switch (e.getKeyCode()) {
                        case 38: //up
                            direction = 'N';
                            break;
                        case 40: //down
                            direction = 'S';
                            break;
                        case 39: //right
                            direction = 'E';
                            break;
                        case 37: //left
                            direction = 'W';
                            break;
                        default:
                    }
                    if (! theHero.isMoving() && !levelOver) {
                        moveHero(direction);
                    }
                }
                @Override
                public void keyReleased(KeyEvent e) {

                }
            });

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //this.add(theCanvas);

        JPanel rootPanel = new JPanel();
        rootPanel.setLayout(new BoxLayout(rootPanel, BoxLayout.PAGE_AXIS));
        rootPanel.add(theCanvas);
        gameInfo = new JLabel("remaining time: 0:10", JLabel.CENTER);
        rootPanel.add(gameInfo);
        this.add(rootPanel);

        this.pack();
        this.setVisible(true);
        this.setResizable(false);

        /* ### CHANGE LATER ### */
        // initialize the blocks array
        this.theBlocks = new BLOCK[GAME.GAME_WIDTH * GAME.GAME_HEIGHT];
        this.theRectangles = new Rectangle[theBlocks.length];
        this.blockCount = 0;

        /* ########## REMOVE LATER ########## */
        this.levelOver = false;
        this.theSpawnPoint = spawnpoint;
        this.theGoal = goal;

        /* ### CHANGE LATER ### */
        // initialize the two-dimensional blocks array
        theField = new BLOCK[GAME.GAME_WIDTH][GAME.GAME_HEIGHT];

        /* ########## REMOVE LATER ########## */
        /*
        // fill the first and last row with wall blocks
        int lastRowIndex = theField[0].length-1;
        for (int i = 0; i < theField.length; i++) {
            WALL firstRowWall = new WALL(i, 0);
            theField[i][0] = firstRowWall;
            theBlocks[blockCount++] = firstRowWall;
            WALL lastRowWall = new WALL(i, lastRowIndex);
            theField[i][lastRowIndex] = lastRowWall;
            theBlocks[blockCount++] = lastRowWall;
        }

        // fill the first and last column with wall blocks
        int lastColIndex = theField.length-1;
        for (int i = 0; i < theField[0].length; i++) {
            WALL firstColWall = new WALL(0, i);
            theField[0][i] = firstColWall;
            theBlocks[blockCount++] = firstColWall;
            WALL lastColWall = new WALL(lastColIndex, i);
            theField[lastColIndex][i] = lastColWall;
            theBlocks[blockCount++] = lastColWall;
        }
        */
    }

    /* ########## REMOVE LATER ########## */
    public void updateGameInfoText(String text) {
        gameInfo.setText(text);
    }
    /**
     * Calls the move methods and updates the view.
     * @param direction 'N' (north), 'E' (east), 'S' (south) or 'W' (west)
     */
    public void moveHero(char direction) {
        switch (direction) {
            case 'N':
                startMovingHeroNorth();
                break;
            case 'S':
                startMovingHeroSouth();
                break;
            case 'E':
                startMovingHeroEast();
                break;
            case 'W':
                startMovingHeroWest();
                break;
            default:
        }
    }

    /* ########## REMOVE LATER ########## */
    /**
     * Moves the hero as far east as possible
     */
    private void startMovingHeroEast() {
        // get hero coordinates
        int heroX = theHero.getX();
        int lastFreeX = heroX;
        for (int i = heroX; i < theField.length; i++) {
            if (theField[i][theHero.getY()] instanceof WALL) {
                break;
            } else {
                lastFreeX = i;
            }
        }
        theHero.startMoving('E');
        theHero.setX(lastFreeX);
    }

    /* ########## REMOVE LATER ########## */
    /**
     * Moves the hero as far north as possible
     */
    public void startMovingHeroNorth() {
        // get hero coordinates
        int heroY = theHero.getY();
        int lastFreeY = heroY;
        for (int i = heroY; i >= 0; i--) {
            if (theField[theHero.getX()][i] instanceof WALL) {
                break;
            } else {
                lastFreeY = i;
            }
        }
        theHero.startMoving('N');
        theHero.setY(lastFreeY);
    }

    /**
     * Moves the hero as far south as possible
     */
    public void startMovingHeroSouth() {
        // get hero coordinates
        int heroY = theHero.getY();
        int lastFreeY = heroY;
        for (int i = heroY; i < theField[0].length; i++) {
            if (theField[theHero.getX()][i] instanceof WALL) {
                break;
            } else {
                lastFreeY = i;
            }
        }
        theHero.startMoving('S');
        theHero.setY(lastFreeY);
    }

    /**
     * Calculates the furthest possible coordinate and makes the hero start moving.
     */
    private void startMovingHeroWest() {
        // get hero coordinates
        int heroX = theHero.getX();
        int lastFreeX = heroX;
        for (int i = heroX; i >= 0; i--) {
            if (theField[i][theHero.getY()] instanceof WALL) {
                break;
            } else {
                lastFreeX = i;
            }
        }
        theHero.startMoving('W');
        theHero.setX(lastFreeX);
    }

    /**
     * If it should still move, moves the hero one step towards the given direction.
     * @param direction Current movingDirection of hero
     */
    private void moveHeroTo(char direction) {
        if (isBlockNextTo(theHero.getPosX(), theHero.getPosY(), direction)) {
            theHero.stopMoving();
            if (theGoal.getX() == theHero.getX() && theGoal.getY() == theHero.getY()) {
                levelOver = true;
                gameInfo.setText("level complete");
            }
        } else {
            switch (direction) {
                case 'N':
                    theHero.setPosY(theHero.getPosY() - GAME.HERO_STEP_WIDTH);
                    break;
                case 'S':
                    theHero.setPosY(theHero.getPosY() + GAME.HERO_STEP_WIDTH);
                    break;
                case 'E':
                    theHero.setPosX(theHero.getPosX() + GAME.HERO_STEP_WIDTH);
                    break;
                case 'W':
                    theHero.setPosX(theHero.getPosX() - GAME.HERO_STEP_WIDTH);
                    break;
                default:
            }
            //theHero.setPosX(theHero.getPosX() - GAME.HERO_STEP_WIDTH);
        }
    }

    /**
     * Check if there is a block next to the current position.
     * Check E/W and N/S seperately.
     * Only check if at an exact coordinate.
     * @param x current xPosition of hero
     * @param y current yPosition of hero
     * @param direction current movingDirection of hero
     * @return
     */
    private boolean isBlockNextTo(int x, int y, char direction) {
        // only check if at an exact coordinate
        if ((direction == 'W' || direction == 'E' ) && (x % GAME.BLOCK_SIZE) == 0) {
            // check for W/E
            int sign = (direction == 'W' ? -1 : 1);
            int xCoordinate = x / GAME.BLOCK_SIZE;
            int yCoordinate = y / GAME.BLOCK_SIZE;
            if (xCoordinate > 0 && theField[xCoordinate+sign][yCoordinate] instanceof WALL) {
                return true;
            }
            return false;
        } else if ((direction == 'N' || direction == 'S' ) && (y % GAME.BLOCK_SIZE) == 0) {
            // check for N/S
            int sign = (direction == 'N' ? -1 : 1);
            int xCoordinate = x / GAME.BLOCK_SIZE;
            int yCoordinate = y / GAME.BLOCK_SIZE;
            if (xCoordinate > 0 && theField[xCoordinate][yCoordinate+sign] instanceof WALL) {
                return true;
            }
            return false;
        }
        // else you can always go further
        return false;
    }

    /* ########## REMOVE LATER ########## */
    /**
     * Outputs the whole field on the console
     */
    public void OutputTheFieldToConsole() {

        int heroPosX = theHero.getX();
        int heroPosY = theHero.getY();
        for (int i = 0; i < theField[0].length; i++) {
            for (int j = 0; j < theField.length; j++) {
                if (theField[j][i] instanceof WALL) {
                    System.out.print("#");
                } else if (i == heroPosY && j == heroPosX) {
                    System.out.print("H");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println("");
        }
    }

    public boolean isLevelOver() {
        return levelOver;
    }


    /* ########## REMOVE LATER ########## */
    /**
     * Initialize the hero by settings it's position to the spawn point's position
     */
    public void InitHero() {
        theHero = new HERO(theSpawnPoint.getX(), theSpawnPoint.getY());
    }

    /* ########## REMOVE LATER ########## */
    /**
     * Adds the given BLOCK to the array list of BLOCKS (theBlocks)
     */
    public void AddBlock(BLOCK newBlock) {
        theField[newBlock.getX()][newBlock.getY()] = newBlock;
        theBlocks[blockCount] = newBlock;
        theRectangles[blockCount] = new Rectangle(newBlock.getPosX(), newBlock.getPosY(), GAME.BLOCK_SIZE, GAME.BLOCK_SIZE);
        blockCount++;
    }






    /* #################### DO NOT CHANGE ANYTHING BELOW! #################### */
    
    /**
     * Adds all the wall blocks.
     */
    public void UpdateView() {
        for (int i = 0; i < blockCount; i++) {
            theCanvas.addRectangle(theRectangles[i], theBlocks[i].getColor());
        }
        /* ### CHANGE LATER ### */
        theCanvas.addRectangle(new Rectangle(theGoal.getPosX(), theGoal.getPosY(), GAME.BLOCK_SIZE, GAME.BLOCK_SIZE), theGoal.getColor());
        //theCanvas.addRectangle(new Rectangle(theHero.getPosX(), theHero.getPosY(), GAME.BLOCK_SIZE, GAME.BLOCK_SIZE), theHero.getColor());
    }

    /**
     * Calls moveHeroTo(direction), if the hero should be moving.
     */
    public void UpdateHero() {
        if (theHero.isMoving()) {
            moveHeroTo(theHero.getMovingDirection());
        }
        //theCanvas.addRectangle(new Rectangle(theHero.getPosX(), theHero.getPosY(), GAME.BLOCK_SIZE, GAME.BLOCK_SIZE), theHero.getColor());
    }
    
    /**
     * Clears the Canvas by calling its clear() method.
     */
    public void Clear() {
        theCanvas.clear();
    }

    /**
     * The canvas where everything gets painted on.
     * Also, this class determines the size of the window.
     */
    private class GAMECANVAS extends JPanel {

        private final static int AREA_SIZE = 400;
        private BufferedImage image = new BufferedImage(AREA_SIZE, AREA_SIZE, BufferedImage.TYPE_INT_ARGB);
        private BufferedImage image2;

        /**
         * Creates a new instance of MyCanvas with the given color as backgorund color.
         * Sets the dimensions to the given width and height.
         */
        public GAMECANVAS(int width, int height, Color color) {
            image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            try {
                image2 = ImageIO.read(new File("sprites/snowman-game-character.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            setBackground(color);
            setPreferredSize(new Dimension(width, height));

        }

        /**
         * The method that is invoked when repaint() is called.
         */
        @Override
        protected void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            if (image != null)
            {
                g.drawImage(image, 0, 0, null);
                //g.drawImage(image2, 50, 50, null);
                g.drawImage(image2, theHero.getPosX(), theHero.getPosY(), GAME.BLOCK_SIZE, GAME.BLOCK_SIZE, null);
            }
        }

        /**
         * Creates an empty BufferedImage.
         */
        private void createEmptyImage()
        {
            image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = (Graphics2D)image.getGraphics();
            g2d.setColor(Color.BLACK);
        }

        /**
         * Adds a single rectangle.
         */
        public void addRectangle(Rectangle rect, Color color)
        {
            //  Draw the Rectangle onto the BufferedImage
            Graphics2D g2d = (Graphics2D)image.getGraphics();
            g2d.setColor(color);
            g2d.fillRect((int)rect.getX(), (int)rect.getY(), (int)rect.getWidth(), (int)rect.getHeight());
            //revalidate();
            //repaint();
        }

        /**
         * Clears the component by painting an empty image.
         */
        public void clear()
        {
            createEmptyImage();
            repaint();
        }
    }
}
