package gui;

import model.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class ConfigurationGUI extends JFrame {
    private final List<JTextField> nameFields = new ArrayList<>();
    private final List<JRadioButton> plumberButtons = new ArrayList<>();
    private final List<JRadioButton> saboteurButtons = new ArrayList<>();
    private final ButtonGroup playerCountGroup = new ButtonGroup();
    private final JPanel playerPanel = new JPanel(new GridLayout(0, 1));
    private Game game;

    public ConfigurationGUI(Game game) {
        this.game = game;
        initializeForm();
    }

    private void initializeForm() {
        setTitle("Game Configuration");
        setSize(500, 700);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        setupPlayerOptions();
        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(this::collectData);
        add(submitButton, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void setupPlayerOptions() {
        JPanel optionsPanel = new JPanel();
        JRadioButton fourPlayers = new JRadioButton("2v2", true);
        JRadioButton sixPlayers = new JRadioButton("3v3", false);

        fourPlayers.setActionCommand("4");
        sixPlayers.setActionCommand("6");

        playerCountGroup.add(fourPlayers);
        playerCountGroup.add(sixPlayers);

        optionsPanel.add(fourPlayers);
        optionsPanel.add(sixPlayers);

        fourPlayers.addActionListener(e -> updatePlayerInputs(4));
        sixPlayers.addActionListener(e -> updatePlayerInputs(6));

        add(optionsPanel, BorderLayout.NORTH);
        updatePlayerInputs(4);  // Default to 4 players initially
    }

    private void updatePlayerInputs(int numPlayers) {
        playerPanel.removeAll();
        nameFields.clear();
        plumberButtons.clear();
        saboteurButtons.clear();

        for (int i = 0; i < numPlayers; i++) {
            JPanel row = new JPanel();
            JTextField playerName = new JTextField(10);
            JRadioButton plumber = new JRadioButton("Plumber");
            JRadioButton saboteur = new JRadioButton("Saboteur");
            ButtonGroup teamGroup = new ButtonGroup();
            teamGroup.add(plumber);
            teamGroup.add(saboteur);

            row.add(new JLabel("Player " + (i + 1) + ": "));
            row.add(playerName);
            row.add(plumber);
            row.add(saboteur);

            nameFields.add(playerName);
            plumberButtons.add(plumber);
            saboteurButtons.add(saboteur);

            playerPanel.add(row);
        }
        add(playerPanel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    private void collectData(ActionEvent e) {
        List<String> names = new ArrayList<>();
        List<Boolean> isPlumberList = new ArrayList<>();
        int plumbers = 0, saboteurs = 0;

        for (int i = 0; i < nameFields.size(); i++) {
            String name = nameFields.get(i).getText();
            boolean isPlumber = plumberButtons.get(i).isSelected();
            names.add(name);
            isPlumberList.add(isPlumber);

            if (isPlumber) plumbers++;
            else saboteurs++;
        }

        if (plumbers != saboteurs) {
            JOptionPane.showMessageDialog(this, "Teams must be balanced: equal numbers of Plumbers and Saboteurs required.");
        } else {
            JOptionPane.showMessageDialog(this, "Configuration successful. Click OK to start the game!");
            dispose();  // Close this window
            launchGameMap(); // subject to change
            game.configureGame(names, isPlumberList);
        }
    }
    private void launchGameMap() {
        JFrame gameFrame = new JFrame("Game Map");
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.setLayout(new BorderLayout());
        MapGUI mapGUI = new MapGUI(game);
        gameFrame.add(mapGUI, BorderLayout.CENTER);
        gameFrame.pack();
        gameFrame.setLocationRelativeTo(null);
        gameFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        gameFrame.setVisible(true);
    }
}
