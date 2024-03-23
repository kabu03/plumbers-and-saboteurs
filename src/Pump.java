public class Pump extends Element {
    private Pipe inPipe;
    private Pipe outPipe;
    private int workingTurns;
    public void setWorkingTurns(int x){
        workingTurns = x;
    }

    @Override
    public void update() {
        System.out.println("Update");
    }
}
