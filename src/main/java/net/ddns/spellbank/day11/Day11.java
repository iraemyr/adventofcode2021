package net.ddns.spellbank.day11;

import net.ddns.spellbank.utils.InputFile;

public class Day11 {

    public static void main(String[] args) {
        String file = "day11/input1";
        String[] lines = InputFile.getLines(file);

        System.out.println(part1(lines)); // 1603
        System.out.println(part2(lines)); // 222
    }

    public static int part1(String[] lines) {
        int[][] map = new int[lines.length][lines[0].length()];
        for (int i = 0; i < lines.length; i++) {
            char[] chars = lines[i].toCharArray();
            for (int j = 0; j < chars.length; j++) map[i][j] = chars[j] - '0';
        }
        
        int flashes = 0;
        for (int i = 0; i < 100; i++) {
            flashes += cycle(map);
        }
        return flashes;
    }

    public static int part2(String[] lines) {
        int[][] map = new int[lines.length][lines[0].length()];
        for (int i = 0; i < lines.length; i++) {
            char[] chars = lines[i].toCharArray();
            for (int j = 0; j < chars.length; j++) map[i][j] = chars[j] - '0';
        }
        
        int d = 0;
        while(true) {
            d++;
            if (cycle(map) == 100) break;
        }
        return d;
    }
    
    private static int cycle(int[][] map) {
        int flashed = 0;
        boolean f = true;
        for (int row = 0; row < map.length; row++) {
            for (int col = 0; col < map[0].length; col++) map[row][col]++;
        }
        
        while (f) {
            f = false;
            for (int row = 0; row < map.length; row++) {
                for (int col = 0; col < map[0].length; col++) {
                    if (map[row][col] > 9) {
                        f = true;
                        flashed++;
                        map[row][col] = Integer.MIN_VALUE;
                        flash(map, row, col);
                    }
                }
            }
        }
        
        for (int row = 0; row < map.length; row++) {
            for (int col = 0; col < map[0].length; col++) 
                if (map[row][col] < 0) map[row][col] = 0;
        }
        return flashed;     
    }
    
    private static void flash(int[][] map, int row, int col) {
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if (i == 0 && j == 0) continue;
                int r = row + i;
                int c = col + j;
                if (r >= 0 && r < map.length && 
                    c >= 0 && c < map[0].length && 
                    map[r][c] >= 0) map[r][c]++;
            }
        }
    }

}