import java.lang.*;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.InputMismatchException;

/**
 * Represents individual players in the game, each being part of a team as plumbers or saboteurs.
 * Players take turns to perform actions such as moving, repairing pipes, sabotaging the system,
 * transferring water, and managing pump operations including fixing, inserting, changing input/output
 * pipes, and setting water flow direction. Each player is identified by their name, team, and index number.
 */
public abstract class Player {
    public String playerName;

    public Element currentElement;
    /**
     * Abstract method representing a player's turn in the game.
     * Subclasses (Plumber & Saboteur ) implement this method to define specific player actions.
     *
     * @return The integer representing the chosen action.
     * @author Basel Al-Raoush
     */
    protected abstract boolean takeTurn(Game g);

    protected void passTurn(){
        System.out.println("Player " + playerName + " passed their turn.");
    }

    /**
     * Changes the input pipe of a specified pump to a new pipe based on user input.
     * The method prompts the user to select a pump and then choose which of the
     * connected pipes to set as the new input pipe. It performs checks to ensure the action
     * is permissible and outputs the result of the change. The method assumes that there are 5 pumps in the game, and 3 connected
     * pipes connected to every pump. In the GUI implementation, this will be different.
     *
     * @param p1 The pump object whose input pipe is to be changed.
     * @param p2 The new pipe object to be set as the input pipe. Note: This parameter is not directly used in the current implementation.
     * @author Karam Abu Judom
     */
    protected void changeInputPipe(Pump p1, Pipe p2) {
        int pumpNumber;
        int pipeNumber;
        System.out.println("Enter the number of the pump. (1-5)");
        Scanner sc = new Scanner(System.in);
        pumpNumber = sc.nextInt();
        while (pumpNumber < 1 || pumpNumber > 5) {
            System.out.println("Invalid input. Enter a number from 1 to 5.");
            pumpNumber = sc.nextInt();
        }
        System.out.println("Pump " + pumpNumber + " currently has 3 connected pipes.");
        System.out.println("Which pipe do you want to set as the input pipe? (1-3)");

        pipeNumber = sc.nextInt();
        while (pipeNumber < 1 || pipeNumber > 3) {
            System.out.println("Invalid input. Enter 1, 2 or 3.");
            pipeNumber = sc.nextInt();
        }
        System.out.println("Pipe " + pipeNumber + " selected.");
        System.out.println("Are you on the pump? Enter 1 if yes, enter 0 if not.");
        if (sc.nextInt() == 1) {
            System.out.println("Performing checks...");
            System.out.println("Pump.isOccupied() returns true.");
            System.out.println("Pump.connectedPipes.Contains(Pipe) returns true.");
            System.out.println("Checks done.");
            System.out.printf("changeInputPipe(%d, %d)\n", pumpNumber, pipeNumber);
            System.out.printf("Pump.InPipe = Pipe %d\n", pipeNumber);
        } else {
            System.out.println("You cannot perform this action if you're not on the pump.");
        }
    }

    /**
     * Changes the output pipe of a specified pump to a new pipe based on user input.
     * Similar to {@link #changeInputPipe(Pump, Pipe)}, this method prompts the user
     * to select a pump and then choose one of the connected pipes to set as the
     * new output pipe. It checks if the action can be performed and displays the result.
     * The method assumes that there are 5 pumps in the game, and 3 connected
     * pipes connected to every pump. In the GUI implementation, this will be different.
     * @param p1 The pump object whose output pipe is to be changed.
     * @param p2 The new pipe object to be set as the output pipe. Note: This parameter is not directly used in the current implementation.
     * @author Karam Abu Judom
     */
    protected void changeOutputPipe(Pump p1, Pipe p2) {
        System.out.println("changeOutputPipe(Pump, Pipe)");
        int pumpNumber;
        int pipeNumber;
        System.out.println("Enter the number of the pump (1-5).");
        Scanner sc = new Scanner(System.in);
        pumpNumber = sc.nextInt();
        while (pumpNumber < 1 || pumpNumber > 5) {
            System.out.println("Invalid input. Enter a number from 1 to 5.");
            pumpNumber = sc.nextInt();
        }
        System.out.println("Pump " + pumpNumber + " currently has 3 connected pipes.");
        System.out.println("Which pipe do you want to set as the output pipe? (1-3)");

        pipeNumber = sc.nextInt();
        while (pipeNumber < 1 || pipeNumber > 3) {
            System.out.println("Invalid input. Enter 1, 2 or 3.");
            pipeNumber = sc.nextInt();
        }
        System.out.println("Pipe " + pipeNumber + " selected.");
        System.out.println("Are you on the pump? Enter 1 if yes, enter 0 if not.");
        if (sc.nextInt() == 1) {
            System.out.println("Performing checks...");
            System.out.println("Pump.isOccupied() returns true.");
            System.out.println("Pump.connectedPipes.Contains(Pipe) returns true.");
            System.out.println("Checks done.");
            System.out.printf("changeOutputPipe(%d, %d)\n", pumpNumber, pipeNumber);
            System.out.printf("Pump.OutPipe = Pipe %d\n", pipeNumber);
        } else {
            System.out.println("You cannot perform this action if you're not on the pump.");
        }
    }

    /**
     * Facilitates player movement between different game locations, including pipes, pumps, and a cistern.
     * This method engages the player with a choice-driven interface to select their next destination within the game's environment.
     * Upon selection, the method evaluates the feasibility of the move based on game logic, such as the occupancy state of the target location.
     *
     * The method operates in several steps:
     * 1. Prompts the user to choose their desired destination type (pipe, pump, or cistern) with an input choice.
     * 2. Based on the selection, further prompts guide the user to specify their destination more precisely (e.g., which pipe or pump).
     * 3. Checks for conditions that might prevent the move, such as an occupied destination.
     * 4. Updates the player's location in the game world if the move is valid.
     * @author : Majed
     */
    protected void move(Game game) {
        Scanner sc = new Scanner(System.in);
        int userChoice;

        // Displaying all current elements in the game

        System.out.println("Please select the number of the element you want to move to:");


        List<Element> elements = game.elementList;

        for (int i = 0; i < elements.size(); i++) {
            System.out.println((i + 1) + ". " + elements.get(i).getName());
        }

        // Get user input
        while (true) {
            try {
                userChoice = sc.nextInt() - 1;  // Adjust for 0-based index
                if (userChoice >= 0 && userChoice < elements.size()) {
                    Element chosenElement = elements.get(userChoice);
                    if (currentElement != chosenElement) {  // Example check: if already there, skip moving
                        currentElement = chosenElement;
                        System.out.println("You have moved to: " + chosenElement);

                    } else {
                        System.out.println("You are already at this location.");
                    }
                    break;
                } else {
                    System.out.println("Invalid choice. Please enter a number between 1 and " + elements.size());
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                sc.next();  // Clear the invalid input
            }
        }
    }
}
