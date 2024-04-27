import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
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
        System.out.println("Welcome to Pipes in the Desert Game (Prototype Version) by Team Mansaf!");
        System.out.println("1. Proceed to username and team selection");
        System.out.println("2. Run a pre-defined test");
        System.out.println("3. Exit");

        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                Game game = new Game(false);
                game.initGame();
                break;
            case 2:
                int num = 0;
                do {
                    System.out.println("There are 8 pre-defined tests you can choose from.");
                    System.out.println("For each, an output file will be generated in the tests folder that you can compare with the expected output.");
                    System.out.println("Which one would you like to run?");
                    num = scanner.nextInt();
                    if(num < 1 || num > 8) {
                        System.out.println("Invalid test case number. Please enter a number between 1 and 8.");
                    }
                } while(num < 1 || num > 8);

                testProcessing(num);
                Game testGame = new Game(true);
                Game.testNumber = num;
                testGame.initGame();
                break;
            case 3:
                System.out.println("Exiting the game. Goodbye!");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice. Exiting the game.");
                System.exit(0);
        }
    }

public static void testProcessing(int testNum){
        Game.inputFilePath = "tests/" + testNum + "/input.txt";
        Game.outputFilePath = "tests/" + testNum + "/output.txt";
}
}




