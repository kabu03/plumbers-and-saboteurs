package gui;

import model.Pump;

import javax.swing.*;
import java.awt.*;

/**
 * The PumpGUI class represents the graphical user interface for displaying a pump.
 * It handles loading and scaling the pump image and drawing it on a given graphics context.
 */
public class PumpGUI {
    private Pump pump;
    public ImageIcon pumpImg;

    /**
     * Constructs a PumpGUI with the specified Pump model.
     * Loads and scales the pump image.
     *
     * @param pump the Pump object representing the model of the pump
     */
    public PumpGUI(Pump pump) {
        this.pump = pump;
        Image originalImage = new ImageIcon("src\\gui\\images\\Pump.png").getImage();
        Image scaledImage = originalImage.getScaledInstance(pump.width, pump.height, Image.SCALE_SMOOTH);
        this.pumpImg = new ImageIcon(scaledImage);
    }

    /**
     * Draws the pump image at the pump's position on the given graphics context.
     * If the pump is not visible, it will not be drawn.
     *
     * @param g the Graphics context on which to draw the pump image
     */
    public void draw(Graphics g) {
        if (!pump.isVisible()) {
            return; // Do not draw if the pump is not visible
        }

        Point pos = pump.getPosition();
        g.drawImage(pumpImg.getImage(), pos.x, pos.y, null);
    }
}
