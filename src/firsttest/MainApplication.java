package firsttest;

import sun.applet.Main;

import java.awt.*;

/**
 * Class to test functionalities of the firsttest.MainWindowAWT class.
 *
 * @author Christopher Wagner
 * @version 2017-04-04
 */
public class MainApplication
{
    private MainWindowAWT window;
    private Rectangle[] rects;
    
    public MainApplication() {
        window = new MainWindowAWT("rectangle example", 400, 700);
        rects = new Rectangle[3];
        rects[0] = new Rectangle(0,0,50,50);
        rects[1] = new Rectangle(100,0,50,50);
        rects[2] = new Rectangle(200,0,50,50);
        window.drawRects(rects, Color.BLUE);
        /*
        window.drawRect(0,0,100,150,Color.BLUE);
        window.drawRect(150,0,100,150,Color.GREEN);
        */
       for (int i = 0; i < 10; i++) {
           try {
               Thread.sleep(100);
           } catch (Exception e) {
               
           }
           moveRectOne(0, i);
       }
    }
    
    public MainApplication(int width, int height)
    {
        window = new MainWindowAWT("rectangle example", width, height);
        rects = new Rectangle[3];
        rects[0] = new Rectangle(0,0,50,50);
        rects[1] = new Rectangle(100,0,50,50);
        rects[2] = new Rectangle(200,0,50,50);
        window.drawRects(rects, Color.BLUE);
        /*
        window.drawRect(0,0,100,150,Color.BLUE);
        window.drawRect(150,0,100,150,Color.GREEN);
        */
       for (int i = 0; i < 10; i++) {
           try {
               Thread.sleep(100);
           } catch (Exception e) {
               
           }
           moveRectOne(0, i);
       }
    }

    public void moveRectOne(int distanceX, int distanceY) {
        Rectangle r = rects[0];
        int currentX = (int)r.getX();
        int currentY = (int)r.getY();
        rects[0].setLocation(currentX + distanceX, currentY + distanceY);
        window.clear();
        window.drawRects(rects, Color.BLUE);
    }


    public static void main (String[] args) {
        MainApplication m = new MainApplication();
    }
}
