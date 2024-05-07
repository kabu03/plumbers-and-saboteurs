package gui;
import model.Spring;
import javax.swing.*;
import java.awt.*;

public class SpringGUI {
    private Spring spring;
    public ImageIcon springImg;
    public SpringGUI(Spring spring){
        this.spring = spring;
        Image originalImage = new ImageIcon("src\\gui\\images\\Spring.png").getImage();
        Image scaledImage = originalImage.getScaledInstance(spring.width, spring.height, Image.SCALE_SMOOTH); // Change the width and height to your desired size
        this.springImg = new ImageIcon(scaledImage);
    }


    public void draw(Graphics g){
        Point pos = spring.getPosition();
        g.drawImage(springImg.getImage(), pos.x, pos.y, null);
    }
}
