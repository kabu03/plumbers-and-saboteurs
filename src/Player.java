import java.lang.*;
public abstract class Player {
    public String playerName;
    public int index;
    protected abstract void takeTurn();

    protected void changeInputPipe(Pump p1, Pipe p2){
        System.out.println("changeInputPipe(Pump, Pipe)");
        // Missing functionality
    }

    protected void changeOutputPipe(Pump p1, Pipe p2){
        System.out.println("changeOutputPipe(Pump, Pipe)");
        // Missing functionality
    }

    protected void move(){
        System.out.println("move()");
        // What to implement here?
        // i do not know since we have no postion yet
        // maybe move to an element.
    }

}
