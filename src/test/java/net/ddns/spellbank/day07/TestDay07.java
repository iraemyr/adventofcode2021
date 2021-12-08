package net.ddns.spellbank.day07;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import net.ddns.spellbank.utils.InputFile;

class TestDay07 {

    @Test
    void part1() {
        String[] lines = InputFile.getLines("day07/input1");
        assertEquals(355989, Day07.part1(lines));
    }

    @Test
    void part2() {
        String[] lines = InputFile.getLines("day07/input1");
        assertEquals(102245489, Day07.part2(lines));
    }
    
    @Test
    void part1Sample() {
        String[] lines = InputFile.getLines("day07/test");
        assertEquals(37, Day07.part1(lines));
    }

    @Test
    void part2Sample() {
        String[] lines = InputFile.getLines("day07/test");
        assertEquals(168, Day07.part2(lines));
    }
}