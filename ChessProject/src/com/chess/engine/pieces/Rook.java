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
public class Rook extends Piece
{
    private final static int[] POSSIBLE_VECTOR_COORDINATES = {-8,-1,1,8};
    public Rook(final int piecePosition, final Color color)
    {
        super(piecePosition,color);
    }

    @Override
    public List<Move> availableMoves(Board board)
    {
        final List<Move> legalMoves = new ArrayList<>();
        for(final int currentPossibleOffset: POSSIBLE_VECTOR_COORDINATES)
        {
            int candidateBoardCoordinate = this.piecePosition + currentPossibleOffset;
            while(BoardUtils.isValidMoveCoordinate(candidateBoardCoordinate))
            {
                if (FIRST_COLUMN_EXCLUSION(this.piecePosition, currentPossibleOffset) ||
                        EIGHTH_COLUMN_EXCLUSION(this.piecePosition, currentPossibleOffset))
                {   /*If we are in either the first column or last column, special exceptions apply*/
                    break;
                }
                final Tile possiblePiecePositionTile = board.getTile(candidateBoardCoordinate);
                if (!possiblePiecePositionTile.isTileOccupied())
                {
                    /*This rook is the second argument*/
                    legalMoves.add(new Move.MajorPieceMove(board, this, candidateBoardCoordinate ));
                }
                else
                {
                    final Piece pieceAtDestination = possiblePiecePositionTile.getPiece();
                    final Color pieceColorAtDestination = pieceAtDestination.getPieceColor();
                    /*if is occupied by the opposite color from this rook piece, available to move and can attack*/
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
    private static boolean FIRST_COLUMN_EXCLUSION(final int currentPosition, final int currentOffset)
    {
        return BoardUtils.FIRST_COLUMN[currentPosition] &&(currentOffset == -1);
    }
    private static boolean EIGHTH_COLUMN_EXCLUSION(final int currentPosition, final int currentOffset)
    {
        return BoardUtils.EIGHTH_COLUMN[currentPosition] &&(currentOffset == 1);
    }
}
