package net.ddns.spellbank.day22;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.ddns.spellbank.utils.InputFile;
import net.ddns.spellbank.utils.Point3D;

public class Day22 {

    public static void main(String[] args) {
        String file = "day22/input1";
        String[] lines = InputFile.getLines(file); 

        System.out.println(part1(lines)); // 611176
        System.out.println(part2(lines)); // 1201259791805392
    }

    public static long part1(String[] lines) {
        Set<Point3D> grid = new HashSet<>();
        getInitGrid(lines, grid); 
        return countInit(grid);
    }
    
    public static long part2(String[] lines) {
        List<Cube> cubes = new ArrayList<>();
        for (String line : lines) {
            String[] fields = line.split("\\s");
            boolean on = fields[0].charAt(1) == 'n' ? true : false;
            fields = fields[1].split(",");
            int xlow, xhigh, ylow, yhigh, zlow, zhigh;
            String[] fi = fields[0].substring(2).split("\\.");
            xlow = Integer.parseInt(fi[0]);
            xhigh = Integer.parseInt(fi[fi.length - 1]);
            fi = fields[1].substring(2).split("\\.");
            ylow = Integer.parseInt(fi[0]);
            yhigh = Integer.parseInt(fi[fi.length - 1]);
            fi = fields[2].substring(2).split("\\.");
            zlow = Integer.parseInt(fi[0]);
            zhigh = Integer.parseInt(fi[fi.length - 1]);
            Cube c = new Cube(xlow, xhigh, ylow, yhigh, zlow, zhigh, on);
            List<Cube> add = new ArrayList<>();
            for (Cube cube : cubes) {
                long x0 = Math.max(c.xlow, cube.xlow);
                long x1 = Math.min(c.xhigh, cube.xhigh);
                long y0 = Math.max(c.ylow, cube.ylow);
                long y1 = Math.min(c.yhigh, cube.yhigh);
                long z0 = Math.max(c.zlow, cube.zlow);
                long z1 = Math.min(c.zhigh, cube.zhigh);
                if (x0 <= x1 && y0 <= y1 && z0 <= z1) {
                    add.add(new Cube(x0, x1, y0, y1, z0, z1, -cube.on));
                }
            }
            if (c.on > 0) add.add(c);
            cubes.addAll(add);
        }
        long sum = 0;
        for (Cube cube : cubes) sum += cube.numOn();
        return sum;
    }
    
    private static void toggle(Set<Point3D> points, boolean toggle, 
                               int xlow, int xhigh, int ylow, int yhigh, 
                               int zlow, int zhigh) {
        for (int x = xlow; x <= xhigh; x++) {
            for (int y = ylow; y <= yhigh; y++) {
                for (int z = zlow; z <= zhigh; z++) {
                    Point3D p = new Point3D(x, y, z);
                    if (toggle) points.add(p);
                    else points.remove(p);
                }
            }
        }
    }
    
    private static long countInit(Set<Point3D> grid) {
        long count = 0;
        for (int i = -50; i <= 50; i++) {
            for (int j = -50; j <= 50; j++) {
                for (int k = -50; k <= 50; k++) {
                    if (grid.contains(new Point3D(i, j, k))) count++;
                }
            }
        }
        return count;
    }
    
    private static void getInitGrid(String[] lines, Set<Point3D> grid) {
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            String[] fields = line.split("\\s");
            boolean on = fields[0].charAt(1) == 'n' ? true : false;
            fields = fields[1].split(",");
            int xlow, xhigh, ylow, yhigh, zlow, zhigh;
            String[] fi = fields[0].substring(2).split("\\.");
            xlow = Integer.parseInt(fi[0]);
            xhigh = Integer.parseInt(fi[fi.length - 1]);
            fi = fields[1].substring(2).split("\\.");
            ylow = Integer.parseInt(fi[0]);
            yhigh = Integer.parseInt(fi[fi.length - 1]);
            fi = fields[2].substring(2).split("\\.");
            zlow = Integer.parseInt(fi[0]);
            zhigh = Integer.parseInt(fi[fi.length - 1]);
            if (xlow < -50 || xlow > 50 || ylow < -50 || ylow > 50 || zlow < -50 || zlow > 50)  return;
            toggle(grid, on, xlow, xhigh, ylow, yhigh, zlow, zhigh);
        }
    }
}