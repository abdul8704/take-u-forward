// Ninja has a grid of size R x C, where each cell contains some chocolates. He has two friends: Alice and Bob, and wants to collect as many chocolates as possible using their help.
// Alice starts at the top-left cell (0, 0)
// Bob starts at the top-right cell (0, C - 1)
// Both can only move to the next row, and from position (i, j), they can move to:
// (i + 1, j)
// (i + 1, j - 1)
// (i + 1, j + 1)

// Both must remain within the grid bounds.
// Each collects all chocolates in their current cell.
// If both land on the same cell, the chocolates are only counted once.

// Return the maximum number of chocolates Ninja can collect using his two friends.

// Examples:
// Input: grid = [[2, 3, 1, 2],[3, 4, 2, 2],[5, 6, 3, 5]]
// Output: 21
// Explanation: 
// Alice: (0,0) → (1,1) → (2,1) → chocolates = 2 + 4 + 6 = 12
// Bob:  (0,3) → (1,3) → (2,3) → chocolates = 2 + 2 + 5 = 9
// Total = 12 + 9 = 21

// Input: grid = [[1, 2],[3, 4]]
// Output: 10
// Explanation:
// Alice: (0,0) → (1,0) → 1 + 3 = 4
// Bob:  (0,1) → (1,1) → 2 + 4 = 6
// But both can’t pick at same time (if they land same cell), so:
// Best is (0,0)+(1,0)+(0,1)+(1,1) - overlap = 1+3+2+4 = 10

// Input: grid = [ [10, 1, 10], [1, 1, 1], [1, 1, 1]]
// Output:24

class Solution { // memoizzz
    public int maxChocolates(int[][] grid) {
        int R = grid.length, C = grid[0].length;
        Integer[][][] memo = new Integer[R][C][C];

        return dfs(0, 0, C-1, grid, R, C, memo);
    }
    private static int dfs(int row, int alice, int bob, int[][] grid, int R, int C, Integer[][][] memo){
        if(row == R-1){
            memo[row][alice][bob] = (alice == bob) ? grid[row][alice] : grid[row][alice] + grid[row][bob];
            return memo[row][alice][bob];
        }

        if(memo[row][alice][bob] != null)
            return memo[row][alice][bob];

        int best = Integer.MIN_VALUE;

        for(int aNext = -1; aNext <= 1; aNext++){
            for(int bNext = -1; bNext <= 1; bNext++){
                int aliceNext = alice + aNext;
                int bobNext = bob + bNext;

                if(!inBound(aliceNext, bobNext, C))
                    continue;

                best = Math.max(best, dfs(row+1, aliceNext, bobNext, grid, R, C, memo));
      
            }
        }    

        memo[row][alice][bob] = (alice == bob) ? best + grid[row][alice]: best + grid[row][alice] + grid[row][bob];

        return memo[row][alice][bob];
    }

    private static boolean inBound(int c1, int c2, int C){
        return (c1 >= 0 && c1 < C && c2 >= 0 && c2 < C);
    }
}

class Solution2 { //recursive
    public int maxChocolates(int[][] grid) {
        int R = grid.length, C = grid[0].length;
        return dfs(0, 0, C-1, grid, R, C);
    }
    private static int dfs(int row, int alice, int bob, int[][] grid, int R, int C){
        if(row == R-1)
            return (alice == bob) ? grid[row][alice] : grid[row][alice] + grid[row][bob];

        int best = Integer.MIN_VALUE;

        for(int aNext = -1; aNext <= 1; aNext++){
            for(int bNext = -1; bNext <= 1; bNext++){
                int aliceNext = alice + aNext;
                int bobNext = bob + bNext;

                if(!inBound(aliceNext, bobNext, C))
                    continue;

                best = Math.max(best, dfs(row+1, aliceNext, bobNext, grid, R, C));

                   
            }
        }    

        return (alice == bob) ? best + grid[row][alice]: best + grid[row][alice] + grid[row][bob];
    }

    private static boolean inBound(int c1, int c2, int C){
        return (c1 >= 0 && c1 < C && c2 >= 0 && c2 < C);
    }
}