package net.ddns.spellbank.day13;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import net.ddns.spellbank.utils.InputFile;

class TestDay13 {

    @Test
    void part1() {
        String[] lines = InputFile.getLines("day13/input1");
        assertEquals(638, Day13.part1(lines));
    }

    @Test
    void part2() {
        String[] lines = InputFile.getLines("day13/input1");
        assertEquals(0, Day13.part2(lines));
    }
}