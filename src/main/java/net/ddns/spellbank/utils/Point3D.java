package net.ddns.spellbank.utils;

public class Point3D {
	public int x;
	public int y;
	public int z;
	public char c;
	public Point3D(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Point3D(int x, int y, int z, char c) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.c = c;
	}
}
