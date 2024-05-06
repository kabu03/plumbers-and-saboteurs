package gui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MainMenuGUI extends JFrame {
    public MainMenuGUI() {
        setTitle("Pipes in the Desert - Team Mansaf");
        setSize(1024, 600); // Adjust size as needed
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center on screen
        setLayout(new BorderLayout());

        // Background panel with a desert image
        JPanel background = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon bgIcon = new ImageIcon("src\\gui\\images\\desert.jpg");
                g.drawImage(bgIcon.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        background.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 200)); // Adjust for layout preferences

        // Styling buttons
        JButton proceedButton = new JButton("Proceed");
        styleButton(proceedButton);
        proceedButton.addActionListener(this::onProceed);

        JButton exitButton = new JButton("Exit");
        styleButton(exitButton);
        exitButton.addActionListener(e -> System.exit(0));

        // Adding buttons to the background panel
        background.add(proceedButton);
        background.add(exitButton);

        // Add background to the frame
        add(background, BorderLayout.CENTER);
        setVisible(true);
    }

    private void styleButton(JButton button) {
        button.setFont(new Font("Tahoma", Font.BOLD, 20));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(139, 69, 19)); // A brown color
        button.setFocusPainted(false);
        button.setBorderPainted(true); // Ensure the border is painted
        button.setPreferredSize(new Dimension(200, 50)); // Set preferred size for uniformity

        // Adding a simple line border
        button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
    }

    private void onProceed(ActionEvent e) {
        // Placeholder for what happens when 'Proceed' is clicked
        System.out.println("Proceed to game setup");
        // Transition to game setup or next part of the GUI
    }

    public static void main(String[] args) {
        new MainMenuGUI();
    }
}

