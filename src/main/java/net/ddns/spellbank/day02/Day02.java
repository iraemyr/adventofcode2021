package net.ddns.spellbank.day02;

import net.ddns.spellbank.utils.InputFile;

public class Day02 {

    public static void main(String[] args) {
        String file = "day02/input1";
        String[] lines = InputFile.getLines(file);

        System.out.println(part1(lines)); // 1840243
        System.out.println(part2(lines)); // 1727785422
    }

    public static int part1(String[] lines) {
        int d = 0;
        int pos = 0;
        for (String line : lines) {
            String[] fields = line.split(" ");
            switch (fields[0]) {
                case "up" :
                    d -= Integer.parseInt(fields[1]);
                    if (d < 0) d = 0;
                    break;
                case "down" :
                    d += Integer.parseInt(fields[1]);
                    break;
                case "forward" :
                    pos += Integer.parseInt(fields[1]);
                    break;
                default :
            }
        }
        return pos * d;
    }

    public static int part2(String[] lines) {
        int d = 0;
        int pos = 0;
        int aim = 0;
        for (String line : lines) {
            String[] fields = line.split(" ");
            switch (fields[0]) {
                case "up" :
                    aim -= Integer.parseInt(fields[1]);
                    break;
                case "down" :
                    aim += Integer.parseInt(fields[1]);
                    break;
                case "forward" :
                    int x = Integer.parseInt(fields[1]);
                    pos += x;
                    d += aim * x;
                    if (d < 0) d = 0;
                    break;
                default :
            }
        }
        return pos * d;
    }

}