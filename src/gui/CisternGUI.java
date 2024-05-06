package gui;
import javax.swing.*;
import java.awt.*;
import model.Cistern;
public class CisternGUI {
    private Cistern cistern;
    public CisternGUI(Cistern cistern){
        this.cistern = cistern;
    }
    public ImageIcon cisternImg = new ImageIcon("images/cistern.png");


    public void draw(Graphics g){
        Point pos = cistern.getPosition();
        g.drawImage(cisternImg.getImage(), pos.x, pos.y, null);
    }
}
