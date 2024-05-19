package fiveinrow.board;

import java.util.Objects;

import fiveinrow.board.utils.*;

public class Position {
    private final int x,y;
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public int getX() { return x; }
    public int getY() { return y; }

    @Override
    public boolean equals(Object other) {
        if (other == this) return true;
        if (other == null) return false;
        if (!(other instanceof Position)) return false;
        Position o = (Position)other;
        return o.x == x && o.y == y;
    }

    public int hashCode() {
        return Objects.hash(this);
    }

    public static Position getNextByDirection(Position pos, Direction direction) {
        switch (direction) {
            case Direction.UP:return new Position(pos.getX() - 1, pos.getY());
            case Direction.DOWN: return new Position(pos.getX() + 1, pos.getY());
            case Direction.LEFT: return new Position(pos.getX(), pos.getY() - 1);
            case Direction.RIGHT: return new Position(pos.getX(), pos.getY() + 1);
            case Direction.LEFT_DOWN: return new Position(pos.getX() + 1, pos.getY() - 1);
            case Direction.LEFT_UP: return new Position(pos.getX() - 1, pos.getY() - 1 );
            case Direction.RIGHT_DOWN: return new Position(pos.getX() + 1, pos.getY() + 1);
            case Direction.RIGHT_UP: return new Position(pos.getX() - 1, pos.getY() + 1);
            default: throw new IllegalArgumentException();
        }
    }
}
