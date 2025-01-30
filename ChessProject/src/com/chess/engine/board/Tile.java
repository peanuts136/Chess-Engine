/** the abstract tile isn't meant to be instantiated, only the concrete emptyTile and occupied
 * Tile is, static occupied and empty tiles because there should only be one kind of those classes
 Packages makes it easier to organize
 */
package com.chess.engine.board;
import com.chess.engine.pieces.Piece;
import com.google.common.collect.ImmutableMap;


import java.util.HashMap;
import java.util.Map;

public abstract class Tile
{   /**protected means that it's only allowed to be accessed by subclasses
    final means that it's only set once at construction*/
    protected final int tileCoordinate;

    /**creates a hashmap/dict using Map for all the board tiles, using 0-63 as the indexes for each tile
     * use guava for Immutable map*/
    private static Map<Integer, emptyTile> createAllPossibleEmptyTiles()
    {
        final Map<Integer, emptyTile> emptyTileMap = new HashMap<>();
        for(int i = 0; i < BoardUtils.NUM_TILES; i++)
        {
            emptyTileMap.put(i, new emptyTile(i));
        }
        return ImmutableMap.copyOf(emptyTileMap);
    }
    private static final Map<Integer, emptyTile> EMPTY_TILES_CACHE = createAllPossibleEmptyTiles();
    /**EMPTY_TILES is essentially a cache of all the empty tiles*/
    public static Tile createTile(final int tileCoordinate, final Piece piece)
    {   /* this is essentially a shorthand way of using if-else statement, piece != null ? checks for whether piece is null
     * if true, create an occupied tile with that piece, else return empty tile of that cord from the previously created cache*/
        return piece != null ? new occupiedTile(tileCoordinate, piece) : EMPTY_TILES_CACHE.get(tileCoordinate);
    }

/**private constructor*/
    private Tile(final int tileCoordinate)
    {
        this.tileCoordinate = tileCoordinate;
    }
    public abstract boolean isTileOccupied();
    public abstract Piece getPiece();

    public static final class emptyTile extends Tile
    {
        private emptyTile(final int coordinate)
        {
            super(coordinate);
        }
        @Override
        public boolean isTileOccupied()
        {
            return false;
        }
        @Override
        public Piece getPiece()
        {
            return null;
        }
    }
    public static final class occupiedTile extends Tile
    {
        /**private so no way to reference variable without a setter method*/
        private final Piece pieceOnTile;
        private occupiedTile(int coordinate, final Piece pieceOnTile)
        {
            super(coordinate);
            this.pieceOnTile = pieceOnTile;
        }
        @Override
        public boolean isTileOccupied()
        {
            return true;
        }
        @Override
        public Piece getPiece()
        {
            return this.pieceOnTile;
        }


    }
}