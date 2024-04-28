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
     * Changes the input pipe for the currently stood-on pump by allowing the player to select a new input pipe
     * from a list of currently connected pipes. This method first checks if the player is standing on a pump
     * and then displays a list of all connected pipes to that pump. The player can select which pipe to set
     * as the new input pipe either by entering the number associated with the pipe or, in test mode, the name
     * of the pipe directly.
     *
     * @param game The instance of the game containing all the current game data, used here to access the scanner
     *             for input and possibly other game state manipulations.
     * @throws NumberFormatException if the input provided is not a valid integer when selecting a pipe by number
     *         (outside of test mode). This exception is caught internally and an error message is displayed.
     * @author Nafez Mousa Sayyad
     */
       protected void changeInputPipe(Game game) {
           if (!(currentElement instanceof Pump)) {
               if (!Game.testMode) System.out.println("You are not currently on a pump. Move to a pump first.");
               return;
           }

           Pump currentPump = (Pump) currentElement;
           if (!Game.testMode) System.out.println("Currently connected pipes to the pump '" + currentPump.getName() + "':");
           List<Pipe> connectedPipes = currentPump.connectedPipes;
           for (int i = 0; i < connectedPipes.size(); i++) {
               if (!Game.testMode) System.out.println((i + 1) + ". " + connectedPipes.get(i).getName());
           }

           if (!Game.testMode) System.out.println("Select the number of the pipe to set as the new input pipe:");
           String input;
           try {
               input = Game.scanner.nextLine().trim();
               int pipeNumber = Game.testMode ? -1 : Integer.parseInt(input);
               if (Game.testMode) {
                   for (Pipe pipe : connectedPipes) {
                       if (pipe.getName().equalsIgnoreCase(input)) {
                           currentPump.inPipe = pipe;
                           System.out.println(playerName + " changed the input pipe of " + currentPump.getName() + " to " + pipe.getName());
                           return;
                       }
                   }
                   System.out.println("Invalid input. Select a pipe listed above.");
               } else if (pipeNumber < 1 || pipeNumber > connectedPipes.size()) {
                   System.out.println("Invalid input. Select a number listed above.");
               } else {
                   Pipe selectedPipe = connectedPipes.get(pipeNumber - 1);
                   currentPump.inPipe = selectedPipe;
                   System.out.println(playerName + " changed the input pipe of " + currentPump.getName() + " to " + selectedPipe.getName());
               }
           } catch (NumberFormatException e) {
               System.out.println("Invalid input. Please enter a number or a valid pipe name.");
           }
       }


    /**
     * Changes the output pipe for the currently stood-on pump by enabling the player to select
     * a new output pipe from a list of currently connected pipes. This method verifies if the player
     * is standing on a pump and displays a list of all connected pipes to that pump. The player can
     * then select which pipe to set as the new output pipe by entering the number associated with the
     * pipe. In test mode, the player can directly input the name of the pipe.
     *
     * @param game The instance of the game that holds all current game data, utilized here to access
     *             global settings like test mode and for input reading through the game's scanner.
     * @throws NumberFormatException if the input provided is not a valid integer when selecting a pipe
     *         by number (outside of test mode). This exception is caught internally, and a user-friendly
     *         error message is provided.
     * @author Nafez Mousa Sayyad
     */
    protected void changeOutputPipe(Game game) {
        if (!(currentElement instanceof Pump)) {
            if (!Game.testMode) System.out.println("You are not currently on a pump. Move to a pump first.");
            return;
        }

        Pump currentPump = (Pump) currentElement;
        if (!Game.testMode) System.out.println("Currently connected pipes to the pump '" + currentPump.getName() + "':");
        List<Pipe> connectedPipes = currentPump.connectedPipes;
        for (int i = 0; i < connectedPipes.size(); i++) {
            if (!Game.testMode) System.out.println((i + 1) + ". " + connectedPipes.get(i).getName());
        }

        if (!Game.testMode) System.out.println("Select the number of the pipe to set as the new output pipe:");
        String input;
        try {
            input = Game.scanner.nextLine().trim();
            int pipeNumber = Game.testMode ? -1 : Integer.parseInt(input);
            if (Game.testMode) {
                for (Pipe pipe : connectedPipes) {
                    if (pipe.getName().equalsIgnoreCase(input)) {
                        currentPump.outPipe = pipe;
                        System.out.println(playerName + " changed the output pipe of " + currentPump.getName() + " to " + pipe.getName());
                        return;
                    }
                }
                System.out.println("Invalid input. Select a pipe listed above.");
            } else if (pipeNumber < 1 || pipeNumber > connectedPipes.size()) {
                System.out.println("Invalid input. Select a number listed above.");
            } else {
                Pipe selectedPipe = connectedPipes.get(pipeNumber - 1);
                currentPump.outPipe = selectedPipe;
                System.out.println(playerName + " changed the output pipe of " + currentPump.getName() + " to " + selectedPipe.getName());
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number or a valid pipe name.");
        }
    }



    /**
     * Moves the player to a selected element from a list of all elements in the game.
     * The method displays all available elements, including information about their occupation status
     * if they are pipes. The player can then select an element to move to by entering its corresponding number
     * in the displayed list. If in test mode, the player can directly enter the name of the element.
     * This method ensures that the player cannot move to an occupied pipe and updates the occupation
     * status of the elements accordingly.
     *
     * @param game The game instance containing all elements and global settings necessary for operation,
     *             including the list of elements and input handling mechanisms.
     * @throws InputMismatchException if the input provided is not an integer, which indicates an invalid selection.
     *         This exception is caught internally, prompting the user to re-enter a valid choice.
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
