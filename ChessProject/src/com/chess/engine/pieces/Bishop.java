package com.chess.engine.pieces;

import com.chess.engine.Color;
import com.chess.engine.board.Board;
import com.chess.engine.board.BoardUtils;
import com.chess.engine.board.Move;
import com.chess.engine.board.Tile;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("DuplicatedCode")
public class Bishop extends Piece
{
    private final static int[] POSSIBLE_VECTOR_COORDINATES ={-9,-7,7,9};

    public Bishop(final int  piecePosition, final Color color) {
        super(piecePosition, color);
    }


    @Override
    public List<Move> availableMoves(Board board) {

        final List<Move> legalMoves = new ArrayList<>();
        for(final int currentPossibleMove: POSSIBLE_VECTOR_COORDINATES)
        {
            int candidateBoardCoordinate = this.piecePosition + currentPossibleMove;
            while(BoardUtils.isValidMoveCoordinate(candidateBoardCoordinate))
            {
                if (FIRST_COLUMN_EXCLUSION(this.piecePosition, currentPossibleMove) ||
                        EIGHTH_COLUMN_EXCLUSION(this.piecePosition, currentPossibleMove))
                {   /*If we are in either the first column or last column, special exceptions apply*/
                    break;
                }
                final Tile possiblePiecePositionTile = board.getTile(candidateBoardCoordinate);
                if (!possiblePiecePositionTile.isTileOccupied())
                {
                    /*This bishop is the second argument*/
                    legalMoves.add(new Move.MajorPieceMove(board, this, candidateBoardCoordinate ));
                }
                else
                {
                    final Piece pieceAtDestination = possiblePiecePositionTile.getPiece();
                    final Color pieceColorAtDestination = pieceAtDestination.getPieceColor();
                    /*if is occupied by the opposite color from this bishop piece, available to move and can attack*/
                    if (this.pieceColor != pieceColorAtDestination) {
                        legalMoves.add(new Move.AttackMove(board, this, candidateBoardCoordinate, pieceAtDestination));
                    }
                    /*If encountered any occupied tile, cannot go any further, must break*/
                    break;
                }
                candidateBoardCoordinate += candidateBoardCoordinate;
            }
        }
        return ImmutableList.copyOf(legalMoves);
    }
    private static boolean FIRST_COLUMN_EXCLUSION(final int currentPosition, final int candidateOffset)
    {
        return BoardUtils.FIRST_COLUMN[currentPosition] && (candidateOffset ==-9 || candidateOffset ==7);
    }
    private static boolean EIGHTH_COLUMN_EXCLUSION(final int currentPosition, final int candidateOffset)
    {
        return BoardUtils.EIGHTH_COLUMN[currentPosition] && (candidateOffset ==9 || candidateOffset ==-7);
    }

}
