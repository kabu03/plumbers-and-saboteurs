package gui;

import model.Saboteur;

import javax.swing.*;
import java.awt.*;

/**
 * The SaboteurGUI class represents the graphical user interface for displaying a saboteur.
 * It handles loading and scaling the saboteur image and drawing it on a given graphics context.
 */
public class SaboteurGUI {
    private Saboteur saboteur;
    public ImageIcon saboteurImg;

    /**
     * Constructs a SaboteurGUI with the specified Saboteur model.
     * Loads and scales the saboteur image.
     *
     * @param saboteur the Saboteur object representing the model of the saboteur
     */
    public SaboteurGUI(Saboteur saboteur) {
        this.saboteur = saboteur;
        Image originalImage = new ImageIcon("src\\gui\\images\\Saboteur.png").getImage();
        Image scaledImage = originalImage.getScaledInstance(100, 100, Image.SCALE_SMOOTH); // Change the width and height to your desired size
        this.saboteurImg = new ImageIcon(scaledImage);
    }

    /**
     * Draws the saboteur image at the saboteur's position on the given graphics context.
     *
     * @param g the Graphics context on which to draw the saboteur image
     */
    public void draw(Graphics g) {
        Point pos = saboteur.getPosition();
        g.drawImage(saboteurImg.getImage(), pos.x, pos.y, null);
    }
}
