package fiveinrow;
import fiveinrow.board.Position;
import fiveinrow.board.Board;


import java.util.List;
public abstract class Game {
    protected Board board;

    protected abstract void play(List<String> steps);
    protected abstract boolean isThisMoveWon(Position position);
}
