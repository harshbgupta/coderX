package com.kritsn.lld.connect4;

import java.util.ArrayList;
import java.util.List;

/*
    GAME RULES:
    - Standard Connect 4 or variations allowed?
    - Board size fixed (7x6) or configurable?
    - 2 players or multiplayer?

    FEATURES:
    - Need undo/redo functionality?
    - Need move history tracking?
    - Need AI opponent or just 2 humans?
    - Save/load game state?

    SCALE:
    - Single game instance or multiple concurrent games?
    - Performance requirements for large boards?
    - Display/UI needed or just game logic?

    EDGE CASES:
    - What if column is full?
    - Draw handling (board full, no winner)?
    - Invalid move handling?

    ----------------------------
    Entities:
    - Board: class
    - GamePiece: class
    - Player: class
    - Move: class
    - GameEngine: class
    - WinChecker: interface -> Strategy Pattern
    - HorizontalWinChecker: class
    - VerticalWinChecker: class
    - DiagonalWinChecker: class
    - GameOnserver: interface
 */

enum GamePiece {
    EMPTY, RED, YELLOW;

    public boolean isPlayer() {
        return this == RED || this == YELLOW;
    }
}

class Player {
    private String name;
    private GamePiece piece;
    private boolean isHuman;

    public Player(String name, GamePiece piece, boolean isHuman) {
        this.name = name;
        this.piece = piece;
        this.isHuman = isHuman;
    }

    public String getName() {
        return name;
    }

    public GamePiece getPiece() {
        return piece;
    }

    public boolean isHuman() {
        return isHuman;
    }
}

enum Result {
    WIN, LOOSE, DRAW, CONTINUE
}

class Move {
    private int column;
    private Player player;
    private Long timestamp;
    private Result result;

    public Move(int column, Player player) {
        this.column = column;
        this.player = player;
        this.timestamp = System.currentTimeMillis();
        this.result = Result.CONTINUE;
    }

    public int getColumn() {
        return column;
    }

    public Player getPlayer() {
        return player;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }
}

/*
     COLUMN INDICES (0-6)
        ↓ ↓ ↓ ↓ ↓ ↓ ↓
        0 1 2 3 4 5 6
      ┌───────────────┐
    0 │ . . . . . . . │  ← ROW 0 (Top/Sky)
      │               │
    1 │ . . . . . . . │  ← ROW 1
      │               │
    2 │ . . . . . . . │  ← ROW 2
      │               │
    3 │ . . . . . . . │  ← ROW 3
      │               │
    4 │ . . . . . . . │  ← ROW 4
      │               │
    5 │ . . . . . . . │  ← ROW 5
      │               │
    6 │ . . . . . . . │  ← ROW 6 (Bottom/Gravity)
      └───────────────┘
      ↑ ROW INDEX (6-0)
 */
class Board {
    private GamePiece[][] grid;
    private final int ROWS = 6;
    private final int COLS = 7;
    private int lastMoveColumn;


    public Board() {
        this.grid = new GamePiece[ROWS][COLS];
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                grid[i][j] = GamePiece.EMPTY;
            }
        }
    }

    /**
     * Drop piece in column
     * Piece falls to lowest empty row
     * Returns row where piece was placed, -1 if failed
     */
    public int dropPiece(int column, GamePiece piece)
            throws ColumnFullException {

        if (column < 0 || column >= COLS) {
            throw new IllegalArgumentException("Invalid column: " + column);
        }

        // Find lowest empty row
        int row = findLowestEmptyRow(column);
        if (row == -1) {
            throw new ColumnFullException("Column " + column + " is full");
        }

        // Place piece
        grid[row][column] = piece;
        lastMoveColumn = column;
        return row;
    }

    /**
     * Find lowest empty row in column
     * Return -1 if column full
     * <p>
     * Algorithm: Start from bottom (row 5), move up
     */
    private int findLowestEmptyRow(int column) {
        for (int row = ROWS - 1; row >= 0; row--) {
            if (grid[row][column] == GamePiece.EMPTY) {
                return row;
            }
        }
        return -1; // Column full
    }

    public GamePiece getPiece(int row, int col) {
        if (row < 0 || row >= ROWS || col < 0 || col >= COLS) {
            return GamePiece.EMPTY;
        }
        return grid[row][col];
    }

    public boolean isFull() {
        for (int col = 0; col < COLS; col++) {
            if (!isColumnFull(col)) {
                return false;
            }
        }
        return true;
    }

    /*
     check for top row for specific column
     */
    public boolean isColumnFull(int column) {
        return grid[0][column] != GamePiece.EMPTY;
    }

    public GamePiece[][] getGrid() {
        // Return copy to prevent external modification
        GamePiece[][] copy = new GamePiece[ROWS][COLS];
        for (int i = 0; i < ROWS; i++) {
            System.arraycopy(grid[i], 0, copy[i], 0, COLS);
        }
        return copy;
    }

    public int getLastMoveColumn() {
        return lastMoveColumn;
    }

    public void reset() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                grid[i][j] = GamePiece.EMPTY;
            }
        }
        lastMoveColumn = -1;
    }
}

