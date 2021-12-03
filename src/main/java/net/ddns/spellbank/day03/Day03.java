package net.ddns.spellbank.day03;

import java.util.ArrayList;
import java.util.List;

import net.ddns.spellbank.utils.InputFile;

public class Day03 {

    public static void main(String[] args) {
        String file = "day03/input1";
        String[] lines = InputFile.getLines(file);

        System.out.println(part1(lines)); // 1071734
        System.out.println(part2(lines)); // 6124992
    }

    public static int part1(String[] lines) {
        int[] zeros = new int[lines[0].length()];
        int[] ones = new int[lines[0].length()];
        for (String line : lines) {
            for (int j = 0; j < line.length(); j++) {
                if (line.charAt(j) == '0') zeros[j]++;
                else ones[j]++;
            }
        }
        int gamma = 0;
        int epsilon = 0;
        for (int i = 0; i < zeros.length; i++) {
            gamma <<= 1;
            epsilon <<= 1;
            if (zeros[i] > ones[i]) {
                epsilon++;
            } else gamma++;
        }
        return gamma * epsilon;
    }

    public static int part2(String[] lines) {
        List<String> oxyCodes = new ArrayList<>();
        List<String> carbonCodes = new ArrayList<>();
        
        for (String line : lines) {
            oxyCodes.add(line);
            carbonCodes.add(line);
        }        

        int oxy = Integer.parseInt(findCode(true, oxyCodes), 2);
        int carbon = Integer.parseInt(findCode(false, carbonCodes), 2);
        return oxy * carbon;
    }
    
    private static String findCode(boolean oxygen, List<String> codes) {
        int place = 0;
        int zeros;
        int ones;
        while (codes.size() != 1) {
            zeros = 0;
            ones = 0;
            for (String s : codes) {
                if (s.charAt(place) == '0') zeros++;
                else ones++;
            }
            
            if (zeros > ones) {
                if (oxygen) evalBit(0, place, codes);
                else evalBit(1, place, codes);
            } else  {
                if (oxygen) evalBit(1, place, codes);
                else evalBit(0, place, codes);
            }
            place++;
        }
        return codes.get(0);
    }
    
    private static void evalBit(int val, int place, List<String> codes) {
        List<String> remove = new ArrayList<>();
        for (String code : codes) {
            int i = code.charAt(place) == '0' ? 0 : 1;
            if (i != val) remove.add(code);
        }
        codes.removeAll(remove);
    }
}