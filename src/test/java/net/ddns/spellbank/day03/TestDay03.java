package net.ddns.spellbank.day03;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import net.ddns.spellbank.utils.InputFile;

class TestDay03 {

    @Test
    void part1() {
        String[] lines = InputFile.getLines("day03/input1");
        assertEquals(1071734, Day03.part1(lines));
    }

    @Test
    void part2() {
        String[] lines = InputFile.getLines("day03/input1");
        assertEquals(6124992, Day03.part2(lines));
    }
    
    @Test
    void part1TestInput() {
        String[] lines = InputFile.getLines("day03/test");
        assertEquals(198, Day03.part1(lines));
    }
    
    @Test
    void part2TestInput() {
        String[] lines = InputFile.getLines("day03/test");
        assertEquals(230, Day03.part2(lines));
    }
}