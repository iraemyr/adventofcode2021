package net.ddns.spellbank.day21;

import java.util.Objects;

public class State {
    int p1Score;
    int p2Score;
    int p1Pos;
    int p2Pos;
    int rolls;
    int rollTotal;
    boolean turn;
    
    public State(int p1Pos, int p1Score, int p2Pos, int p2Score, boolean turn) {
        this.p1Score = p1Score;
        this.p1Pos = p1Pos;
        this.p2Pos = p2Pos;
        this.p2Score = p2Score;
        this.turn = turn;
        rolls = 0;
        rollTotal = 0;
    }
    
    public State(int p1Pos, int p1Score, int p2Pos, int p2Score, boolean turn, int rolls, int rollTotal) {
        this.p1Score = p1Score;
        this.p1Pos = p1Pos;
        this.p2Pos = p2Pos;
        this.p2Score = p2Score;
        this.turn = turn;
        this.rolls = rolls;
        this.rollTotal = rollTotal;
    }
    
    public State nextState(int r) { 
        if (rolls == 2) {
            if (turn) {
                int pos = p1Pos + rollTotal + r;
                while (pos > 10) pos -= 10;
                return new State(pos, p1Score + pos, p2Pos, p2Score, !turn);
            }
            int pos = p2Pos + rollTotal + r;
            while (pos > 10) pos -= 10;
            return new State(p1Pos, p1Score, pos, p2Score + pos, !turn);
        } else {
            return new State(p1Pos, p1Score, p2Pos, p2Score, turn, rolls + 1, rollTotal + r);
        }
    }
    
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (this == obj) return true;
        if (!(obj instanceof State)) return false;
        State a = (State) obj;
        return this.p1Pos == a.p1Pos && this.p2Pos == a.p2Pos &&
               this.p1Score == a.p1Score && this.p2Score == a.p2Score &&
               this.turn == a.turn && this.rolls == a.rolls && 
               this.rollTotal == a.rollTotal? true : false;
    }
    
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(p1Score).append(',').append(p2Score).append(',')
          .append(p1Pos).append(',').append(p2Pos).append(',')
          .append(rolls).append(',').append(rollTotal).append(',').append(turn);
        return sb.toString();
    }
    
    public int hasWon() {
        if (p1Score >= 21) return 1;
        if (p2Score >= 21) return 2;
        return 0;
    }
    
    public int hashCode() {
        return Objects.hash(p1Pos, p2Pos, p1Score, p2Score, turn, rolls, rollTotal);
    }
    
    /*
     * Could possibly optimize to remove intermediate rolls
     * 
     * 111 = 3                         3 -> 1
     * 112 121 211 = 4                 4 -> 3
     * 113 131 311 = 5                 5 -> 6
     * 122 212 221 = 5                 6 -> 7
     * 123 132 213 231 321 312 = 6     7 -> 6
     * 222 = 6                         8 -> 3
     * 133 313 331 = 7                 9 -> 1
     * 223 232 322 = 7
     * 233 323 332 = 8
     * 333 = 9
     */
}
