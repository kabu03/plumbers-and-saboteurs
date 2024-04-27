import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.*;

import static java.lang.System.exit;

/**
 * Represents players dedicated to disrupting the pipe system and causing water wastage.
 * Saboteurs employ tactics to change pump directions and puncture pipes, aiming to hinder
 * the plumbers' efforts by strategically targeting system weaknesses to maximize water loss.
 */
public class Saboteur extends Player {

    public Saboteur(String playerName) {
        this.playerName = playerName;
    }


    /**
     * Allows the Saboteur player to take their turn.
     * Displays available actions and prompts the player to choose one.
     * Returns the chosen action as an integer.
     * Overrides the abstract takeTurn method of the Player class.
     *
     * @return The integer representing the chosen action.
     * @author Basel Al-Raoush
     */

    @Override
    protected boolean takeTurn(Game g) {
        boolean passflag = false;
        int actionsTaken = 0; // Track the number of actions taken in the turn
        long turnStartTime = System.currentTimeMillis();
        long turnEndTime = turnStartTime + 5000; // 5 seconds for turn

        while (actionsTaken < 2 && System.currentTimeMillis() < turnEndTime) {
            if (!Game.testMode) {
                System.out.println("Player " + playerName + ", it's your turn.");
                System.out.println("What action would you like to perform?");
                System.out.println("Available actions for Saboteurs:");
                System.out.println("1. Move to an element");
                System.out.println("2. Change the input pipe of a pump");
                System.out.println("3. Change the output pipe of a pump");
                System.out.println("4. Puncture a pipe");
                System.out.println("5. Pass Turn");
                System.out.println("6. End the game");
                System.out.print("Enter the number corresponding to your choice: ");
            }


            try {
                if (System.in.available() > 0) {
                    int choice = Integer.parseInt(Game.scanner.nextLine());
                    switch (choice) {
                        case 1:
                            if (!Game.testMode)
                                System.out.println("You chose: Move to an element");
                            move(g);
                            actionsTaken++;
                            break;
                        case 2:
                            if (!Game.testMode)
                                System.out.println("You chose: Change the input pipe of a pump");
                            changeInputPipe(g); // element selection
                            actionsTaken++;
                            break;
                        case 3:
                            if (!Game.testMode)
                                System.out.println("You chose: Change the output pipe of a pump");
                            changeOutputPipe(g); // element selection
                            actionsTaken++;
                            break;
                        case 4:
                            if (!Game.testMode)
                                System.out.println("You chose: Puncture a pipe");
                            puncture(g.pipeList.get(0));
                            actionsTaken++;
                            break;
                        case 5:
                            if (!Game.testMode)
                                System.out.println("You chose: Pass Turn");
                            passflag = true;
                            passTurn();
                            break;
                        case 6:
                            if (!Game.testMode)
                                System.out.println("You chose: End the game");
                            g.endGame();
                            exit(0);
                            break;
                        default:
                            System.out.println("Invalid input, please choose one of the valid options (1-6).");
                    }
                }
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

            // Check if two actions were taken or if the turn timer ran out
            if (actionsTaken == 2 || System.currentTimeMillis() >= turnEndTime || (actionsTaken == 1 && passflag)) {
                break;
            }
        }

        return true;
    }



    /**
     * Attempts to puncture the specified pipe based on user input and certain conditions.
     * The method first checks if the pipe is in a working state. If it is, it then verifies
     * if the user is standing on the pipe. If both conditions are satisfied, the method
     * sets the pipe's working status to false (punctured), potentially affects water flow,
     * and increments leakage.
     * <p>
     * The method prompts the user for input to confirm the pipe's working status and the
     * user's position relative to the pipe. If the pipe is not working or the user is not
     * standing on it, the action is aborted with an explanatory message.
     * </p>
     *
     * @param p1 The {@link Pipe} object to be punctured. This method directly modifies its
     *           {@code works} property, and may call {@code decrementWater()} and
     *           {@code incrementLeakage()} on it depending on the water level.
     * @author Karam Abu Judom
     */

    public void puncture(Pipe p1) {
        if (Objects.equals(currentElement, p1) && p1.getWorks()) {
            p1.setWorks(false);
            System.out.println(playerName + "punctured" + p1.getName());
            return;
        }
        if (currentElement == p1 && !p1.getWorks()) {
            System.out.println(playerName + " attempted to puncture" + p1.getName() + ",but it is already punctured.");
        } else System.out.println("You have to be standing on a working pipe to puncture it.");

    }
}
