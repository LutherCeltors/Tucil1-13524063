package com.queens.entities;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.file.Paths;

public class BoardTest {
    @Test
    void testSetBoardFromTxt() {

        String path = Paths.get("src", "test", "resources", "board-test.txt").toString();
        Board board = Board.setBoardFromTxt(path);

        assertNotNull(board);
        assertEquals(4, board.getOrder());
    }

    @Test
    void testGenerateArea() {

        String path = Paths.get("src", "test", "resources", "board-test.txt").toString();
        Board board = Board.setBoardFromTxt(path);

        board.generateArea();

        assertEquals(4, board.getAreas().size());

        assertEquals('A', board.getAreas().get(0).getSymbol());
        assertEquals(4, board.getAreas().get(0).getCoordinates().size());
    }

    @Test
    void testAddQueen() {

        String path = Paths.get("src", "test", "resources", "board-test.txt").toString();
        Board board = Board.setBoardFromTxt(path);

        board.generateArea();
        board.addQueen(0, 0);

        assertEquals(1, board.getQueens().size());
    }

    @Test
    void testDisplayBoard() {

        String path = Paths.get("src", "test", "resources", "board-test.txt").toString();
        Board board = Board.setBoardFromTxt(path);

        board.generateArea();
        board.addQueen(0, 0);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        board.displayBoard();

        String output = outputStream.toString();

        assertTrue(output.contains("#"));
        assertTrue(output.contains("A"));
        assertTrue(output.contains("B"));
    }

}

