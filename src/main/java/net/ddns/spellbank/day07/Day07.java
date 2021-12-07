package net.ddns.spellbank.day07;

import java.util.Arrays;

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
        for (int i = 0; i < fields.length; i++) {
            nums[i] = Integer.parseInt(fields[i]);
        }
        Arrays.sort(nums);
        int med = nums[nums.length / 2]; // Not real median but got lucky
        int fuel = 0;
        for (int i : nums) fuel += Math.abs(i - med);
        return fuel;
    }

    public static int part2(String[] lines) {
        String[] fields = lines[0].split(",");
        int[] nums = new int[fields.length];
        int sum = 0;
        for (int i = 0; i < fields.length; i++) {
            int val = Integer.parseInt(fields[i]);
            nums[i] = val;
            sum += val;
        }
        
        int mean = sum / nums.length;
        int fuel = Integer.MAX_VALUE;
        for (int i = mean; i <= mean + 1; i++) {
            int f = 0;
            for (int j : nums) f += calcFuel(Math.abs(j - i));
            fuel = Math.min(f, fuel);
        }
        return fuel;
    }
    
    private static int calcFuel(int d) {
        // Use nth partial sum for Triangular numbers formula
        return (d * (d + 1)) / 2;
    }

}