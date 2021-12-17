package net.ddns.spellbank.day17;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import net.ddns.spellbank.utils.InputFile;

class TestDay17 {

    @Test
    void part1() {
        String[] lines = InputFile.getLines("day17/input1");
        assertEquals(35511, Day17.part1(lines));
    }

    @Test
    void part2() {
        String[] lines = InputFile.getLines("day17/input1");
        assertEquals(3282, Day17.part2(lines));
    }
}