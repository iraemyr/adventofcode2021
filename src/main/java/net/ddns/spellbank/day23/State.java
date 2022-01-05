package net.ddns.spellbank.day23;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import net.ddns.spellbank.utils.MyUtils;
import net.ddns.spellbank.utils.Point;

public class State implements Comparable<State>{

    //private CharMap map;
    private char[][] map;
    long cost;
    //private static Map<Point, List<Path>> paths;
    private static List<Point> AHOME;
    private static List<Point> BHOME;
    private static List<Point> CHOME;
    private static List<Point> DHOME;
    private static List<Point> HALLS;
    private static final Point Hall0 = new Point(1, 1);
    private static final Point Hall1 = new Point(1, 2);
    private static final Point Hall2 = new Point(1, 4);
    private static final Point Hall3 = new Point(1, 6);
    private static final Point Hall4 = new Point(1, 8);
    private static final Point Hall5 = new Point(1, 10);
    private static final Point Hall6 = new Point(1, 11);
    private static final Point A0 = new Point(5, 3);
    private static final Point A1 = new Point(4, 3);
    private static final Point A2 = new Point(3, 3);
    private static final Point A3 = new Point(2, 3);
    private static final Point B0 = new Point(5, 5);
    private static final Point B1 = new Point(4, 5);
    private static final Point B2 = new Point(3, 5);
    private static final Point B3 = new Point(2, 5);
    private static final Point C0 = new Point(5, 7);
    private static final Point C1 = new Point(4, 7);
    private static final Point C2 = new Point(3, 7);
    private static final Point C3 = new Point(2, 7);
    private static final Point D0 = new Point(5, 9);
    private static final Point D1 = new Point(4, 9);
    private static final Point D2 = new Point(3, 9);
    private static final Point D3 = new Point(2, 9);

    private static final long ACost = 1;
    private static final long BCost = 10;
    private static final long CCost = 100;
    private static final long DCost = 1000;

    public boolean isEmpty(Point p, char[][] map) {
        return map[p.x][p.y] == '.';
    }

    private static final Comparator<State> COST_COMPARATOR = Comparator.comparingLong(a -> a.cost);

    @Override
    public int compareTo(State o) {
        return COST_COMPARATOR.compare(this, o);
    }

    private static void init() {
        AHOME = new ArrayList<>();
        AHOME.add(A0); AHOME.add(A1); AHOME.add(A2); AHOME.add(A3);
        BHOME = new ArrayList<>();
        BHOME.add(B0); BHOME.add(B1); BHOME.add(B2); BHOME.add(B3);
        CHOME = new ArrayList<>();
        CHOME.add(C0); CHOME.add(C1); CHOME.add(C2); CHOME.add(C3);
        DHOME = new ArrayList<>();
        DHOME.add(D0); DHOME.add(D1); DHOME.add(D2); DHOME.add(D3);
        HALLS = new ArrayList<>();
        HALLS.add(Hall0);
        HALLS.add(Hall1);
        HALLS.add(Hall2);
        HALLS.add(Hall3);
        HALLS.add(Hall4);
        HALLS.add(Hall5);
        HALLS.add(Hall6);
    }

    public boolean isDone() {
        //System.out.println("Checking Done");
        for (Point point : AHOME) if (map[point.x][point.y] != 'A') {
            //System.out.println(point.x + ":" + point.y + " " + map[point.x][point.y]);
            return false;
        }
        //System.out.println("A OK");
        for (Point p : BHOME) if (map[p.x][p.y] != 'B') return false;
        //System.out.println("B OK");
        for (Point p : CHOME) if (map[p.x][p.y] != 'C') return false;
        //System.out.println("C OK");
        for (Point p : DHOME) if (map[p.x][p.y] != 'D') return false;
        //System.out.println("D OK");
        return true;
    }

    public boolean inHall(Point p) {
        return p.x == 1;
    }

    public char[][] getMapCopy() {
        char[][] copy = new char[map.length][map[0].length];
        for (int i = 0; i < map.length; i++)
            for (int j = 0; j < map[i].length; j++) copy[i][j] = map[i][j];
        return copy;
    }
    public char[][] getMap() {
        return map;
    }

