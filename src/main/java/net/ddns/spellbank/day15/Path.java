package net.ddns.spellbank.day15;

import java.util.Comparator;

import net.ddns.spellbank.utils.Point;

public class Path implements Comparable<Path> {
    Point at;
    int cost;
    
    public Path(Point p, int c) {
        at = p;
        cost = c;
    }
    
    public Path(Point p) {
        at = p;
        cost = Integer.MAX_VALUE;
    }
    
    public int cost() {
        return cost;
    }
    
    public Point at() {
        return at;
    }
    
    private static final Comparator<Path> COST_COMPARATOR = Comparator.comparingInt(a -> a.cost);

    @Override
    public int compareTo(Path o) {
        return COST_COMPARATOR.compare(this, o);
    }
    
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (this == obj) return true;
        if (!(obj instanceof Path)) return false;
        Path a = (Path) obj;
        return this.at.x == a.at.x && this.at.y == a.at.y ? true : false;
    }
    
    public int hashCode() {
        return at.hashCode();
    }
}
