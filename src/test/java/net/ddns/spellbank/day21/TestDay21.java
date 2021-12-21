package net.ddns.spellbank.day21;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class TestDay21 {

    @Test
    void part1() {
        assertEquals(913560, Day21.part1(4, 1));
    }

    @Test
    void part2() {
        assertEquals(110271560863819L, Day21.part2(4, 1));
    }
}