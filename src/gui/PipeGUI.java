package gui;

import model.Pipe;

import javax.swing.*;
import java.awt.*;

public class PipeGUI {
    private Pipe pipe;
    public ImageIcon pipeImg;

    public PipeGUI(Pipe pipe){
        this.pipe = pipe;
        Image originalImage;
        if(pipe.vertical){
        originalImage = new ImageIcon("src\\gui\\images\\PipeVERTICAL.png").getImage();}
        else{
            originalImage = new ImageIcon("src\\gui\\images\\Pipe.png").getImage();
        }
        Image scaledImage = originalImage.getScaledInstance(pipe.width, pipe.height, Image.SCALE_SMOOTH);
        this.pipeImg = new ImageIcon(scaledImage);
    }

    public void draw(Graphics g) {
        if (!pipe.isWorking() && pipe.vertical){
            pipeImg = new ImageIcon("src\\gui\\images\\PuncturedPipeVERTICAL.png");
        }
        else if (!pipe.isWorking()){
            pipeImg = new ImageIcon("src\\gui\\images\\PuncturedPipe.png");
        }
        Point pos = pipe.getPosition();
        g.drawImage(pipeImg.getImage(), pos.x, pos.y, null);
    }
}
