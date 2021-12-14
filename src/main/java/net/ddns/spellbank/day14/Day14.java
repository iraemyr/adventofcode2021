package net.ddns.spellbank.day14;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

import net.ddns.spellbank.utils.InputFile;

public class Day14 {

    public static void main(String[] args) {
        String file = "day14/input1";
        String[] lines = InputFile.getLines(file);

        System.out.println(part1(lines)); // 3058
        System.out.println(part2(lines)); // 3447389044530
    }

    public static long part1(String[] lines) {
        String base = lines[0];
        Map<String, Character> map = new HashMap<>();
        for (int i = 2; i < lines.length; i++) {
            String[] fields = lines[i].split(" -> ");
            map.put(fields[0], fields[1].charAt(0));
        }
        for (int i = 0; i < 10; i++) {
            base = doInserts(map, base);
        }
        return getScore(base);
    }

    public static long part2(String[] lines) {
        String base = lines[0];
        char[] b = base.toCharArray();
        Map<String, Character> map = new HashMap<>();
        Map<String, Long> pattern = new HashMap<>();
        for (int i = 2; i < lines.length; i++) {
            String[] fields = lines[i].split(" -> ");
            map.put(fields[0], fields[1].charAt(0));
        }
        
        for (int i = 0; i < b.length - 1; i++) {
            String key = "" + b[i] + b[i + 1];
            long val = pattern.getOrDefault(key, 0L);
            pattern.put(key, val + 1);
        }
        
        return simulateSteps(map, base, 40);
    }
    
    private static String doInserts(Map<String, Character> map, String base) {
        char[] b = base.toCharArray();
        Queue<Insertion> add = new ArrayDeque<>();
        for (int i = 0; i < b.length - 1; i++) {
            String key = "" + b[i] + b[i + 1];
            Character c = map.get(key);
            if (c == null) continue;
            add.add(new Insertion(c, i));
        }
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while(i < b.length) {
            sb.append(b[i]);
            if (!add.isEmpty()) {
                var in = add.peek();
                if (in.pos == i) {
                    sb.append(in.c);
                    add.poll();
                }
            }
            i++;
        }
        
        return sb.toString();
    }
    
    private static long simulateSteps(Map<String, Character> map, String base, int steps) {
        Map<String, Long> pairs = new HashMap<>();
        for(int i = 0; i < base.length() - 1; i++) {
            String key = base.substring(i, i + 2);
            var v = pairs.getOrDefault(key, 0L);
            pairs.put(key, v + 1);
        }
        
        Map<Character, Long> counts = new HashMap<>();
        for (char c : base.toCharArray()) {
            var v = counts.getOrDefault(c, 0L);
            counts.put(c, v + 1);
        }

        for (int step = 1; step <= steps; step++){
            Map<String, Long> newPairs = new HashMap<>();  
            for (String pair : pairs.keySet()){
                long increment = pairs.get(pair);
                if (map.containsKey(pair)) {
                    char c = map.get(pair);
                    String n1 = "" + pair.charAt(0) + c;
                    String n2 = "" + c + pair.charAt(1);
                    var v = newPairs.getOrDefault(n1, 0L);
                    newPairs.put(n1, v + increment);
                    v = newPairs.getOrDefault(n2, 0L);
                    newPairs.put(n2, v + increment);
                    v = counts.getOrDefault(c, 0L);
                    counts.put(c, v + increment);
                } else {
                    var v = newPairs.getOrDefault(pair, 0L);
                    newPairs.put(pair, v + increment);
                }
            }
            pairs = newPairs;
        }
        return counts.values().stream().mapToLong(e -> e).max().getAsLong() - counts.values().stream().mapToLong(e -> e).min().getAsLong();
    }
    
    private static long getScore(String base) {
        long[] counts = new long[26];
        long max = Long.MIN_VALUE;
        long min = Long.MAX_VALUE;
        for (char c : base.toCharArray()) counts[c - 'A']++;
        for (long c : counts) {
            if (c == 0) continue;
            max = Math.max(max, c);
            min = Math.min(min, c);
        }
        return max - min;
    }
}