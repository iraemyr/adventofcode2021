package net.ddns.spellbank.day16;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import net.ddns.spellbank.utils.InputFile;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TestDay16 {
    private Packet p;
    
    @BeforeAll
    public void init() {
        String[] lines = InputFile.getLines("day16/input1");
        var par = PacketParser.getParserFromHex(lines[0]);
        p = par.parsePacket();
    }

    @Test
    void part1() {
        //String[] lines = InputFile.getLines("day16/input1");
        assertEquals(940, Day16.part1(p));
    }

    @Test
    void part2() {
        //String[] lines = InputFile.getLines("day16/input1");
        assertEquals(13476220616073L, Day16.part2(p));
    }
}