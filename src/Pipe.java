/**
 * The backbone of the pipe system, serving as conduits for water transfer. Flexible in nature,
 * allowing extension, repair, or manipulation by players. Pipes are crucial for water flow between
 * active elements and track water leakage.
 */
public class Pipe extends Element {
    public int leakedAmount;
    public EndOfPipe[] endsOfPipe;
    public void incrementLeakage(){
        System.out.println("incrementLeakage()");
        leakedAmount++;
    }
    public int getLeakedWater(){
        System.out.println("getLeakedWater()");
        return leakedAmount;
    }

    @Override
    public void update() {
        System.out.println("Update");
    }
}
