import java.util.Scanner;

import static java.lang.System.exit;

/**
 * Serves as the central class for managing the game. It orchestrates gameplay, managing plumbers
 * and saboteurs, tracking the current player index, and overseeing elements crucial for water
 * management. The class provides essential methods for game initialization
 * and addition of elements to the system, alongside methods for determining the winner and calculating
 * game metrics.
 */
public class Game {
private Player[] players;
private Saboteur[] saboteurs;
private Plumber[] plumbers;

private int currentPlayerIndex;
private Element[] elementArray;
private Pipe[] pipeArray;
private Pump[] pumpArray;
private Cistern[] cisternArray;
private EndOfPipe[] endOfPipesArray;
private Spring[] springArray;
private int[] turnOrder;

private int[] gameScore = {0, 0}; // Say index 0 represents Plumber score, index 1 represents Saboteur score.
    public Game(Player[] players, Element[] gameElements ){} // this seems useless
    private Timer timer;
    public Game() {}


    /**
     * Initializes the game by setting up players and game elements.
     * Asks for the number of players and assigns them to teams (Plumbers or Saboteurs).
     * Ensures balanced team assignments for 2v2 or 3v3 setups.
     * Initializes game elements such as pipes, pumps, cisterns, and springs.
     * Starts the game.
     * @author Basel Al-Raoush
     */
    public void initGame() {
        Scanner scanner = new Scanner(System.in);

        // Asking for the number of players
        System.out.println("How many players will participate? Please enter a number (4 or 6):");
        int numPlayers = scanner.nextInt();

        // Check if the number of players is valid (4 or 6)
        if (numPlayers != 4 && numPlayers != 6) {
            System.out.println("Invalid number of players. Only 4 or 6 players are allowed.");
            return;
        }

        System.out.println("You've selected " + numPlayers + " players.");

// The majority of this code is not good.
//        plumbers = new Plumber[numPlayers / 2]; // Array to store plumber objects
//        saboteurs = new Saboteur[numPlayers / 2]; // Array to store saboteur objects
//
//        // Counter variables to keep track of plumbers and saboteurs
//        int plumberIndex = 0;
//        int saboteurIndex = 0;
//
//        // Ask for player names and team choice
//        for (int i = 0; i < numPlayers; i++) {
//            System.out.println("Enter the name of player " + (i + 1) + ":");
//            String playerName = scanner.next();
//
//            // Check if any team is already full
//            if (plumberIndex == numPlayers / 2) {
//                System.out.println(playerName + ", you must join the Saboteurs team.");
//                saboteurs[saboteurIndex++] = new Saboteur(playerName);
//                continue;
//            } else if (saboteurIndex == numPlayers / 2) {
//                System.out.println(playerName + ", you must join the Plumbers team.");
//                plumbers[plumberIndex++] = new Plumber(playerName);
//                continue;
//            }
//
//            // Ask for team choice
//            System.out.println("Select the team for " + playerName + ":");
//            System.out.println("Enter '1' for Plumbers and '2' for Saboteurs.");
//            int teamChoice = scanner.nextInt();
//
//            // Create player object based on team choice and add to respective array
//            if (teamChoice == 1) {
//                plumbers[plumberIndex++] = new Plumber(playerName);
//            } else if (teamChoice == 2) {
//                saboteurs[saboteurIndex++] = new Saboteur(playerName);
//            } else {
//                System.out.println("Invalid team choice.");
//                return;
//            }
//        }


        // Game initialization
        System.out.println("Initializing the game...");
        int k=0;
        elementArray= new Element[500];
        pipeArray = new Pipe[5];
        for (int j = 0; j < pipeArray.length; j++) {
            pipeArray[j] = new Pipe();
            addPipe(pipeArray[j]);
            elementArray[k]=pipeArray[j];
            k++;
        }
        pumpArray = new Pump[5];
        for (int j = 0; j < pumpArray.length; j++) {
            pumpArray[j] = new Pump();
            addPump(pumpArray[j]);
            elementArray[k]=pumpArray[j];
            k++;
        }
        cisternArray=new Cistern[1];
        for(int j=0;j<cisternArray.length;j++){
            cisternArray[j]= new Cistern();
            addCistern(cisternArray[j]);
            elementArray[k]=cisternArray[j];
            k++;
        }
        springArray= new Spring[1];
        for (int j=0;j<springArray.length;j++){
            springArray[j]=new Spring();
            addSpring(springArray[j]);
            elementArray[k]=springArray[j];
            k++;
        }
        endOfPipesArray= new EndOfPipe[10];
        for (int j=0;j<endOfPipesArray.length;j++){
            endOfPipesArray[j]=new EndOfPipe();
        }
        System.out.println("Game initialization completed.");

        // Start game
        startGame();


    }

