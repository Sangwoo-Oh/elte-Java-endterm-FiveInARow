package fiveinrow.board;

import fiveinrow.exception.InvalidPositionException;
import fiveinrow.exception.InvalidMoveException;
import java.lang.StringBuilder;
import java.util.Objects;

public class Board {
    public final int size;
    public final Sign[][] board;
    public static final int BOARD_SIZE_FOR_10X10 = 10;
    public static final int BOARD_SIZE_FOR_9X9 = 9;

    public static Board makeBoard(int size) {
        return new Board(size);
    }

    private Board(int size) {
        this.size = size;
        board = new Sign[size][];
        for (int i=0; i<board.length; i++) {
            board[i] = new Sign[size];
            for (int j=0; j<board[i].length; j++) {
                board[i][j] = Sign.EMPTY;
            }
        }
    }

    public Sign getSignByPosition(Position position) throws InvalidPositionException{
        if (!isPositionOnBoard(position)) throw new InvalidPositionException();
        return board[position.getX()][position.getY()];
    }

    public boolean isPositionOnBoard(Position position) {
        return 0 <= position.getX() && position.getX() < size && 0 <= position.getY() && position.getY() < size;
    }

    public void markFieldWithSign(Sign mark, Position position) throws InvalidPositionException, InvalidMoveException {
        if(!isPositionOnBoard(position)) throw new InvalidPositionException();

        if(getSignByPosition(position) == Sign.EMPTY) {
            board[position.getX()][position.getY()] = mark;
        } else {
            throw new InvalidMoveException();
        }
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<size; i++) {
            for (int j=0;j<size;j++) {
                if (board[i][j] == Sign.EMPTY) sb.append(" ");
                if (board[i][j] == Sign.O) sb.append("O");
                if (board[i][j] == Sign.X) sb.append("X");
            }
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) return true;
        if(other == null) return false;
        if(!(other instanceof Board)) return false;
        Board o = (Board)other;
        if (size != o.size) return false;
        for (int i=0; i<size; i++) {
            for (int j=0;j<size;j++) {
                if (o.board[i][j] != board[i][j]) return false;
            }
        }
        return true;
    }
    @Override
    public int hashCode() {
        return Objects.hash(size, board);
    }
}
