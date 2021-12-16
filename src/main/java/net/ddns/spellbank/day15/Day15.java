package net.ddns.spellbank.day15;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

import net.ddns.spellbank.utils.InputFile;
import net.ddns.spellbank.utils.Point;

public class Day15 {
    

    public static void main(String[] args) {
        String file = "day15/input1";
        String[] lines = InputFile.getLines(file);

        System.out.println(part1(lines)); // 373
        System.out.println(part2(lines)); // 2868
    }

    public static long part1(String[] lines) {
        int[][] map = new int[lines.length][lines[0].length()];
        Map<Point, Integer> m = new HashMap<>();
        for (int i = 0; i < lines.length; i++) {
            for (int j = 0; j < lines[i].length(); j++) {
                map[i][j] = lines[i].charAt(j) - '0';
                m.put(new Point(i, j), lines[i].charAt(j) - '0');
            }
        }
        return dijkstra(m, map);
    }

    public static long part2(String[] lines) {
        int[][] map = new int[lines.length][lines[0].length()];
        Map<Point, Integer> m = new HashMap<>();
        for (int i = 0; i < lines.length; i++) {
            for (int j = 0; j < lines[i].length(); j++) {
                map[i][j] = lines[i].charAt(j) - '0';
            }
        }
        map = Tile.extendMap(new Tile(map));
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                m.put(new Point(i, j), map[i][j]);
            }
        }
        return dijkstra(m, map);
    }
    
    private static int dijkstra(Map<Point, Integer> map, int[][] grid) {
        Set<Path> settled = new HashSet<>();
        Queue<Path> unsettled = new PriorityQueue<Path>();
        Map<Point, Path> pointToPath = new HashMap<>();
        for (Point p : map.keySet()) {
            Path path = null;
            if (p.x == 0 && p.y == 0) {
                path = new Path(p, 0);
                unsettled.add(path);
                pointToPath.put(p, path);
            }
            else {
                path = new Path(p);       
                //unsettled.offer(path);
                pointToPath.put(p, path);
            }
        }
        
        Path endPoint = pointToPath.get(new Point(grid.length - 1, grid[0].length - 1));
        while (!unsettled.contains(endPoint)) {
            Path pa = unsettled.poll();
            settled.add(pa);
            for (Point p : getNeighbors(grid, pa.at)) {
                Path path = pointToPath.get(p);
                if (settled.contains(path)) continue;
                int cost = pa.cost() + map.get(p);
                if (cost < path.cost()) {
                    unsettled.remove(path);
                    path.cost = cost;
                    unsettled.add(path);
                }
            }
        }
        return endPoint.cost; 
    }
    
    private static List<Point> getNeighbors(int[][] map, Point p) {
        List<Point> points = new ArrayList<>();
        if (p.x > 0) points.add(new Point(p.x - 1, p.y));
        if (p.y > 0) points.add(new Point(p.x, p.y - 1));
        if (p.x < map.length - 1) points.add(new Point(p.x + 1, p.y));
        if (p.y < map[0].length - 1) points.add(new Point(p.x, p.y + 1));
        return points;
    }
}