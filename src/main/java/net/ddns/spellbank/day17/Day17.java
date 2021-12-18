package net.ddns.spellbank.day17;

public class Day17 {

    public static void main(String[] args) {
        System.out.println(part1(14, 50, -267, -225)); // 35511
        System.out.println(part2(14, 50, -267, -225)); // 3282
    }

    public static long part1(int x1, int x2, int y1, int y2) {
        Projectile.setBounds(x1, x2, y1, y2);
        int maxHeight = Integer.MIN_VALUE;
        for (int x = 4; x < 52; x++) {
            for (int y = -268; y < 300; y++) {
                Projectile p = new Projectile(x, y);
                for (int i = 0; i < 1000; i++) {
                    p.doStep();
                    if (p.inTarget()) {
                        maxHeight = Math.max(maxHeight, p.maxHeight);
                    }
                }
            }
        }
        return maxHeight;
    }

    public static long part2(int x1, int x2, int y1, int y2) {
        Projectile.setBounds(x1, x2, y1, y2);
        int count = 0;
        for (int x = 4; x < 52; x++) {
            for (int y = -268; y < 300; y++) {
                Projectile p = new Projectile(x, y);
                while (p.canHitTarget()) {
                    p.doStep();
                    if (p.inTarget()) {
                        count++;
                        break;
                    }
                }
            }
        }
        return count;
    }

}