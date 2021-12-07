package net.ddns.spellbank.day07;

import java.util.HashMap;
import java.util.Map;

import net.ddns.spellbank.utils.InputFile;

public class Day07 {

    public static void main(String[] args) {
        String file = "day07/input1";
        String[] lines = InputFile.getLines(file);

        System.out.println(part1(lines)); // 355989
        System.out.println(part2(lines)); // 102245489
    }

    public static int part1(String[] lines) {
        String[] fields = lines[0].split(",");
        int[] nums = new int[fields.length];
        int minNum = Integer.MAX_VALUE;
        int maxNum = Integer.MIN_VALUE;
        for (int i = 0; i < fields.length; i++) {
            int val = Integer.parseInt(fields[i]);
            minNum = Math.min(minNum, val);
            maxNum = Math.max(maxNum, val);
            nums[i] = val;
        }
        
        int fuel = Integer.MAX_VALUE;
        for (int i = minNum; i <= maxNum; i++) {
            int f = 0;
            for (int j : nums) f += Math.abs(j - i);
            fuel = Math.min(f, fuel);
        }
        return fuel;
    }

    public static int part2(String[] lines) {
        String[] fields = lines[0].split(",");
        int[] nums = new int[fields.length];
        Map<Integer, Integer> counts = new HashMap<>();
        int minNum = Integer.MAX_VALUE;
        int maxNum = Integer.MIN_VALUE;
        for (int i = 0; i < fields.length; i++) {
            int val = Integer.parseInt(fields[i]);
            minNum = Math.min(minNum, val);
            maxNum = Math.max(maxNum, val);
            nums[i] = val;
            int c = counts.getOrDefault(val, 0);
            counts.put(val, c + 1);
        }
        
        int fuel = Integer.MAX_VALUE;
        for (int i = minNum; i <= maxNum; i++) {
            int f = 0;
            for (int j : nums) f += calcFuel(Math.abs(j - i));
            fuel = Math.min(f, fuel);
        }
        return fuel;
    }
    
    private static int calcFuel(int d) {
        int f = 0;
        for (int i = 1; i <= d; i++) f += i;
        return f;
    }

}