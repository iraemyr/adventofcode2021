package net.ddns.spellbank.day20;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import net.ddns.spellbank.utils.InputFile;

class TestDay20 {

    @Test
    void part1() {
        String[] lines = InputFile.getLines("day20/input1");
        assertEquals(5597, Day20.part1(lines));
    }

    @Test
    void part2() {
        String[] lines = InputFile.getLines("day20/input1");
        assertEquals(18723, Day20.part2(lines));
    }
}