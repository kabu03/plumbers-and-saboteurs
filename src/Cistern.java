/* added a game instance attribute for cistern class */

import java.util.ArrayList;
import java.util.Random;

/**
 * Large storage tanks located at the system's endpoint, crucial for victory by storing water
 * directed through pipes. Cisterns are also involved in the manufacture of new pipes and pumps,
 * representing the ultimate goal for the plumber team to ensure a steady water supply.
 */
public class Cistern extends Element {
    public Cistern(Game g)
    {
        gameInstance = g;
        setMaxCapacity(Integer.MAX_VALUE);
        connectedPipes= new ArrayList<Pipe>();
    }

    Random rand = new Random();
    private Game gameInstance;
    /**
     * The number of turns remaining until a new pipe can be manufactured.
     */
    private int turnsUntilPipeReady = rand.nextInt(8,22); // we can decide on this later

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
        Pipe p = new Pipe();
        EndOfPipe temp = new EndOfPipe(p); // why is it a temp EoP, it should be in the game
        p.endsOfPipe[0] = temp;
        g.pipeList.add(p); // we should add it to both pipeList and elementList, so use addPipe method
        temp.connectToElement(this);
        // we will decide how to inform the user.
    }

    /**
     *Initiates the manufacturing process for a new pump.
     * @author: Basel Al-Raoush
     */
    public void manufacturePump(Game g) {
        if(manufacturedPump == null)
        {
            Pump temp = new Pump();
            manufacturedPump = temp;
            g.pumpList.add(temp);// we should add it to both pumpList and elementList, so use addPump method
            System.out.println("A new Pump Has been Manufactured at the cistern");
        }
    }

    @Override
    public void update() { // its update() should include the collection of water
        boolean decrementPumpTurns = true;
        boolean decrementPipeTurns = true;
        if(turnsUntilPipeReady == 0)
        {
            manufacturePipe(gameInstance);
            turnsUntilPipeReady = rand.nextInt(8,22);
            decrementPipeTurns = false;
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
        decrementPumpTurns = true; // you already set them to true as soon as update is called, no need for this
        decrementPipeTurns = true;
    }
}
