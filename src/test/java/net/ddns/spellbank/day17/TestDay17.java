package net.ddns.spellbank.day17;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class TestDay17 {

    @Test
    void part1() {
        assertEquals(35511, Day17.part1(14, 50, -267, -225));
    }

    @Test
    void part2() {
        assertEquals(3282, Day17.part2(14, 50, -267, -225));
    }
    
    @Test
    void part1TestInput() {
        assertEquals(45, Day17.part1(20, 30, -10, -5));
    }

    @Test
    void part2TestInput() {
        assertEquals(112, Day17.part2(20, 30, -10, -5));
    }
}