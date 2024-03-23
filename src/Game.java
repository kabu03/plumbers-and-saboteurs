import java.util.Scanner;

public class Game {
private Plumber[] plumbers;
private Saboteur[] saboteurs;
private int currentPlayerIndex;
private Element[] gameElements;
private int[] turnOrder;
private int[] gameScore = {0, 0}; // Say index 0 represents Plumber score, index 1 represents Saboteur score.
    // Don't we need a method to set that the gameScore depends on game calculateLeakedWater and calculateCollectedWater?
private Timer timer;
public void initGame(){
    System.out.println("initGame()");
    /*
    addCistern();
    addPipe();
    addPump();
    addSpring();
     */
    System.out.println("The game's state has been initialized.");
}
public void startGame(){
    System.out.println("startGame()");
    System.out.println("The game has started!");
    // start the timer here? Or start decrementing the gameLength 1 per real-world sec?
    // I do not understand the difference here
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
//Scanner sc = new Scanner(System.in);
//    System.out.println("Enter 1 if the leaked water was greater than collected water.");
//    if (sc.nextInt() == 1){
//        System.out.println("The Saboteurs won the game.");
//    }
//    else {
//        System.out.println("The Plumbers won the game.");
//    }
    // What do you think about this?
    // i think it is good.
}
public void calculateLeakedWater(){
    System.out.println("calculateLeakedWater()");
    // The return value of this method and the one below it should be double, but what should we do now?
}
public void calculateCollectedWater(){
    System.out.println("calculateCollectedWater()");
}
}
