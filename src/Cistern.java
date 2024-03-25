/**
 * Large storage tanks located at the system's endpoint, crucial for victory by storing water
 * directed through pipes. Cisterns are also involved in the manufacture of new pipes and pumps,
 * representing the ultimate goal for the plumber team to ensure a steady water supply.
 */
public class Cistern extends Element {
    /**
     * The number of turns remaining until a new pipe can be manufactured.
     */
    int turnsUntilPipeReady;

    /**
     *The number of turns remaining until a new pump can be manufactured.
     */
    int turnsUntilPumpReady;


    /**
     * Initiates the manufacturing process for a new pipe.
     * @author: Basel Al-Raoush
     */
    public void manufacturePipe() {
        System.out.println("manufacturePipe()");
        System.out.println("The Cistern has manufactured a pipe.");
    }

    /**
     *Initiates the manufacturing process for a new pump.
     * @author: Basel Al-Raoush
     */
    public void manufacturePump() {
        System.out.println("manufacturePump()");
        System.out.println("The Cistern has manufactured a pump.");
    }

    @Override
    public void update() {
        System.out.println("update()");
    }
}
