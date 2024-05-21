package gui;

import model.Plumber;

import javax.swing.*;
import java.awt.*;

/**
 * The PlumberGUI class represents the graphical user interface for displaying a plumber.
 * It handles loading and scaling the plumber image and drawing it on a given graphics context.
 */
public class PlumberGUI {
    private Plumber plumber;
    public ImageIcon plumberImg;

    /**
     * Constructs a PlumberGUI with the specified Plumber model.
     * Loads and scales the plumber image.
     *
     * @param plumber the Plumber object representing the model of the plumber
     */
    public PlumberGUI(Plumber plumber) {
        this.plumber = plumber;
        Image originalImage = new ImageIcon("src\\gui\\images\\Plumber.png").getImage();
        Image scaledImage = originalImage.getScaledInstance(100, 100, Image.SCALE_SMOOTH); // Change the width and height to your desired size
        this.plumberImg = new ImageIcon(scaledImage);
    }

    /**
     * Draws the plumber image at the plumber's position on the given graphics context.
     *
     * @param g the Graphics context on which to draw the plumber image
     */
    public void draw(Graphics g) {
        Point pos = plumber.getPosition();
        g.drawImage(plumberImg.getImage(), pos.x, pos.y, null);
    }
}

