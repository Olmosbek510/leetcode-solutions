package uz.olmos.leetcode;

import java.util.*;

public class FifthMain {
    public static void main(String[] args) {
//        camelMatch(new String[]{"FooBar", "FooBarTest", "FootBall", "FrameBuffer", "ForceFeedBack"}, "FoBa");
//        System.out.println(maxProfit(new int[]{7, 1, 5, 3, 6, 4}));
//
//
//        System.out.printf("Result: %s\n", gcdOfStrings("ABCABC", "ABC"));
//
//        System.out.println(canPlaceFlowers(new int[]{0, 0, 1, 0, 1}, 1));

//        System.out.println(Arrays.toString(productExceptSelf(new int[]{1, 2, 3, 4})));

//        System.out.println(pickGifts(new int[]{25, 64, 9, 4, 100}, 4));
//        System.out.println(minStoneSum(new int[]{5, 4, 9}, 2));
//        System.out.println(Arrays.toString(leetcode238(new int[]{1, 2, 3, 4})));
        System.out.println(isSubsequence("azab", "abzba"));
        System.out.println(longestMonotonicSubarray(new int[]{1, 4, 3, 3, 2}));

        System.out.println(maxArea(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7}));

        System.out.println(maxVowels("weallloveyou", 7));

        System.out.println(largestAltitude(new int[]{-4, -3, -2, -1, 4, 3, 2}));
    }

    public static List<Boolean> camelMatch(String[] queries, String pattern) {
        List<String> patterns = new LinkedList<>();
        StringBuilder temp = new StringBuilder();

        for (int i = 0; i < pattern.length(); i++) {
            temp.append(pattern.charAt(i));
            if (i == 0) continue;
            if (i < pattern.length() - 1 && Character.isUpperCase(pattern.charAt(i + 1))) {
                patterns.add(temp.toString());
                temp = new StringBuilder();
            }
        }

        System.out.println(patterns);
        return new LinkedList<>();
    }

    /**
     * <h2>122. Best Time to Buy and Sell Stock II</h2>
     *
     * <h3>Medium</h3>
     *
     * <strong>Topics:</strong> Array, Greedy<br>
     * <strong>Companies:</strong> [List of relevant companies, if any]
     *
     * <p>
     * You are given an integer array <code>prices</code> where <code>prices[i]</code> is the price of a given stock on the <code>i<sup>th</sup></code> day.
     * </p>
     *
     * <p>
     * On each day, you may decide to buy and/or sell the stock. You can only hold at most one share of the stock at any time. However, you can buy it then immediately sell it on the same day.
     * </p>
     *
     * <p>
     * <strong>Return</strong> the <em>maximum profit</em> you can achieve.
     * </p>
     *
     * <h4>Examples:</h4>
     *
     * <h5>Example 1:</h5>
     * <pre>
     * <strong>Input:</strong> prices = [7,1,5,3,6,4]
     * <strong>Output:</strong> 7
     * <strong>Explanation:</strong>
     * Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit = 5 - 1 = 4.
     * Then buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6 - 3 = 3.
     * Total profit is 4 + 3 = 7.
     * </pre>
     *
     * <h5>Example 2:</h5>
     * <pre>
     * <strong>Input:</strong> prices = [1,2,3,4,5]
     * <strong>Output:</strong> 4
     * <strong>Explanation:</strong>
     * Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5 - 1 = 4.
     * Total profit is 4.
     * </pre>
     *
     * <h5>Example 3:</h5>
     * <pre>
     * <strong>Input:</strong> prices = [7,6,4,3,1]
     * <strong>Output:</strong> 0
     * <strong>Explanation:</strong>
     * There is no way to make a positive profit, so we never buy the stock to achieve the maximum profit of 0.
     * </pre>
     *
     * <h4>Constraints:</h4>
     * <ul>
     *   <li>1 &le; <code>prices.length</code> &le; 3 * 10<sup>4</sup></li>
     *   <li>0 &le; <code>prices[i]</code> &le; 10<sup>4</sup></li>
     * </ul>
     */
    public static int maxProfit(int[] prices) {
        int profit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i - 1] < prices[i]) {
                profit += prices[i] - prices[i - 1];
            }
        }
        return profit;
    }

    //    1071. Greatest Common Divisor of Strings
    public static String gcdOfStrings(String str1, String str2) {
        if (!(str2 + str1).equals(str1 + str2)) return "";
        return str1.substring(0, gcdLen(str1.length(), str2.length()));
    }

    public static int gcdLen(int str1Len, int str2Len) {
        int gcd = 1;
        int length = Math.min(str1Len, str2Len);
        for (int i = 1; i <= length; i++) {
            if (str1Len % i == 0 && str2Len % i == 0) {
                gcd = i;
            }
        }
        return gcd;
    }

    public static boolean canPlaceFlowers(int[] flowerbed, int n) {
        for (int i = 0; i < flowerbed.length - 1; i++) {
            if (flowerbed[i] != 1 && i > 0 && flowerbed[i + 1] == 0 && flowerbed[i - 1] == 0 || flowerbed[i] == 0 && flowerbed[i + 1] == 0) {
                i += 1;
                if (n > 0) {
                    n--;
                    flowerbed[i] = 1;
                }
            }
        }
        return n == 0;
    }

    public static int[] productExceptSelf(int[] nums) {
        int[] res = new int[nums.length];
        res[0] = 1;
        int prefix = 1;

        for (int i = 0; i < nums.length; i++) {
            res[i] = prefix;
            prefix *= nums[i];
        }

        int postfix = 1;
        for (int i = nums.length - 1; i > 0; i--) {
            res[i] *= postfix;
            postfix *= nums[i];
        }
        return res;
    }

    public static long pickGifts(int[] gifts, int k) {
        if (k == 0) {
            int result = 0;
            for (int gift : gifts) {
                result += gift;
            }
            return result;
        }
        int max = gifts[0];
        int index = 0;
        for (int i = 0; i < gifts.length; i++) {
            if (max < gifts[i]) {
                max = gifts[i];
                index = i;
            }
        }
        gifts[index] = (int) Math.floor(Math.sqrt(max));
        return pickGifts(gifts, k - 1);
    }

    // 1962
    public static int minStoneSum(int[] piles, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>((a, b) -> b - a);

        for (int pile : piles) {
            queue.add(pile);
        }

        while (k-- > 0) {
            int max = queue.poll();
            max -= (int) Math.floor((double) max / 2);
            queue.add(max);
        }

        int res = 0;
        while (!queue.isEmpty()) {
            res += queue.poll();
        }
        return res;
    }

    public static boolean isSubsequence(String s, String t) {
        int j = 0;
        for (int i = 0; i < t.length(); i++) {
            if (s.charAt(j) == t.charAt(i)) {
                j++;
                if (j == s.length()) return true;
            }
        }
        return false;
    }

    public static int longestMonotonicSubarray(int[] nums) {
        int len = nums.length;
        int incr = 1;
        int dec = 1;

        int incrSize = 1;
        int decrSize = 1;

        for (int i = 0; i < len; i++) {
            if (i < len - 1 && nums[i] < nums[i + 1]) {
                incrSize++;
            } else {
                incr = Math.max(incr, incrSize);
                incrSize = 1;
            }

            if (i > 0 && (nums[i]) > nums[i - 1]) {
                decrSize++;
            } else {
                dec = Math.max(decrSize, dec);
                decrSize = 1;
            }
        }
        return Math.max(incr, dec);
    }

    public static int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int maxArea = Integer.MIN_VALUE;
        while (right != left) {
            int area = Math.min(height[left], height[right]) * (right - left);
            maxArea = Math.max(maxArea, area);
            if (height[right] > height[left]) {
                left++;
            } else {
                right--;
            }
        }
        return maxArea;
    }

    public static int maxVowels(String s, int k) {
        if (k > s.length()) return 0;
        int len = s.length();

        int tempCount = 0;

        Set<Character> vowels = new HashSet<>(List.of('a', 'o', 'u', 'i', 'e'));

        for (int i = 0; i < k; i++) {
            char current = s.charAt(i);
            if (vowels.contains(current)) {
                tempCount++;
            }
        }

        int maxVowels = tempCount;


        for (int i = 0; i < len - k; i++) {
            char current = s.charAt(i);
            if (vowels.contains(current)) {
                tempCount--;
            }
            if (vowels.contains(s.charAt(i + k))) {
                tempCount++;
            }
            maxVowels = Math.max(tempCount, maxVowels);
        }
        return maxVowels;
    }

    public static int largestAltitude(int[] gain) {
        int max = 0;

        int alt = 0;
        for (int j : gain) {
            alt += j;
            max = Math.max(alt, max);
        }
        return max;
    }
}