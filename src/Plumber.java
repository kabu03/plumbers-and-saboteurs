import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.*;

import static java.lang.System.exit;

/**
 * Represents players tasked with maintaining and repairing the pipe system. Plumbers can fix
 * broken pumps, repair leaking pipes, manage pipe ends, and extend the system. They play a
 * crucial role in setting pump directions and defending against sabotage, with their actions
 * being vital for water transfer efficiency and system operation.
 */
public class Plumber extends Player {
    public Pump pickedUpPump=null;
    private Pipe TempPipe; // ?
    public EndOfPipe pickedUpEoP;

    public Plumber(String playerName) {
        this.playerName = playerName;
    }

    public int newPipecount=1;
    public int newPumpCount=1;

    /**
     * Allows the Plumber player to take their turn.
     * Displays available actions and prompts the player to choose one.
     *
     * Overrides the abstract takeTurn method of the Player class.
     *
     *
     * @author Basel Al-Raoush
     * The method allows each player 2 actions to pick from in a 5-second interval for each turn.
     *                      In essence in each turn the player has either 2 actions to perform within 5 seconds.
     * @author Ibrahim Muheisen
     */
    @Override
    protected void takeTurn(Game g) { // should happen twice, user should be prompted twice EXCEPT FOR PASS TURN
        boolean passflag = false;
        int actionstaken=0;
        long turnStartTime = System.currentTimeMillis();
        long turnDuration = 5000; //

        if (!Game.testMode) {
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

        while (System.currentTimeMillis() < turnStartTime + turnDuration && actionstaken < 2) {
            // Check if input is available
            try {
                if (System.in.available() > 0 || Game.testMode) {
                    int choice = Integer.parseInt(Game.scanner.nextLine());
                    switch (choice) {
                        case 1:
                            if (!Game.testMode)
                                System.out.println("You chose: Move to an element");
                            move(g);
                            actionstaken++;
                            break;
                        case 2:
                            if (!Game.testMode)
                                System.out.println("You chose: Pick up a pump");
                             getPump(g);
                            actionstaken++;
                            break;
                        case 3:
                            if (!Game.testMode)
                                System.out.println("You chose: Insert pump into a pipe");
                             insertPump(g);
                            actionstaken++;

                            break;
                        case 4:
                            if (!Game.testMode)
                                System.out.println("You chose: Fix a broken pump");
                             fixPump();
                            actionstaken++;
                            break;
                        case 5:
                            if (!Game.testMode)
                                System.out.println("You chose: Fix a broken pipe");
                            fixPipe();
                            actionstaken++;
                            break;
                        case 6:
                            if (!Game.testMode)
                                System.out.println("You chose: Pick up the end of a pipe");
                            getEnd(currentElement);
                            actionstaken++;
                            break;
                        case 7:
                            if (!Game.testMode)
                                System.out.println("You chose: Insert the end of a pipe");
                            insertPipeEnd(currentElement);
                            actionstaken++;
                            break;
                        case 8:
                            if (!Game.testMode)
                                System.out.println("You chose: Change the input pipe of a pump");
                            changeInputPipe(g);
                            actionstaken++;
                            break;
                        case 9:
                            if (!Game.testMode)
                                System.out.println("You chose: Change the output pipe of a pump");
                            changeOutputPipe(g);
                            actionstaken++;
                            break;
                        case 10:
                            if (!Game.testMode)
                                System.out.println("You chose: Pass Turn");
                            passflag = true;
                            passTurn();
                            return;
                        case 11:
                            if (!Game.testMode)
                                System.out.println("You chose: End the game");
                            g.endGame();
                            exit(0);
                            break;
                        default:
                            System.out.println("Invalid input, please choose one of the valid options (1-11).");
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        if (passflag && actionstaken==1){
            return;
        }
    }


    /**
     * This method allows a plumber to pick up an end of a pipe from a selected pipe.
     * The plumber must be standing on the element where the pipe is connected.
     * The method lists all connected pipes to the element and prompts the user to select a pipe.
     * If the selected pipe is valid and connected to the element, the method attempts to pick up an end of the pipe.
     * The end of the pipe is disconnected from the element and the pipe, and is then assigned to the plumber.
     * If no free end is available to pick up from the selected pipe, an appropriate message is displayed.
     *
     * @param e The element from which an end of a pipe is to be picked up.
     * @author : Basel Al-Raoush , Nafez sayyad
     */
    public void getEnd(Element e) {
        // First, check if the plumber is standing on the element
        if (currentElement != e) {
            System.out.println("You have to be standing on the element to pick up the end of the pipe.");
            return;
        }

        if (!Game.testMode) {
            // List connected pipes
            System.out.println("Connected pipes to " + e.getName() + ":");
            if (e.connectedPipes.isEmpty()) {
                System.out.println("There are no connected pipes.");
                return;
            }
            e.connectedPipes.forEach(pipe -> System.out.println(pipe.getName()));

            // Get user input on which pipe to manipulate
            System.out.print("Enter the name of the pipe to pick up an end from: ");
        }
        String pipeName = Game.scanner.nextLine();
        Pipe selectedPipe = e.connectedPipes.stream()
                .filter(pipe -> pipe.getName().equals(pipeName))
                .findFirst()
                .orElse(null);

        if (selectedPipe == null) {
            System.out.println("Invalid pipe selection or pipe is not connected.");
            return;
        }

        // Attempt to pick up the end of the selected pipe
        for (EndOfPipe end : selectedPipe.endsOfPipe) {
            if (end != null) {
                end.disconnectFromElement(e); // This should handle both the element and pipe updates
                pickedUpEoP = end;
                System.out.println(playerName + " picked up the end of " + selectedPipe.getName()+" connected to "+ currentElement.getName());
                return;
            }
        }
        System.out.println("No free end available to pick up from selected pipe.");
    }


    /**
     * This method allows a plumber to insert an end of a pipe to a selected element.
     * The plumber must be standing on the element where the pipe is to be connected.
     * The method checks if the plumber has picked up an end of a pipe and if the element is connectable.
     * If the selected element is valid and connectable, the method attempts to insert the end of the pipe.
     * The end of the pipe is connected to the element and the pipe, and is then removed from the plumber.
     * If the end of the pipe cannot be inserted to the element, an appropriate message is displayed.
     *
     * @param e The element to which an end of a pipe is to be inserted.
     * @author :Basel Al-Raoush , Nafez sayyad
     */
    public void insertPipeEnd(Element e) {
        // Check if the player is standing on the element
        if (currentElement != e) {
            System.out.println("You have to be standing on the element to insert the end of the pipe.");
            return;
        }

        // Check if the player has picked up an end of pipe
        if (pickedUpEoP == null) {
            System.out.println("No end of pipe picked up to insert.");
            return;
        }

        if (!Game.testMode) {
            // List the connectable pipes that are not yet fully connected
            if (e.connectablePipes.isEmpty()) {
                System.out.println("There are no connectable pipes available at this element.");
                return;
            }
            System.out.println("Connectable pipes:");
            e.connectablePipes.forEach(pipe -> System.out.println(pipe.getName()));

            // Get user input on which pipe to connect the end to
            System.out.print("Enter the name of the pipe to insert the end into: ");
        }
        String pipeName = Game.scanner.nextLine();
        Pipe selectedPipe = e.connectablePipes.stream()
                .filter(pipe -> pipe.getName().equals(pipeName))
                .findFirst()
                .orElse(null);

        if (selectedPipe == null) {
            System.out.println("Invalid pipe selection or not connectable.");
            return;
        }

        // Check if the selected pipe can accept more connections
        if (selectedPipe.endsOfPipe[0] != null && selectedPipe.endsOfPipe[1] != null) {
            System.out.println("Selected pipe already has both ends connected.");
            return;
        }

        // Insert the end of pipe to the selected pipe and connect it to the element
        for (int i = 0; i < 2; i++) {
            if (selectedPipe.endsOfPipe[i] == null) {
                selectedPipe.endsOfPipe[i] = pickedUpEoP; // Assign the end of pipe to the pipe
                pickedUpEoP.connectToElement(e); // Connect the end of pipe to the element
                pickedUpEoP.setCurrentPipe(selectedPipe); // Set the current pipe of the end of pipe to the selected pipe
                if (!e.connectedPipes.contains(selectedPipe)) {
                    e.connectedPipes.add(selectedPipe); // Add the pipe to the connected pipes list if not already added
                }
                pickedUpEoP = null; // Clear the picked up end of pipe after insertion

                if (Game.testMode) {
                    System.out.println(playerName + " inserted the end of pipe into " + selectedPipe.getName());
                }
                return;
            }
        }
    }


    /**
     * This method serves the purpose of fixing a punctured pipe.
     * if the pipe is punctured the plumber is allowed to fix if he is standing on it, otherwise the action will be aborted.
     * Conditions Checked: Plumber is occupying a pipe that has been punctured.
     * @author Ibrahim
     */
    public  void fixPipe() {
        if (currentElement instanceof Pipe && currentElement.getWorks())
            System.out.println(playerName + " attempted to fix " + currentElement.getName() + ", but it's already working.");
        if (currentElement instanceof Pipe) {
            if (!currentElement.getWorks()) {
                currentElement.setWorks(true);
                System.out.println(playerName + " fixed " + currentElement.getName());
            }
        } else
            System.out.println("You need to be standing on a punctured pipe to fix it.");
    }


    /**
     * Method for picking up a pump that was manufactured at a cistern
     * @param g1 is the Game instance
     *  Conditions Checked: Currently occupying a Cistern.
     *                      Whether the Cistern has a manufactured pump ready.
     * @author Ibrahim
     */
    public  void getPump(Game g1) {
        if(!(currentElement instanceof Cistern)){
            System.out.println("You are not on a Cistern, move to a cistern with a manufactured pump to pick it up");
            return;
        }
        if(((Cistern) currentElement).manufacturedPump==null){
            System.out.println("This cistern does not have a pump available for pickup.");
            return;
        }
        pickedUpPump=((Cistern) currentElement).manufacturedPump;
        g1.pumpList.add(pickedUpPump);
        System.out.println(playerName + " picked up " + ((Cistern) currentElement).manufacturedPump.getName() + " from the Cistern.");


    }

    /**
     * Methods that inserts a pump that was obtained from a cistern, into the pipe grid.
     *
     * @param g1 is the game instance.
     * When called, the method will simulate the breaking of a pipe (currently occupied) into 2 new pipes.
     *          initialized with the respective connectivity based on direction through EndOfPipe instances.
     * In essence, the pump inserted (pickedUpPump) is inserted in the middle of the old pipe, where two new pipes simulate the 2 broken halves.
     *           Connectivity with the new pump is also handled.
     * Each element is also inserted into their respective element-lists, index accounted for when needed.
     * Condition checks implemented to handle when the circumstance is not applicable.(!Currently occupying a pipe & !Having a pump picked up form a  cistern.)
     * @author Ibrahim
     *
     */
    public void insertPump(Game g1){
        if (pickedUpPump != null)
            if (currentElement instanceof Pipe) {
                Pipe pipe = (Pipe) currentElement;

                Pump newPump;
                newPump=pickedUpPump;
                Pipe newPipe1 = new Pipe("newPipe"+newPipecount);
                newPipecount++;
                Pipe newPipe2 = new Pipe("newPipe"+newPipecount);
                newPipecount++;

                EndOfPipe newEnd1A = new EndOfPipe(newPipe1);
                EndOfPipe newEnd1B = new EndOfPipe(newPipe1);
                EndOfPipe newEnd2A  = new EndOfPipe(newPipe2);
                EndOfPipe newEnd2B  = new EndOfPipe(newPipe2);

                if(pipe.endsOfPipe[0] != null) {
                    newPipe1.endsOfPipe[0] = pipe.endsOfPipe[0];
                }
                newPipe1.endsOfPipe[0].setCurrentPipe(newPipe1);
                newPipe1.endsOfPipe[1].setCurrentPipe(newPipe1);
                if(pipe.endsOfPipe[1] != null) // in the test case pipe 6 has only one and we need to take that into consideration.
                {
                    newPipe2.endsOfPipe[1]=pipe.endsOfPipe[1];
                }
                newPipe2.endsOfPipe[1].setCurrentPipe(newPipe2);
                newPipe2.endsOfPipe[0].setCurrentPipe(newPipe2);

                newPipe1.leakedAmount=pipe.leakedAmount/2;
                newPipe2.leakedAmount=pipe.leakedAmount/2;

                pipe.setWorks(false);
                int index = g1.pipeList.indexOf(pipe);
                int index2 = g1.elementList.indexOf(pipe);
                /* these are not needed since connectable list order does not effect anything
                int index3 = pipe.endsOfPipe[0].getConnectedElement().connectablePipes.indexOf(pipe);
                int index4= pipe.endsOfPipe[1].getConnectedElement().connectablePipes.indexOf(pipe);
                 */
                g1.elementList.remove(pipe);
                g1.pipeList.remove(pipe);

                g1.pipeList.add(index, newPipe1);
                g1.pipeList.add(index + 1, newPipe2);

                g1.elementList.add(index2,newPipe1);
                g1.elementList.add(index2+1,newPump);
                g1.elementList.add(index2+2,newPipe2);
                if(pipe.endsOfPipe[0] != null) {
                    pipe.endsOfPipe[0].getConnectedElement().connectablePipes.remove(pipe);
                }
                if(pipe.endsOfPipe[1] != null) {
                    pipe.endsOfPipe[1].getConnectedElement().connectablePipes.remove(pipe);
                }
                if(pipe.endsOfPipe[0] != null) {
                    pipe.endsOfPipe[0].getConnectedElement().connectablePipes.add(newPipe1);
                }
                if(pipe.endsOfPipe[1] != null){
                pipe.endsOfPipe[1].getConnectedElement().connectablePipes.add(newPipe2);}

                newPipe1.endsOfPipe[1].connectToElement(newPump);
                newPipe2.endsOfPipe[0].connectToElement(newPump);

                newPump.connectedPipes.add(newPipe1);
                newPump.connectedPipes.add(newPipe2);

                g1.pumpList.add(newPump);

                pickedUpPump=null;
                System.out.println(playerName + " inserted a pump into "+ pipe.getName() + ".");

            }
        else if(currentElement instanceof Pipe && pickedUpPump==null){
            System.out.println("You dont have pump picked up to insert here.");
        } else {
            System.out.println("You have to have picked up a pump and be standing on a pipe.");
        }
    }

    /**
     * this method fixes a broken pump.
     * Conditions checked: Currently occupying a broken pump.
     * @author Ibrahim
     */
    public void fixPump(){
        if(currentElement instanceof Pump && currentElement.getWorks()){
            System.out.println(playerName + " attempted to fix " + currentElement.getName() + ", but it's already working.");}
        if (currentElement instanceof Pump) {
            if (!currentElement.getWorks()) {
                currentElement.setWorks(true);
                System.out.println(playerName + " fixed " + currentElement.getName());
            }
        } else {
            System.out.println("You need to be standing on a broken pump to fix it.");}
    }
    }