package net.ddns.spellbank.day16;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PacketParser {
    
    private int index;
    private String input;
    private static Map<Character, String> hexToBin;
    
    public PacketParser(String in) {
        index = 0;
        input = in;
    }
    
    public static PacketParser getParserFromHex(String hex) {
        var map = getMap();
        StringBuilder sb = new StringBuilder();
        for (char c : hex.toCharArray()) sb.append(map.get(c));
        return new PacketParser(sb.toString());
    }
    
    public Packet parsePacket() {
        int version = Integer.parseInt(input.substring(index, index + 3), 2);
        index += 3;
        int type = Integer.parseInt(input.substring(index, index + 3), 2);
        index += 3;
        Packet p = new Packet(version, type);
        if (type == 4) p.setVal(parseLiteral());
        else {
            if (input.charAt(index++) == '0') {
                int len = Integer.parseInt(input.substring(index, index + 15), 2);
                index += 15;
                PacketParser par = new PacketParser(input.substring(index, index + len));
                index += len;
                p.addChildren(par.parsePackets());
            } else {
                int numPackets = Integer.parseInt(input.substring(index, index + 11), 2);
                index += 11;
                for (int i = 0; i < numPackets; i++) {
                    p.addChild(parsePacket());
                }
            }
        }
        return p;
    }
    
    private List<Packet> parsePackets() {
        List<Packet> children = new ArrayList<>();
        while (index < input.length()) {
            children.add(parsePacket());
        }
        return children;
    }
    
    private Long parseLiteral() {
        StringBuilder sb = new StringBuilder();
        boolean done = false;
        while (!done && index < input.length()) {
            if (input.charAt(index++) == '0') done = true;
            sb.append(input.substring(index, index + 4));
            index += 4;
        }
        return Long.parseLong(sb.toString(), 2);
    }
    
    private static Map<Character, String> getMap() {
        if (hexToBin == null) { 
            hexToBin = new HashMap<>();
            hexToBin.put('0', "0000");
            hexToBin.put('1', "0001");
            hexToBin.put('2', "0010");
            hexToBin.put('3', "0011");
            hexToBin.put('4', "0100");
            hexToBin.put('5', "0101");
            hexToBin.put('6', "0110");
            hexToBin.put('7', "0111");
            hexToBin.put('8', "1000");
            hexToBin.put('9', "1001");
            hexToBin.put('A', "1010");
            hexToBin.put('B', "1011");
            hexToBin.put('C', "1100");
            hexToBin.put('D', "1101");
            hexToBin.put('E', "1110");
            hexToBin.put('F', "1111");
        }   
        return hexToBin;
    }
}