interface WinChecker {
    /**
     * Check if last move at (row, col) caused a win
     * <p>
     * Strategy Pattern - Why: Different algorithms for different directions
     * Benefit: Easy to add new win conditions
     * Trade-off: More classes, but code is cleaner
     */
    boolean checkWin(Board board, int row, int column, GamePiece piece);
}

class HorizontalWinChecker implements WinChecker {
    @Override
    public boolean checkWin(Board board, int row, int col, GamePiece piece) {
        /**
         * Check horizontal: left + current + right
         * Algorithm:
         *   1. Count consecutive pieces to LEFT
         *   2. Count consecutive pieces to RIGHT
         *   3. If left + 1 + right >= 4: WIN
         */

        // Count to the left
        int leftCount = 0;
        for (int c = col - 1; c >= 0; c--) {
            if (board.getPiece(row, c) == piece) {
                leftCount++;
            } else {
                break;
            }
        }

        // Count to the right
        int rightCount = 0;
        for (int c = col + 1; c < 7; c++) {
            if (board.getPiece(row, c) == piece) {
                rightCount++;
            } else {
                break;
            }
        }

        return leftCount + 1 + rightCount >= 4;
    }
}

class VerticalWinChecker implements WinChecker {

    @Override
    public boolean checkWin(Board board, int row, int col, GamePiece piece) {
        /**
         * Check vertical: only check BELOW
         * Why: Pieces always fall down, can't have above current piece
         * Algorithm:
         *   1. Count consecutive pieces BELOW (row, col)
         *   2. If count + 1 >= 4: WIN
         */

        int belowCount = 0;
        for (int r = row + 1; r < 6; r++) {
            if (board.getPiece(r, col) == piece) {
                belowCount++;
            } else {
                break;
            }
        }

        return belowCount + 1 >= 4;
    }
}

class DiagonalWinChecker implements WinChecker {
    @Override
    public boolean checkWin(Board board, int row, int col, GamePiece piece) {
        /**
         * Check both diagonals: \ and /
         * Algorithm:
         *   1. Check \ diagonal (top-left to bottom-right)
         *      - Count UP-LEFT and DOWN-RIGHT
         *   2. Check / diagonal (top-right to bottom-left)
         *      - Count UP-RIGHT and DOWN-LEFT
         *   3. If any >= 4: WIN
         */

        // Check \ diagonal (top-left to bottom-right)
        int upLeftCount = 0;
        for (int r = row - 1, c = col - 1; r >= 0 && c >= 0; r--, c--) {
            if (board.getPiece(r, c) == piece) {
                upLeftCount++;
            } else {
                break;
            }
        }

        int downRightCount = 0;
        for (int r = row + 1, c = col + 1; r < 6 && c < 7; r++, c++) {
            if (board.getPiece(r, c) == piece) {
                downRightCount++;
            } else {
                break;
            }
        }

        if (upLeftCount + 1 + downRightCount >= 4) {
            return true;
        }

        // Check / diagonal (top-right to bottom-left)
        int upRightCount = 0;
        for (int r = row - 1, c = col + 1; r >= 0 && c < 7; r--, c++) {
            if (board.getPiece(r, c) == piece) {
                upRightCount++;
            } else {
                break;
            }
        }

        int downLeftCount = 0;
        for (int r = row + 1, c = col - 1; r < 6 && c >= 0; r++, c--) {
            if (board.getPiece(r, c) == piece) {
                downLeftCount++;
            } else {
                break;
            }
        }

        return upRightCount + 1 + downLeftCount >= 4;
    }
}

