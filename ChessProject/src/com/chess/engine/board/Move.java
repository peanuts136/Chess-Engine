package com.chess.engine.board;

import com.chess.engine.pieces.Piece;


public abstract class Move {
    final Board board;
    final Piece movedPiece;
    final int destinationCoordinate;

    Move(final Board board, final Piece movedPiece, final int destinationCoordinate) {
        this.board = board;
        this.movedPiece = movedPiece;
        this.destinationCoordinate = destinationCoordinate;
    }


    public static final class MajorPieceMove extends Move
    {
        public MajorPieceMove(final Board board, final Piece movedPiece, final int destinationCoordinate) {
            super(board, movedPiece, destinationCoordinate);
        }
    }

    public static final class AttackMove extends Move
    {
        final Piece attackedPiece;
        public AttackMove(final Board board, final Piece movedPiece, final int destinationCoordinate, final Piece attackedPiece) {
            super(board, movedPiece, destinationCoordinate);
            this.attackedPiece = attackedPiece;
        }
    }

    public static final class KingMove extends Move
    {
        /*implement boolean whether that tile is currently being attacked*/
        public KingMove(final Board board, final Piece movedPiece, final int destinationCoordinate) {
            super(board, movedPiece, destinationCoordinate);
        }
    }
    public static final class KingAttackMove extends Move
    {
        /*implement whether the piece being attacked by king is defended*/
        public KingAttackMove(final Board board, final Piece movedPiece, final int destinationCoordinate) {
            super(board, movedPiece, destinationCoordinate);
        }
    }

}
