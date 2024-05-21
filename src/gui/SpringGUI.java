package gui;

import model.Spring;

import javax.swing.*;
import java.awt.*;

/**
 * The SpringGUI class represents the graphical user interface for displaying a spring.
 * It handles loading and scaling the spring image and drawing it on a given graphics context.
 */
public class SpringGUI {
    private Spring spring;
    public ImageIcon springImg;

    /**
     * Constructs a SpringGUI with the specified Spring model.
     * Loads and scales the spring image.
     *
     * @param spring the Spring object representing the model of the spring
     */
    public SpringGUI(Spring spring) {
        this.spring = spring;
        Image originalImage = new ImageIcon("src\\gui\\images\\Spring.png").getImage();
        Image scaledImage = originalImage.getScaledInstance(spring.width, spring.height, Image.SCALE_SMOOTH);
        this.springImg = new ImageIcon(scaledImage);
    }

    /**
     * Draws the spring image at the spring's position on the given graphics context.
     *
     * @param g the Graphics context on which to draw the spring image
     */
    public void draw(Graphics g) {
        Point pos = spring.getPosition();
        g.drawImage(springImg.getImage(), pos.x, pos.y, null);
    }
}
