package model;/*Changes made to element compared to documentation :
Getter and Setter method for Works attribute
Setter For maxCapacity attribute
Getter for standable attribute
Made connectedPipes Array public.
Setter and getter For maxConnectablePipes
Added A new name attribute
 */

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Acts as a superclass for elements in the game, including pipes, pumps, cisterns, and springs.
 * These elements are responsible for managing water flow and system functionality, tracking
 * occupancy, operational status, water levels, defining maximum capacities, and maintaining
 * connections among each other.
 */
public abstract class Element {
    private Point position;
    public int width;
    public int height;
    public void setPosition(Point position) {
        this.position = position;
    }
    public Point getPosition() {
        return position;
    }
    public boolean contains(int x, int y) {
        return x >= getPosition().x && x <= getPosition().x + width &&
                y >= getPosition().y && y <= getPosition().y + height;
    }
    public Element(String n, Point position)
    {
        name = n;
        this.position = position;
        connectedPipes = new ArrayList<>();
        connectablePipes = new ArrayList<>();
    }
    /**
     * A Property to get the names of the element
     * @author Nafez Mousa Sayyad
     */
    private final String name;
    public String getName(){return name;}

    /** a public list that contains all the connectable pipes in the map
     * @author Nafez Mousa Sayyad
     */
    public List<Pipe> connectablePipes;
    /**
     * A boolean that indicates whether the element is currently occupied by a player or not.
     */
    private boolean occupied = false;
    /**
     * A boolean indicating whether the element is currently in working condition or not.
     * For example, a broken pump would have this value as False.
     */
    private boolean works = true;
    /**
     * An integer that represents the current water level in the element.
     */
    private int waterLevel = 0;
    /**
     * An integer that represents the maximum amount of water that
     * the element can theoretically hold.
     */
    private int maxCapacity;
    /**
     * Represents a List of pipes that are connected to a certain element.
     */
    public List<Pipe> connectedPipes;
    /**
     * An integer that represents the maximum number of pipes that can be theoretically connected to an element.
     */
    private int maxConnectablePipes;
    /**
     * A boolean that indicates whether the element can be stood upon.
     */
    private boolean standable = false;

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
    /**
     * Sets the occupied status of the element.
     * @param bool true if the element is to be occupied, false otherwise.
     */
    public void setOccupied(boolean bool) {
        occupied = bool;
    }

    /**
     * Returns the maximum water capacity of the element.
     * @return The maximum water capacity.
     */
    public int getMaxCapacity() {
        return maxCapacity;
    }

    /**
     * Retrieves the current water level in the element.
     * @return The current water level.
     */
    public int getWaterLevel() {
        return waterLevel;
    }

    /**
     * An abstract method to be implemented by subclasses for element-specific updates.
     */
    public abstract void update();

    /**
     * Sets the standability of the element.
     * @param bool true if the element can be stood upon, false otherwise.
     */
    public void setStandable(boolean bool) {
        standable = bool;
    }

    /**
     * Retrieves the working status of the element.
     * @return true if the element is working, otherwise false.
     */


    /**
     * Sets the working status of the element.
     * @param bool true if the element is to be set as working, false if not.
     */
    public void setWorks(boolean bool) {
        works = bool;
    }

    /**
     * Sets the maximum water capacity of the element.
     * @param value the maximum water capacity to set.
     */
    public void setMaxCapacity(int value) {
        maxCapacity = value;
    }

    /**
     * Retrieves whether the element can be stood upon.
     * @return true if the element can be stood upon, otherwise false.
     */
    public boolean getStandable() {
        return standable;
    }

    /**
     * Sets the maximum number of connectable pipes to the element.
     * @param value the maximum number of pipes that can be connected.
     */
    public void setMaxConnectablePipes(int value) {
        maxConnectablePipes = value;
    }

    /**
     * Gets the maximum number of connectable pipes for the element.
     * @return The maximum number of connectable pipes.
     */
    public int getMaxConnectablePipes() {
        return maxConnectablePipes;
    }

}


