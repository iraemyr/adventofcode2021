package net.ddns.spellbank.day19;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.ddns.spellbank.utils.InputFile;
import net.ddns.spellbank.utils.Point3D;

public class Day19 {

    public static void main(String[] args) {
        String file = "day19/input1";
        String[] lines = InputFile.getLines(file);
        List<Point3D> locs = new ArrayList<>();
        Set<Point3D> beacons = new HashSet<>();
        
        solve(lines, beacons, locs);

        System.out.println(part1(beacons)); // 372
        System.out.println(part2(locs)); // 12241
    }
    
    public static long part1(Set<Point3D> beacons) {
        return beacons.size();
    }

    public static void solve(String[] lines, Set<Point3D> b, List<Point3D> locs) {
        int index = 1;
        List<Set<Point3D>> scanners = new ArrayList<>();
        while (index < lines.length) {
            Set<Point3D> beacons = new HashSet<>();
            scanners.add(beacons);
            while (index < lines.length && !lines[index].equals("")) {
                String[] fields = lines[index++].split(",");
                beacons.add(new Point3D(Integer.parseInt(fields[0]),
                            Integer.parseInt(fields[1]),
                            Integer.parseInt(fields[2])));
            }
            index += 2;
        }
        
        calcDeltas(scanners);
        Set<Point3D> scanner0 = scanners.remove(0);
        locs.add(new Point3D(0, 0, 0));
        while(!scanners.isEmpty()) {
            for (int i = 0; i < scanners.size(); i++) {
                var scanner = scanners.get(i);
                Map<Point3D, Point3D> ptp = new HashMap<>();
                for (var p : scanner0) {
                    for (var p1 : scanner) {
                        var intersection = p.getDeltas();
                        intersection.retainAll(p1.getDeltas());
                        if (intersection.size() > 10) ptp.put(p, p1);
                    }
                }
                if (ptp.size() >= 12) {
                    Point3D loc = getLocationofScanner(ptp, scanner0, scanner);
                    if (loc == null) continue;
                    locs.add(loc);
                    for (Point3D p : scanner0) p.addDeltas(scanner0);
                    scanners.remove(i);
                    break;
                }
            }
        }
        b.addAll(scanner0);
    }

    public static long part2(List<Point3D> locs) {
        Long max = Long.MIN_VALUE;
        for (int i = 0; i < locs.size() - 1; i++)
            for (int j = i + 1; j < locs.size(); j++)
                max = Math.max(max, manhattan(locs.get(i), locs.get(j)));
        return max;
    }
    
    private static long manhattan(Point3D p, Point3D p1) {
        return Math.abs(p.x - p1.x) + Math.abs(p.y - p1.y) + Math.abs(p.z - p1.z);
    }
    
    private static void calcDeltas(List<Set<Point3D>> scanners) {
        for (var s : scanners) {
            for (var p : s) {
                p.addDeltas(s);
            }
        }
    }
    
    private static Point3D getLocationofScanner(Map<Point3D, Point3D> ptp, 
                                                Set<Point3D> base,
                                                Set<Point3D> scanner) {
        Point3D known = ptp.keySet().iterator().next();
        Point3D match = ptp.get(known);
        for (int i = 0; i < 24; i++) {
            var trans = match.transform(i);
            Point3D loc = new Point3D(known.x - trans.x, known.y - trans.y, known.z - trans.z);
            int count = 0;
            for (var key : ptp.keySet()) {
                Point3D p = ptp.get(key).transform(i);
                Point3D t = new Point3D(p.x + loc.x, p.y + loc.y, p.z + loc.z);
                if (!key.equals(t)) break;
                count++;
            }
            
            if (count >= 12) {
                for (Point3D p : scanner) {
                    Point3D tr =  p.transform(i);
                    Point3D t = new Point3D(tr.x + loc.x, tr.y + loc.y, tr.z + loc.z);
                    base.add(t);
                }
                return loc;
            }
        }
        return null;
    }

}