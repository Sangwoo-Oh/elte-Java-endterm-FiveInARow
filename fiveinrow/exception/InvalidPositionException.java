package fiveinrow.exception;

import java.lang.Exception;

public class InvalidPositionException extends Exception {
    public InvalidPositionException() {
        super("The position is not on the board");
    }
}
