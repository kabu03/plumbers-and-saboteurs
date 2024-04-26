import java.util.ArrayList;

/**
 * Natural water source marking the starting point of the pipe system. A spring provides a
 * continuous water supply, essential for sustaining flow within pipes. They play a foundational
 * role in the system's functionality.
 */
public class Spring extends Element {
    public Spring(String n)
    {
        super(n);
        setMaxCapacity(Integer.MAX_VALUE);
    }

    @Override
    public void update() {
    }
    // Its update() should include the decrementation of water based on how many pipes are connected to it. We can use this for further checks later on.
}
