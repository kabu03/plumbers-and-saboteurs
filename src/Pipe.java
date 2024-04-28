/*
Changes made to Pipe compard to docs:
Added a new Add End of Pipe method.
changed inpipe and outpipe to public.
made leaked amount public
*/



/**
 * The backbone of the pipe system, serving as conduits for water transfer. Flexible in nature,
 * allowing extension, repair, or manipulation by players. Pipes are crucial for water flow between
 * active elements and track water leakage.
 */
public class Pipe extends Element {

    /**
     * Publicly accessible amount of water that has leaked from the pipe.
     */
    public int leakedAmount = 0;

    /**
     * Array of two endpoints, each capable of connecting to different game elements.
     */
    public EndOfPipe[] endsOfPipe = new EndOfPipe[2];

    /**
     * Initializes a new Pipe with a specified name, setting default capacity and connectable pipes.
     *
     * @param n The name of the pipe.
     */
    public Pipe(String n) {
        super(n);
        setMaxConnectablePipes(2);
        setMaxCapacity(10);
    }

    /**
     * Increments the leaked amount if the water level is sufficient and decrements the water level.
     */
    public void incrementLeakage() {
        if (getWaterLevel() >= 2) {
            leakedAmount += 2;
        }
        decrementWater();
    }
    /**
     * Periodically updates the pipe's state, checking for free ends and managing water flow.
     */
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
