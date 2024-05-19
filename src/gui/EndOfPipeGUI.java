package gui;

import model.EndOfPipe;

import javax.swing.*;
import java.awt.*;

public class EndOfPipeGUI {
    private EndOfPipe endOfPipe;
    public ImageIcon endOfPipeImg;

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

    public void draw(Graphics g) {
        Point pos = endOfPipe.getPosition();
        g.drawImage(endOfPipeImg.getImage(), pos.x, pos.y, null);
    }
}