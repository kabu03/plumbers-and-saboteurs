package gui;

import model.Pipe;

import javax.swing.*;
import java.awt.*;

/**
 * The PipeGUI class represents the graphical user interface for displaying a pipe.
 * It handles loading and scaling the pipe image based on its orientation and state (working or punctured).
 */
public class PipeGUI {
    private Pipe pipe;
    public ImageIcon pipeImg;

    /**
     * Constructs a PipeGUI with the specified Pipe model.
     * Loads and scales the appropriate image based on the pipe's orientation and state.
     *
     * @param pipe the Pipe object representing the model of the pipe
     */
    public PipeGUI(Pipe pipe) {
        this.pipe = pipe;
        Image originalImage;
        if (pipe.vertical) {
            originalImage = new ImageIcon("src\\gui\\images\\PipeVERTICAL.png").getImage();
        } else {
            originalImage = new ImageIcon("src\\gui\\images\\Pipe.png").getImage();
        }
        Image scaledImage = originalImage.getScaledInstance(pipe.width, pipe.height, Image.SCALE_SMOOTH);
        this.pipeImg = new ImageIcon(scaledImage);
    }

    /**
     * Draws the pipe image at the pipe's position on the given graphics context.
     * If the pipe is not working (punctured), it loads and draws the punctured pipe image.
     *
     * @param g the Graphics context on which to draw the pipe image
     */
    public void draw(Graphics g) {
        if (!pipe.isWorking()) {
            Image originalImage;
            if (pipe.vertical) {
                originalImage = new ImageIcon("src\\gui\\images\\PuncturedPipeVERTICAL.png").getImage();
            } else {
                originalImage = new ImageIcon("src\\gui\\images\\PuncturedPipe.png").getImage();
            }
            Image scaledImage = originalImage.getScaledInstance(pipe.width, pipe.height, Image.SCALE_SMOOTH);
            pipeImg = new ImageIcon(scaledImage);
        }
        Point pos = pipe.getPosition();
        g.drawImage(pipeImg.getImage(), pos.x, pos.y, null);
    }
}
