package model;

import java.awt.*;

/**
 * Represents the terminal endpoints of a pipe, facilitating attachment and detachment from other
 * elements. It retains information about the connected element for later retrieval.
 */


public class EndOfPipe {
    private Point position;
    public boolean atStart;
    public int width;
    public int height;
    int adjustX = 0;
    int adjustY = 0;
    public Pipe currentPipe;
    private boolean visible = true;
    public Point getPosition() {
        return position;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean contains(int x, int y) {
        return x >= getPosition().x && x <= getPosition().x + width &&
                y >= getPosition().y && y <= getPosition().y + height;
    }

    public EndOfPipe(Pipe p, boolean atStart)
    {
        currentPipe = p;
        this.atStart = atStart;
        width = 30;
        height = 30;
        for(int i = 0; i < 2; i++)
        {
            if(p.endsOfPipe[i] == null)
            {
                p.endsOfPipe[i] = this;
                break;
            }
        }
        setPosition();
    }
    public EndOfPipe(Pipe p, boolean atStart, int adjustX, int adjustY)
    {
        currentPipe = p;
        this.atStart = atStart;
        width = 30;
        height = 30;
        for(int i = 0; i < 2; i++)
        {
            if(p.endsOfPipe[i] == null)
            {
                p.endsOfPipe[i] = this;
                break;
            }
        }
        this.adjustX = adjustX;
        this.adjustY = adjustY;
        setPosition();
    }

    /**
     * An attribute that stores the element that is currently connected to the end of pipe object.
     */
    private Element connectedElement;

    /**
     * The pipe that this end of the pipe is associated with.
     */

    /**
     * A method that connects the end of pipe object to an element in the system.
     * @param e The element that should be connected to.
     */
    public void connectToElement(Element e){
        connectedElement = e;
        e.connectedPipes.add(currentPipe);
    }

    /**
     * A method that disconnects the end of pipe object from an element in the system.
     * @param e The element that should be disconnected from.
     */
    public void disconnectFromElement(Element e){
        connectedElement = null;
        e.connectedPipes.remove(currentPipe);
    }

    /**
     * Retrieves the element currently connected to this end of the pipe.
     *
     */
    public Element getConnectedElement(){
        return connectedElement;
    }
    /**
     * Sets a new pipe to this end of the pipe, updating the connected element's list of connected pipes
     * if there is one.
     *
     * @param p The new pipe to be associated with this end of the pipe.
     */
    public void setCurrentPipe(Pipe p)
    {
        if(connectedElement != null) {
            connectedElement.connectedPipes.remove(currentPipe);
            currentPipe = p;
            connectedElement.connectedPipes.add(currentPipe);
        }
        else
        {
            currentPipe = p;
        }
    }
    private void setPosition() {
        int x, y;

        // Adjust the alignment for vertical and horizontal pipes
        if (currentPipe.vertical) {
            x = currentPipe.getPosition().x + (currentPipe.width - width) / 2;  // Center horizontally

            if (atStart) {
                y = currentPipe.getPosition().y - height / 2;  // Adjust to visually connect at the start
            } else {
                y = (currentPipe.getPosition().y + currentPipe.height - height / 2) - adjustY;  // Adjust to visually connect at the end
            }
        } else {
            if (atStart) {
                x = currentPipe.getPosition().x - width / 2;  // Adjust to visually start at the pipe's beginning
                y = currentPipe.getPosition().y + (currentPipe.height - height) / 2;  // Center vertically
            } else {
                x = currentPipe.getPosition().x + currentPipe.width - width / 2;  // Adjust to visually end at the pipe's edge
                y = currentPipe.getPosition().y + (currentPipe.height - height) / 2;  // Center vertically
            }
        }

        this.position = new Point(x, y);
    }

}
