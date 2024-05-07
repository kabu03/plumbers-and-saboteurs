package gui;
import javax.swing.*;
import java.awt.*;
import model.Cistern;

public class CisternGUI {
    private Cistern cistern;
    public ImageIcon cisternImg;
    public CisternGUI(Cistern cistern){
        this.cistern = cistern;
        Image originalImage = new ImageIcon("src\\gui\\images\\Cistern.png").getImage();
        Image scaledImage = originalImage.getScaledInstance(cistern.width, cistern.height, Image.SCALE_SMOOTH); // Change the width and height to your desired size
        this.cisternImg = new ImageIcon(scaledImage);
    }

    public void draw(Graphics g){
        Point pos = cistern.getPosition();
        g.drawImage(cisternImg.getImage(), pos.x, pos.y, null);
    }
}