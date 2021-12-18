package net.ddns.spellbank.day18;

import java.util.ArrayList;
import java.util.List;

import net.ddns.spellbank.utils.InputFile;

public class Day18 {

    public static void main(String[] args) {
        String file = "day18/input1";
        String[] lines = InputFile.getLines(file);

        System.out.println(part1(lines)); // 4435
        System.out.println(part2(lines)); // 4802
    }

    public static long part1(String[] lines) {
        List<Pair> pairs = new ArrayList<>();
        for (String line : lines) {
            PairParser pp = new PairParser(line);
            Pair p = pp.parsePair();
            pairs.add(p);
        }
        Pair p = pairs.get(0);
        for (int i = 1; i < pairs.size(); i++) {
            p = p.add(pairs.get(i));
        }
        return p.mag();
    }

    public static long part2(String[] lines) {
        long max = Long.MIN_VALUE;
        for (int i = 0; i < lines.length; i++) {
            for (int j = 0; j < lines.length; j++) {
                if (i == j) continue;
                PairParser pp = new PairParser(lines[i]);
                var p1 = pp.parsePair();
                pp = new PairParser(lines[j]);
                var p2 = pp.parsePair();
                max = Math.max(max, p1.add(p2).mag());
            }
        }
        return max;
    }

}