package net.ddns.spellbank.utils;

public class Point {
	public int x;
	public int y;
	public char c;
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Point(int x, int y, char c) {
		this.x = x;
		this.y = y;
		this.c = c;
	}
	
	public String toString() {
		return x + ":" + y;
	}
	
	public static Point getPoint(String s) {
	    String[] fields = s.split(":");
	    return new Point(Integer.parseInt(fields[0]), Integer.parseInt(fields[1]));
	}
}
