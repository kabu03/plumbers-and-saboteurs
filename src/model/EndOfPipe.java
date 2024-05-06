package model;/*
Changes made for endofpipe compared to documentation:
Added a new currentPipe Attribute
Also Added an setCurrnetPipe method
*/

/**
 * Represents the terminal endpoints of a pipe, facilitating attachment and detachment from other
 * elements. It retains information about the connected element for later retrieval.
 */
public class EndOfPipe {
    public EndOfPipe(Pipe p)
    {
        currentPipe = p;
        for(int i = 0; i < 2; i++)
        {
            if(p.endsOfPipe[i] == null)
            {
                p.endsOfPipe[i] = this;
                break;
            }
        }
    }
    /**
     * An attribute that stores the element that is currently connected to the end of pipe object.
     */
    private Element connectedElement;

    /**
     * The pipe that this end of the pipe is associated with.
     */
    private Pipe currentPipe;

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

}
