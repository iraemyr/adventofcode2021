package net.ddns.spellbank.day21;

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
        TreeMap<Integer, Long> r = rec(0, 0, pos1, pos2, 1, 0, 3);
        //TreeMap<Integer, Long> r = rec(new State(4, 0, 1, 0, true));
        //System.out.println("Q: " + r);
        //System.out.println("Part 2: " + Math.max(r.get(1), r.get(2)));
        //System.out.println("Memo states: " + memo.size());
        return Math.max(r.get(1), r.get(2));
    }
    
    private static TreeMap<String, TreeMap<Integer, Long>> memo = new TreeMap<>();
    //private static HashMap<State, TreeMap<Integer, Long>> memo = new HashMap<>();

    private static TreeMap<Integer, Long> rec(int p1Score, int p2Score, int p1Loc, int p2Loc, 
                                              int turn, int rollSum, int rollRem) {
        String state = String.format("%d,%d,%d,%d,%d,%d,%d", p1Score, p2Score, p1Loc, p2Loc, 
                                                             turn, rollSum, rollRem);
        if (memo.containsKey(state)) return memo.get(state);

        TreeMap<Integer, Long> out = new TreeMap<>();
        out.put(1, 0L);
        out.put(2, 0L);

        if (rollRem > 0)
            for (int d = 1; d <= 3; d++)
              add(out, rec(p1Score, p2Score, p1Loc, p2Loc, turn, rollSum + d,
                           rollRem - 1));
        else {
            if (turn == 1) {
                p1Loc += rollSum;
                while (p1Loc > 10) p1Loc -= 10;
                p1Score += p1Loc;
                if (p1Score >= 21) add(out, 1, 1L);
                else add(out, rec(p1Score, p2Score, p1Loc, p2Loc, 2, 0, 3));
            } else {
                p2Loc += rollSum;
                while (p2Loc > 10) p2Loc -= 10;
                p2Score += p2Loc;
                if (p2Score >= 21) add(out, 2, 1L);
                else add(out, rec(p1Score, p2Score, p1Loc, p2Loc, 1, 0, 3));
            }
        }

        memo.put(state, out);
        return out;
    }
    
    /*private static TreeMap<Integer, Long> rec(State state) {
        //String state = String.format("%d,%d,%d,%d,%d,%d,%d", p1_score, p2_score, p1_loc, p2_loc, turn, roll_sum, roll_rem);
        if (memo.containsKey(state)) return memo.get(state);

        TreeMap<Integer, Long> out = new TreeMap<>();
        out.put(1, 0L);
        out.put(2, 0L);

        for (int d = 1; d <= 3; d++) {
            State s = state.nextState(d);
            int winner = s.hasWon();
            if (winner == 1) add(out, 1, 1L);
            else if (winner == 2) add(out, 2, 2L);
            else add(out, rec(s));
        }

        memo.put(state, out);
        return out;
    }*/

    private  static void add(TreeMap<Integer, Long> col, int player, long wins) {
      col.put(player, col.get(player) + wins);
    }
    
    private static void add(TreeMap<Integer, Long> col, TreeMap<Integer, Long> add) {
        for (int k : add.keySet()) col.put(k, col.get(k) + add.get(k));
    }

}