package net.ddns.spellbank.day16;

import java.util.ArrayList;
import java.util.List;

public class Op {
    int type;
    Long val;
    List<Op> children;
    
    public Op() {
        val = null;
        children = new ArrayList<>();
    }
    
    public Op(long v) {
        val = v;
    }
    
    public void setType(int t) {
        type = t;
    }
    
    public void setVal(long v) {
        val = v;
    }
    
    public void addChild(Op c) {
        children.add(c);
    }
    
    public void addChildren(List<Op> ch) {
        children.addAll(ch);
    }
    
    public Long getVal() {
        if (val == null) {
            val = switch (type) {
            case 0 -> add();
            case 1 -> product();
            case 2 -> min();
            case 3 -> max();
            case 5 -> greater();
            case 6 -> less();
            case 7 -> equal();
            default -> Long.MIN_VALUE;
            };
        }
        //System.out.println(val);
        return val;
    }
    
    private long add() {
        long sum = 0;
        for (Op o : children) sum += o.getVal();
        //System.out.print("+");
        //for (Op o : children) System.out.print(" " + o.getVal());
        //System.out.println();
        //System.out.println(sum);
        return sum;
    }
    
    private long product() {
        long prod = 1;
        for (Op o : children) prod *= o.getVal();
        //System.out.print("*");
        //for (Op o : children) System.out.print(" " + o.getVal());
        //System.out.println();
        //System.out.println(prod);
        return prod;
    }
    
    private long min() {
        long min = Long.MAX_VALUE;
        for (Op o : children) min = Math.min(min, o.getVal());
        //System.out.print("min");
        //for (Op o : children) System.out.print(" " + o.getVal());
        //System.out.println();
        //System.out.println(min);
        return min;
    }
    
    private long max() {
        long max = Long.MIN_VALUE;
        for (Op o : children) max = Math.max(max, o.getVal());
        /*System.out.print("max ");
        for (Op o : children) {
            if (o.getVal() == null) System.out.print(" Uhoh");
            else System.out.print(" " + o.getVal());
        }*/
        //System.out.println();
        //System.out.println(max);
        return max;
    }
    
    private long greater() {
        if (children.size() != 2) System.out.println("Error");
        return children.get(0).getVal() > children.get(1).getVal() ? 1 : 0;
        //long v = children.get(0).getVal() > children.get(1).getVal() ? 1L : 0L;
        //System.out.println(children.get(0).getVal() + " > " + children.get(1).getVal());
        //System.out.println(v);
        //return v;
    }
    
    private long less() {
        if (children.size() != 2) System.out.println("Error");
        return children.get(0).getVal() < children.get(1).getVal() ? 1 : 0;
        /*long v = children.get(0).getVal() < children.get(1).getVal() ? 1L : 0L;
        //System.out.println(children.get(0).getVal() + " < " + children.get(1).getVal());
        //System.out.println(v);
        return v;*/
    }
    
    private long equal() {
        if (children.size() != 2) System.out.println("Error");
        //return children.get(0).getVal() == children.get(1).getVal() ? 1 : 0;
        long v1 = children.get(0).getVal();
        long v2 = children.get(1).getVal();
        long v = v1 == v2 ? 1L : 0L;
        //long v = children.get(0).getVal() == children.get(1).getVal() ? 1L : 0L;
        //System.out.println(children.get(0).getVal() + " = " + children.get(1).getVal());
        //System.out.println(v);
        return v;
    }

}
