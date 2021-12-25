package net.ddns.spellbank.day25;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import net.ddns.spellbank.utils.InputFile;

class TestDay25 {

    @Test
    void part1() {
        String[] lines = InputFile.getLines("day25/input1");
        assertEquals(518, Day25.part1(lines));
    }

    @Test
    void part1TestInput() {
        String[] lines = InputFile.getLines("day25/test");
        assertEquals(58, Day25.part1(lines));
    }
}