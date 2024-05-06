package gui;

import model.Pump;

import javax.swing.*;
import java.awt.*;

public class PumpGUI {
    private Pump pump;
    public ImageIcon pumpImg = new ImageIcon("images/model.Pump.png");

    public PumpGUI(Pump pump){
        this.pump = pump;
    }
    public void draw(Graphics g){
        Point pos = pump.getPosition();
        g.drawImage(pumpImg.getImage(), pos.x, pos.y, null);
    }
}
