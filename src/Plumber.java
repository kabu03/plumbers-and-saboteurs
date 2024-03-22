public class Plumber extends Player {
    private Pump playerPump;
    private EndOfPipe playerEndOfPipe;
    @Override
    protected void takeTurn() {
        System.out.println("takeTurn()");
        System.out.println("The Plumber is now playing their turn.");
    }
    public void getPump(Pump pump){
        System.out.println("getPump(Pump)");
        playerPump = pump;
        System.out.println("The plumber has picked up a pump. They can now insert it.");
    }
    public void getEnd(EndOfPipe EoP){
        System.out.println("getEnd(EndOfPipe)");
        playerEndOfPipe = EoP;
        System.out.println("The plumber has picked up an end of pipe. They can now insert it.");
    }

    public void insertPump(Pump pump, Pipe pipe){
        System.out.println("insertPump(Pump, Pipe)");
        // Why is pump a parameter? We know they can only insert the one they have in the inventory.
        // you are right
    }

    public void insertPipeEnd(EndOfPipe e, Element e2){
        System.out.println("insertPipeEnd(EndOfPipe, Element)");
        // Why is EndOfPipe a parameter? We know they can only insert the one they have in the inventory.
        //
    }
}
