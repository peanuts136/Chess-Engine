package com.chess.engine.board;

public class BoardUtils
{

    public static final int NUM_TILES = 64;
    public static final int NUM_TILES_PER_ROW = 8;
    public static final int STARTING_TILE = 0;
    /*set these certain positions to be true in their respective columns*/

    public static final boolean[] FIRST_COLUMN = initColumn(0);
    public static final boolean[] SECOND_COLUMN = initColumn(1);
    public static final boolean[] SEVENTH_COLUMN = initColumn(6);
    public static final boolean[] EIGHTH_COLUMN = initColumn(7);

    public static final boolean[] SECOND_ROW = initRow(1);
    public static final boolean[] SEVENTH_ROW = initRow(6);

    private static boolean[] initColumn(int columnNumber)
    {   /*List of booleans*/
        final boolean [] column = new boolean[NUM_TILES];
        while (columnNumber < NUM_TILES)
        {
            column[columnNumber] = true;
            columnNumber +=NUM_TILES_PER_ROW;
        }
        return column;
    }

    private static boolean[] initRow(int rowNumber)
    {
        final boolean [] row = new boolean[NUM_TILES_PER_ROW];
        int actualCoordinate = rowNumber * NUM_TILES_PER_ROW;
        while(actualCoordinate < rowNumber * NUM_TILES_PER_ROW + 8)
        {
            row[actualCoordinate] = true;
            actualCoordinate += 1;
        }
        return row;
    }
    private BoardUtils(){throw new RuntimeException("Cannot Instantiate this class");}
    public static boolean isValidMoveCoordinate(final int coordinate) {
        return coordinate >= STARTING_TILE && coordinate < NUM_TILES;
    }
}
