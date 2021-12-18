package net.ddns.spellbank.day18;

public class Pair {
    Integer leftVal;
    Integer rightVal;
    Pair left;
    Pair right;
    boolean exploded;
    
    public Pair() {
    }
    
    public Pair(int l, int r) {
        leftVal = l;
        rightVal = r;
    }
    
    public void setLeft(Pair p) {
        left = p;
        leftVal = null;
    }
    
    public void setRight(Pair p) {
        right = p;
        rightVal = null;
    }
    
    public void setLeft(int v) {
        leftVal = v;
        left = null;
    }
    
    public void setRight(int v) {
        rightVal = v;
        right = null;
    }
    
    private boolean isComplete() {
        return leftVal != null && rightVal != null ? true : false;
    }
    
    private boolean isEmpty() {
        return leftVal == null && rightVal == null ? true : false;
    }
    
    public void print() {
        System.out.print("[");
        if (leftVal != null) System.out.print(leftVal);
        else left.print();
        System.out.print(",");
        if (rightVal != null) System.out.print(rightVal);
        else right.print();
        System.out.print("]");
    }
    
    public Pair add(Pair p) {
        Pair pa = new Pair();
        pa.left = this;
        pa.right = p;
        pa.reduce();
        return pa;
    }
    
    public void reduce() {
        while (true) {
            if (explode(0) != null) continue;
            if (split() == true) continue;
            break;
        }
    }
    
    private Pair explode(int depth) {
        //Check Explode
        if (depth >= 4 && isComplete()) {
            exploded = true;
            return this;
        }
        Pair p;
        if (left != null) {
            p = left.explode(depth + 1);
            if (p != null) {
                if (p.isEmpty()) return p;
                if (p.exploded) {
                    leftVal = 0;
                    left = null;
                    p.exploded = false;
                    if (rightVal != null) {
                        rightVal += p.rightVal;
                        p.rightVal = null;
                    } else {
                        if (right.fillRight(p.rightVal)) {
                            p.rightVal = null;
                        }
                    }
                    return p;
                } else {
                    if (p.rightVal != null && rightVal != null) {
                        rightVal += p.rightVal;
                        p.rightVal = null;
                        return p;
                    }
                    if (p.rightVal != null && right != null && right.fillRight(p.rightVal)) {
                        p.rightVal = null;
                        return p;
                    }
                }
                return p;
            }
        }
        if (right != null) {
            p = right.explode(depth + 1);
            if (p != null) {
                if (p.isEmpty()) return p;
                if (p.exploded) {
                    rightVal = 0;
                    right = null;
                    p.exploded = false;
                    if (leftVal != null) {
                        leftVal += p.leftVal;
                        p.leftVal = null;
                    } else {
                        if (left.fillLeft(p.leftVal)) {
                            p.leftVal = null;
                        }
                    }
                    return p;
                } else {
                    if (p.leftVal != null && leftVal != null) {
                        leftVal += p.leftVal;
                        p.leftVal = null;
                        return p;
                    }
                    if (p.leftVal != null && left != null && left.fillLeft(p.leftVal)) {
                        p.leftVal = null;
                        return p;
                    }
                }
                return p;
            }
        }
        return null;
    }
    
    private boolean split() {
        if (leftVal != null && leftVal > 9) {
            int lef = leftVal / 2;
            int rght = lef + (leftVal % 2);
            left = new Pair(lef, rght);
            leftVal = null;
            return true;
        }
        
        if (left != null && left.split()) return true;
        
        if (rightVal != null && rightVal > 9) {
            int lef = rightVal / 2;
            int rght = lef + (rightVal % 2);
            right = new Pair(lef, rght);
            rightVal = null;
            return true;
        }
        
        if (right != null && right.split()) return true;
        return false;      
    }
    
    private boolean fillLeft(int v) {
        if (rightVal != null) {
            rightVal += v;
            return true;
        }
        
        if (right.fillLeft(v)) return true;
        
        if (leftVal != null) {
            leftVal += v;
            return true;
        }
        
        return left.fillLeft(v);
    }
    
    private boolean fillRight(int v) {
        if (leftVal != null) {
            leftVal += v;
            return true;
        }
        
        if (left.fillRight(v)) return true;
        
        if (rightVal != null) {
            rightVal += v;
            return true;
        }
        
        return right.fillRight(v);
    }
    
    public long mag() {
        long l = leftVal != null ? leftVal : left.mag();
        long r = rightVal != null ? rightVal : right.mag();
        return 3 * l + 2 * r;
    }

}
