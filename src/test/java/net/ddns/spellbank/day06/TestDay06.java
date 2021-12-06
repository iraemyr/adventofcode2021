package net.ddns.spellbank.day06;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import net.ddns.spellbank.utils.InputFile;

class TestDay06 {

    @Test
    void part1() {
        String[] lines = InputFile.getLines("day06/input1");
        assertEquals(385391L, Day06.part1(lines));
    }

    @Test
    void part2() {
        String[] lines = InputFile.getLines("day06/input1");
        assertEquals(1728611055389L, Day06.part2(lines));
    }
}