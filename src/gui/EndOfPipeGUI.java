package gui;

import model.EndOfPipe;

import javax.swing.*;
import java.awt.*;

/**
 * The EndOfPipeGUI class represents the graphical user interface for displaying the end of a pipe.
 * It handles loading the appropriate image based on the pipe's orientation and drawing it on a given graphics context.
 */
public class EndOfPipeGUI {
    private EndOfPipe endOfPipe;
    public ImageIcon endOfPipeImg;

    /**
     * Constructs an EndOfPipeGUI with the specified EndOfPipe model.
     * Loads the appropriate image based on the pipe's orientation.
     *
     * @param endOfPipe the EndOfPipe object representing the model of the end of the pipe
     */
    public EndOfPipeGUI(EndOfPipe endOfPipe) {
        this.endOfPipe = endOfPipe;
        Image originalImage;
        if (!endOfPipe.currentPipe.vertical) {
            originalImage = new ImageIcon("src\\gui\\images\\endOfPipeImg.png").getImage();
        } else {
            originalImage = new ImageIcon("src\\gui\\images\\endOfPipeImg_vertical.png").getImage();
        }
        Image scaledImage = originalImage.getScaledInstance(endOfPipe.width, endOfPipe.height, Image.SCALE_SMOOTH);
        this.endOfPipeImg = new ImageIcon(scaledImage);
    }

    /**
     * Draws the end of the pipe image at the end of pipe's position on the given graphics context.
     *
     * @param g the Graphics context on which to draw the end of pipe image
     */
    public void draw(Graphics g) {
        Point pos = endOfPipe.getPosition();
        g.drawImage(endOfPipeImg.getImage(), pos.x, pos.y, null);
    }
}
