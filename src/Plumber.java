import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.NoSuchElementException;
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
//        try {
        if(!Game.testMode) {
            System.out.println("Player " + playerName + ", it's your turn.");
            System.out.println("What action would you like to perform?");
            System.out.println("Available actions for Plumbers:");
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
            System.out.print("Enter the number corresponding to your choice: ");
        }
        int choice = Integer.parseInt(Game.scanner.nextLine());
        switch (choice) {
            case 1:
                if (!Game.testMode)
                    System.out.println("You chose: Move to an element"); // element selection menu
                move();
                break;
            case 2:
                if (!Game.testMode)
                    System.out.println("You chose: Pick up a pump");
                getPump(g.pumpList.getFirst());
                break;
            case 3:
                if (!Game.testMode)
                    System.out.println("You chose: Insert pump into a pipe");
                insertPump(g.pumpList.getFirst(), g.pipeList.get(0));
                break;
            case 4:
                if (!Game.testMode)
                    System.out.println("You chose: Fix a broken pump");
                fixPump(g.pumpList.getFirst());
                break;
            case 5:
                if (!Game.testMode)
                    System.out.println("You chose: Fix a broken pipe");
                fixPipe(g.pipeList.get(0));
                break;
            case 6:
                if (!Game.testMode)
                    System.out.println("You chose: Pick up the end of a pipe"); // selection based on connected pipes of the element you are standing on
                getEnd(pickedUpEoP);
                break;
            case 7:
                if (!Game.testMode)
                    System.out.println("You chose: Insert the end of a pipe"); // up to implementer
                insertPipeEnd(g.pipeList.get(0));
                break;
            case 8:
                if (!Game.testMode)
                    System.out.println("You chose: Change the input pipe of a pump"); // based on connectedpipes array
                changeInputPipe(g.pumpList.getFirst(), g.pipeList.get(0));
                break;
            case 9:
                if (!Game.testMode)
                    System.out.println("You chose: Change the output pipe of a pump"); // based on connectedpipes array
                changeOutputPipe(g.pumpList.getFirst(), g.pipeList.get(0));
                break;
            case 10:
                if (!Game.testMode)
                    System.out.println("You chose: Pass Turn");
                passTurn();
                break;
            case 11:
                if (!Game.testMode)
                    System.out.println("You chose: End the game");
                g.endGame();
                exit(0);
                break;
            default:
                System.out.println("Invalid input, please choose one of the valid options (1-11).");
        }
//        } catch (NoSuchElementException e) {
//            PrintStream console = new PrintStream(new FileOutputStream(FileDescriptor.out));
//            console.println("No more lines to read from the file.");
//        }

    }

    /**
     * this method will allow a plumber to pick up an end of pipe object from the end of a pipe.
     * @param EoP this will be the end of pipe object that we will pick up.
     * @author : Majed
     */


    // getEnd method will allow the plumber to pick up the end of a pipe.
    // in order to pick up the end of pipe, the plumber has to be standing on the Element. we can check using currentElement attribute in the player class.
    // then we have to know which end of the pipe we are picking up, as there are 2 for each pipe.
    // we have to pick up the end of pipe from the selected element, which will be a parameter in the method.
    // so we iterate through the endsOfPipe array in the pipe class, and remove the end of pipe of that is connected to the passed element.
    // we have to check if the pipe p is in the connected elements list of the element e, if it is we will be able to pick it up.


    public void getEnd(EndOfPipe EoP, Pipe p, Element e){

        if (currentElement == e){
            if (e.connectedPipes.contains(p)){
                for (int i = 0; i < 2; i++){
                    if (p.endsOfPipe[i] == EoP){
                        p.endsOfPipe[i] = null;
                        EoP.disconnectFromElement(e);
                        EoP.setCurrentPipe(null);
                    }
                }
            }
            else { System.out.println("You can't pick up the end of the pipe from this element.");}
        }
        else { System.out.println("You have to be standing on the element to pick up the end of the pipe.");}

        }







    /**
     * this method connects an end of pipe object to an element in the game.
     * @param e this will be the element that we will insert the pipe end to.\
     * @author :Majed
     */

    // insertPipeEnd method will allow the plumber to insert the end of a pipe to an element.
    // in order to insert the end of pipe, the plumber has to be standing on the element.
    // then we have to know which pipe we are inserting the end of pipe to.
    //there will be a list in Element class called connectedPipes, which will store the pipes that are connected to the element.
    // and there will be a list in Element class called connectablePipes, which will store the pipes that can be connected to the element.
    // so we iterate through the connectablePipes list, and add the end of pipe to the chosen pipe from the selection menu. (so it will be a parameter in the method)
    // then we add the pipe to the connectedPipes list in the element class.
    // we have to check if the pipe is in connectable pipes list and NOT in connected pipes list, if it is not in the connected pipes list we will be able to connect it.
    // we have to make sure that after connecting the pipe the maximum number of connected pipes is not exceeded.
    // we have to update the connectedPipes list in the element class after connecting the pipe.
    // we have to insert the picked up end of pipe to the selected pipe.

    public void insertPipeEnd(Element e, Pipe p){

        // check if the player is standing on the element
        if (currentElement == e){

            // check if the pipe is connectable to the element and not connected to it and the element can connect to more pipes.
            if (e.connectablePipes.contains(p) && !e.connectedPipes.contains(p) && e.connectedPipes.size() < e.getMaxConnectablePipes()){
                e.connectedPipes.add(p); // add the pipe to the connected pipes list in the element class.

                // before assigning the end of pipe to the pipe, we have to check which end of the pipe we are inserting.

                for (int i = 0; i < 2; i++){
                    if (p.endsOfPipe[i] == null){
                        p.endsOfPipe[i] = pickedUpEoP; // assign the end of pipe to the pipe.
                        pickedUpEoP.connectToElement(e); // connect the end of pipe to the element.
                        pickedUpEoP.setCurrentPipe(p); // set the current pipe of the end of pipe to the selected pipe.
                        pickedUpEoP = null;
                    }
                    else {
                        System.out.println("You can't insert the end of the pipe to this element.");
                    }
                }
            }
            else { System.out.println("You can't insert the end of the pipe to this element.");}
        }

        else { System.out.println("You have to be standing on the element to insert the end of the pipe.");}
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
