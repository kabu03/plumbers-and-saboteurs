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
    protected void changeInputPipe(Game game) {
        if (!(currentElement instanceof Pump currentPump)) {
            System.out.println("You are not currently on a pump. Move to a pump first.");
            return;
        }

        Scanner sc = new Scanner(System.in);

        System.out.println("Currently connected pipes to the pump '" + currentPump.getName() + "':");
        List<Pipe> connectablePipes = currentPump.connectablePipes;
        for (int i = 0; i < connectablePipes.size(); i++) {
            System.out.println((i + 1) + ". " + connectablePipes.get(i).getName());
        }

        System.out.println("Select the number of the pipe to set as the new input pipe:");
        int pipeNumber;
        try {
            pipeNumber = sc.nextInt();
            if (pipeNumber < 1 || pipeNumber > connectablePipes.size()) {
                System.out.println("Invalid input. Select a number listed above.");
            } else {
                Pipe selectedPipe = connectablePipes.get(pipeNumber - 1);
                currentPump.inPipe = selectedPipe; // Setting the selected pipe as new input pipe
                System.out.println("New input pipe set to: " + selectedPipe.getName());
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number.");
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
    protected void changeOutputPipe(Game game) {
        if (!(currentElement instanceof Pump currentPump)) {
            System.out.println("You are not currently on a pump. Move to a pump first.");
            return;
        }

        Scanner sc = new Scanner(System.in);

        System.out.println("Currently connected pipes to the pump '" + currentPump.getName() + "':");
        List<Pipe> connectablePipes = currentPump.connectablePipes;
        for (int i = 0; i < connectablePipes.size(); i++) {
            System.out.println((i + 1) + ". " + connectablePipes.get(i).getName());
        }

        System.out.println("Select the number of the pipe to set as the new output pipe:");
        int pipeNumber;
        try {
            pipeNumber = sc.nextInt();
            if (pipeNumber < 1 || pipeNumber > connectablePipes.size()) {
                System.out.println("Invalid input. Select a number listed above.");
            } else {
                Pipe selectedPipe = connectablePipes.get(pipeNumber - 1);
                currentPump.outPipe = selectedPipe; // Setting the selected pipe as new output pipe
                System.out.println("New output pipe set to: " + selectedPipe.getName());
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number.");
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
            Element element = elements.get(i);
            // Displaying occupancy status only for Pipe elements
            String occupancyStatus = (element instanceof Pipe && element.isOccupied()) ? " (Occupied)" : "";
            System.out.println((i + 1) + ". " + element.getName() + occupancyStatus);
        }

        // Get user input
        while (true) {
            try {
                userChoice = sc.nextInt() - 1; // Adjust for 0-based index
                if (userChoice >= 0 && userChoice < elements.size()) {
                    Element chosenElement = elements.get(userChoice);
                    if (currentElement != chosenElement) {
                        if (!(chosenElement instanceof Pipe) || !chosenElement.isOccupied()) { // Check if it is a pipe and not occupied
                            if (currentElement != null && currentElement instanceof Pipe) {
                                currentElement.setOccupied(false); // Mark current element as not occupied if it is a pipe
                            }
                            currentElement = chosenElement; // Move player to new element
                            if (chosenElement instanceof Pipe) {
                                chosenElement.setOccupied(true); // Mark new element as occupied if it is a pipe
                            }
                            System.out.println("You have moved to: " + chosenElement.getName());
                        } else {
                            System.out.println("This location is currently occupied. Choose another location.");
                        }
                    } else {
                        System.out.println("You are already at this location.");
                    }
                    break;
                } else {
                    System.out.println("Invalid choice. Please enter a number between 1 and " + elements.size());
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                sc.next(); // Clear the invalid input
            }
        }
    }


}
