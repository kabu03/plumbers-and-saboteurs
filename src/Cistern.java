/* added a game instance attribute for cistern class
Added two new attributes numOfCreatedPipes and numOfCreatedPumps
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
        if(gameInstance.testMode && gameInstance.testNumber == 2)
        {
            turnsUntilPumpReady = 0;
        }
        else if(gameInstance.testMode && gameInstance.testNumber == 7)
        {
            turnsUntilPipeReady = 0;
        }
        else if(gameInstance.testMode)
        {
            turnsUntilPipeReady = 100000;
            turnsUntilPumpReady = 100000;
        }
    }

    Random rand = new Random();
    /**
     * attribute that keeps track of how many pipes were manufactured by the cistern
     */
    private int numOfcreatedPipes = 0;
    /**
     * attribute that keeps track of how many pumps were manufactured by the cistern
     */
    private int numOfCreatedPumps = 0;
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
        numOfcreatedPipes++;
        Pipe p = new Pipe("New Pipe " + numOfcreatedPipes);
        EndOfPipe newEnd = new EndOfPipe(p);
        p.endsOfPipe[1] = newEnd; // the cistern will be on the right.
        p.endsOfPipe[0] = null;
        g.addPipe(p);
        newEnd.connectToElement(this);
        if(g.testMode && g.testNumber == 7)
        {
            Pump toBeconnected = g.pumpList.get(2);
            toBeconnected.connectablePipes.add(p);
        }
        Pump toBeconnected = g.pumpList.get(0);
        toBeconnected.connectablePipes.add(p);
        if (!Game.testMode) {
        System.out.println("A new Pipe Has been added"); }
    }

    /**
     *Initiates the manufacturing process for a new pump.
     * @author: Basel Al-Raoush
     */
    public void manufacturePump(Game g) {
        if(manufacturedPump == null)
        {
            numOfCreatedPumps++;
            Pump temp = new Pump("New Pump " + numOfCreatedPumps);
            manufacturedPump = temp;
            g.addPump(temp);
            if(gameInstance.testMode && gameInstance.testNumber == 2)
            {
                ;
            }
            else {
                System.out.println("A new Pump Has been Manufactured at the cistern");
            }
        }
    }

    /**
     *Updates the status of manufacturing timers and triggers manufacturing processes for pipes and pumps
     */
    @Override
    public void update() {
        boolean decrementPumpTurns = true;
        boolean decrementPipeTurns = true;
        if(turnsUntilPipeReady == 0)
        {
            manufacturePipe(gameInstance);
            turnsUntilPipeReady = rand.nextInt(25,50);
            decrementPipeTurns = false;
            numOfcreatedPipes++;
        }
        if(turnsUntilPumpReady == 0)
        {
            manufacturePump(gameInstance);
            turnsUntilPumpReady = rand.nextInt(10,23);
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
