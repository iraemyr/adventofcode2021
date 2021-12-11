package net.ddns.spellbank.day11;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import net.ddns.spellbank.utils.InputFile;

class TestDay11 {

    @Test
    void part1() {
        String[] lines = InputFile.getLines("day11/input1");
        assertEquals(1603, Day11.part1(lines));
    }

    @Test
    void part2() {
        String[] lines = InputFile.getLines("day11/input1");
        assertEquals(222, Day11.part2(lines));
    }
    
    @Test
    void part1TestInput() {
        String[] lines = InputFile.getLines("day11/test");
        assertEquals(1656, Day11.part1(lines));
    }

    @Test
    void part2TestInput() {
        String[] lines = InputFile.getLines("day11/test");
        assertEquals(195, Day11.part2(lines));
    }
}