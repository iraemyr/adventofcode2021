package net.ddns.spellbank.day04;

import java.util.ArrayList;
import java.util.List;

import net.ddns.spellbank.utils.InputFile;

public class Day04 {

    public static void main(String[] args) {
        String file = "day04/input1";
        String[] lines = InputFile.getLines(file);

        System.out.println(part1(lines)); // 34506
        System.out.println(part2(lines)); // 7686
    }

    public static int part1(String[] lines) {
        String nums = lines[0];
        int line = 2;
        List<int[][]> boards = new ArrayList<>();
        while (line < lines.length) {
            int[][] board = new int[5][5];
            for (int i = 0; i < 5; i++) {
                String s = lines[line + i];
                String[] fields = s.trim().split("\\s+");
                for (int j = 0; j < 5; j++) {
                    board[i][j] = Integer.parseInt(fields[j]);
                }
            }
            boards.add(board);
            line += 6;
        }
        
        String[] fields = nums.split(",");
        int winner = -1;
        int call = -1;
        for (String field : fields) {
            int val = Integer.parseInt(field);
            winner = markBoards(boards, val);
            if (winner != -1) {
                call = val;
                break;
            }
        }
        
        return scoreBoard(boards.get(winner)) * call;
    }

    public static int part2(String[] lines) {
        String nums = lines[0];
        int line = 2;
        List<int[][]> boards = new ArrayList<>();
        while (line < lines.length) {
            int[][] board = new int[5][5];
            for (int i = 0; i < 5; i++) {
                String s = lines[line + i];
                String[] fields = s.trim().split("\\s+");
                for (int j = 0; j < 5; j++) {
                    board[i][j] = Integer.parseInt(fields[j]);
                }
            }
            boards.add(board);
            line += 6;
        }
        
        String[] fields = nums.split(",");
        int last = -1;
        for (int i = 0; i < fields.length; i++) {
            int val = Integer.parseInt(fields[i]);
            last = i;
            markBoardsRemoveWinners(boards, val);
            if (boards.size() == 1) break;
        }
        
        for (int i = last + 1; i < fields.length; i++) {
            int val = Integer.parseInt(fields[i]);
            if (markBoards(boards, val) != -1) {
                last = val;
                break;
            }
        }
        
        return scoreBoard(boards.get(0)) * last;
    }
    
    private static int markBoards(List<int[][]> boards, int val) {
        int count = 0;
        for (int[][] board : boards) {
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    if (board[i][j] == val) {
                        if (val == 0) board[i][j] = Integer.MIN_VALUE;
                        else board[i][j] = -board[i][j];
                    }
                }
            }
            if (checkBoard(board)) return count;
            count++;
        }
        return -1;
    }
    
    private static void markBoardsRemoveWinners(List<int[][]> boards, int val) {
        List<Integer> remove = new ArrayList<>();
        for (int b = 0; b < boards.size(); b++) {
            int[][] board = boards.get(b);
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    if (board[i][j] == val) {
                        if (val == 0) board[i][j] = Integer.MIN_VALUE;
                        else board[i][j] = -board[i][j];
                    }
                }
            }
            if (checkBoard(board)) remove.add(b);
        }
 
        for (int i = remove.size() - 1; i >= 0; i--) boards.remove((int) remove.get(i)); 
    }
    
    private static boolean checkBoard(int[][] board) {
        for (int i = 0; i < 5; i++) {
            int count = 0;
            for (int j = 0; j < 5; j++) {
                if (board[i][j] < 0) count++;
            }
            if (count == 5) return true;
        }
        
        for (int i = 0; i < 5; i++) {
            int count = 0;
            for (int j = 0; j < 5; j++) {
                if (board[j][i] < 0) count++;
            }
            if (count == 5) return true;
        }
        return false;
    }
    
    private static int scoreBoard(int[][] board) {
        int score = 0;
        for (int[] row : board) {
            for (int i : row) if (i > 0) score += i;
        }
        return score;
    }

}