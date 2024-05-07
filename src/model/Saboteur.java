package model;

import java.io.IOException;
import java.util.Scanner;

import static java.lang.System.exit;

/**
 * Represents players dedicated to disrupting the pipe system and causing water wastage.
 * Saboteurs employ tactics to change pump directions and puncture pipes, aiming to hinder
 * the plumbers' efforts by strategically targeting system weaknesses to maximize water loss.
 */
public class Saboteur extends Player {

    public Saboteur(String playerName) {
        this.playerName = playerName;
    } // set initial position to the cisterns


    /**
     * Allows the model.Saboteur player to take their turn.
     * Displays available actions and prompts the player to choose one.
     *
     * Overrides the abstract takeTurn method of the model.Player class.
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


            while (System.currentTimeMillis() < turnStartTime + turnDuration && actionstaken < 2) {
                        Scanner sc = new Scanner(System.in);
                        String choice = sc.next();
                    switch (choice) {
                        case "Q":
                                System.out.println("You chose: Move to an element");
                            move(g);
                            actionstaken++;
                            break;
                        case "A":
                                System.out.println("You chose: Change the input pipe of a pump");
                            changeInputPipe(g);
                            actionstaken++;
                            break;
                        case "S":
                                System.out.println("You chose: Change the output pipe of a pump");
                            changeOutputPipe(g);
                            actionstaken++;
                            break;
                        case "P":
                                System.out.println("You chose: Puncture a pipe");
                            puncture();
                            actionstaken++;
                            break;
                        case "W":
                                System.out.println("You chose: Pass Turn");
                            passflag = true;
                            passTurn();
                            return;
                        case "E":

                                System.out.println("You chose: End the game");
                            g.endGame();
                            exit(0);
                            break;
                        default:
                            System.out.println("Invalid input, please choose one of the valid options (1-6).");
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
        if (currentElement instanceof Pipe && currentElement.isWorking()) {
            currentElement.setWorks(false);
            System.out.println(playerName + " punctured " + currentElement.getName());
            return;
        }
        if (currentElement instanceof Pipe && !currentElement.isWorking()) {
            System.out.println(playerName + " attempted to puncture" + currentElement.getName() + ",but it is already punctured.");
        } else System.out.println("You have to be standing on a working pipe to puncture it.");

    }
}