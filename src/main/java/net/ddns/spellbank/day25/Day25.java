package net.ddns.spellbank.day25;

import java.util.ArrayList;
import java.util.List;

import net.ddns.spellbank.utils.InputFile;
import net.ddns.spellbank.utils.Point;

public class Day25 {

    public static void main(String[] args) {
        String file = "day25/test";
        String[] lines = InputFile.getLines(file);

        System.out.println(part1(lines));
    }

    public static long part1(String[] lines) {
        char[][] map = new char[lines.length][lines[0].length()];
        for (int i = 0; i < lines.length; i++) {
            for (int j = 0; j < lines[0].length(); j++) {
                map[i][j] = lines[i].charAt(j);
            }
        }
        long c = 0;
        while (doTurn(map)) {
            c++;
        }
        return c + 1;
    }

    public static long part2(String[] lines) {
        return 0;
    }
    
    private static boolean doTurn(char[][] map) {
        List<Point> move = new ArrayList<>();
        boolean moved = false;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                char c = map[i][j];
                if (c == '>') {
                    if (j == map[0].length - 1) { 
                        if (map[i][0] == '.') {
                            move.add(new Point(i, j, '.'));
                            move.add(new Point(i, 0, '>'));
                        }
                    } else if (map[i][j + 1] == '.') {
                        move.add(new Point(i, j, '.'));
                        move.add(new Point(i, j + 1, '>'));
                    }
                }
            }
        }
        if (!move.isEmpty()) moved = true;
        for (Point p : move) map[p.x][p.y] = p.c;
        move.clear();
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                char c = map[i][j];
                if (c == 'v') {
                    if (i == map.length - 1) { 
                        if (map[0][j] == '.') {
                            move.add(new Point(i, j, '.'));
                            move.add(new Point(0, j, 'v'));
                        }
                    } else if (map[i + 1][j] == '.') {
                        move.add(new Point(i, j, '.'));
                        move.add(new Point(i + 1, j, 'v'));
                    }
                }
            }
        }
        if (!move.isEmpty()) moved = true;
        for (Point p : move) map[p.x][p.y] = p.c;
        return moved;
    }
}