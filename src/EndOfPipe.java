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
