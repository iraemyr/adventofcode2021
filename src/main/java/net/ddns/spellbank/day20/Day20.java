package net.ddns.spellbank.day20;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.ddns.spellbank.utils.InputFile;
import net.ddns.spellbank.utils.Point;

public class Day20 {

    public static void main(String[] args) {
        String file = "day20/input1";
        String[] lines = InputFile.getLines(file);

        System.out.println(part1(lines)); // 5597
        System.out.println(part2(lines)); // 18723
    }

    public static long part1(String[] lines) {
        String algorithm = lines[0];
        //System.out.println(lines[2].length());
        Set<Point> grid = new HashSet<>(); 
        for (int i = 0; i < lines.length - 2; i++) {
            for (int j = 0; j < lines[i + 2].length(); j++) {
                if (lines[i + 2].charAt(j) == '#') {
                    //System.out.println(i + ":" + j);
                    grid.add(new Point(i, j));
                }
            }
        }
        //printGrid(grid, -60, 160);
        //return grid.size();
        
        List<Point> on = new ArrayList<>();
        List<Point> off = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            for (int j = -5; j < 105; j++) {
                for (int k = -5; k < 105; k++) {
                    Point p = new Point(j, k);
                    if (pixelOn(grid, p, algorithm)) on.add(p);
                    else off.add(p);
                }
            }
            for (Point p : on) grid.add(p);
            for (Point p : off) grid.remove(p);
            on.clear();
            off.clear();
            //printGrid(grid, -60, 160);
        }
        trimGrid(grid, 3);
        //printGrid(grid, -60, 160);
        return grid.size();
    }

    public static long part2(String[] lines) {
        String algorithm = lines[0];
        //System.out.println(lines[2].length());
        Set<Point> grid = new HashSet<>(); 
        for (int i = 0; i < lines.length - 2; i++) {
            for (int j = 0; j < lines[i + 2].length(); j++) {
                if (lines[i + 2].charAt(j) == '#') {
                    //System.out.println(i + ":" + j);
                    grid.add(new Point(i, j));
                }
            }
        }
        //printGrid(grid, -60, 160);
        //return grid.size();
        
        List<Point> on = new ArrayList<>();
        List<Point> off = new ArrayList<>();
        int trimsize = 2;
        for (int i = 0; i < 50; i++) {
            trimsize += 2;
            for (int j = -160; j < 260; j++) {
                for (int k = -160; k < 260; k++) {
                    Point p = new Point(j, k);
                    if (pixelOn(grid, p, algorithm)) on.add(p);
                    else off.add(p);
                }
            }
            for (Point p : on) grid.add(p);
            for (Point p : off) grid.remove(p);
            on.clear();
            off.clear();
            if (i > 0 && i % 2 == 1) trimGrid(grid, trimsize);
            //printGrid(grid, -200, 300);
        }
        //printGrid(grid, -60, 160);
        return grid.size();
    }
    
    private static void printGrid(Set<Point> grid, int min, int max) {
        for (int row = -60; row < 160; row++) {
            for (int col =-60; col < 160; col++) {
                char c = grid.contains(new Point(row , col)) ? '#' : '.'; 
                System.out.print(c);
            }
            System.out.println();
        }
        System.out.println();
    }
    
    
    private static void trimGrid(Set<Point> grid, int val) {
        List<Point> remove = new ArrayList<>();
        for (Point p : grid) {
            if (p.x < -val || p.y < -val || p.x > 100 + val || p.y > 100 + val) remove.add(p); 
        }
        grid.removeAll(remove);
    }
    
    private static boolean pixelOn(Set<Point> grid, Point p, String algorithm) {
        List<Point> neighbors = p.getNeighbors();
        int val = 0;
        for (Point n : neighbors) {
            //System.out.println(n.toString());
            //System.out.println(grid.contains(n));
            val <<= 1;
            val += grid.contains(n) ? 1 : 0;
            //System.out.println(val);
        }
        //if (val != 0) System.out.println(val);
        return algorithm.charAt(val) == '#' ? true : false;
    }

}