package net.ddns.spellbank.day21;

public class Dice {
    int val = 1;
    int rolls = 0;
    
    public Dice() {
        
    }
    
    public int roll() {
        int v = val;
        val++; 
        if (val > 100) val = 1;
        rolls++;
        return v;
    }
    
    public int roll3() {
        int sum = 0;
        for (int i = 0; i < 3; i++) sum += roll();
        return sum;
    }
}
