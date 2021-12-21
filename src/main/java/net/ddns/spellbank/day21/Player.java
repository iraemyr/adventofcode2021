package net.ddns.spellbank.day21;

public class Player {
    
    int score = 0;
    int pos;
    
    public Player(int p) {
        pos = p;
    }
    
    public Player(int p, int s) {
        pos = p;
        score = s;
    }
    
    public void move(int x) {
        pos += x;
        while (pos > 10) pos -= 10;
        score += pos;
    }

    

}
