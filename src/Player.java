import java.lang.*;
import java.util.Scanner;

public abstract class Player {
    public String playerName;
    public int index;

    protected abstract void takeTurn();

    protected void changeInputPipe(Pump p1, Pipe p2) {
        int pumpNumber;
        int pipeNumber;
        System.out.println("changeInputPipe(Pump, Pipe)");
        System.out.println("Enter the number of the pump.");
        Scanner sc = new Scanner(System.in);
        pumpNumber = sc.nextInt();
        System.out.println("Pump " + pumpNumber + " currently has 3 connected pipes.");
        System.out.println("Which pipe do you want to set as the input pipe? (1-3)");

        pipeNumber = sc.nextInt();
        while (pipeNumber < 1 || pipeNumber > 3) {
            System.out.println("Invalid input. Enter 1, 2 or 3.");
            pipeNumber = sc.nextInt();
        }
        System.out.println("Pipe " + pipeNumber + " selected.");
        System.out.println("Are you on the pump? Enter 1 if yes, enter anything else if not.");
        if (sc.nextInt() == 1) {
            System.out.println("Performing checks...");
            System.out.println("Pump.isOccupied() returns true.");
            System.out.println("Pump.connectedPipes.Contains(Pipe) returns true.");
            System.out.println("Checks done.");
            System.out.printf("changeInputPipe(%d, %d)\n", pumpNumber, pipeNumber);
            System.out.printf("Pump.InPipe = Pipe %d", pipeNumber);
        } else {
            System.out.println("You cannot perform this action if you're not on the pump.");
        }
    }

    protected void changeOutputPipe(Pump p1, Pipe p2) {
        System.out.println("changeOutputPipe(Pump, Pipe)");
        int pumpNumber;
        int pipeNumber;
        System.out.println("Enter the number of the pump");
        Scanner sc = new Scanner(System.in);
        pumpNumber = sc.nextInt();
        System.out.println("Pump " + pumpNumber + " currently has 3 connected pipes.");
        System.out.println("Which pipe do you want to set as the output pipe? (1-3)");

        pipeNumber = sc.nextInt();
        while (pipeNumber < 1 || pipeNumber > 3) {
            System.out.println("Invalid input. Enter 1, 2 or 3.");
            pipeNumber = sc.nextInt();
        }
        System.out.println("Pipe " + pipeNumber + " selected.");
        System.out.println("Are you on the pump? Enter 1 if yes, enter anything else if not.");
        if (sc.nextInt() == 1) {
            System.out.println("Performing checks...");
            System.out.println("Pump.isOccupied() returns true.");
            System.out.println("Pump.connectedPipes.Contains(Pipe) returns true.");
            System.out.println("Checks done.");
            System.out.printf("changeOutputPipe(%d, %d)\n", pumpNumber, pipeNumber);
            System.out.printf("Pump.OutPipe = Pipe %d", pipeNumber);
        } else {
            System.out.println("You cannot perform this action if you're not on the pump.");
        }
    }

    /**
     * Allows the player to move between different game locations (pipes, pumps, or the cistern).
     */
    protected void move() {
        int userChoice1;
        String userChoice2;
        String userChoice3;
        Scanner sc = new Scanner(System.in);
        System.out.println("Where do you want to move? ");
        System.out.println("Enter 1 for pipe");
        System.out.println("Enter 2 for pump");
        System.out.println("Enter 3 for cistern");
        userChoice1 = sc.nextInt();
        sc.nextLine();
        if(userChoice1 == 1)
        {
            System.out.println("There are currently 5 pipes");
            System.out.println("Which pipe do you want to move to?");
            userChoice2 = sc.nextLine();
            System.out.println(userChoice2+".Standable() == True");
            System.out.println("is " + userChoice2 + " occupied?");
            userChoice3 = sc.nextLine();
            if(userChoice3.equalsIgnoreCase("no"))
            {
                System.out.println("Player.move()");
                System.out.println("You have moved to " + userChoice2);
            }
            else {
                System.out.println("You can not move since the pipe is occupied");
            }
        }
        if(userChoice1 == 2)
        {
            System.out.println("There are currently 5 pumps");
            System.out.println("Which pipe do you want to move to?");
            userChoice2 = sc.nextLine();
            System.out.println("Player.move()");
            System.out.println("You have moved to " + userChoice2);
        }
        if(userChoice1 == 3)
        {
            System.out.println("Player.move()");
            // assuming there is only one cistern
            System.out.println("You have moved to the cistern");
        }
    }

}
