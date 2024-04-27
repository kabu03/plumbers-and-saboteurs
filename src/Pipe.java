/*
Changes made to Pipe compard to docs:
Added a new Add End of Pipe method.
changed inpipe and outpipe to public.
*/

import java.util.ArrayList;

/**
 * The backbone of the pipe system, serving as conduits for water transfer. Flexible in nature,
 * allowing extension, repair, or manipulation by players. Pipes are crucial for water flow between
 * active elements and track water leakage.
 */
public class Pipe extends Element {

    public Pipe(String n)
    {
        super(n);
        setMaxConnectablePipes(2); setMaxCapacity(10);
    }
    public int leakedAmount = 0;
    public EndOfPipe[] endsOfPipe = new EndOfPipe[2]; // I think a pipe will have max 2 endofpipes.
    public void incrementLeakage(){
        if(getWaterLevel() >= 2) {
            leakedAmount = leakedAmount + 2;
        }
        decrementWater();
    }
    public int getLeakedWater(){
        return leakedAmount;
    }
    public void incrementWaterToPump(Pump p)
    {
        if(p.inPipe == this)
        {
            p.incrementWater();
        }
    }
    @Override
    public void update() {
        // to check if we have a free end.
        boolean haveFreeEnd = false;
        for(int i = 0; i < 2; i++)
        {
            if (endsOfPipe[i] == null) {
                haveFreeEnd = true;
                break;
            }
        }
        if(!getWorks() || haveFreeEnd)
        {
            incrementLeakage();
        }
        else
        {
            if(getWaterLevel() >= 2 && endsOfPipe[1].getConnectedElement() != null)
            {
                if(endsOfPipe[1].getConnectedElement() instanceof Pump p)
                {
                    if(p.inPipe == this)
                    {
                        endsOfPipe[1].getConnectedElement().incrementWater();
                    }
                }
                else
                {
                    endsOfPipe[1].getConnectedElement().incrementWater();
                }
            }

        }
    }
}
