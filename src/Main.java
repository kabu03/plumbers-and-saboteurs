import java.util.*;
/**
 * The {@code Main} class serves as the entry point for the Pipes in the Desert CLI Game.
 * This class is responsible for presenting the initial game menu to the user and handling
 * their selection to either start the game or exit. It creates an instance of the {@code Game}
 * class and initializes the game based on user input.
 * <p>
 * Upon execution, the user is greeted with a welcome message and presented with options to
 * either start the game or exit. Selecting to start the game will initialize the game's
 * environment and settings, whereas choosing to exit will terminate the application.
 * </p>
 */
public class Main {
    /**
     * Main method to run the game.
     * It displays a simple command-line interface for the user to start the game or exit.
     * User choices are handled through basic switch-case logic.
     *
     * @param args The command-line arguments. Not used in this application.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Pipes in the Desert CLI Game by Team MANSAF!");
        System.out.println("Are you ready to start?");
        System.out.println("1. Start Game");
        System.out.println("2. Exit");

        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                Game game = new Game();
                game.initGame();
                break;
            case 2:
                System.out.println("Exiting the game. Goodbye!");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice. Exiting the game.");
                System.exit(0);
        }
    }
}
