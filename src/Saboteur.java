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
     *
     * Overrides the abstract takeTurn method of the Player class.
     *
     * @author Basel Al-Raoush
     *
     * The method allows each player 2 actions to pick from in a 5-second interval for each turn.
     *                    In essence in each turn the player has either 2 actions to perform within 5 seconds.
     *@author Ibrahim Muheisen
     */
    @Override
    protected void takeTurn(Game g) {
        boolean passflag = false;
        int actionstaken=0;
        long turnStartTime = System.currentTimeMillis();
        long turnDuration = 5000;


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

            while (System.currentTimeMillis() < turnStartTime + turnDuration && actionstaken < 2) {

                try {
                    if (System.in.available() > 0 || Game.testMode) {
                        int choice = Integer.parseInt(Game.scanner.nextLine());
                    switch (choice) {
                        case 1:
                            if (!Game.testMode)
                                System.out.println("You chose: Move to an element");
                            move(g);
                            actionstaken++;
                            break;
                        case 2:
                            if (!Game.testMode)
                                System.out.println("You chose: Change the input pipe of a pump");
                            changeInputPipe(g);
                            actionstaken++;
                            break;
                        case 3:
                            if (!Game.testMode)
                                System.out.println("You chose: Change the output pipe of a pump");
                            changeOutputPipe(g);
                            actionstaken++;
                            break;
                        case 4:
                            if (!Game.testMode)
                                System.out.println("You chose: Puncture a pipe");
                            puncture();
                            actionstaken++;
                            break;
                        case 5:
                            if (!Game.testMode)
                                System.out.println("You chose: Pass Turn");
                            passflag = true;
                            passTurn();
                            return;
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
            } catch (IOException e) {
                    throw new RuntimeException(e);
                }


            // Check if two actions were taken or if the turn timer ran out
            if (actionstaken == 1 && passflag) {
                break;
            }
        }
    }


    /**
     * Attempts to puncture the specified pipe based on user input and certain conditions.
     * The method first checks if the pipe is in a working state. If it is, it then verifies
     * if the user is standing on the pipe. If both conditions are satisfied, the method
     * sets the pipe's working status to false (punctured).
     * @author Ibrahim
     */

    public void puncture() {
        if (currentElement instanceof Pipe && currentElement.getWorks()) {
            currentElement.setWorks(false);
            System.out.println(playerName + " punctured " + currentElement.getName());
            return;
        }
        if (currentElement instanceof Pipe && !currentElement.getWorks()) {
            System.out.println(playerName + " attempted to puncture" + currentElement.getName() + ",but it is already punctured.");
        } else System.out.println("You have to be standing on a working pipe to puncture it.");

    }
}