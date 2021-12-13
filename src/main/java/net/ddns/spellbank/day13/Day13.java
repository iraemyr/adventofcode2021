package net.ddns.spellbank.day13;

import java.util.HashSet;
import java.util.Set;

import net.ddns.spellbank.utils.InputFile;
import net.ddns.spellbank.utils.Point;

public class Day13 {

    public static void main(String[] args) {
        String file = "day13/input1";
        String[] lines = InputFile.getLines(file);

        System.out.println(part1(lines)); // 638
        System.out.println(part2(lines)); // CJCKBAPB
    }

    public static long part1(String[] lines) {
        Set<Point> points = new HashSet<>();
        int i = 0;
        while (!lines[i].equals("")) {
            String[] fields = lines[i].split(",");
            int c = Integer.parseInt(fields[0]);
            int r = Integer.parseInt(fields[1]);
            Point p = new Point(r, c);
            points.add(p);
            i++;
        }
        i++;
        String[] fields = lines[i].split("\\s");
        String[] vals = fields[2].split("=");
        if (vals[0].equals("y")) points = foldVert(points, Integer.parseInt(vals[1]));
        else points = foldHoriz(points, Integer.parseInt(vals[1]));
        return points.size();
    }

    public static long part2(String[] lines) {
        Set<Point> points = new HashSet<>();
        int i = 0;
        while (!lines[i].equals("")) {
            String[] fields = lines[i].split(",");
            int c = Integer.parseInt(fields[0]);
            int r = Integer.parseInt(fields[1]);
            Point p = new Point(r, c);
            points.add(p);
            i++;
        }
        i++;

        for (;i < lines.length;i++) {
            String[] fields = lines[i].split("\\s");
            String[] vals = fields[2].split("=");
            if (vals[0].equals("y")) points = foldVert(points, Integer.parseInt(vals[1]));
            else points = foldHoriz(points, Integer.parseInt(vals[1]));
        }
        
        printSet(points);
        return points.size();
    }
    
    private static void printSet(Set<Point> points) {
        int rows = points.stream().mapToInt(a -> a.x).max().getAsInt();
        int cols = points.stream().mapToInt(a -> a.y).max().getAsInt();
        for (int i = 0; i <= rows; i++) {
            for (int j = 0; j <= cols; j++) {
                Point p = new Point(i, j);
                char c = points.contains(p) ? '#' : ' ';
                System.out.print(c);
            }
            System.out.println();
        }
        System.out.println();
    }
        
    private static Set<Point> foldHoriz(Set<Point> points, int fold) {
        Set<Point> folded = new HashSet<>();
        for (Point p : points) {
            if (p.y == fold) continue;
            if (p.y < fold) folded.add(p);
            else {
                int delta = p.y - fold;
                folded.add(new Point(p.x, fold - delta));
            }
        }
        return folded;
    }
    
    private static Set<Point> foldVert(Set<Point> points, int fold) {
        Set<Point> folded = new HashSet<>();
        for (Point p : points) {
            if (p.x == fold) continue;
            if (p.x < fold) folded.add(p);
            else {
                int delta = p.x - fold;
                folded.add(new Point(fold - delta, p.y));
            }
        }
        return folded;
    }
}