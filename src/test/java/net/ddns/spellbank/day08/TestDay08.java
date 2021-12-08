package net.ddns.spellbank.day08;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import net.ddns.spellbank.utils.InputFile;

class TestDay08 {

    @Test
    void part1() {
        String[] lines = InputFile.getLines("day08/input1");
        assertEquals(488, Day08.part1(lines));
    }

    @Test
    void part2() {
        String[] lines = InputFile.getLines("day08/input1");
        assertEquals(1040429, Day08.part2(lines));
    }
}