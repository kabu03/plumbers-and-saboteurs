/* added a game instance attribute for cistern class
*/

import java.util.ArrayList;
import java.util.Random;

/**
 * Large storage tanks located at the system's endpoint, crucial for victory by storing water
 * directed through pipes. Cisterns are also involved in the manufacture of new pipes and pumps,
 * representing the ultimate goal for the plumber team to ensure a steady water supply.
 */
public class Cistern extends Element {
    public Cistern(String n, Game g)
    {
        super(n);
        gameInstance = g;
        setMaxCapacity(Integer.MAX_VALUE);
    }

    Random rand = new Random();
    private int numOfcreatedPipes = 0;
    private final Game gameInstance;
    /**
     * The number of turns remaining until a new pipe can be manufactured.
     */
    private int turnsUntilPipeReady = rand.nextInt(25,50); // we can decide on this later

    /**
     *The number of turns remaining until a new pump can be manufactured.
     */
    private int turnsUntilPumpReady = rand.nextInt(10,23); // we can decide on this later.
    public Pump manufacturedPump = null;

    /**
     * Initiates the manufacturing process for a new pipe.
     * @author: Basel Al-Raoush
     */
    public void manufacturePipe(Game g) {
        // init logic
        Pipe p = new Pipe("New Pipe " + numOfcreatedPipes + 1);
        EndOfPipe newEnd = new EndOfPipe(p);
        p.endsOfPipe[1] = newEnd; // the cistern will be on the right.
        g.pipeList.add(p); // we should add it to both pipeList and elementList, so use addPipe method
        newEnd.connectToElement(this);
        Pump toBeconnected = g.pumpList.get(0);
        toBeconnected.connectablePipes.add(p);
        System.out.println("A new Pipe Has been added");
    }

    /**
     *Initiates the manufacturing process for a new pump.
     * @author: Basel Al-Raoush
     */
    public void manufacturePump(Game g) {
        if(manufacturedPump == null)
        {
            Pump temp = new Pump("New Pump");
            manufacturedPump = temp;
            g.addPump(temp);
            System.out.println("A new Pump Has been Manufactured at the cistern");
        }
    }

    @Override
    public void update() {
        boolean decrementPumpTurns = true;
        boolean decrementPipeTurns = true;
        if(turnsUntilPipeReady == 0)
        {
            manufacturePipe(gameInstance);
            turnsUntilPipeReady = rand.nextInt(8,22);
            decrementPipeTurns = false;
            numOfcreatedPipes++;
        }
        if(turnsUntilPumpReady == 0)
        {
            manufacturePump(gameInstance);
            turnsUntilPumpReady = rand.nextInt(8,22);
            decrementPumpTurns = false;
        }
        if(decrementPipeTurns)
        {
            turnsUntilPipeReady--;
        }
        if(decrementPumpTurns)
        {
            turnsUntilPumpReady--;
        }
    }
}
