package gui;
import model.Spring;
import javax.swing.*;
import java.awt.*;

public class SpringGUI {
    private Spring spring;
    public ImageIcon springImg = new ImageIcon("images/model.Spring.png");
    public SpringGUI(Spring spring){
        this.spring = spring;
    }


    public void draw(Graphics g){
        Point pos = spring.getPosition();
        g.drawImage(springImg.getImage(), pos.x, pos.y, null);
    }
}
