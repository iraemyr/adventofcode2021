package net.ddns.spellbank.day14;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import net.ddns.spellbank.utils.InputFile;

class TestDay14 {

    @Test
    void part1() {
        String[] lines = InputFile.getLines("day14/input1");
        assertEquals(3058, Day14.part1(lines));
    }

    @Test
    void part2() {
        String[] lines = InputFile.getLines("day14/input1");
        assertEquals(3447389044530L, Day14.part2(lines));
    }
    
    @Test
    void part1TestInput() {
        String[] lines = InputFile.getLines("day14/test");
        assertEquals(1588, Day14.part1(lines));
    }

    @Test
    void part2TestInput() {
        String[] lines = InputFile.getLines("day14/test");
        assertEquals(2188189693529L, Day14.part2(lines));
    }
}