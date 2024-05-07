package model;

import gui.MainMenuGUI;
import gui.MapGUI;

import java.util.*;
/**
 * The {@code model.Main} class serves as the entry point for the Pipes in the Desert CLI model.Game.
 * This class is responsible for presenting the initial game menu to the user and handling
 * their selection to either start the game or exit. It creates an instance of the {@code model.Game}
 * class and initializes the game based on user input.
 * <p>
 * Upon execution, the user is greeted with a welcome message and presented with options to
 * either start the game or exit. Selecting to start the game will initialize the game's
 * environment and settings, whereas choosing to exit will terminate the application.
 * </p>
 */
public class Main {
    /**
     * model.Main method to run the game.
     * It displays a simple command-line interface for the user to start the game or exit.
     * User choices are handled through basic switch-case logic.
     *
     * @param args The command-line arguments. Not used in this application.
     */

    public static void main(String[] args) {
        new MainMenuGUI();
    }
}




