
/**
 * Natural water source marking the starting point of the pipe system. A spring provides a
 * continuous water supply, essential for sustaining flow within pipes. They play a foundational
 * role in the system's functionality.
 */
public class Spring extends Element {
    // for debugging
    public int givenWater = 0;
    public Spring(String n)
    {
        super(n);
        setMaxCapacity(Integer.MAX_VALUE);
    }

    @Override
    public void update() {
        for(Pipe pipe : connectedPipes)
        {
            pipe.incrementWater();
            givenWater +=2;
        }
    }
}
