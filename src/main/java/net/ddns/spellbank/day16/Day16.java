package net.ddns.spellbank.day16;

import net.ddns.spellbank.utils.InputFile;

public class Day16 {
    public static void main(String[] args) {
        String file = "day16/input1";
        String[] lines = InputFile.getLines(file);
        PacketParser p = PacketParser.getParserFromHex(lines[0]);
        Packet pack = p.parsePacket();

        System.out.println(part1(pack)); // 940
        System.out.println(part2(pack)); // 13476220616073
    }

    public static long part1(Packet p) {
        return p.versionSum();
    }

    public static long part2(Packet p) {
        return p.getVal();
    }
}