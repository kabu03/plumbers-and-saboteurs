package model;

import java.awt.*;
import java.util.Random;

/**
 * Essential elements responsible for propelling water through the system, featuring
 * a specific number of connections but only one input and one output pipe at a time. Pumps
 * contain a water tank for maintaining flow, requiring strategic placement and maintenance
 * by players to ensure efficient water transfer. They can malfunction after a random set number
 * of working turns.
 */
public class Pump extends Element {
    public Pump(String n, Point p)
    {
        super(n,p);
        width = 100;
        height = 100;
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
    /**
     * Updates the pump's operational status and handles water transfer based on current conditions.
     * This method processes the pump's functionality each cycle, checking for operational status,
     * managing breakdowns, and transferring water if conditions are met.
     */
    @Override
    public void update() {
        if(inPipe == null && outPipe == null)
        {
            ;
        }
        else {
            if (workingTurns == 0 && isWorking()) {
                setWorks(false);
                System.out.println( this.getName() + " randomly broke down.");
            } else {
                workingTurns--;
            }
            if (!inPipe.isWorking() & isWorking()) {
                decrementWater();
            }
            if (isWorking() && getWaterLevel() >= 2) {
                outPipe.incrementWater();
            }
        }
    }
}
