public class Pipe extends Element {
    public int leakedAmount;
    public EndOfPipe[] endsOfPipe;
    public void incrementLeakage(){
        System.out.println("incrementLeakage()");
        leakedAmount++;
    }
    public int getLeakedWater(){
        System.out.println("getLeakedWater()");
        return leakedAmount;
    }
}
