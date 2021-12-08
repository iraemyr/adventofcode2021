package net.ddns.spellbank.day08;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.ddns.spellbank.utils.InputFile;

public class Day08 {
    private static Map<String, Integer> Decoder;

    public static void main(String[] args) {
        String file = "day08/input1";
        String[] lines = InputFile.getLines(file);

        System.out.println(part1(lines)); // 488
        System.out.println(part2(lines)); // 1040429
    }

    public static int part1(String[] lines) {
        int count = 0;
        for (String line : lines) {
            String[] io = line.split("\\|");
            String[] fields = io[1].trim().split("\\s");
            for (String f : fields) {
                if (f.length() == 2 | 
                    f.length() == 3 |
                    f.length() == 4 |
                    f.length() == 7) count++;
            }
        }
        return count;
    }

    public static int part2(String[] lines) {
        Decoder = new HashMap<>();
        Decoder.put("abcefg", 0);
        Decoder.put("cf", 1);
        Decoder.put("acdeg", 2);
        Decoder.put("acdfg", 3);
        Decoder.put("bcdf", 4);
        Decoder.put("abdfg", 5);
        Decoder.put("abdefg", 6);
        Decoder.put("acf", 7);
        Decoder.put("abcdefg", 8);
        Decoder.put("abcdfg", 9);

        int sum = 0;
        for (String line : lines) {
            String[] io = line.split("\\|");
            String[] patterns = io[0].trim().split("\\s");
            Map<Character, Character> code  = decodePatterns(patterns);
            String[] fields = io[1].trim().split("\\s");
            sum += getOutput(code, fields);
        }
        return sum;
    }
    
    private static Map<Character, Character> decodePatterns(String[] patterns) {
        /* 0 abcefg -  6
         * 1 cf     -  2*
         * 2 acdeg  -  5
         * 3 acdfg  -  5
         * 4 bcdf   -  4*
         * 5 abdfg  -  5
         * 6 abdefg -  6
         * 7 acf    -  3*
         * 8 abcdefg - 7*
         * 9 abcdfg  - 6
         */
        Map<Character, Character> code = new HashMap<>();
        
        Set<Character> zero = null;
        Set<Character> one = new HashSet<>();

        Set<Character> three = null;
        Set<Character> four = new HashSet<>();

        Set<Character> six = null;
        Set<Character> seven = new HashSet<>();
        Set<Character> eight = new HashSet<>();
        Set<Character> nine = null;
        
        List<Set<Character>> fiveLength = new ArrayList<Set<Character>>();
        List<Set<Character>> sixLength = new ArrayList<Set<Character>>();
        for (String p : patterns) {
            if (p.length() == 2) {
                addCharsToSet(one, p);
            } else if (p.length() == 4) {
                addCharsToSet(four, p);
            } else if (p.length() == 3) {
                addCharsToSet(seven, p);
            } else if (p.length() == 7) {
                addCharsToSet(eight, p);
            } else if (p.length() == 5) {
                Set<Character> s = new HashSet<>();
                addCharsToSet(s, p);
                fiveLength.add(s);
            } else {
                Set<Character> s = new HashSet<>();
                addCharsToSet(s, p);
                sixLength.add(s);
            }
        }
        seven.removeAll(one);
        code.put(seven.iterator().next(), 'a');
        for (var s: sixLength) {
            if (!s.containsAll(one)) six = s;
        }
        sixLength.remove(six);
        for (char c : one) {
            if (six.contains(c)) code.put(c, 'f');
            else code.put(c, 'c');
        }
        
        for (char c : four) {
            if (six.contains(c) && !one.contains(c)) code.put(c,'b');
        }
        
        for (var s : fiveLength) {
            if (s.containsAll(one)) three = s;
        }
        
        for (var s : sixLength) {
            if (s.containsAll(three)) nine = s;
            else zero = s;
        }
        
        for (char c : nine) {
            if (!zero.contains(c)) code.put(c, 'd');
        }
        
        for (char c : zero) {
            if (!nine.contains(c) && !code.containsKey(c)) code.put(c, 'e');
        }
        
        for (int i = 0; i < 7; i++) {
            if (!code.containsKey((char) ('a' + i))) code.put((char) ('a' + i), 'g');
        }

        return code;
    }
    
    private static void addCharsToSet(Set<Character> s, String p) {
        for (char c :p.toCharArray()) {
            s.add(c);
        }
    }
    
    private static int getOutput(Map<Character, Character> code, String[] fields) {
        StringBuilder sb = new StringBuilder();
        for (String f : fields) {
            char[] c = f.toCharArray();
            for (int i = 0; i < c.length; i++) c[i] = code.get(c[i]);
            Arrays.sort(c);
            sb.append(Decoder.get(new String(c)));
        }
        return Integer.parseInt(sb.toString());
    }

}