package net.ddns.spellbank.day24;

public class ALU {
    static String[] program;
    int index = 0;
    private static final int SEGMENT_SIZE = 18;
    
    int[] reg;
    
    public ALU() {
        reg = new int[4];
    }
    
    public static void init(String[] lines) {
        program = lines;
    }
    
    public int getW() {
        return reg[0];
    }
    public int getX() {
        return reg[1];
    }
    
    public int getY() {
        return reg[2];
    }
    
    public int getZ() {
        return reg[3];
    }
    
    public void reset() {
        for (int i = 0; i < reg.length; i++) reg[i] = 0;
        index = 0;
    }
    
    public boolean inp(int val, char var) {
        reg[var - 'w'] = val;
        return true;
    }
    
    public boolean add(char v1, String s) {
        int v2 = getVal(s);
        reg[v1 - 'w'] = reg[v1 - 'w'] + v2;
        return true;
    }
    
    public boolean mul(char v1, String s) {
        int val2 = getVal(s);
        reg[v1 - 'w'] = reg[v1 - 'w'] * val2;
        return true;
    }

    public boolean div(char v1, String s) {
        int val2 = getVal(s);
        if (val2 == 0) return false;
        reg[v1 - 'w'] = reg[v1 - 'w'] / val2;
        return true;
    }
    
    public boolean mod(char v1, String s) {
        int val2 = getVal(s);
        if (reg[v1 - 'w'] < 0 || val2 <= 0) return false;
        reg[v1 - 'w'] = reg[v1 - 'w'] % val2;
        return true;
    }
    
    public boolean eql(char v1, String s) {
        int val2 = getVal(s);
        reg[v1 - 'w'] = reg[v1 - 'w'] == val2 ? 1 : 0;
        return true;
    }
    
    private int getVal(String s) {
        return Character.isLowerCase(s.charAt(0)) ? 
               reg[s.charAt(0) - 'w'] : Integer.parseInt(s);
    }
    
    public void runSegments(int segment1, int val1, int segment2, int val2) {
        index = SEGMENT_SIZE * segment1;
        runUntilInput(val1);
        index = SEGMENT_SIZE * segment2;
        runUntilInput(val2);
    }
    
    public void runUntilInput(int v) {
        runInstruction(v);
        while (!done() && program[index].charAt(0) != 'i') runInstruction(v);
    }
    
    public void runInstruction(int v) {
        String line = program[index++];
        String[] fields = line.split("\\s");
        switch (fields[0]) {
        case "inp" :
            inp(v, fields[1].charAt(0));
            break;
        case "add" :
            add(fields[1].charAt(0), fields[2]);
            break;
        case "mul" :
            mul(fields[1].charAt(0), fields[2]);
            break;
        case "div" :
            div(fields[1].charAt(0), fields[2]);
            break;
        case "mod" :
            mod(fields[1].charAt(0), fields[2]);
            break;
        case "eql" :
            eql(fields[1].charAt(0), fields[2]);
            break;
        default :
            System.out.println("Invalid instruction");
        }
    }
    
    public boolean done() {
        return index == program.length; 
    }
    
    public void print() {
        System.out.println(getW());
        System.out.println(getX());
        System.out.println(getY());
        System.out.println(getZ());
        System.out.println();
    }
}
