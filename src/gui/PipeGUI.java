package gui;

import model.Pipe;

import javax.swing.*;
import java.awt.*;

public class PipeGUI {
    private Pipe pipe;
    public ImageIcon pipeImg = new ImageIcon("images/model.Pipe.png");
    public PipeGUI(Pipe pipe){
        this.pipe = pipe;
    }


    public void draw(Graphics g){
        Point pos = pipe.getPosition();
        g.drawImage(pipeImg.getImage(), pos.x, pos.y, null);
    }
}
