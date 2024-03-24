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

    protected void move() {
        System.out.println("move()");
        // What to implement here?
        // i do not know since we have no postion yet
        // maybe move to an element.
    }

}
