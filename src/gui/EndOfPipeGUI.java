package gui;

import model.EndOfPipe;

import javax.swing.*;
import java.awt.*;

public class EndOfPipeGUI {
    private EndOfPipe endOfPipe;
    public ImageIcon endOfPipeImg;

    public EndOfPipeGUI(EndOfPipe endOfPipe) {
        this.endOfPipe = endOfPipe;
        if (!endOfPipe.currentPipe.vertical) {
            endOfPipeImg = new ImageIcon("src\\gui\\images\\endOfPipeImg.png");
        } else {
            endOfPipeImg = new ImageIcon("src\\gui\\images\\endOfPipeImg_vertical.png");
        }
    }

    public void draw(Graphics g) {
        Point pos = endOfPipe.getPosition();
        g.drawImage(endOfPipeImg.getImage(), pos.x, pos.y, null);
    }
}