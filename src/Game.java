import java.util.Scanner;

public class Game {
private Player[] players;
private Saboteur[] saboteurs; // index even -> ta3al hon
private Plumber[] plumbers; // index odd -> ta3al hon

private int currentPlayerIndex;
private Element[] elementArray;
private Pipe[] pipeArray;
private Pump[] pumpArray;
private Cistern[] cisternArray;
private EndOfPipe[] endOfPipesArray;
private int[] turnOrder;
private int[] gameScore = {0, 0}; // Say index 0 represents Plumber score, index 1 represents Saboteur score.
    // Don't we need a method to set that the gameScore depends on game calculateLeakedWater and calculateCollectedWater?
    public Game(Player[] players, Element[] gameElements ){}
private Timer timer;
    public void initGame(){

        System.out.println("initGame()");

        Scanner scanner = new Scanner(System.in);

        //asking for the number of players
        System.out.println("How many players will participate? Please enter a number.");
        int numPlayers = scanner.nextInt();
        System.out.println("You've selected " + numPlayers + " players.");

        plumbers = new Plumber[numPlayers]; // Array to store plumber objects
        saboteurs = new Saboteur[numPlayers]; // Array to store saboteur objects

        // Ask for player names and team choice
        for (int i = 0; i < numPlayers; i++) {
            System.out.println("Enter the name of player " + (i + 1) + ":");
            String playerName = scanner.next();

            // Ask for team choice
            System.out.println("Select the team for " + playerName + ":");
            System.out.println("Enter '1' for Plumbers and '2' for Saboteurs.");
            int teamChoice = scanner.nextInt();

            // Create player object based on team choice and add to respective array
            if (teamChoice == 1) {
                plumbers[i] = new Plumber(playerName); // Assuming Plumber is a subclass of Player
            } else if (teamChoice == 2) {
                saboteurs[i] = new Saboteur(playerName); // Assuming Saboteur is a subclass of Player
            } else {
                System.out.println("Invalid team choice.");
                return;
            }
        }


        // Game initialization
        System.out.println("Initializing the game...");
        Pipe[] pipeArray = new Pipe[5]; // Creating an array of size 5 to store 5 pipes

        // Adding 5 pipes to the pipeArray
        for (int i = 0; i < 5; i++) {
            pipeArray[i] = new Pipe(); // Creating a new Pipe object and adding it to the pipeArray
        }

        Pipe pipe = new Pipe(); //pipe object
        addPipe(pipe);
        Pump pump = new Pump(); //pump object
        addPump(pump);
        Cistern cistern = new Cistern(); //cistern object
        addCistern(cistern);
        Spring spring = new Spring(); //spring object
        addSpring(spring);

        System.out.println("Game initialization completed.");

        // Start game
        System.out.println("Starting the game...");
        startGame();

        System.out.println("The game's state has been initialized.");
    }


    public void startGame(){

        System.out.println("startGame()");
        System.out.println("Starting the game...");

        Timer.setTime(5.0);
        System.out.println("Game timer started.");
        // start the timer here? Or start decrementing the gameLength 1 per real-world sec?
        // I do not understand the difference here


        // Players are placed on the map
        System.out.println("Players are placed on the map.");

        // Water flow has started
        System.out.println("Water flow has started.");


        int numPlumbers = plumbers.length;
        int numSaboteurs = saboteurs.length;
        int totalPlayers = numPlumbers + numSaboteurs;

        int maxTurns = Math.max(numPlumbers, numSaboteurs);

        for (int i = 0; i < maxTurns; i++) {
            // Determine whose turn it is based on the current turn number If (i) is even, it's the plumber's turn;
            // otherwise, it's the saboteur's turn. We iterate through the combined array of plumbers and saboteurs,
            // alternating between them until all players have had a turn.

            boolean isPlumberTurn = i % 2 == 0; // Alternates between true (plumber's turn) and false (saboteur's turn)

            int plumberIndex = 0; // Initialize plumber index to keep track of the current plumber's turn
            int saboteurIndex = 0; // Initialize saboteur index to keep track of the current saboteur's turn

            if (isPlumberTurn && plumberIndex < numPlumbers) {
                // If it's the plumber's turn and there are plumbers left to play, execute the plumber's turn.
                System.out.println("Plumber's turn:");
                plumbers[plumberIndex++].takeTurn();
            } else if (!isPlumberTurn && saboteurIndex < numSaboteurs) {
                // If it's the saboteur's turn and there are saboteurs left to play, execute the saboteur's turn.
                System.out.println("Saboteur's turn:");
                saboteurs[saboteurIndex++].takeTurn();
            } else if (plumberIndex < numPlumbers) {
                // If there are no saboteurs left to play but there are plumbers, continue executing plumbers' turns.
                System.out.println("Plumber's turn:");
                plumbers[plumberIndex++].takeTurn();
            } else {
                // If there are no plumbers left to play, execute the remaining saboteurs' turns.
                System.out.println("Saboteur's turn:");
                saboteurs[saboteurIndex++].takeTurn();
            }
        }



        System.out.println("The game has started!");

    }
public void endGame(){
    System.out.println("endGame()");
    System.out.println("The timer has stopped, and the game has ended. The results are being evaluated...");
    determineWinner();
}
public void nextTurn(){
    // it will make every element call the update method
    //for(element ele: gameElements){ele.update()};
    System.out.println("nextTurn()");
    System.out.println("It's now the turn of the next player.");
    // logic of incrementing index of turnOrder array, I guess?
    // we have to either create a get index method or make the index attr public for players,
    // + i think we should change it to be one arr of players not an arr of saboteurs and an array of plumbers
    // then we do something like this: player[currentindex++].taketurn()
}
public void addPipe(Pipe pipe){
    System.out.println("addPipe(Pipe)");
    System.out.println("A new pipe has been added.");
}
public void addPump(Pump pump){
    System.out.println("addPump(Pump)");
    System.out.println("A new pump has been added.");
}
public void addCistern(Cistern c){
    System.out.println("addCistern(Cistern)");
    System.out.println("A new Cistern has been added.");
}
public void addSpring(Spring s){
    System.out.println("addSpring(Spring)");
    System.out.println("A new Spring has been added.");
}
public void determineWinner(){
    System.out.println("determineWinner()");
calculateLeakedWater();
calculateCollectedWater();
Scanner sc = new Scanner(System.in);
    System.out.println("Enter 1 if the leaked water was greater than collected water.");
    if (sc.nextInt() == 1){
        System.out.println("The Saboteurs won the game.");
    }
    else {
        System.out.println("The Plumbers won the game.");
    }
}
public void calculateLeakedWater(){
    System.out.println("calculateLeakedWater()");
    // The return value of this method and the one below it should be double, but what should we do now?
}
public void calculateCollectedWater(){
    System.out.println("calculateCollectedWater()");
}
}
