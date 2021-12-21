package net.ddns.spellbank.day21;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Day21 {

    public static void main(String[] args) {
        System.out.println(part1(4, 1)); // 913560
        System.out.println(part2(4, 1)); // 110271560863819
    }

    public static long part1(int pos1, int pos2) {
        Player p1 = new Player(pos1);
        Player p2 = new Player(pos2);
        Player p;
        boolean turn = true;
        Dice die = new Dice();
        while (true) {
            p = turn ? p1 : p2;
            turn = !turn;
            p.move(die.roll3());
            if (p.score >= 1000) break;
        }
        int min = Math.min(p1.score, p2.score);
        return min * die.rolls;
    }

    public static long part2(int pos1, int pos2) {
        TreeMap<Integer, Long> r = rec(new State(4, 0, 1, 0, true));
        //System.out.println("States: " + memo.size());
        return Math.max(r.get(1), r.get(2));
    }
    
    private static Map<State, TreeMap<Integer, Long>> memo = new HashMap<>();
    
    private static TreeMap<Integer, Long> rec(State state) {
        
        if (memo.containsKey(state)) return memo.get(state);
        TreeMap<Integer, Long> out = new TreeMap<>();
        out.put(1, 0L);
        out.put(2, 0L);

        for (int d = 1; d <= 3; d++) {
            State s = state.nextState(d);
            int winner = s.hasWon();
            if (winner == 1) add(out, 1, 1L);
            else if (winner == 2) add(out, 2, 1L);
            else add(out, rec(s));
        }

        memo.put(state, out);
        return out;
    }

    private  static void add(TreeMap<Integer, Long> col, int player, long wins) {
      col.put(player, col.get(player) + wins);
    }
    
    private static void add(TreeMap<Integer, Long> col, TreeMap<Integer, Long> add) {
        for (int k : add.keySet()) col.put(k, col.get(k) + add.get(k));
    }

}