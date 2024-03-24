import java.util.Scanner;

/**
 * Represents players tasked with maintaining and repairing the pipe system. Plumbers can fix
 * broken pumps, repair leaking pipes, manage pipe ends, and extend the system. They play a
 * crucial role in setting pump directions and defending against sabotage, with their actions
 * being vital for water transfer efficiency and system operation.
 */
public class Plumber extends Player {
    private Pump playerPump;
    private Pipe TempPipe;
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
                move();
                break;
            case 2:
                System.out.println("You chose: GetPump");
                getPump(playerPump);
                break;
            case 3:
                System.out.println("You chose: InsertPump");
                insertPump(playerPump,TempPipe);
                break;
            case 4:
                System.out.println("You chose: FixPump");

                break;
            case 5:
                System.out.println("You chose: FixPipe");
                FixPipe(TempPipe);
                break;
            case 6:
                System.out.println("You chose: GetEnd");
                getEnd(playerEndOfPipe);
                break;
            case 7:
                System.out.println("You chose: InsertPipeEnd");
                insertPipeEnd(playerPump);

                break;
            case 8:
                System.out.println("You chose: ChangeInputPipe");
                    changeInputPipe(playerPump,TempPipe);
                break;
            case 9:
                System.out.println("You chose: ChangeOutputPipe");
                changeOutputPipe(playerPump,TempPipe);
                break;
            case 10:
                System.out.println("You chose: End the Game");
                break;
            default:
                System.out.println("Invalid choice. Please enter a number corresponding to the action you want to perform.");
        }
    }

    /**
     * this method will allow a plumber to pick up an end of pipe object from the end of a pipe.
     * @param EoP this will be the end of pipe object that we will pick up
     */
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


    /**
     * this method connects an end of pipe object to an element in the game.
     * @param e this will be the element that we will insert the pipe end to.
     */
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
    public static void FixPipe(Pipe p){
        String userChoice,userChoice2;
        Scanner sc = new Scanner(System.in);
        Scanner sc2 = new Scanner(System.in);
        System.out.println("Are you on the pipe?");
        userChoice = sc.nextLine();
        if(userChoice.equalsIgnoreCase("yes")){
            System.out.println("is the pipe punctured?");
            userChoice2 = sc2.nextLine();
            if(userChoice2.equalsIgnoreCase("yes")){
                System.out.println("FixPipe(Pipe)\n Pipe.Works=True;\n Pipe is now repaired and working");
            }else{
                System.out.print("You can't fix a pipe that is not punctured.");
            }

        }else {
            System.out.println("Please first move to the Pipe you want to fix.");
        }

    }
    public static void getPump(Pump p) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Are you on the pump you want to pick up?");
        String userChoice = scanner.nextLine();

        if (userChoice.equalsIgnoreCase("yes")) {
            System.out.println("GetPump(p: Pump)\n You have picked up the pump.");
        } else {
            System.out.println("First go to a cistern that has a pump available for pick up.");
        }


    }
    public void insertPump(Pump pump, Pipe pipe){

        String userChoice;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Are you on the pipe you want to insert a pump on?");
        userChoice = scanner.nextLine();

        if (userChoice.equalsIgnoreCase("yes")) {
            System.out.println("InsertPump(p1:Pump, p2:Pipe)\n Pump is successfully inserted into the pipe system.");
        } else {
            System.out.println("Please move to the pipe you want to insert the pump on.");
        }


        // Why is pump a parameter? We know they can only insert the one they have in the inventory.
        // you are right
    }
}
