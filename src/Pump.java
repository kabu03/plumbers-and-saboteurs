import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * Essential elements responsible for propelling water through the system, featuring
 * a specific number of connections but only one input and one output pipe at a time. Pumps
 * contain a water tank for maintaining flow, requiring strategic placement and maintenance
 * by players to ensure efficient water transfer. They can malfunction after a random set number
 * of working turns.
 */
public class Pump extends Element {
    public Pump(String n)
    {
        super(n);
        setMaxConnectablePipes(rand.nextInt(3,6)); setMaxCapacity(50);
    }
    Random rand = new Random();
    /**
     * the input pipe connected to the pump.
     */
    public Pipe inPipe;

    /**
     * The output pipe connected to the pump
     */
    public Pipe outPipe;

    /**
     * the number of turns that the pump will work for.
     */
    private int workingTurns = rand.nextInt(8,24);

    /**
     * A method used to set the working turns of a pump.
     * It allows for the specification of the number of turns the pump will operate for.
     * @param x the number of turns that the pump will work for.
     */
    public void setWorkingTurns(int x){
        workingTurns = x;
    }

    @Override
    public void update() { // shouldn't it actually send water from the inPipe to the outPipe?
        if(workingTurns == 0)
        {
            setWorks(false);
        }
        else
        {
            workingTurns--;
        }
        if(!inPipe.getWorks() & isWorking())
        {
            decrementWater();
        }
        if(isWorking() && getWaterLevel() >= 2)
        {
            outPipe.incrementWater();
        }
    }
}
