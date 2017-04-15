package firsttest;

import java.awt.Graphics;
import java.awt.*;
import java.awt.image.*;
import javax.swing.*;
import java.awt.event.*;

/**
 * Creates a JFrame with a JPanel as canvas. Provides methods to draw on that canvas.
 *
 * @author Christopher Wagner
 * @version 2017-04-04
 */
public class MainWindowAWT extends JFrame implements KeyListener {

    private MyCanvas mycanvas;

    /**
     * Creates a new instance of firsttest.MainWindowAWT with the given frame title.
     * Sets the look and feel to default.
     * Set the default close operation to EXIT_ON_CLOSE.
     * Adds an instance of MyCanvas and packs its contents (which is a try to set the size of the frame to the size of its contents).
     * Shows the frame and disables rezisability.
     */
    public MainWindowAWT(String frameTitle) {
        super(frameTitle);
        mycanvas = new MyCanvas();
        this.addKeyListener(this);
        JFrame.setDefaultLookAndFeelDecorated(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //theFrame.getContentPane().add(mycanvas);
        this.add(mycanvas);
        this.pack();
        this.setVisible(true);
        this.setResizable(false);
    }
    
    public MainWindowAWT(String frameTitle, int canvasWidth, int canvasHeight) {
        super(frameTitle);
        mycanvas = new MyCanvas(canvasWidth, canvasHeight, Color.WHITE);
        this.addKeyListener(this);
        JFrame.setDefaultLookAndFeelDecorated(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //theFrame.getContentPane().add(mycanvas);
        this.add(mycanvas);
        this.pack();
        this.setVisible(true);
        this.setResizable(false);
    }
    
    /* OVERRIDE METHODS FROM INTERFACE */
    @Override
    public void keyTyped(KeyEvent e) {
        System.out.println("key typed");
    }
    
    public void keyPressed(KeyEvent e) {
        System.out.println("key pressed");
    }
    
    public void keyReleased(KeyEvent e) {
        
    }
    
    
    /**
     * Draws the given rectangle in the given color.
     * Invokes the repaint() method.
     */
    public void drawRect(Rectangle rect, Color color) {
        mycanvas.addRectangle(rect, color);
        //theFrame.getContentPane().repaint();
        this.repaint();
    }
    
    /**
     * Draws the given rectangles (array) in the given color.
     * Invokes the repaint() method.
     */
    public void drawRects(Rectangle[] rects, Color color) {
        for (int i = 0; i < rects.length; i++) {
            mycanvas.addRectangle(rects[i], color);
        }
        //theFrame.getContentPane().repaint();
        this.repaint();
    }
    
    /**
     * Clears the Canvas by calling its clear() method.
     */
    public void clear() {
        mycanvas.clear();
    }

    /**
     * The canvas where everything gets painted on.
     * Also, this class determines the size of the window.
     */
    private static class MyCanvas extends JPanel {  
        
        private final static int AREA_SIZE = 400;
        private BufferedImage image = new BufferedImage(AREA_SIZE, AREA_SIZE, BufferedImage.TYPE_INT_ARGB);
        
        /**
         * Creates a new instance of MyCanvas with a white background.
         * Sets the dimensions to the value of AREA_SIZE.
         */
        public MyCanvas() {
            setBackground(Color.WHITE);
            setPreferredSize(new Dimension(AREA_SIZE, AREA_SIZE));
        }
        
        /**
         * Creates a new instance of MyCanvas with the given color as backgorund color.
         * Sets the dimensions to the given width and height.
         */
        public MyCanvas(int width, int height, Color color) {
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
         * Adds a single rectangle and repaints the component.
         */
        public void addRectangle(Rectangle rect, Color color)
        {
            //  Draw the Rectangle onto the BufferedImage
            Graphics2D g2d = (Graphics2D)image.getGraphics();
            g2d.setColor(color);
            g2d.fillRect((int)rect.getX(), (int)rect.getY(), (int)rect.getWidth(), (int)rect.getHeight());
            //revalidate();
            repaint();
        }
        
        /**
         * Clears the component by painting an empty image.
         */
        public void clear()
        {
            createEmptyImage();
            repaint();
        }
        
        /*
        public void paint(Graphics g) {
            g.setColor(Color.blue);
            g.fillRect(10, 10, 200, 200);  
        }
        */
    }
}