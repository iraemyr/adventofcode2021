package net.ddns.spellbank.day22;

public class Cube {
    
    long xlow, xhigh, ylow, yhigh, zlow, zhigh;
    long on;
    
    public Cube(long xlow, long xhigh, long ylow, long yhigh, long zlow, long zhigh, boolean on) {
        this.xlow = xlow;
        this.xhigh = xhigh;
        this.ylow = ylow;
        this.yhigh = yhigh;
        this.zlow = zlow;
        this.zhigh = zhigh;
        this.on = on ? 1 : -1;
    }
    
    public Cube(long xlow, long xhigh, long ylow, long yhigh, long zlow, long zhigh, long on) {
        this.xlow = xlow;
        this.xhigh = xhigh;
        this.ylow = ylow;
        this.yhigh = yhigh;
        this.zlow = zlow;
        this.zhigh = zhigh;
        this.on = on;
    }
    
    public long numOn() {
        long s = (xhigh - xlow + 1) * (yhigh - ylow + 1) * (zhigh - zlow + 1) * on;
        return s;
    }
    
    public void print() {
        System.out.println(xlow + ":" + xhigh + "," + ylow + ":" + yhigh + "," +
                           zlow + ":" + zhigh + "," + on);
    }
}
