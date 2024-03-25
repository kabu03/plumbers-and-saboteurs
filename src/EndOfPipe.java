/**
 * Represents the terminal endpoints of a pipe, facilitating attachment and detachment from other
 * elements. It retains information about the connected element for later retrieval.
 */
public class EndOfPipe {
    /**
     * An attribute that stores the element that is currently connected to the end of pipe object.
     */
    private Element connectedElement;

    /**
     * A method that connects the end of pipe object to an element in the system.
     * @param e The element that should be connected to.
     */
    public void connectToElement(Element e){
        System.out.println("connectToElement(Element)");

    }

    /**
     * A method that disconnects the end of pipe object from an element in the system.
     * @param e The element that should be disconnected from.
     */
    public void disconnectFromElement(Element e){
        System.out.println("disconnectFromElement(Element)");

    }

    public Element getConnectedElement(){
        System.out.println("getConnectedElement()");
        return connectedElement;
    }
}
