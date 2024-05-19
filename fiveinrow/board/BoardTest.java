package fiveinrow.board;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import fiveinrow.exception.*;

// Show that you can set a marker to a correct position.
// Show that if the position is not on the board, the desired exception is generated.
// show that if there is already a mark on the position, the desired exception is generated.
// show that you can decide if a position is on the board.
public class BoardTest {
    @Test
    public void test01() {
        Board b = Board.makeBoard(5);
        assertNotNull(b);
    }

    @ParameterizedTest
    @CsvSource({
        "1,1,O,O",
        "3,3,O,O",
        "2,3,X,X"
    })
    public void test02(int x, int y, Sign sign, Sign expected) throws InvalidPositionException, InvalidMoveException{
        Board b = Board.makeBoard(4);
        b.markFieldWithSign(sign, new Position(x,y));
        assertEquals(b.getSignByPosition(new Position(x,y)), expected);
    }

    @Test
    public void test03() {
        Board b = Board.makeBoard(5);
        assertThrows(InvalidPositionException.class, () -> {
            b.getSignByPosition(new Position(-1,1));
        });
    }

    @Test
    public void test04() throws InvalidPositionException, InvalidMoveException {
        Board b = Board.makeBoard(3);
        b.markFieldWithSign(Sign.O, new Position(0,0));
        assertThrows(InvalidMoveException.class, () -> {
            b.markFieldWithSign(Sign.X, new Position(0,0));
        });
    }

    @Test
    public void test05() {
        Board b = Board.makeBoard(3);
        assertTrue(b.isPositionOnBoard(new Position(2,2)));
        assertFalse(b.isPositionOnBoard(new Position(2,4)));
    }
}
