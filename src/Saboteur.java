import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.NoSuchElementException;
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
    protected boolean takeTurn(Game g) { // should happen twice, user should be prompted twice EXCEPT FOR PASS TURN
//        try {
        long turnStartTime = System.currentTimeMillis();
        long turnEndTime = turnStartTime + 10000; // 10 seconds
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
        while (System.currentTimeMillis() < turnEndTime) {
            // Check if input is available
            try {
                if (System.in.available() > 0) {
                    int choice = Integer.parseInt(Game.scanner.nextLine());
                    switch (choice) {
                        case 1:
                            if (!Game.testMode)
                                System.out.println("You chose: Move to an element");
                            move(g);
                            return true;
                        case 2:
                            if (!Game.testMode)
                                System.out.println("You chose: Change the input pipe of a pump");
                            changeInputPipe(g); // element selection
                            return true;
                        case 3:
                            if (!Game.testMode)
                                System.out.println("You chose: Change the output pipe of a pump");
                            changeOutputPipe(g); // element selection
                            return true;
                        case 4:
                            if (!Game.testMode)
                                System.out.println("You chose: Puncture a pipe");
                            puncture(g.pipeList.get(0));
                            return true;
                        case 5:
                            if (!Game.testMode)
                                System.out.println("You chose: Pass Turn");
                            passTurn();
                            return true;
                        case 6:
                            if (!Game.testMode)
                                System.out.println("You chose: End the game");
                            g.endGame();
                            exit(0);
                            return true;
                        default:
                            System.out.println("Invalid input, please choose one of the valid options (1-6).");
                    }
//        } catch (NoSuchElementException e) {
//            PrintStream console = new PrintStream(new FileOutputStream(FileDescriptor.out));
//            console.println("No more lines to read from the file");
//        }

                }
            } catch (IOException ex) {
                throw new RuntimeException(ex);
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
        Scanner scanner = new Scanner(System.in);
        System.out.println("puncture(Pipe)");
        System.out.println("Is the Pipe working? Enter 1 if yes, enter anything else if not.");

        String sc1 = scanner.nextLine();

        if (sc1.equals("1")) {
            System.out.println("Are you standing on the pipe? Enter 1 if yes, enter anything else if not.");

            String sc2 = scanner.nextLine();

            if (sc2.equals("1")) {
                p1.setWorks(false);
                System.out.println("Pipe.works = False");
                System.out.println("The pipe has been punctured.");
                System.out.println("IF WaterLevel > 0 (meaning there is water currently flowing through the pipe)");
                p1.decrementWater();
                p1.incrementLeakage();

            } else {
                System.out.println("You cannot puncture a pipe that you're not currently standing on.");
            }
        } else {
            System.out.println("You cannot puncture a pipe that is not working!");
        }
    }
}
