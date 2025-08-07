// A ninja has planned a n-day training schedule. Each day he has to perform one of three activities - running, stealth training, or fighting practice. The same activity cannot be done on two consecutive days and the ninja earns a specific number of merit points, based on the activity and the given day.
// Given a n x 3-sized matrix, where matrix[i][0], matrix[i][1], and matrix[i][2], represent the merit points associated with running, stealth and fighting practice, on the (i+1)th day respectively. Return the maximum possible merit points that the ninja can earn.

// Examples:
// Input: matrix = [[10, 40, 70], [20, 50, 80], [30, 60, 90]]
// Output: 210
// Explanation:
// Day 1: fighting practice = 70
// Day 2: stealth training = 50
// Day 3: fighting practice = 90
// Total = 70 + 50 + 90 = 210
// This gives the optimal points.

// Input: matrix = [[70, 40, 10], [180, 20, 5], [200, 60, 30]]
// Output: 290
// Explanation:
// Day 1: running = 70
// Day 2: stealth training = 20
// Day 3: running = 200
// Total = 70 + 20 + 200 = 290
// This gives the optimal points.

// Input: matrix = [[20, 10, 10], [20, 10, 10], [20, 30, 10]]
// Output: 60

// Constraints:
// 1 <= n <= 104
// n == number of rows in matrix
// 3 == number of columns in matrix
// 0 <= matrix[i][j] <= 1000

class Solution {
    public int ninjaTraining(int[][] matrix) {
        int N = matrix.length;
        int[][] dp = new int[N][3];

        
        dp[0][0] = matrix[0][0]; 
        dp[0][1] = matrix[0][1]; 
        dp[0][2] = matrix[0][2];

        for(int i=1; i<N; i++){
            dp[i][0] = matrix[i][0] + Math.max(dp[i-1][1], dp[i-1][2]);
            dp[i][1] = matrix[i][1] + Math.max(dp[i-1][0], dp[i-1][2]);
            dp[i][2] = matrix[i][2] + Math.max(dp[i-1][0], dp[i-1][1]);
        }

        return Math.max(dp[N-1][0], Math.max(dp[N-1][1], dp[N-1][2]));

    }
}