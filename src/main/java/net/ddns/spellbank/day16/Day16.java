package net.ddns.spellbank.day16;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.ddns.spellbank.utils.InputFile;

public class Day16 {
    private static String LINE;
    
    private static int VERSION = 0;

    public static void main(String[] args) {
        String file = "day16/input1";
        String[] lines = InputFile.getLines(file);

        System.out.println(part1(lines)); // 940
        System.out.println(part2(lines)); // 13476220616073
    }

    public static long part1(String[] lines) {
        String line = lines[0];
        LINE = convert(line);
        parsePacket();
        return VERSION;
    }

    public static long part2(String[] lines) {
        String line = lines[0];
        LINE = convert(line);
        Op op = parsePacket();
        return op.getVal();
    }
    
    
    private static String convert(String line) {
        StringBuilder sb = new StringBuilder();
        Map<Character, String> map = getMap();
        for (char c : line.toCharArray()) sb.append(map.get(c));
        return sb.toString();
    }
    
    private static Op parsePacket() {
        //System.out.println(LINE);
        VERSION += Integer.parseInt(LINE.substring(0, 3), 2);
        LINE = LINE.substring(3);
        int type = Integer.parseInt(LINE.substring(0, 3), 2);
        LINE = LINE.substring(3);
        //System.out.println(VERSION + ":" + type);
        if (type == 4) return parseLiteral();
        else return parseOperator(type);
    }
    
    private static String parsePacket(String line, Op op) {
        /* System.out.println("Parsing fixed packet");
        System.out.flush(); */
        VERSION += Integer.parseInt(line.substring(0, 3), 2);
        line = line.substring(3);
        int type = Integer.parseInt(line.substring(0, 3), 2);
        line = line.substring(3);
        //System.out.println(version + ":" + type);
        if (type == 4) line = parseLiteralFixed(line, op);
        else line = parseOperatorFixed(type, line, op);
        return line;
    }
    
    private static Op parseLiteral() {
        /*System.out.println("parsing Literal");
        System.out.flush();*/
        StringBuilder sb = new StringBuilder();
        boolean done = false;
        while (!done && !LINE.isEmpty()) {
            if (LINE.charAt(0) == '0') done = true;
            LINE = LINE.substring(1);
            sb.append(LINE.substring(0, 4));
            LINE = LINE.substring(4);
        }
        return new Op(Long.parseLong(sb.toString(), 2));
    }
    
    private static Op parseOperator(int type) {
        //System.out.println("Parsing Operator : " + type);
        //System.out.flush();
        Op op = new Op();
        if (LINE.charAt(0) == '0') {
            int len = Integer.parseInt(LINE.substring(1, 16), 2);
            LINE = LINE.substring(16);
            op.addChildren(parseFixedPackets(LINE.substring(0, len)));
            LINE= LINE.substring(len);
        } else {
            int numPackets = Integer.parseInt(LINE.substring(1, 12), 2);
            LINE = LINE.substring(12);
            for (int i = 0; i < numPackets; i++) {
                op.addChild(parsePacket());
            }
        }
        op.setType(type); 
        return op;
    }
    
    private static List<Op> parseFixedPackets(String line) {
        //System.out.println("Parsing fixed packet");
        //System.out.flush();
        List<Op> children= new ArrayList<>();
        while (!line.isEmpty()) {
            VERSION += Integer.parseInt(line.substring(0, 3), 2);
            line = line.substring(3);
            int type = Integer.parseInt(line.substring(0, 3), 2);
            line = line.substring(3);
            //System.out.println(version + ":" + type);
            Op op = new Op();
            if (type == 4) line = parseLiteralFixed(line, op);
            else line = parseOperatorFixed(type, line, op);
            children.add(op);
        }
        return children;
    }
    
    private static String parseLiteralFixed(String line, Op op) {
        //System.out.println("Parsing fixed literal");
        //System.out.flush();
        StringBuilder sb = new StringBuilder();
        boolean done = false;
        while (!done) {
            if (line.charAt(0) == '0') done = true;
            line = line.substring(1);
            sb.append(line.substring(0, 4));
            line = line.substring(4);
        }
        op.setVal(Long.parseLong(sb.toString(), 2));
        return line;
    }
    
    private static String parseOperatorFixed(int type, String line, Op op) {
        /*System.out.println("Parsing fixed operator");
        System.out.println(line);
        System.out.flush();*/
        if (line.charAt(0) == '0') {
            int len = Integer.parseInt(line.substring(1, 16), 2);
            line = line.substring(16);
            op.addChildren(parseFixedPackets(line.substring(0, len)));
            line = line.substring(len);
        } else {
            int numPackets = Integer.parseInt(line.substring(1, 12), 2);
            line = line.substring(12);
            for (int i = 0; i < numPackets; i++) {
                Op o = new Op();
                line = parsePacket(line, o);
                op.addChild(o);
            }
        }
        op.setType(type);
        return line;
    }
    
    private static Map<Character, String> getMap() {
        Map<Character, String> conversion = new HashMap<>();
        conversion.put('0', "0000");
        conversion.put('1', "0001");
        conversion.put('2', "0010");
        conversion.put('3', "0011");
        conversion.put('4', "0100");
        conversion.put('5', "0101");
        conversion.put('6', "0110");
        conversion.put('7', "0111");
        conversion.put('8', "1000");
        conversion.put('9', "1001");
        conversion.put('A', "1010");
        conversion.put('B', "1011");
        conversion.put('C', "1100");
        conversion.put('D', "1101");
        conversion.put('E', "1110");
        conversion.put('F', "1111");
        
        return conversion;
    }

}