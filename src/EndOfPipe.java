/*
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
    }
    /**
     * An attribute that stores the element that is currently connected to the end of pipe object.
     */
    private Element connectedElement;
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

    public Element getConnectedElement(){
        return connectedElement;
    }
    public void setCurrentPipe(Pipe p)
    {
        currentPipe = p;
    }

}
