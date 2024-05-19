package fiveinrow.exception;

import java.lang.Exception;

public class InvalidMoveException extends Exception {
    public InvalidMoveException() {
        super("The field of the board is not empty");
    }
}
