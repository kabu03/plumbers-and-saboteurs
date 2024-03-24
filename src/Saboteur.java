import java.util.Scanner;

/**
 * Represents players dedicated to disrupting the pipe system and causing water wastage.
 * Saboteurs employ tactics to change pump directions and puncture pipes, aiming to hinder
 * the plumbers' efforts by strategically targeting system weaknesses to maximize water loss.
 */
public class Saboteur extends Player {

    public Saboteur(String playerName) {
        this.playerName = playerName;
    }

    @Override
    protected void takeTurn() {
        System.out.println("takeTurn()");
        System.out.println("The Saboteur is now playing their turn.");
        System.out.println("Player " + playerName + ", it's your turn.");
        System.out.println("What action would you like to perform?");
        System.out.println("Available actions for Saboteurs:");

        // Print the list of actions
        System.out.println("1. Move");
        System.out.println("2. ChangeInputPipe");
        System.out.println("3. ChangeOutputPipe");
        System.out.println("4. Puncture");

        // Prompt the user to enter a number
        System.out.print("Enter the number corresponding to your choice: ");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        // Switch case
        switch (choice) {
            case 1:
                System.out.println("You chose: Move");

                break;
            case 2:
                System.out.println("You chose: ChangeInputPipe");

                break;
            case 3:
                System.out.println("You chose: ChangeOutputPipe");

                break;
            case 4:
                System.out.println("You chose: Puncture");

                break;
            default:
                System.out.println("Invalid choice. Please enter a number corresponding to the action you want to perform.");
        }
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
    public void puncture(Pipe p1){
        Scanner sc = new Scanner(System.in);
        System.out.println("puncture(Pipe)");
        System.out.println("Is the Pipe working? Enter 1 if yes, enter anything else if not.");
        if (sc.nextInt() == 1){
            System.out.println("Are you standing on the pipe? Enter 1 if yes, enter anything else if not.");
            if (sc.nextInt() == 1){
                p1.works = false;
                System.out.println("Pipe.works = False");
                System.out.println("The pipe has been punctured.");
                System.out.println("IF WaterLevel > 0 (meaning there is water currently flowing through the pipe)");
                p1.decrementWater();
                p1.incrementLeakage();
            }
            else {
                System.out.println("You cannot puncture a pipe that you're not currently standing on.");
            }
        }
        else {
            System.out.println("You cannot puncture a pipe that is not working!");
        }

    }
}
