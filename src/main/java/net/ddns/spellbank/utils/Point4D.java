package net.ddns.spellbank.utils;

public class Point4D {
	public int w;
	public int x;
	public int y;
	public int z;
	public char c;
	public Point4D(int w, int x, int y, int z) {
		this.w = w;
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Point4D(int w, int x, int y, int z, char c) {
		this.w = w;
		this.x = x;
		this.y = y;
		this.z = z;
		this.c = c;
	}
}
