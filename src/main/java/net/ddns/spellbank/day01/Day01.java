package net.ddns.spellbank.day01;

import net.ddns.spellbank.utils.InputFile;

public class Day01 {

    public static void main(String[] args) {
        String file = "day01/input1";
        String[] lines = InputFile.getLines(file);

        System.out.println(part1(lines)); // 1400
        System.out.println(part2(lines)); // 1429
    }

    public static int part1(String[] lines) {
        int prev = -1;
        int count = 0;
        for (String line : lines) {
            int d = Integer.parseInt(line);
            if (prev != -1 && d > prev) count++;
            prev = d;
        }
        return count;
    }

    public static int part2(String[] lines) {
        int count = 0;
        int[] nums = new int[lines.length];
        for (int i = 0; i < lines.length; i++) {
            nums[i] = Integer.parseInt(lines[i]);
        }
        int sum = nums[0] + nums[1] + nums[2];
        int prev = sum;
        for (int i = 3; i < nums.length; i++) {
            sum += nums[i] - nums[i - 3];
            if (sum > prev) count++;
            prev = sum;
        }
        return count;
    }

}