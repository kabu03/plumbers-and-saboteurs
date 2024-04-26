import java.util.Scanner;

import static java.lang.System.exit;

/**
 * Represents players tasked with maintaining and repairing the pipe system. Plumbers can fix
 * broken pumps, repair leaking pipes, manage pipe ends, and extend the system. They play a
 * crucial role in setting pump directions and defending against sabotage, with their actions
 * being vital for water transfer efficiency and system operation.
 */
public class Plumber extends Player {
    public Pump pickedUpPump;
    private Pipe TempPipe; // ?
    public EndOfPipe pickedUpEoP;

    public Plumber(String playerName) {
        this.playerName = playerName;
    }

    /**
     * Allows the Plumber player to take their turn.
     * Displays available actions and prompts the player to choose one.
     * Returns the chosen action as an integer.
     * Overrides the abstract takeTurn method of the Player class.
     *
     * @return The integer representing the chosen action.
     * @author Basel Al-Raoush
     */
    @Override
    protected void takeTurn(Game g) { // should happen twice, user should be prompted twice EXCEPT FOR PASS TURN
        System.out.println("Player " + playerName + ", it's your turn.");
        System.out.println("What action would you like to perform?");
        System.out.println("Available actions for Plumbers:");
        // Print the list of actions
        System.out.println("1. Move to an element");
        System.out.println("2. Pick up a pump");
        System.out.println("3. Insert pump into a pipe");
        System.out.println("4. Fix a broken pump");
        System.out.println("5. Fix a broken pipe");
        System.out.println("6. Pick up the end of a pipe");
        System.out.println("7. Insert the end of a pipe");
        System.out.println("8. Change the input pipe of a pump");
        System.out.println("9. Change the output pipe of a pump");
        System.out.println("10. Pass Turn");
        System.out.println("11. End the game");

        // Prompt the user to enter a number
        System.out.print("Enter the number corresponding to your choice: ");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                System.out.println("You chose: Move to an element"); // element selection menu
                move();
                break;
            case 2:
                System.out.println("You chose: Pick up a pump");
                getPump(g.pumpList.getFirst());
                break;
            case 3:
                System.out.println("You chose: Insert pump into a pipe");
                insertPump(g.pumpList.getFirst(), g.pipeList.get(0));
                break;
            case 4:
                System.out.println("You chose: Fix a broken pump");
                fixPump(g.pumpList.getFirst());
                break;
            case 5:
                System.out.println("You chose: Fix a broken pipe");
                fixPipe(g.pipeList.get(0));
                break;
            case 6:
                System.out.println("You chose: Pick up the end of a pipe"); // selection based on connected pipes of the element you are standing on
                getEnd(pickedUpEoP);
                break;
            case 7:
                System.out.println("You chose: Insert the end of a pipe"); // up to implementer
                insertPipeEnd(g.pipeList.get(0));
                break;
            case 8:
                System.out.println("You chose: Change the input pipe of a pump"); // based on connectedpipes array
                changeInputPipe(g.pumpList.getFirst(), g.pipeList.get(0));
                break;
            case 9:
                System.out.println("You chose: Change the output pipe of a pump"); // based on connectedpipes array
                changeOutputPipe(g.pumpList.getFirst(), g.pipeList.get(0));
                break;
            case 10:
                System.out.println("You chose: Pass Turn");
                passTurn();
                break;
            case 11:
                System.out.println("You chose: End the game");
                g.endGame();
                exit(0);
                break;
            default:
                System.out.println("Invalid input, please choose one of the valid options (1-11).");
        }

    }

    /**
     * this method will allow a plumber to pick up an end of pipe object from the end of a pipe.
     * @param EoP this will be the end of pipe object that we will pick up.
     * @author : Majed
     */
    public void getEnd(EndOfPipe EoP){
        String userChoice;
        Scanner sc = new Scanner(System.in);
        System.out.println("Are you on the end of the pipe?");
        userChoice = sc.nextLine();
        if(userChoice.equalsIgnoreCase("yes")) {
            System.out.println("getEnd(EndOfPipe)");
            pickedUpEoP = EoP;
            System.out.println("The plumber has picked up an end of pipe. They can now insert it.");
        }
        else {
            System.out.println("You can not pick up the end of the pipe");
        }
    }


    /**
     * this method connects an end of pipe object to an element in the game.
     * @param e this will be the element that we will insert the pipe end to.\
     * @author :Majed
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

    /**
     * This method serves the purpose of fixing a punctured pipe.
     * The user is asked about the condition of the pipe before the game proceeds with any action,
     * if the pipe is punctured the plumber is allowed to fix if he is standing on it, otherwise the action will be aborted.
     * @param p will be the broken pipe to be fixed
     * @author Ibrahim
     */
    public  void fixPipe(Pipe p){
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

    /**
     * Method for picking up a pump that was manufactured at a cistern
     * @param p is the pump that will be picked up
     * @author Ibrahim
     */
    public  void getPump(Pump p) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Are you on the pump you want to pick up?");
        String userChoice = scanner.nextLine();

        if (userChoice.equalsIgnoreCase("yes")) {
            System.out.println("GetPump(p: Pump)\n You have picked up the pump.");
        } else {
            System.out.println("First go to a cistern that has a pump available for pick up.");
        }


    }

    /**
     * Methods that inserts a pump that was obtained from a cistern, into the pipe grid.
     * Pipe in which the pump is to be connected to, has to be specified.
     * @param pump will be the inserted pump
     * @param pipe will be where the pump is inserted on
     * @author Ibrahim
     *
     */
    public void insertPump(Pump pump, Pipe pipe){

        String userChoice;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Are you on the pipe you want to insert a pump on?");
        userChoice = scanner.nextLine();

        if (userChoice.equalsIgnoreCase("yes")) {
            System.out.println("InsertPump(p1:Pump, p2:Pipe)\nPipe.ConnectToElement(Pump)\n Pump is successfully inserted into the pipe system.");
        } else {
            System.out.println("Please move to the pipe you want to insert the pump on.");
        }

    }

    /**
     * this method fixes a broken pump
     * @param pump this will be the element that we will fix.\
     * @author Nafez
     */
    public void fixPump(Pump pump){
        String userChoice,userChoice2;
        Scanner sc = new Scanner(System.in);
        Scanner sc2 = new Scanner(System.in);
        System.out.println("Are you on the pump?");
        userChoice = sc.nextLine();
        if(userChoice.equalsIgnoreCase("yes")){
            System.out.println("is the pump working?");
            userChoice2 = sc2.nextLine();
            if(userChoice2.equalsIgnoreCase("no")){
                System.out.println("FixPump(Pump)\n Pump.Works=True;\n Pump is now repaired and working");
            }else{
                System.out.print("You can't fix a pump that is not broken.");
            }

        }else {
            System.out.println("Please first move to the pump you want to fix.");
        }


    }
}
