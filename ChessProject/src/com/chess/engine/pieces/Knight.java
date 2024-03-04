package com.chess.engine.pieces;

import com.chess.engine.Color;
import com.chess.engine.board.Board;
import com.chess.engine.board.BoardUtils;
import com.chess.engine.board.Move;
import com.chess.engine.board.Tile;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece
{

    private final static int[] POSSIBLE_MOVES_COORDINATES = {-17, -15, -10, -6, 6, 10, -15, -17};

    Knight(final int piecePosition, final Color pieceColor) {
        super(piecePosition, pieceColor);
    }

    @Override
    public List<Move> availableMoves(Board board) {
        int actualBoardCoordinate;
        final List<Move> legalMoves = new ArrayList<>();
        /*loops through the possible moves*/
        for (final int currentPossibleMove : POSSIBLE_MOVES_COORDINATES) {
            actualBoardCoordinate = this.piecePosition + currentPossibleMove;
            /*if its a valid move, check if its occupied and its implications*/
            if (BoardUtils.isValidMoveCoordinate(currentPossibleMove)) {
                if (firstColumnExceptions(this.piecePosition, currentPossibleMove))
                {/*if its on the first column and the current move matches an exception, do not continue*/
                    continue;
                }
                /*if unoccupied tile, add to available moves*/
                final Tile possiblePiecePositionTile = board.getTile(actualBoardCoordinate);
                if (!possiblePiecePositionTile.isTileOccupied()) {
                    legalMoves.add(new Move());
                } else {
                    final Piece pieceAtDestination = possiblePiecePositionTile.getPiece();
                    final Color pieceColorAtDestination = pieceAtDestination.getPieceColor();
                    /*if is occupied by the opposite color, available to move*/
                    if (this.pieceColor != pieceColorAtDestination) {
                        legalMoves.add(new Move());
                    }
                }
            }
        }
        return ImmutableList.copyOf(legalMoves);
    }

    private static boolean firstColumnExceptions(final int currentPosition, final int possiblePosition) {
        /*first part is array of booleans which checks if the piece position is on the that specific column
        * second part checks if the current position being iterated matches these exceptions*/
        return BoardUtils.FIRST_COLUMN[currentPosition] && ((possiblePosition == -17) || (possiblePosition == -10) ||
                (possiblePosition == 6) || (possiblePosition == 15));
    }

    private static boolean secondColumnExceptions(final int currentPosition, final int possiblePosition) {
        return BoardUtils.SECOND_COLUMN[currentPosition] && ((possiblePosition == -10) || (possiblePosition == 6));
    }

    private static boolean seventhColumnExceptions(final int currentPosition, final int possiblePosition) {
        return BoardUtils.SEVENTH_COLUMN[currentPosition] && ((possiblePosition == -6) || (possiblePosition == 10));
    }
    private static boolean eighthColumnExceptions(final int currentPosition, final int possiblePosition) {
        return BoardUtils.EIGHTH_COLUMN[currentPosition] && ((possiblePosition == -15) || (possiblePosition == -6)
                || (possiblePosition == 10 ) || (possiblePosition == 17));
    }
}
