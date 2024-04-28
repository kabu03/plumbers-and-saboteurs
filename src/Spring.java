/* changes made to spring compared to docs :
1- made a given water attribute
2- gave implementation to update for the water logic.
 */
/**
 * Natural water source marking the starting point of the pipe system. A spring provides a
 * continuous water supply, essential for sustaining flow within pipes. They play a foundational
 * role in the system's functionality.
 */
public class Spring extends Element {
    /**
     * Tracks the amount of water given to connected pipes.
     */
    public int givenWater = 0;

    /**
     * Constructs a new Spring with a specified name and sets its water capacity to unlimited.
     *
     * @param n The name of the spring.
     */
    public Spring(String n) {
        super(n);
        setMaxCapacity(Integer.MAX_VALUE);
    }

    /**
     * Updates the state of the spring by providing water to all connected pipes and
     * increments the water given count.
     */
    @Override
    public void update() {
        for (Pipe pipe : connectedPipes) {
            pipe.incrementWater();
            givenWater += 2;
        }
    }
}