    /**
     * Starts the game and manages turns for players.
     * starts the game timer, places players on the map, and starts water flow.
     * Players take turns performing actions until the game ends.
     * Actions include moving, interacting with pipes and pumps, allowing the players
        to perform actions based on their team (Plumber or Saboteur).

     * Each player's turn is executed sequentially, with actions being chosen based on user input,

     *The method handles player turns,provides feedback to players throughout the game,
      keeping them informed of the current state and their available actions.
     * @author Basel Al-Raoush
     */
    public void startGame() {
        System.out.println("startGame()");

        System.out.println("Starting the game...");

        Timer.setTime(5.0);
        System.out.println("Game timer started.");

        System.out.println("Players are placed on the map.");

        System.out.println("Water flow has started.");

        System.out.println("The game has started!");


        int numPlumbers = plumbers.length;
        int numSaboteurs = saboteurs.length;

        // Initialize plumber and saboteur indices outside the loop
        int plumberIndex = 0;
        int saboteurIndex = 0;
        int i = 0;
        while(true) {
            // Determine whose turn it is based on the current turn number.
            // If the turn number is even, it's the plumber's turn; otherwise, it's the saboteur's turn.

            boolean isPlumberTurn = i % 2 == 0;
            // Check if it's the plumber's turn and there are plumbers left to play
            if (isPlumberTurn) {
                i++;
                System.out.println("Plumber's turn:");
                int choice = plumbers[plumberIndex].takeTurn();
                switch (choice) {
                    case 1:
                        System.out.println("You chose: Move to an element");
                        plumbers[plumberIndex].move();
                        break;
                    case 2:
                        System.out.println("You chose: Pick up a pump");
                        plumbers[plumberIndex].getPump(pumpArray[0]);
                        break;
                    case 3:
                        System.out.println("You chose: Insert pump into a pipe");
                        plumbers[plumberIndex].insertPump(pumpArray[0],pipeArray[0]);
                        break;
                    case 4:
                        System.out.println("You chose: Fix a broken pump");
                        plumbers[plumberIndex].fixPump(pumpArray[0]);
                        break;
                    case 5:
                        System.out.println("You chose: Fix a broken pipe");

                        plumbers[plumberIndex].fixPipe(pipeArray[0]);
                        break;
                    case 6:
                        System.out.println("You chose: Pick up the end of a pipe");
                        plumbers[plumberIndex].getEnd(endOfPipesArray[0]);
                        break;
                    case 7:
                        System.out.println("You chose: Insert the end of a pipe");
                        plumbers[plumberIndex].insertPipeEnd(pipeArray[0]);
                        break;
                    case 8:
                        System.out.println("You chose: Change the input pipe of a pump");
                        plumbers[plumberIndex].changeInputPipe(pumpArray[0],pipeArray[0]);
                        break;
                    case 9:
                        System.out.println("You chose: Change the output pipe of a pump");
                        plumbers[plumberIndex].changeOutputPipe(pumpArray[0],pipeArray[0]);
                        break;
                    case 10:
                        System.out.println("You chose: Pass Turn");
                        plumbers[plumberIndex].passTurn();
                        break;
                    case 11:
                        System.out.println("You chose: End the game");
                        determineWinner();
                        exit(0);
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter a number corresponding to the action you want to perform.");
                }
                if(numPlumbers == 2) {
                    plumberIndex++;
                    if (plumberIndex == 2) {
                        plumberIndex = 0;
                    }
                }
                else if(numPlumbers == 3)
                {
                    plumberIndex++;
                    if (plumberIndex == 3) {
                        plumberIndex = 0;
                    }
                }

            }
            // If it's not the plumber's turn.
            else if (!isPlumberTurn) {
                i++;
                System.out.println("Saboteur's turn:");
                int choice = saboteurs[saboteurIndex].takeTurn();
                switch (choice) {
                    case 1:
                        System.out.println("You chose: Move to an element");
                        saboteurs[saboteurIndex].move();
                        break;
                    case 2:
                        System.out.println("You chose: Change the input pipe of a pump");
                        saboteurs[saboteurIndex].changeInputPipe(pumpArray[0],pipeArray[0]);
                        break;
                    case 3:
                        System.out.println("You chose: Change the output pipe of a pump");
                        saboteurs[saboteurIndex].changeOutputPipe(pumpArray[0],pipeArray[0]);
                        break;
                    case 4:
                        System.out.println("You chose: Puncture a pipe");
                        saboteurs[saboteurIndex].puncture(pipeArray[0]);
                        break;
                    case 5:
                        System.out.println("You chose: Pass Turn");
                        saboteurs[saboteurIndex].passTurn();
                        break;
                    case 6:
                        System.out.println("You chose: End the game");
                        determineWinner();
                        exit(0);
                    default:
                        System.out.println("Invalid choice. Please enter a number corresponding to the action you want to perform.");
                }
                // this is if the number of players is two, so that the loop will work correctly.
                if(numPlumbers == 2) {
                    saboteurIndex++;
                    if (saboteurIndex == 2) {
                        saboteurIndex = 0;
                    }
                }
                else if(numPlumbers == 3)
                {
                    saboteurIndex++;
                    if(saboteurIndex == 3)
                    {
                        saboteurIndex = 0;
                    }
                }
            }
        }
    }

