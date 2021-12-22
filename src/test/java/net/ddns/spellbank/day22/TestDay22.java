package net.ddns.spellbank.day22;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import net.ddns.spellbank.utils.InputFile;

class TestDay22 {

    @Test
    void part1() {
        String[] lines = InputFile.getLines("day22/input1");
        assertEquals(611176, Day22.part1(lines));
    }

    @Test
    void part2() {
        String[] lines = InputFile.getLines("day22/input1");
        assertEquals(1201259791805392L, Day22.part2(lines));
    }
    
    @Test
    void part1TestInput() {
        String[] lines = InputFile.getLines("day22/test");
        assertEquals(474140, Day22.part1(lines));
    }

    @Test
    void part2TestInput() {
        String[] lines = InputFile.getLines("day22/test");
        assertEquals(2758514936282235L, Day22.part2(lines));
    }
}