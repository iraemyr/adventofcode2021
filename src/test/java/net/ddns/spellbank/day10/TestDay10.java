package net.ddns.spellbank.day10;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import net.ddns.spellbank.utils.InputFile;

class TestDay10 {

    @Test
    void part1() {
        String[] lines = InputFile.getLines("day10/input1");
        assertEquals(323691, Day10.part1(lines));
    }

    @Test
    void part2() {
        String[] lines = InputFile.getLines("day10/input1");
        assertEquals(2858785164L, Day10.part2(lines));
    }
    
    @Test
    void part1TestInput() {
        String[] lines = InputFile.getLines("day10/test");
        assertEquals(26397, Day10.part1(lines));
    }

    @Test
    void part2TestInput() {
        String[] lines = InputFile.getLines("day10/test");
        assertEquals(288957, Day10.part2(lines));
    }
}