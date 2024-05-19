package model;

import gui.EndGameGUI;
import gui.MapGUI;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.Thread.sleep;

/**
 * Serves as the central class for managing the game. It orchestrates gameplay, managing plumbers
 * and saboteurs, tracking the current player index, and overseeing elements crucial for water
 * management. The class provides essential methods for game initialization
 * and addition of elements to the system, alongside methods for determining the winner and calculating
 * game metrics.
 */
public class Game {
    public Player[] players;
    public Saboteur[] saboteurs;
    public Plumber[] plumbers;
    public int currentPlayerIndex = 0;
    public List<Element> elementList;
    public List<Pipe> pipeList;
    public List<Pump> pumpList;
    public List<Cistern> cisternList;
    public List<Spring> springList;
    public List<EndOfPipe> endOfPipeList = new ArrayList<>();
    private final int[] gameScore = {0, 0}; // Index 0 represents model.Plumber score, index 1 represents model.Saboteur score.
    public Timer timer;
    private volatile char currentAction = '\0';

    public synchronized void setCurrentAction(char action) {
        this.currentAction = action;
    }

    public synchronized char getCurrentAction() {
        char action = currentAction;
        currentAction = '\0';  // Reset after reading
        return action;
    }

    public MapGUI mapGUI;

    public Game() {}


