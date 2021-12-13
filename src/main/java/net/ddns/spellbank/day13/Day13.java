package net.ddns.spellbank.day13;

import java.util.ArrayList;
import java.util.List;

import net.ddns.spellbank.utils.InputFile;
import net.ddns.spellbank.utils.Point;

public class Day13 {

    public static void main(String[] args) {
        String file = "day13/test";
        String[] lines = InputFile.getLines(file);

        System.out.println(part1(lines)); // 638
        System.out.println(part2(lines)); // CJCKBAPB
    }

    public static long part1(String[] lines) {
        List<Point> points = new ArrayList<>();
        int i = 0;
        int maxrow = Integer.MIN_VALUE;
        int maxcol = Integer.MIN_VALUE;
        while (!lines[i].equals("")) {
            String[] fields = lines[i].split(",");
            int c = Integer.parseInt(fields[0]);
            int r = Integer.parseInt(fields[1]);
            maxrow = Math.max(maxrow, r);
            maxcol = Math.max(maxcol,  c);
            Point p = new Point(r, c);
            points.add(p);
            i++;
        }
        boolean[][] grid = new boolean[maxrow + 1][maxcol + 1];
        for (Point p : points) {
            grid[p.x][p.y] = true;
        }
        printGrid(grid);
        i++;
        String[] fields = lines[i].split("\\s");
        String[] vals = fields[2].split("=");
        if (vals[0].equals("y")) grid = foldVert(grid, Integer.parseInt(vals[1]));
        else grid = foldHoriz(grid, Integer.parseInt(vals[1]));
        return countGrid(grid);
    }

    public static long part2(String[] lines) {
        List<Point> points = new ArrayList<>();
        int i = 0;
        int maxrow = Integer.MIN_VALUE;
        int maxcol = Integer.MIN_VALUE;
        while (!lines[i].equals("")) {
            String[] fields = lines[i].split(",");
            int c = Integer.parseInt(fields[0]);
            int r = Integer.parseInt(fields[1]);
            maxrow = Math.max(maxrow, r);
            maxcol = Math.max(maxcol,  c);
            Point p = new Point(r, c);
            points.add(p);
            i++;
        }
        boolean[][] grid = new boolean[maxrow + 1][maxcol + 1];
        for (Point p : points) {
            grid[p.x][p.y] = true;
        }

        i++;
        for (; i < lines.length; i++) {
            String[] fields = lines[i].split("\\s");
            String[] vals = fields[2].split("=");
            if (vals[0].equals("y")) grid = foldVert(grid, Integer.parseInt(vals[1]));
            else grid = foldHoriz(grid, Integer.parseInt(vals[1]));
            //printGrid(grid);
            System.out.println();
        }
        printGrid(grid);
        return 0;
    }
    
    private static void printGrid(boolean[][] grid) {
        for (boolean[] r : grid) {
            for (boolean b : r) {
                char c = b ? '#' : ' ';
                System.out.print(c);
            }
            System.out.print('-');
            System.out.println();
        }
    }
    
    private static boolean[][] foldVert(boolean[][] grid, int base) {
        //System.out.println("Folding vertically");
        boolean[][] g = new boolean[base][grid[0].length];
        for (int r = 0; r < g.length; r++) {
            for (int c = 0; c < g[0].length; c++) {
                g[r][c] = grid[r][c] || grid[grid.length - 1 - r][c];
            }
        }
        return g;
    }
    
    private static boolean[][] foldHoriz(boolean[][] grid, int base) {
        //System.out.println("Folding horizontally");
        boolean[][] g = new boolean[grid.length][base];
        for (int r = 0; r < g.length; r++) {
            for (int c = 0; c < g[0].length; c++) {
                g[r][c] = grid[r][c] || grid[r][grid[0].length - 1 - c];
            }
        }
        return g;
    }
    
    private static long countGrid(boolean[][] grid) {
        long c = 0;
        for (boolean[] r : grid) 
            for (boolean b : r) if (b) c++;
        return c;
    }

}