import java.util.TimerTask;

public class Timer {
    public static final int GAME_LENGTH = 300; // 5 min long game
    private static java.util.Timer gameTimer;
    private int gameTime;

    public Timer() {
        this.gameTime = GAME_LENGTH;
    }

    public void startGameTimer() {
        gameTimer = new java.util.Timer();
        gameTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                gameTime--;
                if (gameTime <= 0) {
                    gameTimer.cancel();
                }
            }
        }, 0, 1000);
    }
    public boolean isGameOver() {
        return gameTime <= 0;
    }

}