    /**
     * Initializes the game by setting up players and game elements.
     * Asks for the number of players and assigns them to teams (Plumbers or Saboteurs).
     * Ensures balanced team assignments for 2v2 or 3v3 setups.
     * Initializes game elements such as pipes, pumps, cisterns, and springs.
     * Starts the game.
     *
     * @author Basel Al-Raoush
     */
    public void configureGame(List<String> names, List<Boolean> isPlumberList) {
        int numPlayers = names.size();
        players = new Player[numPlayers];
        plumbers = new Plumber[numPlayers / 2];
        saboteurs = new Saboteur[numPlayers / 2];

        int plumberIndex = 0, saboteurIndex = 0, plumberPlayerIndex = 0, saboteurPlayerIndex = 1;

        for (int i = 0; i < numPlayers; i++) {
            if (isPlumberList.get(i)) {
                if (plumberIndex < plumbers.length) {
                    plumbers[plumberIndex] = new Plumber(names.get(i));
                    players[plumberPlayerIndex] = plumbers[plumberIndex++];
                    plumberPlayerIndex += 2; // Skip one index for a saboteur
                }
            } else {
                if (saboteurIndex < saboteurs.length) {
                    saboteurs[saboteurIndex] = new Saboteur(names.get(i));
                    players[saboteurPlayerIndex] = saboteurs[saboteurIndex++];
                    saboteurPlayerIndex += 2; // Skip one index for a plumber
                }
            }
        }
        initMap();
    }
    public void initMap() {

        // model.Game initialization
        System.out.println("Initializing the game...");
        elementList = new ArrayList<>();
        pipeList = new ArrayList<>();
        pumpList = new ArrayList<>();
        springList = new ArrayList<>();
        cisternList = new ArrayList<>();

            // Creating the spring
            Spring s1 = new Spring("Spring", new Point(50,300));
            addSpring(s1);

            //creating the model.Cistern
            Cistern cistern = new Cistern("Cistern", new Point(1200,300), this);

            // Creating pipes
            Pipe Pipe1 = new Pipe("Pipe1", new Point(400, 300), false);
            Pipe Pipe2 = new Pipe("Pipe2", new Point (700, 300), false);
            Pipe Pipe3 = new Pipe("Pipe3", new Point (1000, 300), false);
            Pipe Pipe4 = new Pipe("Pipe4", new Point (400, 550), false);
            Pipe Pipe5 = new Pipe("Pipe5", new Point(635, 350), true);
            Pipe Pipe6 = new Pipe("Pipe6", new Point(935, 350), true);
            Pipe Pipe7 = new Pipe("Pipe7", new Point(700, 550), false);
            Pipe Pipe8 = new Pipe("Pipe8", new Point(1000, 550), false);
            Pipe Pipe9 = new Pipe("Pipe9", new Point(935, 585), true,30,50);
            Pipe Pipe10 = new Pipe("Pipe10", new Point(935, 200), true,30,70);
            Pipe Pipe11 = new Pipe("Pipe11", new Point(980, 150), false,350,30);
            Pipe pipe12 = new Pipe("Pipe12", new Point(980, 650), false,350,30);
            Pipe pipe13 = new Pipe("Pipe13", new Point(1330, 605), true,30,35);
            Pipe Pipe14 = new Pipe("Pipe14", new Point(1340, 200), true,30,100);
            addPipe(Pipe10);
            addPipe(Pipe11);
            addPipe(pipe12);
            // Creating pumps
            Pump Pump1 = new Pump("Pump1", new Point(600,265));
            Pump Pump2 = new Pump("Pump2", new Point(900,265));
            Pump Pump3 = new Pump("Pump3", new Point(600,500));
            Pump Pump4 = new Pump("Pump4", new Point(900,500));
            Pump Pump5 = new Pump("Pump5", new Point(912,635),75,50);
            Pump Pump6 = new Pump("Pump6", new Point(914,125),70,80);
            Pump Pump7 = new Pump("Pump7", new Point(1320,635),50,50);
            Pump pump8 = new Pump("Pump8", new Point(1320,125),70,70);

        // Creating ends of pipes
        EndOfPipe EoP1pipe1 = new EndOfPipe(Pipe1,true);
        EndOfPipe EoP2pipe1 = new EndOfPipe(Pipe1,false);
        endOfPipeList.add(EoP1pipe1);
        endOfPipeList.add(EoP2pipe1);

        EndOfPipe EoP1pipe2 = new EndOfPipe(Pipe2,true);
        EndOfPipe EoP2pipe2 = new EndOfPipe(Pipe2,false);
        endOfPipeList.add(EoP1pipe2);
        endOfPipeList.add(EoP2pipe2);

        EndOfPipe EoP1pipe3 = new EndOfPipe(Pipe3,true);
        EndOfPipe EoP2pipe3 = new EndOfPipe(Pipe3,false);
        endOfPipeList.add(EoP1pipe3);
        endOfPipeList.add(EoP2pipe3);

        EndOfPipe EoP1pipe4 = new EndOfPipe(Pipe4,true);
        EndOfPipe EoP2pipe4 = new EndOfPipe(Pipe4,false);
        endOfPipeList.add(EoP1pipe4);
        endOfPipeList.add(EoP2pipe4);

        EndOfPipe EoP1pipe5 = new EndOfPipe(Pipe5,true);
        EndOfPipe EoP2pipe5 = new EndOfPipe(Pipe5,false,0,35);
        endOfPipeList.add(EoP1pipe5);
        endOfPipeList.add(EoP2pipe5);

        // only one end of pipe for pipe 6
        EndOfPipe EoP1pipe6 = new EndOfPipe(Pipe6,true);
        endOfPipeList.add(EoP1pipe6);

        EndOfPipe EoP1pipe7 = new EndOfPipe(Pipe7,true);
        EndOfPipe EoP2pipe7 = new EndOfPipe(Pipe7,false);
        endOfPipeList.add(EoP1pipe7);
        endOfPipeList.add(EoP2pipe7);

        EndOfPipe EoP1pipe8 = new EndOfPipe(Pipe8,true);
        EndOfPipe EoP2pipe8 = new EndOfPipe(Pipe8,false);
        endOfPipeList.add(EoP1pipe8);
        endOfPipeList.add(EoP2pipe8);

        EndOfPipe EoP1pipe9 = new EndOfPipe(Pipe9,true);
        EndOfPipe EoP2pipe9 = new EndOfPipe(Pipe9,false);
        endOfPipeList.add(EoP1pipe9);
        endOfPipeList.add(EoP2pipe9);
        EndOfPipe EoP1pipe10 = new EndOfPipe(Pipe10,true);
        EndOfPipe EoP2pipe10 = new EndOfPipe(Pipe10,false);
        endOfPipeList.add(EoP1pipe10);
        endOfPipeList.add(EoP2pipe10);
        EndOfPipe EoP1pipe11 = new EndOfPipe(Pipe11,true);
        EndOfPipe EoP2pipe11 = new EndOfPipe(Pipe11,false);
        endOfPipeList.add(EoP1pipe11);
        endOfPipeList.add(EoP2pipe11);
        EndOfPipe EoP1pipe12 = new EndOfPipe(pipe12,true);
        EndOfPipe EoP2pipe12 = new EndOfPipe(pipe12,false);
        endOfPipeList.add(EoP1pipe12);
        endOfPipeList.add(EoP2pipe12);

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

            // connecting pipe9 to pump5 and pump4
            Pump4.connectablePipes.add(Pipe9);
            Pump5.connectablePipes.add(Pipe9);
            EoP1pipe9.connectToElement(Pump4);
            EoP2pipe9.connectToElement(Pump5);
            Pump5.inPipe = Pipe9;
            addPipe(Pipe9);
            addPump(Pump5);


            //connecting pipe10 to pump6 and pump2
            Pump6.connectablePipes.add(Pipe10);
            Pump2.connectablePipes.add(Pipe10);
            EoP1pipe10.connectToElement(Pump6);
            EoP2pipe10.connectToElement(Pump2);
            Pump6.inPipe = Pipe10;
            addPipe(Pipe10);
            addPump(Pump6);

            //connecting pipe11 to pump 6 and pump 8
            Pump6.connectablePipes.add(Pipe11);
            EoP1pipe11.connectToElement(Pump6);
            Pump6.outPipe = Pipe11;
            pump8.connectablePipes.add(Pipe11);
            EoP2pipe11.connectToElement(pump8);
            pump8.inPipe = Pipe11;
            addPipe(Pipe11);
            addPump(pump8);

            //connecting pipe12 to pump 5 and pump 7
            Pump5.connectablePipes.add(pipe12);
            EoP1pipe12.connectToElement(Pump5);
            Pump5.outPipe = pipe12;
            Pump7.connectablePipes.add(pipe12);
            EoP2pipe12.connectToElement(Pump7);
            Pump7.inPipe = pipe12;
            addPipe(pipe12);
            addPump(Pump7);

            //connecting pipe3 and pipe8 to the cistern
            cistern.connectablePipes.add(Pipe3);
            cistern.connectablePipes.add(Pipe8);
            EoP2pipe3.connectToElement(cistern);
            EoP2pipe8.connectToElement(cistern);
            addCistern(cistern);
        System.out.println("The game’s elements have been initialized successfully.");
        for (int i = 0; i < saboteurs.length; i++) {
            Saboteur s = saboteurs[i];
            s.currentElement = cistern;
            int x = 1250 + 50 * i;
            int y = 350 + 50 * i;
            s.setPosition(new Point(x, y));
        }

        for (int i = 0; i < plumbers.length; i++) {
            Plumber p = plumbers[i];
            p.currentElement = s1;
            int x = 150 + 50 * i;
            int y = 400 + 50 * i;
            p.setPosition(new Point(x, y));
        }
        startGame();
    }

