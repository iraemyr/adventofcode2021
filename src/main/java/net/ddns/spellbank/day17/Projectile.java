package net.ddns.spellbank.day17;

public class Projectile {
    int xVel;
    int yVel;
    int xPos = 0;
    int yPos = 0;
    public int maxHeight = Integer.MIN_VALUE;
    static private int xMin;
    static private int xMax;
    static private int yMin;
    static private int yMax;
    
    public Projectile(int xVel, int yVel) {
        this.xVel = xVel;
        this.yVel = yVel;
    }
    
    public static void setBounds(int x1, int x2, int y1, int y2) {
        xMin = x1;
        xMax = x2;
        yMin = y1;
        yMax = y2;
    }
    
    public boolean inTarget() {
        return xPos >= xMin && xPos <= xMax &&
               yPos >= yMin && yPos <= yMax;
    }
    
    public void doStep() {
        xPos += xVel;
        yPos += yVel;
        maxHeight = Math.max(yPos, maxHeight);
        if (xVel > 0) xVel--;
        else if (xVel < 0) xVel++;
        yVel--;
    }
    
    public boolean canHitTarget() {
        if (xVel == 0)
            if (xPos < xMin || xPos > xMax) return false;
        if (yVel < 0 && yPos < yMin) return false;
        return true;
    }

}
