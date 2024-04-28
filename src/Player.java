import java.lang.*;
import java.util.Scanner;
import java.util.List;
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
     * @author Basel Al-Raoush
     */
    protected abstract void takeTurn(Game g);

    protected void passTurn(){
        System.out.println(playerName + " passed their turn.");
    }

       /**
     * Changes the input pipe of a specified pump to a new pipe based on user input.
     * The method checks if the user is on a pump or not
     * The method prints all the current connected elements to the pump
     * connected pipes to set as the new input pipe. It performs checks to ensure the action
     * is permissible and outputs the result of the change.
     * some pipes are connected, and some are connectable. Method will only show connected pipes
     *
     *
     * @author Nafez Mousa Sayyad
     **/
    protected void changeInputPipe(Game game) {
        if (!(currentElement instanceof Pump)) {
            if (!Game.testMode)  System.out.println("You are not currently on a pump. Move to a pump first.");
            return;
        }

        Pump currentPump = (Pump) currentElement;

        if (!Game.testMode) System.out.println("Currently connected pipes to the pump '" + currentPump.getName() + "':");
        List<Pipe> connectablePipes = currentPump.connectablePipes;
        for (int i = 0; i < connectablePipes.size(); i++) {
            if (!Game.testMode)  System.out.println((i + 1) + ". " + connectablePipes.get(i).getName());
        }

        if (!Game.testMode) System.out.println("Select the number of the pipe to set as the new input pipe:");
        int pipeNumber;
        try {
            pipeNumber = Integer.parseInt(Game.scanner.nextLine());
            if (pipeNumber < 1 || pipeNumber > connectablePipes.size()) {
                System.out.println("Invalid input. Select a number listed above.");
            } else {
                Pipe selectedPipe = connectablePipes.get(pipeNumber - 1);
                currentPump.inPipe = selectedPipe; // Setting the selected pipe as new input pipe
                System.out.println(playerName+" changed the input pipe of "+currentPump.getName()+" to " + selectedPipe.getName());
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number.");
        }
    }


    /**
     * Similar to the changeInputPipe, once the user is on a pump, he will be able
     * to change the input of that specific pump by calling this method
     * the method, check if the user is on a pump or not
     * then prints the connected elements to that current pump the user is standing on
     * the user can choose which pipe to become the input for the pump
     * @author Nafez Mousa Sayyad
     */
    protected void changeOutputPipe(Game game) {
        if (!(currentElement instanceof Pump)) {
            if (!Game.testMode)    System.out.println("You are not currently on a pump. Move to a pump first.");
            return;
        }

        Pump currentPump = (Pump) currentElement;

        if (!Game.testMode) System.out.println("Currently connected pipes to the pump '" + currentPump.getName() + "':");
        List<Pipe> connectablePipes = currentPump.connectablePipes;
        for (int i = 0; i < connectablePipes.size(); i++) {
            if (!Game.testMode)    System.out.println((i + 1) + ". " + connectablePipes.get(i).getName());
        }

        if (!Game.testMode) System.out.println("Select the number of the pipe to set as the new output pipe:");
        int pipeNumber;
        try {
            pipeNumber = Integer.parseInt(Game.scanner.nextLine());
            if (pipeNumber < 1 || pipeNumber > connectablePipes.size()) {
                if (!Game.testMode)      System.out.println("Invalid input. Select a number listed above.");
            } else {
                Pipe selectedPipe = connectablePipes.get(pipeNumber - 1);
                currentPump.outPipe = selectedPipe; // Setting the selected pipe as new output pipe
                System.out.println(playerName + " changed the output pipe of " + currentPump.getName()+" to " + selectedPipe.getName());
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number.");
        }
    }


    /**
     * The move method, is called once a player wants to move his location
     * ( move to a new element)
     * once the user chooses to move, he will be able to select any of the current standable element in the
     * elementList, (every element in the game)
     * if a user moves to a pipe, the method sets this pipe as occupied.
     * the other players are able to see that the pipe is occupied.
     * if in test mode, the method is able to accept strings as input to move to a location
     *
     * @author Nafez Mousa Sayyad
     */
    protected void move(Game game) {
        String userInput;
        Element chosenElement = null;

        // Displaying all current elements in the game

        List<Element> elements = game.elementList;

        for (int i = 0; i < elements.size(); i++) {
            Element element = elements.get(i);
            String occupancyStatus = (element instanceof Pipe && element.isOccupied()) ? " (Occupied)" : "";
            if (!Game.testMode)     System.out.println((i + 1) + ". " + element.getName() + occupancyStatus);
        }

        // Get user input
        while (true) {
            try {
                if (!Game.testMode) {
                    int userChoice = Integer.parseInt(Game.scanner.nextLine()) - 1; // Adjust for 0-based index
                    if (userChoice >= 0 && userChoice < elements.size()) {
                        chosenElement = elements.get(userChoice);
                    } else {
                        System.out.println("Invalid choice. Please enter a number between 1 and " + elements.size());
                        continue;
                    }
                } else {
                    userInput = Game.scanner.nextLine().trim();
                    for (Element element : elements) {
                        if (element.getName().equalsIgnoreCase(userInput)) {
                            chosenElement = element;
                            break;
                        }
                    }
                    if (chosenElement == null) {
                        System.out.println("No element matches the name entered. Please try again.");
                        continue;
                    }
                }

                if (currentElement != chosenElement) {
                    if (!(chosenElement instanceof Pipe) || !chosenElement.isOccupied()) {
                        if (currentElement != null && currentElement instanceof Pipe) {
                            currentElement.setOccupied(false); // Mark current element as not occupied if it is a pipe
                        }
                        currentElement = chosenElement; // Move player to new element
                        if (chosenElement instanceof Pipe) {
                            chosenElement.setOccupied(true); // Mark new element as occupied if it is a pipe
                        }
                        System.out.println( playerName + " moved to " + chosenElement.getName());
                    } else {
                        System.out.println("This location is currently occupied. Choose another location.");
                    }
                    break;
                } else {
                    System.out.println("You are already at this location.");
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                Game.scanner.next(); // Clear the invalid input
            }
        }
    }


}
