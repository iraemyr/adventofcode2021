package net.ddns.spellbank.day12;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.ddns.spellbank.utils.InputFile;

public class Day12 {

    public static void main(String[] args) {
        String file = "day12/input1";
        String[] lines = InputFile.getLines(file);

        System.out.println(part1(lines)); // 3450
        System.out.println(part2(lines)); // 96528
    }

    public static long part1(String[] lines) {
        var map = getMap(lines);
        return traverse(map, new HashSet<String>(), "start", new ArrayList<>(), false);
    }

    public static long part2(String[] lines) {
        var map = getMap(lines);
        return traverse2(map, new HashSet<String>(), "start", new ArrayList<>(),
                         "", false);
    }
    
    private static Map<String, List<String>> getMap(String[] lines) {
        Map<String, List<String>> map = new HashMap<>();
        for (String line : lines) {
            String[] fields = line.split("-");
            var li = map.getOrDefault(fields[0], new ArrayList<>());
            li.add(fields[1]);
            map.put(fields[0], li);
            li = map.getOrDefault(fields[1], new ArrayList<>());
            li.add(fields[0]);
            map.put(fields[1], li);
        }
        return map;
    }
    
    private static long traverse(Map<String, List<String>> map, Set<String> visited, 
                                 String start, List<String> path, boolean debug) {
        var li = map.get(start);
        visited.add(start);
        if (debug) path.add(start);
        long count = 0;
        for (String dest : li) {
            if (dest.equals("end")) {
                count++;
                if (debug) {
                    path.add(dest);
                    printPath(path);
                    path.remove(path.size() - 1);
                }
                continue;
            }
            if (Character.isLowerCase(dest.charAt(0))) {
                if (visited.contains(dest)) continue;
                else visited.add(dest);
            }
            count+= traverse(map, visited, dest, path, debug);
            visited.remove(dest);
            if (debug) path.remove(path.size() - 1);
        }
        return count;
    }
    
    private static long traverse2(Map<String,List<String>> map, Set<String> visited, 
            String start, List<String> path, String twice, boolean debug) {
        var li = map.get(start);
        visited.add(start);
        if (debug) path.add(start);
        long count = 0;
        for (String dest : li) {
            if (dest.equals("start")) continue;
            if (dest.equals("end")) {
            count++;
            if (debug) {
                path.add(dest);
                printPath(path);
                path.remove(path.size() - 1);
            }
            continue;
        }
        if (Character.isLowerCase(dest.charAt(0))) {
            if (visited.contains(dest)) {
                if (!twice.equals("")) continue;
                else twice = dest;
            }
            else visited.add(dest);
        }
        count += traverse2(map, visited, dest, path, twice, debug);
        if (dest.equals(twice)) twice = "";
        else visited.remove(dest);
        if (debug) path.remove(path.size() - 1);
    }
    return count;
}
    
    private static void printPath(List<String> path) {
        for (String s : path) System.out.print(s + " ");
        System.out.println();
    }

}