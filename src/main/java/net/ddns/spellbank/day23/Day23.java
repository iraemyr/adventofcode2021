package net.ddns.spellbank.day23;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

import net.ddns.spellbank.utils.InputFile;

public class Day23 {

    public static void main(String[] args) {
        String file = "day23/input2";
        String[] lines = InputFile.getLines(file);
        System.out.println(part1(lines)); // 15109
        
        file = "day23/input1";
        lines = InputFile.getLines(file);
        System.out.println(part1(lines)); // 53751
    }

    public static long part1(String[] lines) {
        char[][] map = loadMap(lines);
        State state = new State(map, 0);
        //MyUtils.printMap(state.getMap());
        Queue<State> q = new PriorityQueue<>();
        Set<State> visited = new HashSet<>();
        q.add(state);
        //int i = 0;
        while (!q.isEmpty()) {
            //i++;
            //if (i % 100 == 0) System.out.println(visited.size());
        //for (int i = 0; i < 5; i++) {
            State s = q.poll();
            //if (i % 100 == 0) System.out.println(s.cost);
            visited.add(s);
            if (s.isDone()) return s.cost;
            var moves = s.getMoves();
            if (moves != null) {
                for (State move : moves) if (!visited.contains(move)) q.add(move);
            }
        }
        return 0;
    }
    
    private static char[][] loadMap(String[] lines) {
        char[][] map = new char[lines.length][lines[0].length()];
        for (int i = 0; i < lines.length; i++) {
            for (int j = 0; j < lines[i].length(); j++) {
                map[i][j] = lines[i].charAt(j);
            }
        }
        //MyUtils.printMap(map);
        return map;
    }

}