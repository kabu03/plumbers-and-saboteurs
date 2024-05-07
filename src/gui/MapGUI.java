package gui;

import model.*;
import model.Spring;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MapGUI extends JPanel implements KeyListener {
    private Element selectedElement;
    private javax.swing.Timer refreshTimer;
    public Image tileImage = new ImageIcon("src\\gui\\images\\MapTiles2.png").getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
    private Game game;

    public MapGUI(Game game) {
        this.game = game;
        setupUI();
        setupRefreshTimer();
    }

    private void setupUI() {
        setPreferredSize(new Dimension(800, 600));
        setBackground(Color.WHITE);
        setFocusable(true);
        addKeyListener(this);
        requestFocusInWindow();
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                selectObjectAt(e.getX(), e.getY());
                repaint();
            }
        });
    }

    private void setupRefreshTimer() {
        refreshTimer = new javax.swing.Timer(1000, e -> repaint()); // Refresh every second
        refreshTimer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int iw = tileImage.getWidth(this);
        int ih = tileImage.getHeight(this);
        if (iw > 0 && ih > 0) { // Make sure the image has loaded correctly
            for (int x = 0; x < getWidth(); x += iw) {
                for (int y = 70; y < getHeight(); y += ih) {
                    g.drawImage(tileImage, x, y, iw, ih, this);
                }
            }
            drawElements(g);
            drawPlayers(g);
            drawTimer(g);
            drawScores(g);
        }
        if (selectedElement != null) {
            g.setColor(Color.RED); // Set highlight color
            Point pos = selectedElement.getPosition();
            for (int i = 0; i < 5; i++) { // Change '5' to the desired border thickness
                g.drawRect(pos.x - i, pos.y - i, selectedElement.width + 2 * i, selectedElement.height + 2 * i);
            }
        }

        g.setColor(Color.BLACK); // Reset color for other drawing
    }

    private void drawTimer(Graphics g) {
        if (game.timer != null) {
            String time = game.timer.getCurrentTimeFormatted();
            g.setColor(Color.BLACK);
            g.setFont(new Font("SansSerif", Font.BOLD, 50));
            int x = getWidth() - 135; // Position from the right edge
            int y = 55; // Margin from the top
            g.drawString(time, x, y);
        }
    }
    private void drawScores(Graphics g) {
        int collectedWater = game.calculateCollectedWater();
        int leakedWater = game.calculateLeakedWater();

        g.setColor(Color.BLACK);
        g.setFont(new Font("SansSerif", Font.BOLD, 30));
        int x = 10; // Margin from the left edge
        int y = 30; // Margin from the top

        g.drawString("Team Plumbers: " + collectedWater, x, y);
        g.drawString("Team Saboteurs: " + leakedWater, x, y + 30); // Slightly below the first string
    }
    private void selectObjectAt(int x, int y) {
        for (Element e : game.elementList) {
            if (e.contains(x, y)) {
                selectedElement = e;
                break;
            }
        }
    }
    private void drawElements(Graphics g) {
        for (Pipe pipe : game.pipeList) {
            new PipeGUI(pipe).draw(g);
        }
        for (Pump pump : game.pumpList) {
            new PumpGUI(pump).draw(g);
        }
        for (Cistern cistern : game.cisternList) {
            new CisternGUI(cistern).draw(g);
        }
        for (Spring spring : game.springList) {
            new SpringGUI(spring).draw(g);
        }
    }
    private void drawPlayers(Graphics g){
        for (Saboteur saboteur : game.saboteurs) {
            new SaboteurGUI(saboteur).draw(g);
        }
        for (Plumber plumber : game.plumbers) {
            new PlumberGUI(plumber).draw(g);
        }
    }
    @Override
    public void keyTyped(KeyEvent e) { }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyChar() == 'q' || e.getKeyChar() == 'Q') {
            repaint();  // Repaint the component when Q is pressed
        }
    }

    @Override
    public void keyReleased(KeyEvent e) { }

}

