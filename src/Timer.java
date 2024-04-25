public class Timer {
    public static double gameLength = 300; // Just an idea, 5 min long game
    public double turnLength = 5;
    public double getTime(){
        return gameLength;
    }
    public static void setTime(double x){
        gameLength = x;
    }
}
