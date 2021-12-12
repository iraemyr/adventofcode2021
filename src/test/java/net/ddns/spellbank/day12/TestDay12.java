package net.ddns.spellbank.day12;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import net.ddns.spellbank.utils.InputFile;

class TestDay12 {

    @Test
    void part1() {
        String[] lines = InputFile.getLines("day12/input1");
        assertEquals(3450, Day12.part1(lines));
    }

    @Test
    void part2() {
        String[] lines = InputFile.getLines("day12/input1");
        assertEquals(96528, Day12.part2(lines));
    }
    
    @Test
    void part1TestInput() {
        String[] lines = InputFile.getLines("day12/test");
        assertEquals(10, Day12.part1(lines));
    }

    @Test
    void part2TestInput() {
        String[] lines = InputFile.getLines("day12/test");
        assertEquals(36, Day12.part2(lines));
    }
}