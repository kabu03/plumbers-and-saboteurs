/**
 * Essential elements responsible for propelling water through the system, featuring
 * a specific number of connections but only one input and one output pipe at a time. Pumps
 * contain a water tank for maintaining flow, requiring strategic placement and maintenance
 * by players to ensure efficient water transfer. They can malfunction after a random set number
 * of working turns.
 */
public class Pump extends Element {
    private Pipe inPipe;
    private Pipe outPipe;
    private int workingTurns;
    public void setWorkingTurns(int x){
        workingTurns = x;
    }

    @Override
    public void update() {
        System.out.println("Update");
    }
}
