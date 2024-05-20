package model;/* added a game instance attribute for cistern class
Added two new attributes numOfCreatedPipes and numOfCreatedPumps
*/

import gui.MapGUI;

import java.awt.*;
import java.util.Random;

/**
 * Large storage tanks located at the system's endpoint, crucial for victory by storing water
 * directed through pipes. Cisterns are also involved in the manufacture of new pipes and pumps,
 * representing the ultimate goal for the plumber team to ensure a steady water supply.
 */
public class Cistern extends Element {
    public Cistern(String n, Point p, Game g)
    {
        super(n,p);
        width = 300;
        height = 300;
        gameInstance = g;
        setMaxCapacity(Integer.MAX_VALUE);
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
    private int turnsUntilPipeReady =rand.nextInt(10,20); // we can decide on this later

    /**
     *The number of turns remaining until a new pump can be manufactured.
     */
    private int turnsUntilPumpReady = 1; // we can decide on this later.
    public Pump manufacturedPump = null;
    /**
     * Initiates the manufacturing process for a new pipe.
     * @author: Basel Al-Raoush
     */
    public void manufacturePipe(Game g) {
        numOfcreatedPipes++;
        if(numOfcreatedPipes == 1)
        {
            g.removeCistern(this);
            Pipe p = new Pipe("New Pipe " + numOfcreatedPipes, new Point (1330,605), true,30,35);
            EndOfPipe newEnd = new EndOfPipe(p,true);
            g.endOfPipeList.add(newEnd);
            p.endsOfPipe[0] = newEnd;
            p.endsOfPipe[1] = null;
            g.addPipe(p);
            newEnd.connectToElement(this);

            Pump toBeconnected = g.pumpList.get(7);
            toBeconnected.connectablePipes.add(p);
            System.out.println("A new model.Pipe Has been added");
            g.addCistern(this);
        }
        else if(numOfcreatedPipes == 2)
        {
            g.removeCistern(this);
            Pipe p = new Pipe("Pipe14", new Point(1340, 200), true,30,100);
            EndOfPipe newEnd = new EndOfPipe(p,false);
            p.endsOfPipe[1] = newEnd; // the cistern will be on the right.
            p.endsOfPipe[0] = null;
            g.endOfPipeList.add(newEnd);
            g.addPipe(p);
            newEnd.connectToElement(this);
            Pump toBeconnected = g.pumpList.get(6);
            toBeconnected.connectablePipes.add(p);
            System.out.println("A new model.Pipe Has been added");
            g.addCistern(this);
        }
    }

    /**
     *Initiates the manufacturing process for a new pump.
     * @author: Basel Al-Raoush
     */
    public void manufacturePump(Game g) {
        if(manufacturedPump == null)
        {
            numOfCreatedPumps++;
            Pump temp = new Pump("New Pump " + numOfCreatedPumps, new Point(this.getPosition().x + 72 , this.getPosition().y - 80),75, 75);
            manufacturedPump = temp;
            g.addPump(temp);
            System.out.println("A new model.Pump Has been Manufactured at the cistern");
            g.mapGUI.repaint();
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
            turnsUntilPipeReady = 1;
            decrementPipeTurns = false;
        }
        if(turnsUntilPumpReady == 0)
        {
            manufacturePump(gameInstance);
            turnsUntilPumpReady = rand.nextInt(100,200);
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
