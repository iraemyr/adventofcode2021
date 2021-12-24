package net.ddns.spellbank.day24;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

import net.ddns.spellbank.utils.InputFile;

public class Day24 {

    public static void main(String[] args) {
        String file = "day24/input1";
        String[] lines = InputFile.getLines(file);
        System.out.println(part1(lines)); // 99999795919456
        System.out.println(part2(lines)); // 45311191516111    
    }
    
    public static long part1(String[] lines) {
        ALU.init(lines);
        int[] inputs = new int[14];
        Arrays.fill(inputs, -1);
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(0);
        //System.out.println("Push 0");
        while (!stack.isEmpty()) {
            findPop(stack, inputs);
        }
        StringBuilder sb = new StringBuilder();
        for (int i : inputs) sb.append(i);
        return Long.parseLong(sb.toString());
    }
    
    public static long part2(String[] lines) {
        ALU.init(lines);
        int[] inputs = new int[14];
        Arrays.fill(inputs, -1);
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(0);
        //System.out.println("Push 0");
        while (!stack.isEmpty()) {
            findPopLow(stack, inputs);
        }
        StringBuilder sb = new StringBuilder();
        for (int i : inputs) sb.append(i);
        return Long.parseLong(sb.toString());
    }
    
    private static void findPop(Deque<Integer> stack, int[] inputs) {
        boolean pop = false;
        int i = 0;
        ALU alu = new ALU();
        if (!stack.isEmpty()) i = stack.peek();
        int j =  i + 1;
        while (inputs[j] != -1) j++;
        while (!pop) {
            //System.out.println(i + " " + j);
            for (int x = 9; x > 0; x--) {
                for (int y = 9; y > 0; y--) {
                    alu.reset();
                    alu.runSegments(i, x, j, y);
                    if (alu.getZ() == 0) {
                        //Found pop
                        inputs[i] = x;
                        inputs[j] = y;
                        pop = true;
                        @SuppressWarnings("unused")
                        int z = stack.pop();
                        //System.out.println("Pop " + z);
                        return;
                    }
                }
            }
            if (!pop) stack.push(j);
            //System.out.println("Push " + j);
            j++;
            i = j - 1; 
        }
    }
    
    private static void findPopLow(Deque<Integer> stack, int[] inputs) {
        boolean pop = false;
        int i = 0;
        ALU alu = new ALU();
        if (!stack.isEmpty()) i = stack.peek();
        int j =  i + 1;
        while (inputs[j] != -1) j++;
        while (!pop) {
            //System.out.println(i + " " + j);
            for (int x = 1; x < 10; x++) {
                for (int y = 1; y < 10; y++) {
                    alu.reset();
                    alu.runSegments(i, x, j, y);
                    if (alu.getZ() == 0) {
                        //Found pop
                        inputs[i] = x;
                        inputs[j] = y;
                        pop = true;
                        @SuppressWarnings("unused")
                        int z = stack.pop();
                        //System.out.println("Pop " + z);
                        return;
                    }
                }
            }
            if (!pop) stack.push(j);
            //System.out.println("Push " + j);
            j++;
            i = j - 1; 
        }
    }
}