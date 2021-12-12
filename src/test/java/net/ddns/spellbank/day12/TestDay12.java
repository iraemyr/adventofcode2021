package net.ddns.spellbank.day12;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import net.ddns.spellbank.utils.InputFile;

class TestDay12 {

    @Test
    void part1() {
        String[] lines = InputFile.getLines("day12/input1");
        assertEquals(0, Day12.part1(lines));
    }

    @Test
    void part2() {
        String[] lines = InputFile.getLines("day12/input1");
        assertEquals(0, Day12.part2(lines));
    }
}