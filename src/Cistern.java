/**
 * Large storage tanks located at the system's endpoint, crucial for victory by storing water
 * directed through pipes. Cisterns are also involved in the manufacture of new pipes and pumps,
 * representing the ultimate goal for the plumber team to ensure a steady water supply.
 */
public class Cistern extends Element {
    // I added this two attributes for the random manufacturing of a pump and pipe
    int turnsUntilPipeReady;
    int turnsUntilPumpReady;

    public void manufacturePipe() {
        System.out.println("manufacturePipe()");
        System.out.println("The Cistern has manufactured a pipe."); // Was this called by Game.AddPipe()?
        // No, because it will be called by update.
    }

    public void manufacturePump() {
        System.out.println("manufacturePump()");
        System.out.println("The Cistern has manufactured a pump."); // Was this called by Game.AddPump()? same as above.
    }

    @Override
    public void update() {
        System.out.println("update()");
        /* we will use update for calling the methods that update the state of
        the class between turns for exp:
        if(turnsUntilPumpReady == 0){ manufacturePumo();
        turnsUntilPumpReady = Rand;}
        else
        { turnsUntilPumpReady--}
         */
    }
}
