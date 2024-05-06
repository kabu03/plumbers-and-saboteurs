package gui;

import model.Cistern;
import model.Game;
import model.Pipe;
import model.Pump;
import model.Spring;

import javax.swing.*;
import java.awt.*;

public class MapGUI extends JPanel {
    private Game game;

    public MapGUI(Game game) {
        this.game = game;
        setPreferredSize(new Dimension(800, 600)); // Set as needed
        setBackground(Color.BLACK); // Or set a suitable background image or color
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawElements(g);
    }

    private void drawElements(Graphics g) {
        // Draw pipes
        for (Pipe pipe : game.pipeList) {
            new PipeGUI(pipe).draw(g);
        }
        // Draw pumps
        for (Pump pump : game.pumpList) {
            new PumpGUI(pump).draw(g);
        }
        // Similarly draw cisterns and springs
        for (Cistern cistern : game.cisternList) {
            new CisternGUI(cistern).draw(g);
        }
        for (Spring spring : game.springList) {
            new SpringGUI(spring).draw(g);
        }
    }
}