    /**
     * Ends the game and evaluates the results to determine the winner.
     * This method stops the game timer and evaluates the results to determine
     * which team (Saboteurs or Plumbers) wins the game based on the comparison between leaked and collected water.
     */
    public void endGame(){
    System.out.println("endGame()");
    System.out.println("The timer has stopped, and the game has ended. The results are being evaluated...");
    determineWinner();
}

    /**
     *Advances the game to the next player's turn.
     */
    public void nextTurn(){
    System.out.println("nextTurn()");
    System.out.println("It's now the turn of the next player.");

}

    /**
     * Adds a new pipe to the game.
     * This method adds a new pipe element to the system.
     * @param pipe The pipe to be added.
     */
public void addPipe(Pipe pipe){
    System.out.println("addPipe(Pipe)");
    System.out.println("A new pipe has been added.");
}

    /**
     * Adds a new pump to the game.
     * This method adds a new pump element to the system.
     * @param pump The pump to be added.
     */
public void addPump(Pump pump){
    System.out.println("addPump(Pump)");
    System.out.println("A new pump has been added.");
}

    /**
     * Adds a new cistern to the game.
     * This method adds a new cistern element to the system.
     * @param c The cistern to be added.
     */
public void addCistern(Cistern c){
    System.out.println("addCistern(Cistern)");
    System.out.println("A new Cistern has been added.");
}

    /**
     * Adds a new spring to the game.
     * This method adds a new spring element to the  system.
     * @param s The spring to be added.
     */
public void addSpring(Spring s){
    System.out.println("addSpring(Spring)");
    System.out.println("A new Spring has been added.");
}

    /**
     *Determines the winner of the game based on the comparison between leaked and collected water.
     *If the leaked water was greater, the Saboteurs win the game. Otherwise, the Plumbers win.
     */
    public void determineWinner() {
        System.out.println("determineWinner()");
        calculateLeakedWater();
        calculateCollectedWater();
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter 1 if the leaked water was greater than collected water.");
        String input = sc.nextLine();

        if (input.trim().equals("1")) {
            System.out.println("The Saboteurs won the game.");
        } else {
            System.out.println("The Plumbers won the game.");
        }
    }

    /**
     *method to calculate the amount of water leaked from the pipes during the entire game.
     */
    public void calculateLeakedWater(){
    System.out.println("calculateLeakedWater()");
}

    /**
     *method to calculate the amount of water collected by the cistern during the entire game.
     */
    public void calculateCollectedWater(){
    System.out.println("calculateCollectedWater()");
}
}
