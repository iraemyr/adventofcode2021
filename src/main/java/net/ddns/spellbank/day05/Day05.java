package net.ddns.spellbank.day05;

import net.ddns.spellbank.utils.InputFile;

public class Day05 {

    public static void main(String[] args) {
        String file = "day05/input1";
        String[] lines = InputFile.getLines(file);

        System.out.println(part1(lines)); // 7380
        System.out.println(part2(lines)); // 21373
    }

    public static int part1(String[] lines) {
        int[][] grid = new int[1000][1000];
        for (String line : lines) {
            String[] coords = line.split(" -> ");
            String[] fields = coords[0].split(",");
            int x1 = Integer.parseInt(fields[0]);
            int y1 = Integer.parseInt(fields[1]);
            fields = coords[1].split(",");
            int x2 = Integer.parseInt(fields[0]);
            int y2 = Integer.parseInt(fields[1]);
            if (x1 == x2) drawVert(grid, x1, y1, y2);
            else if (y1 == y2) drawHor(grid, y1, x1, x2);
        }
        
        return countCrossings(grid);
    }
    
    public static int part2(String[] lines) {
        int[][] grid = new int[1000][1000];
        for (String line : lines) {
            String[] coords = line.split(" -> ");
            String[] fields = coords[0].split(",");
            int x1 = Integer.parseInt(fields[0]);
            int y1 = Integer.parseInt(fields[1]);
            fields = coords[1].split(",");
            int x2 = Integer.parseInt(fields[0]);
            int y2 = Integer.parseInt(fields[1]);
            if (x1 == x2) drawVert(grid, x1, y1, y2);
            else if (y1 == y2) drawHor(grid, y1, x1, x2);
            else drawDiag(grid, x1, x2, y1, y2);
        }
        
        return countCrossings(grid);
    }
    
    private static void drawVert(int[][] grid, int x, int y1, int y2) {
        int start = Math.min(y1, y2);
        int stop = Math.max(y1, y2);
        for (int i = start; i <= stop; i++) grid[i][x]++;
    }
    
    private static void drawHor(int[][] grid, int y, int x1, int x2) {
        int start = Math.min(x1, x2);
        int stop = Math.max(x1, x2);
        for (int i = start; i <= stop; i++) grid[y][i]++;
    }
    
    private static int countCrossings(int[][] grid) {
        int count = 0;
        for (int[] row : grid) {
            for (int i : row) if (i > 1) count++;
        }
        return count;
    }

    private static void drawDiag(int[][] grid, int x1, int x2, int y1, int y2) {
        int posX = x1, posY = y1;
        while (posX != x2) {
            grid[posY][posX]++;
            if (x1 < x2) posX++;
            else posX--;
            if (y1 < y2) posY++;
            else posY--;
        }
        grid[posY][posX]++;
    }
    
    @SuppressWarnings("unused")
    private static void printGrid(int[][] grid) {
        for (int[] row : grid) {
            for (int i : row) System.out.print(i + " ");
            System.out.println();
        }
    }
}