package gui;

import javax.swing.*;
import java.awt.*;
import model.Cistern;

/**
 * The CisternGUI class represents a graphical user interface for displaying a cistern.
 * It handles loading and scaling the cistern image and drawing it on a given graphics context.
 */
public class CisternGUI {
    private Cistern cistern;
    public ImageIcon cisternImg;

    /**
     * Constructs a CisternGUI with the specified Cistern.
     * Loads and scales the cistern image to match the cistern's dimensions.
     *
     * @param cistern the Cistern object representing the cistern model
     */
    public CisternGUI(Cistern cistern){
        this.cistern = cistern;
        Image originalImage = new ImageIcon("src\\gui\\images\\Cistern.png").getImage();
        Image scaledImage = originalImage.getScaledInstance(cistern.width, cistern.height, Image.SCALE_SMOOTH); // Change the width and height to your desired size
        this.cisternImg = new ImageIcon(scaledImage);
    }

    /**
     * Draws the cistern image at the cistern's position on the given graphics context.
     *
     * @param g the Graphics context on which to draw the cistern image
     */
    public void draw(Graphics g){
        Point pos = cistern.getPosition();
        g.drawImage(cisternImg.getImage(), pos.x, pos.y, null);
    }
}
