package com.chess.engine.pieces;

import com.chess.engine.Color;
import com.chess.engine.board.Board;
import com.chess.engine.board.Move;

import java.util.List;
/**The abstraction OOP*/
public abstract class Piece
{
    protected final int piecePosition;
    protected final Color pieceColor;
    protected final boolean isFirstMove;
    Piece(final int piecePosition, final Color pieceColor)
    {
        this.piecePosition = piecePosition;
        this.pieceColor = pieceColor;

        this.isFirstMove = false;
    }

    public boolean isFirstMove()
    {
        return this.isFirstMove();
    }

    public Color getPieceColor() {
        return this.pieceColor;
    }

    /**returns a  list of legal moves on the board*/
    public abstract List<Move> availableMoves(final Board board);

}
