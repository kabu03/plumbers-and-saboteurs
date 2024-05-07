package gui;

import model.Plumber;

import javax.swing.*;
import java.awt.*;

public class PlumberGUI {
    private Plumber plumber;
    public ImageIcon plumberImg = new ImageIcon("images/model.Plumber.png");
    public PlumberGUI(Plumber plumber){
        this.plumber = plumber;
        Image originalImage = new ImageIcon("src\\gui\\images\\Plumber.png").getImage();
        Image scaledImage = originalImage.getScaledInstance(100, 100, Image.SCALE_SMOOTH); // Change the width and height to your desired size
        this.plumberImg = new ImageIcon(scaledImage);
    }


    public void draw(Graphics g){
        Point pos = plumber.getPosition();
        g.drawImage(plumberImg.getImage(), pos.x, pos.y, null);
    }
}
