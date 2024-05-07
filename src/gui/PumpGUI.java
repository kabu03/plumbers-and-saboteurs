package gui;

import model.Pump;

import javax.swing.*;
import java.awt.*;

public class PumpGUI {
    private Pump pump;
    public ImageIcon pumpImg = new ImageIcon("images/model.Pump.png");

    public PumpGUI(Pump pump){
        this.pump = pump;
        Image originalImage = new ImageIcon("src\\gui\\images\\Pump.png").getImage();
        Image scaledImage = originalImage.getScaledInstance(pump.width, pump.height, Image.SCALE_SMOOTH); // Change the width and height to your desired size
        this.pumpImg = new ImageIcon(scaledImage);
    }
    public void draw(Graphics g){
        Point pos = pump.getPosition();
        g.drawImage(pumpImg.getImage(), pos.x, pos.y, null);
    }
}
