import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.exit;

/*
Changes made on Game:
changed the element array into a List
Changed the pipe Array into a list
Changed the pump Array into a list
Removed end of pipe array
 */

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
    private int currentPlayerIndex = 0;
    public List<Element> elementList;
    public List<Pipe> pipeList;
    public List<Pump> pumpList;
    private List<Cistern> cisternList;
    private List<Spring> springList;
    private int[] gameScore = {0, 0}; // Index 0 represents Plumber score, index 1 represents Saboteur score.
    private Timer timer;
    public static boolean testMode;

    public Game(boolean isTesting) {
        testMode = isTesting;
    }


    /**
     * Initializes the game by setting up players and game elements.
     * Asks for the number of players and assigns them to teams (Plumbers or Saboteurs).
     * Ensures balanced team assignments for 2v2 or 3v3 setups.
     * Initializes game elements such as pipes, pumps, cisterns, and springs.
     * Starts the game.
     *
     * @author Basel Al-Raoush
     */
    public void initGame() {
        Scanner scanner;
        if (testMode) {
            try {
                scanner = new Scanner(new File("input.txt"));
            } catch (FileNotFoundException e) {
                System.out.println("Input file not found.");
                return;
            }
        } else {
            scanner = new Scanner(System.in);
        }

        int numPlayers;
        while (true) {
            System.out.println("How many players will participate? Please enter a number (4 or 6):");
            numPlayers = scanner.nextInt();

            if (numPlayers == 4 || numPlayers == 6) {
                // Valid number of players entered, break the loop
                break;
            } else {
                System.out.println("Invalid number of players. Only 4 or 6 players are allowed.");
            }
        }

        System.out.println("You've selected " + numPlayers + " players.");

        plumbers = new Plumber[numPlayers / 2]; // Array to store plumbers
        saboteurs = new Saboteur[numPlayers / 2]; // Array to store saboteurs

        // Counter variables to keep track of plumbers and saboteurs
        int plumberIndex = 0;
        int saboteurIndex = 0;

        // Ask for player names and team choice
        for (int i = 0; i < numPlayers; i++) {
            String playerName;
            while (true) {
                System.out.println("Enter the name of player " + (i + 1) + ":");
                playerName = scanner.nextLine().trim(); // Use trim() to remove leading and trailing spaces

                if (!playerName.isEmpty()) {
                    // Valid player name entered, break the loop
                    break;
                } else {
                    System.out.println("Invalid input. Player name cannot be empty.");
                }
            }

            // Feature to enforce team balancing
            if (plumberIndex == numPlayers / 2) {
                System.out.println(playerName + ", you must join the Saboteurs team.");
                saboteurs[saboteurIndex++] = new Saboteur(playerName);
                continue;
            } else if (saboteurIndex == numPlayers / 2) {
                System.out.println(playerName + ", you must join the Plumbers team.");
                plumbers[plumberIndex++] = new Plumber(playerName);
                continue;
            }

            // Ask for team choice
            System.out.println("Select the team for " + playerName + ":");
            System.out.println("Enter '1' for Plumbers and '2' for Saboteurs.");
            int teamChoice;
            while (true) {
                System.out.println("Select the team for " + playerName + ":");
                System.out.println("Enter '1' for Plumbers and '2' for Saboteurs.");
                teamChoice = scanner.nextInt();

                if (teamChoice == 1) {
                    plumbers[plumberIndex++] = new Plumber(playerName);
                    break;
                } else if (teamChoice == 2) {
                    saboteurs[saboteurIndex++] = new Saboteur(playerName);
                    break;
                } else {
                    System.out.println("Invalid team choice. Please enter '1' for Plumbers or '2' for Saboteurs.");
                }
            }
        }
        players = new Player[numPlayers];
        int tmp = 0;
        for (int i = 0; i < plumbers.length; i++) {
            players[tmp++] = plumbers[i];  // Add plumber
            if (i < saboteurs.length) {
                players[tmp++] = saboteurs[i];  // Add saboteur
            }
        }
        // Game initialization
        System.out.println("Initializing the game...");
        elementList = new ArrayList<>();
        pipeList = new ArrayList<>();
        pumpList = new ArrayList<>();
        springList = new ArrayList<>();
        cisternList = new ArrayList<>();
        if(!testMode) {
            Spring s1 = new Spring(); // Creating the spring
            addSpring(s1);
            Pipe upperPipe = new Pipe(); // creation of the upper pipe and connecting it to the spring
            EndOfPipe p1upper = new EndOfPipe(upperPipe);
            EndOfPipe p2upper = new EndOfPipe(upperPipe);
            p1upper.connectToElement(s1);
            addPipe(upperPipe); // Creating the upper pump, connecting it to the upper pipe
            Pump upperPump = new Pump();
            p2upper.connectToElement(upperPump);
            addPump(upperPump);
            Pipe middlePipe = new Pipe(); // Creating the middle pipe, connecting it to the upper pump
            EndOfPipe p1middle = new EndOfPipe(middlePipe);
            EndOfPipe p2middle = new EndOfPipe(middlePipe);
            addPipe(middlePipe);
            p1middle.connectToElement(upperPump);
            Pump lowerPump = new Pump(); // Creating the lower pump, connecting it to the middle pipe
            p2middle.connectToElement(lowerPump);
            addPump(lowerPump);
            Pipe lowerPipe = new Pipe(); // Creating the lower pipe, connecting it to the lower pump
            EndOfPipe p1lower = new EndOfPipe(lowerPipe);
            EndOfPipe p2lower = new EndOfPipe(lowerPipe);
            addPipe(lowerPipe);
            p1lower.connectToElement(lowerPump);
            Cistern cistern = new Cistern(this); // Creating the cistern, connecting it to the lower pipe
            addCistern(cistern);
            p2lower.connectToElement(cistern);
        }
        else if (testMode){ // Make the test map
            Spring s1 = new Spring(); // Creating the spring
            addSpring(s1);
            Cistern cistern = new Cistern(this); // Creating the cistern
            addCistern(cistern);
            for(int i = 0; i<8; i++){
                Pipe pipe = new Pipe();
                addPipe(pipe);
            }
//            for (int i = 0; i<15; i++){
//                EndOfPipe EoP = new EndOfPipe();
//            }
            // Pipe list. Pipe 6 is element 6, index 5.
            // Pipe 8 is element 8, index 7
            for(int i = 0; i<4; i++){
                Pump pump = new Pump();
                addPump(pump);
            }

        }
        // Start game
        startGame();
    }

    /**
     * Starts the game and manages turns for players.
     * starts the game timer, places players on the map, and starts water flow.
     * Players take turns performing actions until the game ends.
     * Actions include moving, interacting with pipes and pumps, allowing the players
     * to perform actions based on their team (Plumber or Saboteur).
     * <p>
     * Each player's turn is executed sequentially, with actions being chosen based on user input,
     * <p>
     * The method handles player turns,provides feedback to players throughout the game,
     * keeping them informed of the current state and their available actions.
     *
     * @author Basel Al-Raoush
     */
    public void startGame() {
        System.out.println("Starting the game...");
        Timer.setTime(5.0);
        System.out.println("Game timer started.");
        System.out.println("Players are placed on the map.");
        System.out.println("Water flow has started.");
        System.out.println("The game has started!");

        while (true) {
            Player currentPlayer = players[currentPlayerIndex]; // TIMER IMPLEMENTATION
            currentPlayer.takeTurn(this);
            for (Element e: elementList){
                e.update();
            }
                currentPlayerIndex = (currentPlayerIndex + 1) % players.length;
            }
        }
    /**
     * Ends the game and evaluates the results to determine the winner.
     * This method stops the game timer and evaluates the results to determine
     * which team (Saboteurs or Plumbers) wins the game based on the comparison between leaked and collected water.
     */
    public void endGame() {
        Timer.setTime(0);
        System.out.println("The timer has stopped, and the game has ended. The results are being evaluated...");
        determineWinner();
    }

    /**
     * Adds a new pipe to the game.
     * This method adds a new pipe element to the system.
     *
     * @param pipe The pipe to be added.
     */
    public void addPipe(Pipe pipe) {
        elementList.add(pipe);
        pipeList.add(pipe);
    }

    /**
     * Adds a new pump to the game.
     * This method adds a new pump element to the system.
     *
     * @param pump The pump to be added.
     */
    public void addPump(Pump pump) {
        elementList.add(pump);
        pumpList.add(pump);
    }

    /**
     * Adds a new cistern to the game.
     * This method adds a new cistern element to the system.
     *
     * @param c The cistern to be added.
     */
    public void addCistern(Cistern c) {
        elementList.add(c);
        cisternList.add(c);
    }

    /**
     * Adds a new spring to the game.
     * This method adds a new spring element to the  system.
     *
     * @param s The spring to be added.
     */
    public void addSpring(Spring s) {
        elementList.add(s);
        springList.add(s);
    }

    /**
     * Determines the winner of the game based on the comparison between leaked and collected water.
     * If the leaked water was greater, the Saboteurs win the game. Otherwise, the Plumbers win.
     */
    public void determineWinner() {
        int waterLeaked = calculateLeakedWater();
        int waterCollected = calculateCollectedWater();

        if (waterLeaked > waterCollected) {
            System.out.println("Total water leaked was " + waterLeaked + ", and total water collected was " + waterCollected + ". The Saboteurs won the game.");
        } else if (waterLeaked < waterCollected) {
            System.out.println("Total water leaked was " + waterLeaked + ", and total water collected was " + waterCollected + ". The Plumbers won the game.");
        }
        else {
            System.out.println("The game ended in a tie, as the amount of water leaked and collected was the same.");
        }
    }

    /**
     * method to calculate the amount of water leaked from the pipes during the entire game.
     */
    public int calculateLeakedWater() {
        int sum = 0;
        for (Pipe pipe : pipeList) {
            sum += pipe.leakedAmount;
        }
        gameScore[1] = sum;
        return sum;
    }

    /**
     * method to calculate the amount of water collected by the cistern during the entire game.
     */
    public int calculateCollectedWater() {
        int sum = 0;
        for (Cistern cistern : cisternList) {
            sum += cistern.getWaterLevel();
        }
        gameScore[0] = sum;
        return sum;
    }
}
