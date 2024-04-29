import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
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
    private final int[] gameScore = {0, 0}; // Index 0 represents Plumber score, index 1 represents Saboteur score.
    public Timer timer;
    public static boolean testMode;
    public static String inputFilePath;
    public static String outputFilePath;
    public static Scanner scanner;
    public static PrintStream output;
    public static int testNumber;

    public Game(boolean isTesting) {
        testMode = isTesting;
        if (testMode) {
            try {
                System.out.println(inputFilePath);
                scanner = new Scanner(new File(inputFilePath));
                output = new PrintStream(new FileOutputStream(outputFilePath));
            } catch (FileNotFoundException e) {
                System.out.println("Input file not found.");
                return;
            }
        } else {
            scanner = new Scanner(System.in);
            output = System.out;
        }
        System.setOut(output);
        // System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out))); Important, keep it.
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
        int numPlayers;
        System.out.println("Proceeding to the number of participating players menu.");
        while (true) {
            if(!testMode)
                System.out.println("How many players will participate? Please enter a number (4 or 6):");
            try {
                numPlayers = Integer.parseInt(scanner.nextLine());

                if (numPlayers == 4 || numPlayers == 6) {
                    System.out.println(numPlayers + " players will participate in the game.");
                    break;
                } else {
                    System.out.println("Invalid input, please choose one of the valid options (4 or 6).");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input, please choose one of the valid options (4 or 6).");
            }
        }
        if(!testMode)
            System.out.println("You've selected " + numPlayers + " players.");
        System.out.println("Proceeding to the username and team selection menu.");
        plumbers = new Plumber[numPlayers / 2]; // Array to store plumbers
        saboteurs = new Saboteur[numPlayers / 2]; // Array to store saboteurs

        // Counter variables to keep track of plumbers and saboteurs
        int plumberIndex = 0;
        int saboteurIndex = 0;

        // Ask for player names and team choice
        for (int i = 0; i < numPlayers; i++) {
            String playerName;
            while (true) {
                if(!testMode)
                    System.out.println("Enter the name of player " + (i + 1) + ":");
                playerName = scanner.nextLine().trim(); // Use trim() to remove leading and trailing spaces

                if (!playerName.isEmpty()) {
                    System.out.println("The name \"" + playerName + "\" is validated.");
                    break;
                } else {
                    System.out.println("Invalid input, please enter a valid name.");
                }
            }

            // Feature to enforce team balancing
            if (plumberIndex == numPlayers / 2) {
                System.out.println(playerName + " was automatically placed in the Saboteurs team.");
                saboteurs[saboteurIndex++] = new Saboteur(playerName);
                continue;
            } else if (saboteurIndex == numPlayers / 2) {
                System.out.println(playerName + " was automatically placed in the Plumbers team.");
                plumbers[plumberIndex++] = new Plumber(playerName);
                continue;
            }

            int teamChoice;
            while (true) {
                if (!testMode) {
                    System.out.println("Select the team for " + playerName + ":");
                    System.out.println("Enter '1' for Plumbers and '2' for Saboteurs.");
                }
                teamChoice = Integer.parseInt(scanner.nextLine());

                if (teamChoice == 1) {
                    plumbers[plumberIndex++] = new Plumber(playerName);
                    System.out.println(playerName + " chose the Plumbers team.");
                    break;
                } else if (teamChoice == 2) {
                    saboteurs[saboteurIndex++] = new Saboteur(playerName);
                    System.out.println(playerName + " chose the Saboteurs team.");
                    break;
                } else {
                    System.out.println("Invalid input, please choose one of the valid options (1 or 2).");
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
/// Creating the spring
            Spring s1 = new Spring("Spring");
            addSpring(s1);

// Creating the upper pipe and connecting it to the spring.
            Pipe upperPipe = new Pipe("Upper Pipe");
            EndOfPipe p1upper = new EndOfPipe(upperPipe);
            EndOfPipe p2upper = new EndOfPipe(upperPipe);
            s1.connectablePipes.add(upperPipe);
            p1upper.connectToElement(s1);
            addPipe(upperPipe);

// Creating the upper pump, connecting it to the upper pipe
            Pump upperPump = new Pump("Upper Pump");
            upperPump.connectablePipes.add(upperPipe);
            upperPump.inPipe = upperPipe; // Set inPipe as the first connected pipe
            p2upper.connectToElement(upperPump);
            addPump(upperPump);

// Creating the middle pipe, connecting it to the upper pump
            Pipe middlePipe = new Pipe("Middle Pipe");
            EndOfPipe p1middle = new EndOfPipe(middlePipe);
            EndOfPipe p2middle = new EndOfPipe(middlePipe);
            addPipe(middlePipe);
            upperPump.connectablePipes.add(middlePipe);
            upperPump.outPipe = middlePipe; // Set outPipe as the second connected pipe
            p1middle.connectToElement(upperPump);

// Creating the lower pump, connecting it to the middle pipe
            Pump lowerPump = new Pump("Lower Pump");
            lowerPump.connectablePipes.add(middlePipe);
            lowerPump.inPipe = middlePipe; // Set inPipe as the first connected pipe
            p2middle.connectToElement(lowerPump);
            addPump(lowerPump);

// Creating the lower pipe, connecting it to the lower pump
            Pipe lowerPipe = new Pipe("Lower Pipe");
            EndOfPipe p1lower = new EndOfPipe(lowerPipe);
            EndOfPipe p2lower = new EndOfPipe(lowerPipe);
            addPipe(lowerPipe);
            lowerPump.connectablePipes.add(lowerPipe);
            lowerPump.outPipe = lowerPipe; // Set outPipe as the second connected pipe
            p1lower.connectToElement(lowerPump);

// Creating the cistern, connecting it to the lower pipe
            Cistern cistern = new Cistern("Cistern", this);
            addCistern(cistern);
            cistern.connectablePipes.add(lowerPipe);
            p2lower.connectToElement(cistern);
        }

        else {
            // Creating the spring
            Spring s1 = new Spring("Spring");
            addSpring(s1);

            //creating the Cistern
            Cistern cistern = new Cistern("Cistern", this);

            // Creating pipes
            Pipe Pipe1 = new Pipe("Pipe1");
            Pipe Pipe2 = new Pipe("Pipe2");
            Pipe Pipe3 = new Pipe("Pipe3");
            Pipe Pipe4 = new Pipe("Pipe4");
            Pipe Pipe5 = new Pipe("Pipe5");
            Pipe Pipe6 = new Pipe("Pipe6");
            Pipe Pipe7 = new Pipe("Pipe7");
            Pipe Pipe8 = new Pipe("Pipe8");

            // Creating ends of pipes
            EndOfPipe EoP1pipe1 = new EndOfPipe(Pipe1);
            EndOfPipe EoP2pipe1 = new EndOfPipe(Pipe1);

            EndOfPipe EoP1pipe2 = new EndOfPipe(Pipe2);
            EndOfPipe EoP2pipe2 = new EndOfPipe(Pipe2);

            EndOfPipe EoP1pipe3 = new EndOfPipe(Pipe3);
            EndOfPipe EoP2pipe3 = new EndOfPipe(Pipe3);

            EndOfPipe EoP1pipe4 = new EndOfPipe(Pipe4);
            EndOfPipe EoP2pipe4 = new EndOfPipe(Pipe4);

            EndOfPipe EoP1pipe5 = new EndOfPipe(Pipe5);
            EndOfPipe EoP2pipe5 = new EndOfPipe(Pipe5);

            // only one end of pipe for pipe 6
            EndOfPipe EoP1pipe6 = new EndOfPipe(Pipe6);

            EndOfPipe EoP1pipe7 = new EndOfPipe(Pipe7);
            EndOfPipe EoP2pipe7 = new EndOfPipe(Pipe7);

            EndOfPipe EoP1pipe8 = new EndOfPipe(Pipe8);
            EndOfPipe EoP2pipe8 = new EndOfPipe(Pipe8);


            // Creating pumps
            Pump Pump1 = new Pump("Pump1");
            Pump Pump2 = new Pump("Pump2");
            Pump Pump3 = new Pump("Pump3");
            Pump Pump4 = new Pump("Pump4");



            //connecting pipe1 to the spring
            s1.connectablePipes.add(Pipe1);
            EoP1pipe1.connectToElement(s1);
            addPipe(Pipe1);


            //connecting pipe4 to the spring
            s1.connectablePipes.add(Pipe4);
            EoP1pipe4.connectToElement(s1);
            addPipe(Pipe4);


            //connecting pipe1 to pump1
            Pump1.connectablePipes.add(Pipe1);
            Pump1.inPipe = Pipe1;
            EoP2pipe1.connectToElement(Pump1);
            addPump(Pump1);


            //connecting pipe4 to pump3
            Pump3.connectablePipes.add(Pipe4);
            Pump3.inPipe = Pipe4;
            EoP2pipe4.connectToElement(Pump3);
            addPump(Pump3);


            //connecting pipe5 to pump1 and pump3
            Pump1.connectablePipes.add(Pipe5);
            Pump3.connectablePipes.add(Pipe5);
            EoP1pipe5.connectToElement(Pump1);
            EoP2pipe5.connectToElement(Pump3);
            addPipe(Pipe5);



            //connecting pipe2 to pump1
            Pump1.connectablePipes.add(Pipe2);
            Pump1.outPipe = Pipe2;
            EoP1pipe2.connectToElement(Pump1);
            addPipe(Pipe2);


            //connecting pipe7 to pump3
            Pump3.connectablePipes.add(Pipe7);
            Pump3.outPipe = Pipe7;
            EoP1pipe7.connectToElement(Pump3);
            addPipe(Pipe7);


            //connecting pipe2 to pump2
            Pump2.connectablePipes.add(Pipe2);
            Pump2.inPipe = Pipe2;
            EoP2pipe2.connectToElement(Pump2);
            addPump(Pump2);


            //connecting pipe7 to pump4
            Pump4.connectablePipes.add(Pipe7);
            Pump4.inPipe = Pipe7;
            EoP2pipe7.connectToElement(Pump4);
            addPump(Pump4);


            //connecting pipe6 to pump2 and pump4
            Pump2.connectablePipes.add(Pipe6);
            Pump4.connectablePipes.add(Pipe6);
            EoP1pipe6.connectToElement(Pump2);
            addPipe(Pipe6);



            //connecting pipe3 to pump2
            Pump2.connectablePipes.add(Pipe3);
            EoP1pipe3.connectToElement(Pump2);
            Pump2.outPipe = Pipe3;
            addPipe(Pipe3);

            //connecting pipe8 to pump4
            Pump4.connectablePipes.add(Pipe8);
            EoP1pipe8.connectToElement(Pump4);
            Pump4.outPipe = Pipe8;
            addPipe(Pipe8);

            //connecting pipe3 and pipe8 to the cistern
            cistern.connectablePipes.add(Pipe3);
            cistern.connectablePipes.add(Pipe8);
            EoP2pipe3.connectToElement(cistern);
            EoP2pipe8.connectToElement(cistern);
            addCistern(cistern);
            if(testMode) {
                for (Pump p : pumpList) {
                    p.setWorkingTurns(100000);
                }
                if(testNumber == 3)
                {
                    Pump2.setWorkingTurns(5);
                }
            }
        }
        // Start game
        System.out.println("The game’s elements have been initialized successfully.");
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
        timer = new Timer();
        timer.startGameTimer();
        System.out.println("The game and timer have started!");

        while (!timer.isGameOver()) {
            int elementListSize = elementList.size();
            int i = 0;
            Player currentPlayer = players[currentPlayerIndex];
            currentPlayer.takeTurn(this);
                for (Element e : elementList) {
                    i++;
                    e.update();
                    if(i == elementListSize) // DO NOT CHANGE THIS, VERY IMPORTANT - MAJED.
                    {
                        break;
                    }
                }
            currentPlayerIndex = (currentPlayerIndex + 1) % players.length;
        }
        endGame();
    }
    /**
     * Ends the game and evaluates the results to determine the winner.
     * This method stops the game timer and evaluates the results to determine
     * which team (Saboteurs or Plumbers) wins the game based on the comparison between leaked and collected water.
     */
    public void endGame() {
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
