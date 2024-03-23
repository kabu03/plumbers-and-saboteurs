import java.util.Scanner;

public class Plumber extends Player {
    private Pump playerPump;
    private EndOfPipe playerEndOfPipe;

    public Plumber(String playerName) {
        this.playerName = playerName;
    }
    @Override
    protected void takeTurn() {
        System.out.println("takeTurn()");


        System.out.println("Player " + playerName + ", it's your turn.");
        System.out.println("What action would you like to perform?");
        System.out.println("Available actions for Plumbers:");

        // Print the list of actions
        System.out.println("1. Move to an element");
        System.out.println("2. GetPump");
        System.out.println("3. InsertPump");
        System.out.println("4. FixPump");
        System.out.println("5. FixPipe");
        System.out.println("6. GetEnd");
        System.out.println("7. InsertPipeEnd");
        System.out.println("8. ChangeInputPipe");
        System.out.println("9. ChangeOutputPipe");
        System.out.println("10. End the Game");

        // Prompt the user to enter a number
        System.out.print("Enter the number corresponding to your choice: ");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        // Switch case
        switch (choice) {
            case 1:
                System.out.println("You chose: Move to an element");

                break;
            case 2:
                System.out.println("You chose: GetPump");

                break;
            case 3:
                System.out.println("You chose: InsertPump");

                break;
            case 4:
                System.out.println("You chose: FixPump");

                break;
            case 5:
                System.out.println("You chose: FixPipe");

                break;
            case 6:
                System.out.println("You chose: GetEnd");

                break;
            case 7:
                System.out.println("You chose: InsertPipeEnd");

                break;
            case 8:
                System.out.println("You chose: ChangeInputPipe");

                break;
            case 9:
                System.out.println("You chose: ChangeOutputPipe");

                break;
            case 10:
                System.out.println("You chose: End the Game");

                break;
            default:
                System.out.println("Invalid choice. Please enter a number corresponding to the action you want to perform.");
        }
    }

    public void getPump(Pump pump){
        System.out.println("getPump(Pump)");
        playerPump = pump;
        System.out.println("The plumber has picked up a pump. They can now insert it.");
    }
    public void getEnd(EndOfPipe EoP){
        String userChoice;
        Scanner sc = new Scanner(System.in);
        System.out.println("Are you it the end of the pipe?");
        userChoice = sc.nextLine();
        if(userChoice.equalsIgnoreCase("yes")) {
            System.out.println("getEnd(EndOfPipe)");
            playerEndOfPipe = EoP;
            System.out.println("The plumber has picked up an end of pipe. They can now insert it.");
        }
        else {
            System.out.println("You can not pick up the end of the pipe");
        }
    }

    public void insertPump(Pump pump, Pipe pipe){
        System.out.println("insertPump(Pump, Pipe)");
        // Why is pump a parameter? We know they can only insert the one they have in the inventory.
        // you are right
    }

    public void insertPipeEnd(Element e){
        String userChoice;
        Scanner sc = new Scanner(System.in);
        System.out.println("Are you at the end of the pipe?");
        userChoice = sc.nextLine();
        if(userChoice.equalsIgnoreCase("yes")) {
            System.out.println("insertPipeEnd(Element)");
        }
        else{
            System.out.println("Please Move to the end of the pipe to insert");
        }
    }
}
