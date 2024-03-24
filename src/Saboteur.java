import java.util.Scanner;

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
