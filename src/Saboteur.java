public class Saboteur extends Player {
    @Override
    protected void takeTurn() {
        System.out.println("takeTurn()");
        System.out.println("The Saboteur is playing their turn."); // Will change this later.
    }

    public void puncture(Pipe p1){
        System.out.println("puncture(Pipe)");
        System.out.println("The pipe has been punctured.");
    }
}