    public State(char[][] m, long c) {
        if (AHOME == null) init();
        map = m;
        cost = c;
    }

    public long getCost(Point p, Point dest) {
        long c = switch (p.c) {
        case 'A' -> ACost;
        case 'B' -> BCost;
        case 'C' -> CCost;
        case 'D' -> DCost;
        default -> Integer.MAX_VALUE;
        };
        return Point.manhattan(p, dest) * c;
    }

    public boolean canEnterHallway(Point p) {
        //System.out.println(p.toString());
        //System.out.println("isHome: " + isHome(p));
        if (isHome(p)) return false;
        int x = p.x;
        while (x != 1) {
            x--;
            if (map[x][p.y] != '.') return false;
        }
        //System.out.println("Can enter hallway");
        return true;
    }

    public boolean isHome(Point p) {
        var home = switch(p.c) {
        case 'A' -> AHOME;
        case 'B' -> BHOME;
        case 'C' -> CHOME;
        case 'D' -> DHOME;
        default -> null;
        };
        for (Point a : home) {
            if (a.equals(p)) return true;
            if (map[a.x][a.y] != p.c) return false;
        }
        return false;
    }

    private Point canEnterRoom(Point p) {
        var home = switch(p.c) {
        case 'A' -> AHOME;
        case 'B' -> BHOME;
        case 'C' -> CHOME;
        case 'D' -> DHOME;
        default -> null;
        };
        Point d = null;
        for (Point a : home) {
            char c = map[a.x][a.y];
            if (c == '.') {
                d = a;
                break;
            }
            if (c != p.c) return null;
        }
        if (d == null) return null;
        //System.out.println("Can enter room");
        if (checkPath(p, d)) return d;
        return null;
    }

    private boolean checkPath(Point p, Point d) {
        int deltay = p.y < d.y ? 1 : -1;
        int deltax = p.x < d.x ? 1 : -1;
        int x = p.x;
        int y = p.y;
        if (p.x == 1) {
            while (y != d.y) {
                y += deltay;
                //System.out.println(map[x][y]);
                if (map[x][y] != '.') return false;
            }
            while (x != d.x) {
                x += deltax;
                //System.out.println(map[x][y]);
                if (map[x][y] != '.') return false;
            }
        } else {
            while (x != d.x) {
                x += deltax;
                //System.out.println(map[x][y]);
                if (map[x][y] != '.') return false;
            }
            while (y != d.y) {
                y += deltay;
                //System.out.println(map[x][y]);
                if (map[x][y] != '.') return false;
            }
        }
        //System.out.println("Path ok");
        return true;
    }

    public List<State> getMoves() {
        List<State> states = new ArrayList<>();
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                char c = map[i][j];
                if (Character.isUpperCase(c)) {
                    Point p = new Point(i, j, c);
                    if (inHall(p)) {
                        Point dest = canEnterRoom(p);
                        if (dest != null) {
                            State st = getMove(p, dest, false);
                            states.add(st);
                        }
                    } else {
                        if (canEnterHallway(p)) {
                            for (Point dest : HALLS) {
                                if (map[dest.x][dest.y] == '.' && checkPath(p, dest)) {
                                    State st = getMove(p, dest, false);
                                    states.add(st);
                                }
                            }
                        }
                    }
                }
            }
        }
        if (states.size() == 0) {
            //System.out.println("No moves");
            return null;
        }
        return states;
    }

    private State getMove(Point p, Point dest, boolean debug) {
        if (p == null || dest == null) {
            System.out.println("ERROR");
            System.out.flush();
            return null;
        }
        char[][] newMap = getMapCopy();
        if (debug) MyUtils.printMap(newMap);
        newMap[p.x][p.y] = '.';
        newMap[dest.x][dest.y] = p.c;
        if (debug) MyUtils.printMap(newMap);
        return new State(newMap, cost + getCost(p, dest));
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (this == obj) return true;
        if (!(obj instanceof State)) return false;
        State aState = (State) obj;
        return Arrays.deepEquals(map, aState.getMap());
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(map);
    }
}
