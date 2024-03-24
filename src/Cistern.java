/**
 * Large storage tanks located at the system's endpoint, crucial for victory by storing water
 * directed through pipes. Cisterns are also involved in the manufacture of new pipes and pumps,
 * representing the ultimate goal for the plumber team to ensure a steady water supply.
 */
public class Cistern extends Element {
    int turnsUntilPipeReady;
    int turnsUntilPumpReady;

    public void manufacturePipe() {
        System.out.println("manufacturePipe()");
        System.out.println("The Cistern has manufactured a pipe.");
    }

    public void manufacturePump() {
        System.out.println("manufacturePump()");
        System.out.println("The Cistern has manufactured a pump.");
    }

    @Override
    public void update() {
        System.out.println("update()");
    }
}
