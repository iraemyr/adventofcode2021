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
		return "x=" + x + "y=" + y;
	}
}
