package at.ac.fhcampuswien.tictactoe;

import java.util.Scanner;

public class TicTacToe {

    /*
     * Define instance variables
     */

    private char[][] gameBoard;
    private Player p1;
    private Player p2;

    private final int ROWS = 3;
    private final int COLS = 3;

    /*
     * Define a constructor
     */

    public TicTacToe(Player p1, Player p2) {
        this.p1 = p1;
        this.p2 = p2;

        this.gameBoard = new char[ROWS][COLS];

        char number = '1';
        for (int row = 0; row < this.gameBoard.length; row++) {
            for (int col = 0; col < this.gameBoard[row].length; col++) {
                this.gameBoard[row][col] = number;

                // Convert char to ASCII code, increment and convert back to char
                number = (char) (number + 1);
            }
        }
    }

    /* Define public and private methods
     *
     * */

    public boolean placeTic(Player p, int row, int col) {
        // Get the current char from row and col of the Game Board
        char currentChar = this.gameBoard[row][col];

        if (currentChar == this.p1.getSymbol() || currentChar == this.p2.getSymbol()) {
            return false;
        }

        this.gameBoard[row][col] = p.getSymbol();
        return true;
    }

    private void prettyPrintGameBoard() {
        for (int row = 0; row < this.gameBoard.length; row++) {
            for (int col = 0; col < this.gameBoard[row].length; col++) {
                System.out.print(this.gameBoard[row][col] + " ");

                // check if not in the last col of the row
                if (col < this.COLS - 1) {
                    System.out.print("| ");
                }
            }
            // check if not in the last row
            if (row < this.ROWS - 1) {
                System.out.println();
                System.out.println("--+---+--");
            }
        }

        System.out.println();
    }

    public boolean checkIfWon(Player p) {
        // Check rows and columns
        for (int i = 0; i < 3; i++) {
            if ((this.gameBoard[i][0] == p.getSymbol() && this.gameBoard[i][1] == p.getSymbol() && this.gameBoard[i][2] == p.getSymbol()) ||
                    (this.gameBoard[0][i] == p.getSymbol() && this.gameBoard[1][i] == p.getSymbol() && this.gameBoard[2][i] == p.getSymbol())
            ) {
                return true;
            }
        }

        // Check the diagonals
        if ((this.gameBoard[0][0] == p.getSymbol() && this.gameBoard[1][1] == p.getSymbol() && this.gameBoard[2][2] == p.getSymbol()) ||
                (this.gameBoard[0][2] == p.getSymbol() && this.gameBoard[1][1] == p.getSymbol() && this.gameBoard[2][0] == p.getSymbol())
        ) {
            return true;
        }

        return false;
    }

    public void playGame() {
        Scanner scanner = new Scanner(System.in);

        for (int round = 1; round < 10; round++) {
//            Player playerInTurn;
//
//            // If uneven round -> Player 1 in Turn
//            if (round % 2 == 1) {
//                playerInTurn = p1;
//            } else {
//                playerInTurn = p2;
//            }
            // simplified:
            Player playerInTurn = round % 2 == 1 ? p1 : p2;

            this.prettyPrintGameBoard();
            System.out.println();

            // Player makes move
            boolean validMove = false;

            while(!validMove) {
                int input = playerInTurn.makeMove(this, scanner);
                int rowToSet = 0, colToSet = 0;

                for (int row = 0; row < this.gameBoard.length; row++) {
                    for (int col = 0; col < this.gameBoard[row].length; col++) {
                        if (this.gameBoard[row][col] == String.valueOf(input).charAt(0)) {
                            rowToSet = row;
                            colToSet = col;
                        }
                    }
                }

                validMove = this.placeTic(playerInTurn, rowToSet, colToSet);
            }

            if (this.checkIfWon(playerInTurn)) {
                System.out.println(playerInTurn.getName() + " has won the game!");
                this.prettyPrintGameBoard();
                return;
            }
        }

        System.out.println("Nobody wins!");
        this.prettyPrintGameBoard();
    }

    public static void main(String[] args) {

        /*
         * Create Instances of Class Player like:
         * Player p1 = new Player(....);
         * Player p2 = new Player(....);
         * Create an Instance of TicTacToe itself:
         * TicTacToe ttt = new TicTacToe(....);
         * Start the game via the reference (ttt)
         * */

        Player p1 = new Player('X', "sahil");
        Player p2 = new Player('O', "max");

        TicTacToe ttt = new TicTacToe(p1, p2);
        ttt.playGame();
    }
}
