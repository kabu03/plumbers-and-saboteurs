/**
 * Acts as a superclass for elements in the game, including pipes, pumps, cisterns, and springs.
 * These elements are responsible for managing water flow and system functionality, tracking
 * occupancy, operational status, water levels, defining maximum capacities, and maintaining
 * connections among each other.
 */
public abstract class Element {
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
    protected int waterLevel;
    /**
     * An integer that represents the maximum amount of water that
     * the element can theoretically hold.
     */
    protected int maxCapacity;
    /**
     * A boolean that indicates whether the element is currently clicked.
     */
    protected boolean isClicked = false;
    /**
     * Represents an array of pipes that are connected to a certain element.
     */
    protected Pipe[] connectedPipes;
    /**
     * An integer that represents the maximum number of pipes that can be theoretically connected to an element.
     */
    protected int maxConnectablePipes;
    /**
     * A boolean that indicates whether the element can be stood upon.
     */
    protected boolean standable = false;

    /**
     * Checks if the element is in working condition and returns a boolean.
     */
    public boolean isWorking(){
        System.out.println("isWorking()");
        return works;
    }

    /**
     * Checks if the element is currently occupied and returns a boolean.
     */
    public boolean isOccupied(){
        System.out.println("isOccupied()");
        return occupied;
    }

    /**
     * Increases the water level within the element.
     */
    public void incrementWater(){
        System.out.println("incrementWater()");
        waterLevel+=2;
    }

    /**
     * Decreases the water level within the element.
     */
    public void decrementWater(){
        System.out.println("decrementWater()");
        waterLevel-=2;
    }

    /**
     * Sets the occupied status of an element.
     */
    public void setOccupied(boolean bool){
        System.out.println("setOccupied(boolean)");
        occupied = bool;
    }
    public int getMaxCapacity(){
        System.out.println("getMaxCapacity()");
        return maxCapacity;
    }
    public int getWaterLevel(){
        System.out.println("getWaterLevel()");
        return waterLevel;
    }
    public abstract void update(); // it is an abstract method.
    public void setStandable(boolean bool){
        System.out.println("setStandable()");
        standable = bool;
    }
}
