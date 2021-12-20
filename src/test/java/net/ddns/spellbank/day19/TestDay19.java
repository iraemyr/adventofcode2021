package net.ddns.spellbank.day19;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import net.ddns.spellbank.utils.InputFile;
import net.ddns.spellbank.utils.Point3D;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TestDay19 {
    private List<Point3D> locs = new ArrayList<>();
    private Set<Point3D> beacons = new HashSet<>();

    @BeforeAll
    public void init() {
        String[] lines = InputFile.getLines("day19/input1");
        Day19.solve(lines, beacons, locs);
    }
    
    @Test
    void part1() {
        assertEquals(372, Day19.part1(beacons));
    }

    @Test
    void part2() {
        assertEquals(12241, Day19.part2(locs));
    }
}