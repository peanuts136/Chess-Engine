package com.chess.engine.pieces;

import com.chess.engine.Color;
import com.chess.engine.board.Board;
import com.chess.engine.board.BoardUtils;
import com.chess.engine.board.Move;
import com.chess.engine.board.Tile;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece
{

    private final static boolean PROMOTION_AVAILABLE = false;
    private final static int[] CANDIDATE_MOVES = {16,9,8,7};

    public Pawn(final int piecePosition, final Color color)
    {
        super(piecePosition, color);
    }
    public List<Move> availableMoves(Board board)
    {
        final List<Move> legalMoves = new ArrayList<>();
        for(final int candidateOffset: CANDIDATE_MOVES)
        {   /*If it is black, offset is positive so move down the board, else move up the board*/
            final int candidateCoordinate = this.piecePosition + (candidateOffset * this.getPieceColor().getDirection());
            final Tile possiblePiecePositionTile = board.getTile(candidateCoordinate);
            if(!BoardUtils.isValidMoveCoordinate(candidateCoordinate))
            {
                continue;
            }
            if(candidateOffset == 8 && !board.getTile(candidateCoordinate).isTileOccupied())
            {
                legalMoves.add(new Move.MajorPieceMove(board, this, candidateCoordinate));

            }
            else if (candidateOffset==16 && this.isFirstMove() &&
                    ((BoardUtils.SECOND_ROW[this.piecePosition] && this.getPieceColor().isBlack()) ||
                    (BoardUtils.SEVENTH_ROW[this.piecePosition] && this.getPieceColor().isWhite())))
            {
                final int behindCandidateCoordinate = this.piecePosition + (this.getPieceColor().getDirection() * 8);
                if (!board.getTile(behindCandidateCoordinate).isTileOccupied() && !board.getTile(candidateCoordinate).isTileOccupied())
                {legalMoves.add(new Move.MajorPieceMove(board, this, candidateCoordinate));}
            }
            else if(candidateOffset == 7 &&
                    !(BoardUtils.EIGHTH_COLUMN[this.piecePosition] && (this.getPieceColor().isWhite()) ||
                            (BoardUtils.FIRST_COLUMN[this.piecePosition]) && (this.getPieceColor().isBlack())))
            {
                if (board.getTile(candidateCoordinate).isTileOccupied())
                {
                    final Piece pieceToAttack = board.getTile(candidateCoordinate).getPiece();
                    if (this.getPieceColor()!=pieceToAttack.getPieceColor())
                    {
                        legalMoves.add(new Move.MajorPieceMove(board, this, candidateCoordinate));
                    }
                }
            }
            else if(candidateOffset == 9 &&
                !((BoardUtils.FIRST_COLUMN[candidateCoordinate]) && this.getPieceColor().isWhite() ||
                        (BoardUtils.EIGHTH_COLUMN[candidateCoordinate] && this.getPieceColor().isBlack())))
            {
                if (board.getTile(candidateCoordinate).isTileOccupied())
                {
                    final Piece pieceToAttack = board.getTile(candidateCoordinate).getPiece();
                    if (this.getPieceColor()!=pieceToAttack.getPieceColor())
                    {
                        legalMoves.add(new Move.MajorPieceMove(board, this, candidateCoordinate));
                    }
                }
            }
        }
        return ImmutableList.copyOf(legalMoves);
    }
    private static boolean FIRST_COLUMN_EXCEPTION(final int currentPosition, final int candidatePosition)
    {
        return BoardUtils.FIRST_COLUMN[currentPosition] && (candidatePosition == 9);
    }
}
