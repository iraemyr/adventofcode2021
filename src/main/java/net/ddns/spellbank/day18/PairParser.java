package net.ddns.spellbank.day18;

public class PairParser {
    private int index;
    private String s;
    
    public PairParser(String line) {
        s = line;
    }
    
    public Pair parsePair() {
        if (s.charAt(index++) != '[') {
            System.out.println("Error");
            return null;
        }
        Pair p = new Pair();
        if (s.charAt(index) == '[') p.setLeft(parsePair());
        else p.setLeft(s.charAt(index++) - '0');
        if (s.charAt(index++) != ',') {
            System.out.println("Error");
            return null;
        }
        if (s.charAt(index) == '[') p.setRight(parsePair());
        else p.setRight(s.charAt(index++) - '0');
        if (s.charAt(index++) != ']') {
            System.out.println("Error");
            return null;
        }
        return p;
    }
}
