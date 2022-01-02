package net.ddns.spellbank.day23;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CharPoint {
	public int x;
	public int y;
	public char c;
	public CharPoint(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public CharPoint(int x, int y, char c) {
		this.x = x;
		this.y = y;
		this.c = c;
	}
	
	public String toString() {
		return x + ":" + y;
	}
	
	public static CharPoint getPoint(String s) {
	    String[] fields = s.split(":");
	    return new CharPoint(Integer.parseInt(fields[0]), Integer.parseInt(fields[1]));
	}
	
	public boolean equals(Object obj) {
	    if (obj == null) return false;
	    if (this == obj) return true;
	    if (!(obj instanceof CharPoint)) return false;
	    CharPoint a = (CharPoint) obj;
	    return this.x == a.x && this.y == a.y && this.c == a.c ? true : false;
	}
	
	public int hashCode() {
	    return Objects.hash(x, y, c);
	}
	
	public static int manhattan(CharPoint p, CharPoint dest) {
	    return Math.abs(p.x - dest.x) + Math.abs(p.y - dest.y);
	}
	
	public List<CharPoint> getNeighbors() {
	    List<CharPoint> neighbors = new ArrayList<>();
	    for (int i = -1; i < 2; i++) {
	        for (int j = -1; j < 2; j++) {
	            neighbors.add(new CharPoint(x + i, y + j));
	        }
	    }
	    return neighbors;
	}
}
