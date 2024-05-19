package main;

import fiveinrow.board.Position;
import fiveinrow.board.Board;
import fiveinrow.exception.*;
import fiveinrow.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.lang.NumberFormatException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        if (args.length != 2) return;
        if (args[1].length() != 1) return;
        String filename = args[0];
        String sign = args[1];

        FiveinARow game = new FiveinARow(sign, Board.BOARD_SIZE_FOR_10X10);
        game.play(readMoves(filename));
    }

    private static List<String> readMoves(String filename) {
        List<String> moves = new ArrayList<String>();
        try(BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] token = line.split(" ");
                if (token.length != 2) continue;
                try {
                    Integer.parseInt(token[0]);
                    Integer.parseInt(token[1]);
                    moves.add(line);
                } catch (NumberFormatException e) {}
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {}
        return moves;
    }
}
