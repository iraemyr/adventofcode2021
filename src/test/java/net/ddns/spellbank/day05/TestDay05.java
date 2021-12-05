package net.ddns.spellbank.day05;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import net.ddns.spellbank.utils.InputFile;

class TestDay05 {

    @Test
    void part1() {
        String[] lines = InputFile.getLines("day05/input1");
        assertEquals(7380, Day05.part1(lines));
    }

    @Test
    void part2() {
        String[] lines = InputFile.getLines("day05/input1");
        assertEquals(21373, Day05.part2(lines));
    }
}