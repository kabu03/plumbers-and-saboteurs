import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Pipes in the Desert CLI Game by Team MANSAF!");
        System.out.println("Are you ready to start?");
        System.out.println("1. Start Game");
        System.out.println("2. Exit");

        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                Game game = new Game();
                game.initGame();
                break;
            case 2:
                System.out.println("Exiting the game. Goodbye!");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice. Exiting the game.");
                System.exit(0);
        }
    }
}
