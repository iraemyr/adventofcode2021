package net.ddns.spellbank.day10;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

import net.ddns.spellbank.utils.InputFile;

public class Day10 {

    public static void main(String[] args) {
        String file = "day10/input1";
        String[] lines = InputFile.getLines(file);

        System.out.println(part1(lines)); // 323691
        System.out.println(part2(lines)); // 2858785164
    }

    public static int part1(String[] lines) {
        int score = 0;
        for (String line : lines) {
            score += parseChunks(line);
        }
        return score;
    }

    public static long part2(String[] lines) {
        List<Long> scores = new ArrayList<>();
        for (String line : lines) {
            long score = completeChunks(line);
            if (score != -1) scores.add(score);
        }
        Collections.sort(scores);
        return scores.get(scores.size() / 2);
    }
    
    private static int parseChunks(String line) {
        Deque<Character> stack = new ArrayDeque<>();
        for (char c : line.toCharArray()) {
            if (c == ')') {
                if (stack.isEmpty() || stack.peek() != '(') return 3;
                stack.pop();
            }
            else if (c == ']') {
                if (stack.isEmpty() || stack.peek() != '[') return 57;
                stack.pop();
            } else if (c == '}') {
                if (stack.isEmpty() || stack.peek() != '{') return 1197;
                stack.pop();
            }
            else if (c == '>') {
                if (stack.isEmpty() || stack.peek() != '<') return 25137;
                stack.pop();
            } else stack.push(c);
            
        }
        return 0;
    }
    
    private static long completeChunks(String line) {
        Deque<Character> stack = new ArrayDeque<>();
        for (char c : line.toCharArray()) {
            if (c == ')') {
                if (stack.isEmpty() || stack.peek() != '(') return -1;
                stack.pop();
            }
            else if (c == ']') {
                if (stack.isEmpty() || stack.peek() != '[') return -1;
                stack.pop();
            } else if (c == '}') {
                if (stack.isEmpty() || stack.peek() != '{') return -1;
                stack.pop();
            }
            else if (c == '>') {
                if (stack.isEmpty() || stack.peek() != '<') return -1;
                stack.pop();
            } else stack.push(c);
        }
        
        long score = 0;
        while (!stack.isEmpty()) {
            char c = stack.pop();
            score *= 5;
            switch (c) {
            case '(' :
                score += 1;
                break;
            case '[' :
                score += 2;
                break;
            case '{' :
                score += 3;
                break;
            case '<' :
                score += 4;
                break;
             default :
                 System.out.println("Error: " + c);
                 return 0;
            }
        }
        return score;
    }

}