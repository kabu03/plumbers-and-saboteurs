package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class EndGameGUI extends JDialog {
    private int waterCollected;
    private int waterLeaked;

    public EndGameGUI(int waterCollected, int waterLeaked) {
        this.waterCollected = waterCollected;
        this.waterLeaked = waterLeaked;
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Game Over");
        setSize(600, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setModalityType(ModalityType.APPLICATION_MODAL);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.BLACK);
        add(panel);

        String winnerText = determineWinner();
        JLabel titleLabel = new JLabel("Game over! Final scores are below.", SwingConstants.CENTER);
        titleLabel.setForeground(Color.RED);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 30));
        JLabel scoreLabel = new JLabel("Team Plumbers: " + waterCollected + " | Team Saboteurs: " + waterLeaked, SwingConstants.CENTER);
        scoreLabel.setForeground(Color.RED);
        scoreLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        JLabel winnerLabel = new JLabel(winnerText, SwingConstants.CENTER);
        winnerLabel.setForeground(Color.RED);
        winnerLabel.setFont(new Font("SansSerif", Font.BOLD, 30));

        panel.add(titleLabel, BorderLayout.NORTH);
        panel.add(scoreLabel, BorderLayout.CENTER);
        panel.add(winnerLabel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private String determineWinner() {
        if (waterLeaked > waterCollected) {
            return "The Saboteurs won the game!";
        } else if (waterLeaked < waterCollected) {
            return "The Plumbers won the game!";
        } else {
            return "The game ended in a tie!";
        }
    }
}