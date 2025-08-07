// A robber is targeting to rob houses from a street. Each house has security measures that alert the police when two adjacent houses are robbed. The houses are arranged in a circular manner, thus the first and last houses are adjacent to each other.
// Given an integer array money, where money[i] represents the amount of money that can be looted from the (i+1)th house. Return the maximum amount of money that the robber can loot without alerting the police.

// Examples:
// Input: money = [2, 1, 4, 9]
// Output: 10
// Explanation: [2, 1, 4, 9] The underlined houses would give the maximum loot.
// Note that we cannot loot the 1st and 4th houses together.

// Input: money = [1, 5, 2, 1, 6]
// Output: 11
// Explanation: [1, 5, 2, 1, 6] The underlined houses would give the maximum loot.

// Input: money = [9, 4, 1, 8]
// Output:
// 12

// Constraints:
// 1 <= money.length <= 105
// 0 <= money[i] <= 1000

class Solution {
    public int houseRobber(int[] nums) {
        int N = nums.length;
        
        if(N==1)
            return nums[0];
        if(N==2)
            return Math.max(nums[1], nums[0]);

        return Math.max(rob(nums, 0, N-2), 
        rob(nums, 1, N-1));
    }
    private int rob(int[] nums, int st, int end){
        int N = end - st + 1;

        if(N == 1)
            return nums[st];
            
        int[] dp = new int[N];

        dp[0] = nums[st]; 
        dp[1] = Math.max(dp[0], nums[st+1]);

        for(int i=2; i<N; i++)
            dp[i] = Math.max(dp[i-1], nums[st+i] + dp[i-2]);

        return dp[N-1];
    }
}