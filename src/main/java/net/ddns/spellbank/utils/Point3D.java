package net.ddns.spellbank.utils;

import java.util.Objects;

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
	
	public boolean equals(Object obj) {
        if (obj == null) return false;
        if (this == obj) return true;
        if (!(obj instanceof Point3D)) return false;
        Point3D a = (Point3D) obj;
        return this.x == a.x && this.y == a.y && this.z == a.z ? true : false;
    }
    
    public int hashCode() {
        return Objects.hash(x, y, z);
    }
}
