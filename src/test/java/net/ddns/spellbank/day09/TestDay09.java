package net.ddns.spellbank.day09;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import net.ddns.spellbank.utils.InputFile;

class TestDay09 {

    @Test
    void part1() {
        String[] lines = InputFile.getLines("day09/input1");
        assertEquals(439, Day09.part1(lines));
    }

    @Test
    void part2() {
        String[] lines = InputFile.getLines("day09/input1");
        assertEquals(900900, Day09.part2(lines));
    }
    
    @Test
    void part1TestInput() {
        String[] lines = InputFile.getLines("day09/test");
        assertEquals(15, Day09.part1(lines));
    }

    @Test
    void part2TestInput() {
        String[] lines = InputFile.getLines("day09/test");
        assertEquals(1134, Day09.part2(lines));
    }
}