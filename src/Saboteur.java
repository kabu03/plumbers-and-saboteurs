import java.util.Scanner;

public class Saboteur extends Player {
    @Override
    protected void takeTurn() {
        System.out.println("takeTurn()");
        System.out.println("The Saboteur is playing their turn."); // Will change this later.
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
                System.out.println("Pipe.DecrementWater()");
                System.out.println("Pipe.IncrementLeakage()");
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
