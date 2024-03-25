import java.lang.*;
import java.util.Scanner;

/**
 * Represents individual players in the game, each being part of a team as plumbers or saboteurs.
 * Players take turns to perform actions such as moving, repairing pipes, sabotaging the system,
 * transferring water, and managing pump operations including fixing, inserting, changing input/output
 * pipes, and setting water flow direction. Each player is identified by their name, team, and index number.
 */
public abstract class Player {
    public String playerName;
    public int index;

    /**
     * Abstract method representing a player's turn in the game.
     * Subclasses (Plumber & Saboteur ) implement this method to define specific player actions.
     *
     * @return The integer representing the chosen action.
     * @author Basel Al-Raoush
     */
    protected abstract int takeTurn();

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
        System.out.println("changeInputPipe(Pump, Pipe)");
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
        System.out.println("Are you on the pump? Enter 1 if yes, enter anything else if not.");
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
        System.out.println("Are you on the pump? Enter 1 if yes, enter anything else if not.");
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
    protected void move() {
        int userChoice1;
        String userChoice2;
        String userChoice3;
        Scanner sc = new Scanner(System.in);
        System.out.println("Where do you want to move? ");
        System.out.println("Enter 1 for pipe");
        System.out.println("Enter 2 for pump");
        System.out.println("Enter 3 for cistern");
        userChoice1 = sc.nextInt();
        sc.nextLine();
        if (userChoice1 == 1) {
            while (true) {
                System.out.println("There are currently 5 pipes");
                System.out.println("Which pipe do you want to move to? (Enter a number from 1 to 5)");
                userChoice2 = sc.nextLine();
                int pipeNumber = Integer.parseInt(userChoice2);
                if (pipeNumber < 1 || pipeNumber > 5) {
                    System.out.println("Invalid choice. Please enter a number from 1 to 5.");
                    continue;
                }
                System.out.println("Pipe" + userChoice2 + ".Standable() == True");
                System.out.println("is pipe " + pipeNumber + " occupied?");
                userChoice3 = sc.nextLine();
                if (userChoice3.equalsIgnoreCase("no")) {
                    System.out.println("Player.move()");
                    System.out.println("You have moved to pipe " + pipeNumber);
                } else {
                    System.out.println("You cannot move since the pipe is occupied.");
                }
                break; // Exit the loop after processing valid input
            }
        }
        if (userChoice1 == 2) {
            while (true) {
                System.out.println("There are currently 5 pumps");
                System.out.println("Which pipe do you want to move to?");
                userChoice2 = sc.nextLine();
                int pipeNumber = Integer.parseInt(userChoice2);
                if (pipeNumber < 1 || pipeNumber > 5) {
                    System.out.println("Invalid choice. Please enter a number from 1 to 5.");
                    continue;
                }

                System.out.println("Player.move()");
                System.out.println("You have moved to pump " + pipeNumber);
                break;

            }
        }
        if (userChoice1 == 3) {
            System.out.println("Player.move()");
            // assuming there is only one cistern
            System.out.println("You have moved to the cistern");
        }
        if(userChoice1 != 1 && userChoice1 != 2 && userChoice1 != 3)
        {
            System.out.println("Invaild choice");
            move();
        }
    }
}
