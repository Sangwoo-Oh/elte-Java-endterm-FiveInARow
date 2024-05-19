package fiveinrow;

import fiveinrow.board.*;
import fiveinrow.exception.*;
import java.lang.IllegalArgumentException;
import java.util.List;
import fiveinrow.board.utils.*;
public class FiveinARow extends Game {
    protected Sign sign;
    private final TriFunction<Position, Direction, Sign, Integer> countSign = (Position position, Direction direction, Sign sign) -> {
        try {
            if (!board.isPositionOnBoard(position) || sign != board.getSignByPosition(position)) return 0;
        } catch (InvalidPositionException e){}
        return 1 + this.countSign.apply(Position.getNextByDirection(position,direction), direction, sign);
    };
    public FiveinARow(String sign, int size) {
        try {
            this.sign = Sign.valueOf(sign);
            this.board = Board.makeBoard(size);
        } catch (IllegalArgumentException e) {
            this.sign = Sign.EMPTY;
            this.board = null;
        }
    }
    @Override
    public void play(List<String> moves) {
        if (sign == Sign.EMPTY) return;
        for (String move : moves) {
            String[] coordinates = move.split(" ");
            Position pos = new Position(Integer.parseInt(coordinates[0]), Integer.parseInt(coordinates[1]));
            try {
                board.markFieldWithSign(sign, pos);
                System.out.println(this);
                if(isThisMoveWon(pos)) {
                    System.out.println("Congratulations! The " + sign + " won!");
                    break;
                } else {
                    sign = sign == Sign.X ? Sign.O : Sign.X;
                }
            } catch (InvalidPositionException|InvalidMoveException e){}
        }
    }

    @Override
    protected boolean isThisMoveWon(Position position) {
        return checkSignsInRow(position) || checkSignsInColumn(position) || checkSignsInDiagonal(position);
    }

    @Override
    public String toString() {
        return sign +  "\n------------" + System.lineSeparator() + board + "\n------------" ;
    }

    private boolean checkSignsInRow(Position position) {
        int s = countSign.apply(position, Direction.LEFT, sign) + countSign.apply(position, Direction.RIGHT, sign);
        return s - 1 >= 5;
    }
    private boolean checkSignsInColumn(Position position) {
        int s = countSign.apply(position, Direction.UP, sign) + countSign.apply(position, Direction.DOWN, sign);
        return s - 1 >= 5;
    }
    private boolean checkSignsInDiagonal(Position position) {
        int s1 = countSign.apply(position, Direction.LEFT_DOWN, sign) + countSign.apply(position, Direction.RIGHT_UP, sign);
        int s2 = countSign.apply(position, Direction.LEFT_UP, sign) + countSign.apply(position, Direction.RIGHT_DOWN, sign);
        return s1 - 1 >= 5 || s2 - 1 >= 5;
    }
}
