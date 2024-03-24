/**
 * Represents the terminal endpoints of a pipe, facilitating attachment and detachment from other
 * elements. It retains information about the connected element for later retrieval.
 */
public class EndOfPipe {
    private Element connectedElement;
    public void connectToElement(Element e){
        System.out.println("connectToElement(Element)");

    }
    public void disconnectFromElement(Element e){
        System.out.println("disconnectFromElement(Element)");

    }
    public Element getConnectedElement(){
        System.out.println("getConnectedElement()");
        return connectedElement;
    }
}
