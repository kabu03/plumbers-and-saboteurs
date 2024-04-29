import java.util.TimerTask;

/**
 * This class represents a game timer that counts down from a specified game length to zero.
 * It provides functionality to start a timer and check if the game time has elapsed. The timer
 * operates in seconds and can be used to track the duration of game sessions in minutes.
 */
public class Timer {
    /**
     * The total length of the game in seconds. Set to 300 seconds (5 minutes).
     */
    public static final int GAME_LENGTH = 300;

    /**
     * A static timer instance used for scheduling tasks at fixed rates.
     */
    private static java.util.Timer gameTimer;

    /**
     * Current remaining game time in seconds.
     */
    private int gameTime;

    /**
     * Constructs a new Timer object with the game time initialized to the predefined game length.
     */
    public Timer() {
        this.gameTime = GAME_LENGTH;
    }

    /**
     * Starts the game timer, decrementing the gameTime every second. When gameTime reaches zero,
     * the timer is canceled, effectively ending the game.
     */
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
        }, 0, 1000); // Schedule task with no initial delay, to run every 1000 milliseconds (1 second).
    }

    /**
     * Checks if the game is over, which is determined by whether the gameTime has reached zero.
     *
     * @return true if the game is over (gameTime <= 0), false otherwise.
     */
    public boolean isGameOver() {
        return gameTime <= 0;
    }
}
