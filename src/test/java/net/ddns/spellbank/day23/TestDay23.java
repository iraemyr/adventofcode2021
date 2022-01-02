package net.ddns.spellbank.day23;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import net.ddns.spellbank.utils.InputFile;

class TestDay23 {

    @Test
    void part1TestInput() {
        String[] lines = InputFile.getLines("day23/done");
        assertEquals(3223, Day23.part1(lines));
    }
    
    @Test
    void part1() {
        String[] lines = InputFile.getLines("day23/input2");
        assertEquals(15109, Day23.part1(lines));
    }

    @Test
    void part2() {
        String[] lines = InputFile.getLines("day23/input1");
        assertEquals(53751, Day23.part1(lines));
    }
}