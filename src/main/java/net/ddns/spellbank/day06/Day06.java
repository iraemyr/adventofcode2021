package net.ddns.spellbank.day06;

import net.ddns.spellbank.utils.InputFile;

public class Day06 {

    public static void main(String[] args) {
        String file = "day06/input1";
        String[] lines = InputFile.getLines(file);

        System.out.println(part1(lines)); // 385391
        System.out.println(part2(lines)); // 1728611055389
    }

    public static long part1(String[] lines) {
        String[] times = lines[0].split(",");
        long[] fish = new long[9];
        for (String t : times) fish[Integer.parseInt(t)]++;
        for (int i = 0; i < 80; i++) fish = doDay(fish);
        long sum = 0;
        for (long n : fish) sum += n;
        return sum;
    }

    public static long part2(String[] lines) {
        String[] times = lines[0].split(",");
        long[] fish = new long[9];
        for (String t : times) fish[Integer.parseInt(t)]++;
        for (int i = 0; i < 256; i++) fish = doDay(fish);
        long sum = 0;
        for (long n : fish) sum += n;
        return sum;
    }
    
    private static long[] doDay(long[] fish) {
        long[] day = new long[9];
        for (int i = 0; i < 9; i++) {
            if (i == 6) day[i] = fish[7] + fish[0];
            else if (i == 8) day[i] = fish[0];
            else day[i] = fish[i + 1];
        }
        return day;
    }
}