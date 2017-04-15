import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Write a description of class GAME here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class GAME
{
    /* ########## REMOVE LATER ########## */
    // game width in number of blocks
    public static final int GAME_WIDTH = 22;
    // game height in number of blocks
    public static final int GAME_HEIGHT = 26;
    // block size
    public static final int BLOCK_SIZE = 20;
    // air color
    public static final Color AIR_COLOR = Color.LIGHT_GRAY;
    // frame rate
    public static final int FRAME_RATE = 120;
    // hero step width --> must be a factor of BLOCK_SIZE!
    public static final int HERO_STEP_WIDTH = 20;

    private Timer timer;
    private LEVEL[] level;
    
    public GAME(int levelCount)
    {
        /* ########## REMOVE LATER ########## */
        this.level = new LEVEL[levelCount];
        level[0] = new LEVEL("Level 1", new SPAWNPOINT(GAME_WIDTH-2, GAME_HEIGHT-2), new GOAL(20, 12));
        level[0].InitHero();
        initLevel0();
        level[0].UpdateView();

        // lambda expression for anonymous ActionListener
        timer = new Timer(1000 / FRAME_RATE, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                level[0].Clear();
                level[0].UpdateHero();
                level[0].UpdateView();
                level[0].repaint();
                if (level[0].isLevelOver()) {
                    timer.stop();
                }
            }

        });
        timer.start();
    }


    /* ########## REMOVE LATER ########## */
    /**            111111111122
     *   0123456789012345678901
     *  0######################
     *  1#   .    .    .    . #
     *  2#   .    .    .    . #
     *  3#   .    .    .    . #
     *  4#   .    .    .    . #
     *  5#   .    .    .    . #
     *  6#   .    .    .    . #
     *  7#   .    .    .    . #
     *  8#   .    .    .    . #
     *  9#   .    .    .    . #
     * 10#   .    .    .    . #
     * 11#   .    .    .    . #
     * 12#   .    .    .    .G#
     * 13##############.#######
     * 14#   .    .    .   #. #
     * 15#   . #  .   #.   #. #
     * 16##  . ################
     * 17#   .    .    .    . #
     * 18#   .    .    .    . #
     * 19####.    .    .    . #
     * 20#   .    .    .    . #
     * 21#   #    .    .    . #
     * 22#   .    .    .    .##
     * 23### .    .    .    . #
     * 24#   .    .    .    .S#
     * 25######################
     */
    public void initLevel0() {
        // fill the first and last column
        for (int i = 0; i < GAME_HEIGHT; i++) {
            level[0].AddBlock(new WALL(0,i));
            level[0].AddBlock(new WALL(21, i));
        }
        // fill the first and last row
        for (int i = 0; i < GAME_WIDTH; i++) {
            level[0].AddBlock(new WALL(i, 0));
            level[0].AddBlock(new WALL(i, 25));
        }
        // fill row 13
        for (int i = 0; i < 20; i++) {
            if (i == 13 )
                continue;
            level[0].AddBlock(new WALL(1+i,13));
        }
        // fill row 14
        level[0].AddBlock(new WALL(18,14));
        // fill row 15
        level[0].AddBlock(new WALL(7,15));
        level[0].AddBlock(new WALL(13,15));
        level[0].AddBlock(new WALL(18,15));
        // fill row 16
        for (int i = 0; i < 20; i++) {
            if (i >= 2 && i <= 5 )
                continue;
            level[0].AddBlock(new WALL(1+i,16));
        }
        // fill row 19
        level[0].AddBlock(new WALL(1,19));
        level[0].AddBlock(new WALL(2,19));
        level[0].AddBlock(new WALL(3,19));
        // fill row 21
        level[0].AddBlock(new WALL(4,21));
        // fill row 22
        level[0].AddBlock(new WALL(20,22));
        // fill row 23
        level[0].AddBlock(new WALL(1,23));
        level[0].AddBlock(new WALL(2,23));
    }

    public static void main(String[] args) {
        GAME g = new GAME(1);
    }

}
