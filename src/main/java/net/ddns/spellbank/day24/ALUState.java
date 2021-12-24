package net.ddns.spellbank.day24;

import java.util.Objects;

public class ALUState {
    int counter;
    int w;
    int x;
    int y;
    int z;
    
    public ALUState(int pc, int wval, int xval, int yval, int zval) {
        counter = pc;
        w = wval;
        x = xval;
        y = yval;
        z = zval;
    }
    
    public static ALUState getState(ALU alu) {
        return new ALUState(alu.index, alu.getW(), alu.getX(), alu.getY(), alu.getZ());
    }
    
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (this == obj) return true;
        if (!(obj instanceof ALUState)) return false;
        ALUState a = (ALUState) obj;
        return this.counter == a.counter && this.w == a.w && this.x == a.x &&
                               this.y == a.y && this.z == a.z ? true : false;
    }
    
    public int hashCode() {
        return Objects.hash(counter,w, x, y, z);
    } 
}
