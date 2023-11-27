package at.ac.fhcampuswien.tictactoe;

import java.util.Scanner;

public class Player {
    /*
     * Define instance variables
     */

    private char symbol;
    private String name;

    /*
     * Define a constructor
     */
    public Player(char symbol, String name) {
        this.symbol = symbol;
        this.name = name;
    }

    public char getSymbol() {
        return symbol;
    }

    public String getName() {
        return name;
    }

    /*
     * Define public methods
     *
     * */

    public int makeMove(TicTacToe game, Scanner scanner) {
        String input = "";

        while(true) {
            System.out.print(name + " make your move (choose a number): ");
            input = scanner.next();

            // Falls keine Zahl -> Nochmal nach Eingabe fragen
            if(!checkInput(input)) {
                continue;
            }

            // Eingabe ist Zahl -> Check ob zwischen 1 und 9
            int inputAsInt = Integer.parseInt(input);
            if (inputAsInt < 1 || inputAsInt > 9) {
                continue;
            }

            return inputAsInt;
        }
    }

    // Advanced - Error Handling
    private boolean checkInput(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch(NumberFormatException ex) {
            return false;
        }
    }
}
