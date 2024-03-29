package com.chess.engine.board;

public class BoardUtils
{
    public static final boolean[] FIRST_COLUMN = null;
    public static final boolean[] SECOND_COLUMN = null;
    public static final boolean[] SEVENTH_COLUMN = null;
    public static final boolean[] EIGHTH_COLUMN = null;

    private BoardUtils(){throw new RuntimeException("Cannot Instantiate this class");}
    public static boolean isValidMoveCoordinate(int coordinate) {
        return coordinate >= 0 && coordinate < 64;
    }
}
