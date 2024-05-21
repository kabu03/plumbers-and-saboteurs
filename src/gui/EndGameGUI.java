package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * The EndGameGUI class represents a dialog that is displayed at the end of the game,
 * showing the final scores and the winner.
 */
public class EndGameGUI extends JDialog {
    private int waterCollected;
    private int waterLeaked;

    /**
     * Constructs an EndGameGUI dialog with the specified scores for water collected and water leaked.
     *
     * @param waterCollected the amount of water collected by the Plumbers
     * @param waterLeaked    the amount of water leaked by the Saboteurs
     */
    public EndGameGUI(int waterCollected, int waterLeaked) {
        this.waterCollected = waterCollected;
        this.waterLeaked = waterLeaked;
        initializeUI();
    }

    /**
     * Initializes the user interface for the end game dialog.
     * Sets up the title, size, layout, and window close operation.
     */
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

    /**
     * Determines the winner based on the scores of water collected and water leaked.
     *
     * @return a String indicating the winning team or if the game ended in a tie
     */
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
