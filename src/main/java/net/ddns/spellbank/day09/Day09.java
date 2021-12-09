package net.ddns.spellbank.day09;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.ddns.spellbank.utils.InputFile;

public class Day09 {

    public static void main(String[] args) {
        String file = "day09/input1";
        String[] lines = InputFile.getLines(file);

        System.out.println(part1(lines)); // 439
        System.out.println(part2(lines)); //900900
    }

    public static int part1(String[] lines) {
        int[][] map = getMap(lines);
        int risk = 0;
        for (int row = 0; row < map.length; row++) {
            for (int col = 0; col < map[0].length; col++) {
                risk += lowPointRisk(map, row, col);
            }
        }
        return risk;
    }

    public static int part2(String[] lines) {
        int[][] map = getMap(lines);
        List<Integer> basins = new ArrayList<>();
        for (int row = 0; row < map.length; row++) {
            for (int col = 0; col < map[0].length; col++) {
                int size = getBasinSize(map, row, col);
                if (size > 0) basins.add(size);
            }
        }
        
        Collections.sort(basins);
        int len = basins.size();
        return basins.get(len - 1) * basins.get(len - 2) * basins.get(len - 3);
    }
    
    private static int[][] getMap(String[] lines) {
        int[][] map = new int[lines.length][lines[0].length()];
        for (int i = 0; i < lines.length; i++) {
            char[] line = lines[i].toCharArray();
            for (int j = 0; j < line.length; j++) {
                map[i][j] = line[j] - '0';
            }
        }
        return map;
    }
    
    private static int lowPointRisk(int[][] map, int row, int col) {
        int val = map[row][col];
        if (row  - 1 >= 0 && map[row - 1][col] <= val) return 0;
        if (row  + 1 < map.length && map[row + 1][col] <= val) return 0;
        if (col  - 1 >= 0 && map[row][col - 1] <= val) return 0;
        if (col  + 1 < map[0].length && map[row][col + 1] <= val) return 0;
        return val + 1;
    }
    
    private static int getBasinSize(int[][] map, int row, int col) {
        if (row < 0 || row == map.length || col < 0 || col == map[0].length) return 0;
        if (map[row][col] < 0 || map[row][col] == 9) return 0;
        map[row][col] = -1;
        return 1 + 
               getBasinSize(map, row - 1, col) +
               getBasinSize(map, row + 1, col) +
               getBasinSize(map, row, col - 1) +
               getBasinSize(map, row, col + 1);
    }

}