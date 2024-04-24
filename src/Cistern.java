/* added a game instance attribute for cistern class */

import java.util.Random;

/**
 * Large storage tanks located at the system's endpoint, crucial for victory by storing water
 * directed through pipes. Cisterns are also involved in the manufacture of new pipes and pumps,
 * representing the ultimate goal for the plumber team to ensure a steady water supply.
 */
public class Cistern extends Element {
    Random rand = new Random();
    Game gameInstance;
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
        EndOfPipe temp = new EndOfPipe(p);
        p.endsOfPipe[0] = temp;
        g.pipeList.add(p);
        temp.connectToElement(this);
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
            g.pumpList.add(temp);
        }
    }

    @Override
    public void update() {
        if(turnsUntilPipeReady == 0)
        {
            manufacturePipe(gameInstance);
            turnsUntilPipeReady = rand.nextInt(8,22);
        }
        if(turnsUntilPumpReady == 0)
        {
            manufacturePump(gameInstance);
            turnsUntilPumpReady = rand.nextInt(8,22);
        }
    }
}
