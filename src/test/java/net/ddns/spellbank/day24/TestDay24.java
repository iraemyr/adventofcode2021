package net.ddns.spellbank.day24;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import net.ddns.spellbank.utils.InputFile;

class TestDay24 {

    @Test
    void part1() {
        String[] lines = InputFile.getLines("day24/input1");
        assertEquals(99999795919456L, Day24.part1(lines));
    }

    @Test
    void part2() {
        String[] lines = InputFile.getLines("day24/input1");
        assertEquals(45311191516111L, Day24.part2(lines));
    }
}