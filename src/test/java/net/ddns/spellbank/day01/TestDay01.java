package net.ddns.spellbank.day01;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import net.ddns.spellbank.utils.InputFile;

class TestDay01 {

    @Test
    void part1() {
        String[] lines = InputFile.getLines("day01/input1");
        assertEquals(1400, Day01.part1(lines));
    }

    @Test
    void part2() {
        String[] lines = InputFile.getLines("day01/input1");
        assertEquals(1429, Day01.part2(lines));
    }
}