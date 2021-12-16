package net.ddns.spellbank.day15;

public class Tile {
    int[][] grid;
    int height;
    int width;
    
    public Tile(int[][] grid) {
        this.grid = grid;
        height = grid.length;
        width = grid[0].length;
    }
    
    public static Tile transformTile(Tile t) {
        int[][] base = t.grid;
        int[][] g = new int[t.height][t.width];
        for (int row = 0; row < base.length; row++) {
            for (int col = 0; col < base[0].length; col++) {
                g[row][col] = base[row][col] + 1;
                if (g[row][col] == 10) g[row][col] = 1;
            }
        }
        Tile tile = new Tile(g);
        return tile;
    }
    
    public static int[][] getTileGrid(Tile[][] tiles) {
        Tile base = tiles[0][0];
        int[][] newGrid = new int[base.height * tiles.length][base.width * tiles[0].length];
        for (int row = 0; row < tiles.length; row++) {
            int rowIndex = base.height * row;
            for (int col = 0; col < tiles[0].length; col++) {
                int colIndex = base.width * col;
                int[][] tileGrid = tiles[row][col].grid;
                for (int i = 0; i < tileGrid.length; i++) {
                    for (int j = 0; j < tileGrid[0].length; j++) {
                        newGrid[rowIndex + i][colIndex + j] = tileGrid[i][j];
                    }
                }
            }
        }
        return newGrid;
    }
    
    public static int[][] extendMap(Tile t) {
        Tile[][] tiles = new Tile[5][5];
        for (int row = 0; row < tiles.length; row++) {
            for (int col = 0; col < tiles[0].length; col++) {
                if (col == 0) {
                    if (row == 0) {
                        tiles[row][col] = t;
                    } else tiles[row][col] = transformTile(tiles[row - 1][col]);
                } else {
                    tiles[row][col] = transformTile(tiles[row][col - 1]);
                }
            }
        }
        return getTileGrid(tiles);
    }

}
