import java.util.Scanner;


/**
 * A simple 2-player Tic-Tac-Toe game for the console.
 * Players take turns placing 'X' or 'O' on a 3x3 board
 * until one player wins or the game results in a draw.
 */
public class TicTacToeGame {
    private char[][] board; // 3x3 board to store 'X', 'O', or ' '
    private char currentPlayer; // Tracks current player ('X' or 'O')
    private int movesCount; // Tracks number of moves to detect draw

    /**
     * Constructs a new TicTacToeGame instance with an empty
     * board and sets the starting player to 'X'.
     */
    public TicTacToeGame() {
        board = new char[3][3];
        currentPlayer = 'X';
        movesCount = 0;
        // Initialize board with empty spaces
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }
    }

    /**
     * Displays the current state of the board in a 3x3 grid format.
     */
    public void displayBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(" " + board[i][j] + " ");
                if (j < 2) System.out.print("|");
            }
            System.out.println();
            if (i < 2) System.out.println("---+---+---");
        }
        System.out.println();
    }

    /**
     * Attempts to place the current player's symbol at the given position.
     * @param row the row index (1-3)
     * @param col the column index (1-3)
     * @return true if the move is valid and placed; false otherwise
     */
    public boolean makeMove(int row, int col) {
        // Check if move is within bounds and cell is empty
        if (row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == ' ') {
            board[row][col] = currentPlayer;
            movesCount++;
            return true;
        }
        return false;
    }

    /**
     * Switches the current player from 'X' to 'O' or vice versa.
     */
    public void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    /**
     * Checks if the current player has won the game by aligning 
     * three of their symbols in a row, column, or diagonal.
     * @return true if the current player has won; false otherwise
     */
    public boolean checkWin() {
        // Check rows
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer) {
                return true;
            }
        }
        // Check columns
        for (int j = 0; j < 3; j++) {
            if (board[0][j] == currentPlayer && board[1][j] == currentPlayer && board[2][j] == currentPlayer) {
                return true;
            }
        }
        // Check diagonals
        if (board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) {
            return true;
        }
        if (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer) {
            return true;
        }
        return false;
    }

    /**
     * Determines if the game is a draw (all cells filled, no winner).
     * @return true if the game is a draw; false otherwise
     */
    public boolean isDraw() {
        return movesCount == 9 && !checkWin();
    }

    /**
     * The main game loop where players enter moves, the board is updated,
     * and the gmae checks for a winner or draw after each turn.
     */
    public void playGame() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Tic-Tac-Toe: Players alternate entering row (1-3) and column (1-3).");

        while (true) {
            displayBoard();
            System.out.print("Player " + currentPlayer + ", enter row and column (e.g., 1 2): ");
            int row, col;
            try {
                row = scanner.nextInt();
                col = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter two numbers (1-3).");
                scanner.nextLine(); // Clear input buffer
                continue;
            }

            // Convert 1-3 input to 0-2 for array indexing
            row--;
            col--;
            if (row < 0 || row > 2 || col < 0 || col > 2 || !makeMove(row, col)) {
                System.out.println("Invalid move. Cell is occupied or out of bounds. Try again.");
                continue;
            }

            if (checkWin()) {
                displayBoard();
                System.out.println("Player " + currentPlayer + " wins!");
                break;
            }

            if (isDraw()) {
                displayBoard();
                System.out.println("It's a draw!");
                break;
            }
            switchPlayer();
        }
        scanner.close();
    }

    // Main method to start the game.
    public static void main(String[] args) {
        TicTacToeGame game = new TicTacToeGame();
        game.playGame();
    }
}
