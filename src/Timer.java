public class Timer {
    public double gameLength;
    public double turnLength = 5;
    public double getTime(){
        System.out.println("getTime()");
        return gameLength;
    }
    public void setTime(double x){
        // This method has no input parameters in the class diagram, but I put a double here. Which is correct?
        System.out.println("setTime(double)");
        gameLength = x;
        // you are correct.
    }
}
