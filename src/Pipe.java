/*
Changes made to Pipe compard to docs:
Added a new Add End of Pipe method.
*/

import java.util.ArrayList;

/**
 * The backbone of the pipe system, serving as conduits for water transfer. Flexible in nature,
 * allowing extension, repair, or manipulation by players. Pipes are crucial for water flow between
 * active elements and track water leakage.
 */
public class Pipe extends Element {

    public Pipe()
    {
        setMaxConnectablePipes(2); setMaxCapacity(10);
        connectedPipes = new ArrayList<Pipe>();
    }
    public int leakedAmount = 0;
    public EndOfPipe[] endsOfPipe = new EndOfPipe[2]; // I think a pipe will have max 2 endofpipes.
    public void incrementLeakage(){
        leakedAmount = leakedAmount + getWaterLevel(); // I assumed The leaked amount will depend on the waterLevel.
    }
    public int getLeakedWater(){
        return leakedAmount;
    }

    @Override
    public void update() {
        // to check if we have a free end.
        boolean haveFreeEnd = false;
        for(int i = 0; i < 2; i++)
        {
            if(endsOfPipe[i] == null)
            {
                haveFreeEnd = true;
            }
        }
        if(getWorks() == false || haveFreeEnd)
        {
            // we do not need to worry about the waterlevel being less than zero because the min amount it can take is zero.
            incrementLeakage();
        }
    }
}
