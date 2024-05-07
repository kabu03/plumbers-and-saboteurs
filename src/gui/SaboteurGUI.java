package gui;

import model.Saboteur;

import javax.swing.*;
import java.awt.*;

public class SaboteurGUI {
    private Saboteur saboteur;
    public ImageIcon saboteurImg = new ImageIcon("images/model.Saboteur.png");
    public SaboteurGUI(Saboteur saboteur){
        this.saboteur = saboteur;
        Image originalImage = new ImageIcon("src\\gui\\images\\Saboteur.png").getImage();
        Image scaledImage = originalImage.getScaledInstance(100, 100, Image.SCALE_SMOOTH); // Change the width and height to your desired size
        this.saboteurImg = new ImageIcon(scaledImage);
    }

    public void draw(Graphics g){
        Point pos = saboteur.getPosition();
        g.drawImage(saboteurImg.getImage(), pos.x, pos.y, null);
    }
}