    /**
     * Starts the game and manages turns for players.
     * starts the game timer, places players on the map, and starts water flow.
     * Players take turns performing actions until the game ends.
     * Actions include moving, interacting with pipes and pumps, allowing the players
     * to perform actions based on their team (model.Plumber or model.Saboteur).
     * <p>
     * Each player's turn is executed sequentially, with actions being chosen based on user input,
     * <p>
     * The method handles player turns,provides feedback to players throughout the game,
     * keeping them informed of the current state and their available actions.
     *
     * @author Basel Al-Raoush
     */
    public void startGame() {
        Thread gameThread = new Thread(() -> {
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
                    if (i == elementListSize) { // DO NOT CHANGE THIS, VERY IMPORTANT - MAJED.
                        break;
                    }
                }
                currentPlayerIndex = (currentPlayerIndex + 1) % players.length;
            }

            SwingUtilities.invokeLater(() -> {
                endGame();
            });
        });

        gameThread.start();  // Start the game logic in a new thread
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

        SwingUtilities.invokeLater(() -> {
            EndGameGUI endGameGUI = new EndGameGUI(waterCollected, waterLeaked);
            endGameGUI.setVisible(true);
        });
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
    public void removeCistern(Cistern cistern) {
        cisternList.remove(cistern);
        elementList.remove(cistern);
    }
}

