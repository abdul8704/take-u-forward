// A frog wants to climb a staircase with n steps. Given an integer array heights, where heights[i] contains the height of the ith step, and an integer k
// To jump from the ith step to the jth step, the frog requires abs(heights[i] - heights[j]) energy, where abs() denotes the absolute difference. The frog can jump from the ith step to any step in the range [i + 1, i + k], provided it exists. Return the minimum amount of energy required by the frog to go from the 0th step to the (n-1)th step.

// Examples:
// Input: heights = [10, 5, 20, 0, 15], k = 2
// Output: 15
// Explanation:
// 0th step -> 2nd step, cost = abs(10 - 20) = 10
// 2nd step -> 4th step, cost = abs(20 - 15) = 5
// Total cost = 10 + 5 = 15.

// Input: heights = [15, 4, 1, 14, 15], k = 3
// Output: 2
// Explanation:
// 0th step -> 3rd step, cost = abs(15 - 14) = 1
// 3rd step -> 4th step, cost = abs(14 - 15) = 1
// Total cost = 1 + 1 = 2.

// Input: heights = [15, 4, 1, 14, 15], k = 4
// Output: 0

class Solution {
    public int frogJump(int[] heights, int k) {
        int N = heights.length;
        int[] dp = new int[N];

        dp[0] = 0; dp[1] = Math.abs(heights[0] - heights[1]);

        for(int i=2; i<N; i++){
            int min = Integer.MAX_VALUE;

            for(int j=1; j<=k; j++){
                if(i-j < 0)
                    continue;
                min = Math.min(min, dp[i-j] + Math.abs(heights[i-j] - heights[i]));
            }

            dp[i] = min;
        }

        return dp[N-1];
    }
}