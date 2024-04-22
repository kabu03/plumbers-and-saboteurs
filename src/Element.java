import java.util.Random;

/**
 * Acts as a superclass for elements in the game, including pipes, pumps, cisterns, and springs.
 * These elements are responsible for managing water flow and system functionality, tracking
 * occupancy, operational status, water levels, defining maximum capacities, and maintaining
 * connections among each other.
 */
public abstract class Element {
    // in the doc I wrote that the attributes are private, but them should be protected.
    private Random rand = new Random(); // random object to generate random values.
    /**
     * A boolean that indicates whether the element is currently occupied by a player or not.
     */
    protected boolean occupied = false;
    /**
     * A boolean indicating whether the element is currently in working condition or not.
     * For example, a broken pump would have this value as False.
     */
    protected boolean works = true;
    /**
     * An integer that represents the current water level in the element.
     */
    protected int waterLevel = 0;
    /**
     * An integer that represents the maximum amount of water that
     * the element can theoretically hold.
     */
    protected int maxCapacity = rand.nextInt(10,26); // I assumed that the max capacity will be from 10 to 25
    /**
     * Represents an array of pipes that are connected to a certain element.
     */
    protected Pipe[] connectedPipes;
    /**
     * An integer that represents the maximum number of pipes that can be theoretically connected to an element.
     */
    protected int maxConnectablePipes; // this will be different depending on the element.
    /**
     * A boolean that indicates whether the element can be stood upon.
     */
    protected boolean standable = false;

    /**
     * Checks if the element is in working condition and returns a boolean.
     */
    public boolean isWorking(){
        return works;
    }

    /**
     * Checks if the element is currently occupied and returns a boolean.
     */
    public boolean isOccupied(){
        return occupied;
    }

    /**
     * Increases the water level within the element.
     */
    public void incrementWater(){
        waterLevel+=2;
        if(waterLevel > maxCapacity)
        {
            waterLevel = maxCapacity;
        }
    }

    /**
     * Decreases the water level within the element.
     */
    public void decrementWater(){
        waterLevel-=2;
        if(waterLevel < 0)
        {
            waterLevel = 0;
        }
    }

    /**
     * Sets the occupied status of an element.
     */
    public void setOccupied(boolean bool){
        occupied = bool;
    }
    public int getMaxCapacity(){
        return maxCapacity;
    }
    public int getWaterLevel(){
        return waterLevel;
    }
    public abstract void update(); // it is an abstract method.
    public void setStandable(boolean bool){
        standable = bool;
    }
    // Added  a getter and a setter for Works
    public boolean getWorks()
    {
        return works;
    }
    // i think this will be used in puncture.
    public void setWorks(boolean bool)
    {
        works = bool;
    }
}