class GameEngine {
    private Board board;
    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private boolean gameOver;
    private Player winner; // null if draw
    private List<Move> moveHistory;
    private List<WinChecker> winCheckers;

    public GameEngine(Player player1, Player player2) {
        this.board = new Board();
        this.player1 = player1;
        this.player2 = player2;
        this.currentPlayer = player1;
        this.gameOver = false;
        this.winner = null;
        this.moveHistory = new ArrayList<>();

        // Strategy Pattern - Why: Different win checking algorithms
        // Benefit: Easy to add new strategies (3-in-a-row, etc.)
        // Trade-off: More objects, but cleaner separation
        this.winCheckers = new ArrayList<>();
        winCheckers.add(new HorizontalWinChecker());
        winCheckers.add(new VerticalWinChecker());
        winCheckers.add(new DiagonalWinChecker());
    }

    /**
     * Make a move in the game
     * <p>
     * Flow:
     * 1. Validate column (0-6)
     * 2. Drop piece in board
     * 3. Record move
     * 4. Check all win conditions (3 strategies)
     * 5. If win: Set winner
     * 6. If board full: Draw
     * 7. Switch player
     * 8. Return move with result
     */
    public Move makeMove(int column)
            throws InvalidMoveException, ColumnFullException, GameOverException {

        // Check game not over
        if (gameOver) {
            throw new GameOverException("Game is already over");
        }

        // Validate column
        if (column < 0 || column > 6) {
            throw new InvalidMoveException("Column must be 0-6");
        }

        try {
            // Drop piece in board
            int row = board.dropPiece(column, currentPlayer.getPiece());

            // Record move
            Move move = new Move(column, currentPlayer);
            moveHistory.add(move);

            // Check for win (all 3 strategies)
            boolean isWin = false;
            for (WinChecker checker : winCheckers) {
                if (checker.checkWin(board, row, column, currentPlayer.getPiece())) {
                    isWin = true;
                    break;
                }
            }

            if (isWin) {
                gameOver = true;
                winner = currentPlayer;
                move.setResult(Result.WIN);
                System.out.println(currentPlayer.getName() + " WINS!");
                return move;
            }

            // Check for draw
            if (board.isFull()) {
                gameOver = true;
                winner = null;
                move.setResult(Result.DRAW);
                System.out.println("DRAW - Board is full");
                return move;
            }

            // Game continues
            move.setResult(Result.CONTINUE);

            // Switch player
            switchPlayer();

            return move;

        } catch (ColumnFullException e) {
            throw new ColumnFullException("Column " + column + " is full");
        }
    }

    private void switchPlayer() {
        currentPlayer = (currentPlayer == player1) ? player2 : player1;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public Player getWinner() {
        return winner;
    }

    public Board getBoard() {
        return board;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public List<Move> getMoveHistory() {
        return moveHistory;
    }

    public void printBoard() {
        GamePiece[][] grid = board.getGrid();
        System.out.println("\n  0 1 2 3 4 5 6");
        for (int row = 0; row < 6; row++) {
            System.out.print("  ");
            for (int col = 0; col < 7; col++) {
                GamePiece piece = grid[row][col];
                if (piece == GamePiece.RED) {
                    System.out.print("R ");
                } else if (piece == GamePiece.YELLOW) {
                    System.out.print("Y ");
                } else {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }
}

class InvalidMoveException extends Exception {
    public InvalidMoveException(String msg) {
        super(msg);
    }
}

class ColumnFullException extends Exception {
    public ColumnFullException(String msg) {
        super(msg);
    }
}

class GameOverException extends Exception {
    public GameOverException(String msg) {
        super(msg);
    }
}


public class Connect4Demo {
    public static void main(String[] args) {
        System.out.println("========== CONNECT 4 GAME DEMO ==========\n");

        // Create players
        Player player1 = new Player("Alice", GamePiece.RED, true);
        Player player2 = new Player("Bob", GamePiece.YELLOW, true);

        // Create game
        GameEngine game = new GameEngine(player1, player2);
    }
}