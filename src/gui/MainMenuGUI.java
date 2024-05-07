package gui;

import model.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MainMenuGUI extends JFrame {
    public MainMenuGUI() {
        setTitle("Pipes in the Desert - Team Mansaf");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center on screen
        setLayout(new BorderLayout());
        setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);

        JPanel background = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon bgIcon = new ImageIcon("src\\gui\\images\\desert.jpg");
                g.drawImage(bgIcon.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        background.setLayout(new FlowLayout(FlowLayout.CENTER, 150, 100));

        JButton proceedButton = new JButton("Proceed");
        JButton instructionsButton = new JButton("Instructions");
        JButton exitButton = new JButton("Exit");

        styleButton(proceedButton);
        styleButton(instructionsButton);
        styleButton(exitButton);

        proceedButton.addActionListener(this::onProceed);
        instructionsButton.addActionListener(this::onInstructions);
        exitButton.addActionListener(e -> System.exit(0));

        background.add(proceedButton);
        background.add(instructionsButton);
        background.add(exitButton);

        add(background, BorderLayout.CENTER);
        setVisible(true);
    }

    private void styleButton(JButton button) {
        button.setFont(new Font("Tahoma", Font.BOLD, 28));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(139, 69, 19));
        button.setFocusPainted(false);
        button.setBorderPainted(true);
        button.setPreferredSize(new Dimension(250, 100));
        button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
    }

    private void onProceed(ActionEvent e) {
        System.out.println("Proceed to game setup");
        Game game = new Game(); // Assuming Game class has a no-argument constructor
        new ConfigurationGUI(game); // Start with default 4 players, change as necessary
    }

    private void onInstructions(ActionEvent e) {
        JDialog instructionsDialog = new JDialog(this, "Game Instructions", true);
        instructionsDialog.setSize(800, 600);
        instructionsDialog.setLayout(new BorderLayout());

        JTextArea instructionsText = new JTextArea("Welcome to Pipes in the Desert by Team Mansaf!\n\n" +
                "The goal of Plumbers is to connect the spring to their city's cisterns through the desert using the sophisticated pipe system.\n" +
                "Saboteurs, on the other hand, aim to prevent Plumbers from achieving their goal by sabotaging the pipe system.\n\n" +
                "The game is turn-based, with each player taking turns to perform actions such as moving to different elements, changing the input/output pipes of pumps, puncturing pipes, and more.\n" +
                "A player's turn is over if they perform two actions or five seconds pass. Then, it's the next player's turn.\n\n" +
                "After the timer of the game ends, the amount of leaked water will be compared to the amount of water collected at the cisterns.\n" +
                "If the amount of leaked water is less than the amount of water collected, the Plumbers win. Otherwise, the Saboteurs win.\n\n" +
                "You can view the timer and the scores on the top of the game screen, and the key bindings for each action are displayed on the bottom side.\n\n" +
                "Some actions require the player to select an element on the game board. To do so, click on the element you want to interact with.\n\n" +
                "Good luck and have fun!");
        instructionsText.setFont(new Font("Serif", Font.PLAIN, 18));
        instructionsText.setWrapStyleWord(true);
        instructionsText.setLineWrap(true);
        instructionsText.setEditable(false);
        instructionsText.setForeground(new Color(139, 69, 19)); // Brown text

        JButton backButton = new JButton("Back to Main Menu");
        styleButton(backButton);
        backButton.addActionListener(ev -> instructionsDialog.dispose());

        instructionsDialog.add(new JScrollPane(instructionsText), BorderLayout.CENTER);
        instructionsDialog.add(backButton, BorderLayout.SOUTH);

        instructionsDialog.setLocationRelativeTo(this);
        instructionsDialog.setVisible(true);
    }
}
