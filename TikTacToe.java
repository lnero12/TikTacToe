import java.util.Scanner;

class TicTacToe {
    // Constants
    private static final int BOARD_SIZE = 9;
    private static final char USER = 'X';
    private static final char COMP = 'O';
    private static final char EMPTY = ' ';

    // The Tic Tac Toe board
    private static char[] board = new char[BOARD_SIZE];

    // The positions on the board
    private static int[] positions = {1, 2, 3, 4, 5, 6, 7, 8, 9};

    // The winning combinations
    private static final int[][] WINNING_COMBINATIONS = {
            {0, 1, 2}, {3, 4, 5}, {6, 7, 8}, // rows
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, // columns
            {0, 4, 8}, {2, 4, 6}            // diagonals
    };

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Initialize the board
        for (int i = 0; i < BOARD_SIZE; i++) {
            board[i] = EMPTY;
        }

        // Display the initial board
        printBoard();

        // The game loop
        while (true) {
            // Get the user's move
            int userMove = getUserMove(scanner);

            // Update the board with the user's move
            board[userMove] = USER;

            // Display the updated board
            printBoard();

            // Check if the user has won
            if (isWinner(USER)) {
                System.out.println("You win!");
                break;
            }

            // Check if the game is a tie
            if (isTie()) {
                System.out.println("It's a tie!");
                break;
            }

            // Get the computer's move
            int compMove = getCompMove();

            // Update the board with the computer's move
            board[compMove] = COMP;

            // Display the updated board
            printBoard();

            // Check if the computer has won
            if (isWinner(COMP)) {
                System.out.println("The computer wins!");
                break;
            }

            // Check if the game is a tie
            if (isTie()) {
                System.out.println("It's a tie!");
                break;
            }
        }
    }

    /**
     * Prints the current state of the board.
     */
    private static void printBoard() {
        System.out.println("-------------");
        for (int i = 0; i < 3; i++) {
            System.out.printf("| %c | %c | %c |%n", board[i * 3], board[i * 3 + 1], board[i * 3 + 2]);
            System.out.println("-------------");
        }
    }

    /**
     * Checks if the player has won.
     */
    private static boolean isWinner(char player) {
        for (int[] combination : WINNING_COMBINATIONS) {
            if (board[combination[0]] == player && board[combination[1]] == player && board[combination[2]] == player) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the game is a tie.
     */
    private static boolean isTie() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (board[i] == EMPTY) {
                return false;
            }
        }
        return true;
    }

    /**
     * Gets the user's move.
     */
    private static int getUserMove(Scanner scanner) {
        while (true) {
            System.out.print("Enter a number between 1 and 9 to make your move: ");
            int move = scanner.nextInt() - 1;
            if (move >= 0 && move < BOARD_SIZE && board[move] == EMPTY) {
                return move;
            }
            System.out.println("Invalid move. Please try again.");
        }
    }

    /**
     * Gets the computer's move.
     */
    private static int getCompMove() {
        // Try to win the game
        for (int[] combination : WINNING_COMBINATIONS) {
            int emptyCount = 0;
            int emptyIndex = -1;
            for (int index : combination) {
                if (board[index] == EMPTY) {
                    emptyCount++;
                    emptyIndex = index;
                } else if (board[index] == COMP) {
                    emptyCount = -1;
                    break;
                }
            }
            if (emptyCount == 1) {
                return emptyIndex;
            }
        }

        // Try to block the user
        for (int[] combination : WINNING_COMBINATIONS) {
            int emptyCount = 0;
            int emptyIndex = -1;
            for (int index : combination) {
                if (board[index] == EMPTY) {
                    emptyCount++;
                    emptyIndex = index;
                } else if (board[index] == USER) {
                    emptyCount = -1;
                    break;
                }
            }
            if (emptyCount == 1) {
                return emptyIndex;
            }
        }

        // Pick a random move
        while (true) {
            int move = (int) (Math.random() * BOARD_SIZE);
            if (board[move] == EMPTY) {
                return move;
            }
        }
    }
}

