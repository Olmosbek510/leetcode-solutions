package uz.olmos.leetcode;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static uz.olmos.leetcode.NeetCode150.LetterCombinations.letterCombinations;

public class NeetCode150 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(topKFrequent(new int[]{1, 1, 1, 2, 2, 3}, 2)));
        System.out.println(Arrays.deepToString(merge(new int[][]{{1, 3}, {2, 6}, {8, 10}, {15, 18}})));
        tupleSameProduct(new int[]{1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048, 4096, 8192});
        System.out.println(removeOccurrences("daabcbaabcbc", "abc"));
        System.out.println(clearDigits("abc"));
        System.out.println(maximumSum(new int[]{279, 169, 463, 252, 94, 455, 423, 315, 288, 64, 494, 337, 409, 283, 283, 477, 248, 8, 89, 166, 188, 186, 128}));
        System.out.println(lengthOfLongestSubstring("au"));
        System.out.println(minOperations(
                new int[]{1000000000, 999999999, 1000000000, 999999999, 1000000000, 999999999}, 1000000000
        ));
        System.out.println(halveArray(new int[]{5, 19, 8, 1}));
        System.out.println(letterCombinations("23"));
    }

    public static List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String sorted = new String(chars);
            map.computeIfAbsent(sorted, k -> new ArrayList<>()).add(str);
        }
        return new ArrayList<>(map.values());
    }

    public static int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> occ = new HashMap<>();
        for (int num : nums) {
            occ.put(num, occ.getOrDefault(num, 0) + 1);
        }

        // HeapSort Implementation
        heapSort(nums, occ);

        int[] res = new int[k];
        occ = new LinkedHashMap<>();
        for (int num : nums) {
            occ.put(num, null);
        }
        int index = 0;
        for (Map.Entry<Integer, Integer> integerIntegerEntry : occ.entrySet()) {
            if (k == index) break;
            res[index++] = integerIntegerEntry.getKey();
        }
        return res;
    }

    // HeapSort implementation based on frequency
    private static void heapSort(int[] nums, HashMap<Integer, Integer> occ) {
        int n = nums.length;

        // Build max heap
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(nums, occ, n, i);
        }

        // Extract elements from heap
        for (int i = n - 1; i > 0; i--) {
            swap(nums, 0, i); // Move max element to end
            heapify(nums, occ, i, 0); // Heapify reduced heap
        }
    }

    // Heapify function to maintain max heap property
    private static void heapify(int[] nums, HashMap<Integer, Integer> occ, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && occ.get(nums[left]) > occ.get(nums[largest])) {
            largest = left;
        }
        if (right < n && occ.get(nums[right]) > occ.get(nums[largest])) {
            largest = right;
        }

        if (largest != i) {
            swap(nums, i, largest);
            heapify(nums, occ, n, largest);
        }
    }

    // Swap helper function
    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public boolean areAlmostEqual(String s1, String s2) {
        int mismatchCount = 0;

        int len = s1.length();
        int[] mismatchIndexes = new int[2];
        int mismatchIndex = 0;

        for (int i = 0; i < len; i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                mismatchCount++;
                if (mismatchCount > 2) return false;
                mismatchIndexes[mismatchIndex++] = i;
            }
        }

        int mismatchIndex1 = mismatchIndexes[0];
        int mismatchIndex2 = mismatchIndexes[1];
        return s1.charAt(mismatchIndex1) == s2.charAt(mismatchIndex2) && s2.charAt(mismatchIndex1) == s1.charAt(mismatchIndex2);
    }

    public static int[][] merge(int[][] intervals) {
        List<LinkedList<Integer>> nums = new LinkedList<>();
        Arrays.sort(intervals, (o1, o2) -> o2[0] - o1[0]);

        for (int i = 0; i < intervals.length - 1; i++) {
            if (intervals[i][1] <= intervals[i + 1][1]) {
                // overlap
                nums.add(new LinkedList<>(List.of(intervals[i][0], intervals[i + 1][1])));
            }
        }
        System.out.println(nums);
        return new int[][]{};
    }

    // 1726
    public static int tupleSameProduct(int[] nums) {
        int len = nums.length;
        int count = 0;
        HashMap<Integer, Integer> track = new HashMap<>();
        for (int i = 0; i < len - 1; i++) {
            for (int j = i + 1; j < len; j++) {
                int mult = nums[i] * nums[j];
                track.put(mult, track.getOrDefault(mult, 0) + 1);
            }
        }
        for (var entry : track.entrySet()) {
            var current = entry.getValue();
            if (current > 1) {
                count += 8 * (current - 1) * current / 2;
            }
        }
        return count;
    }

    public static String removeOccurrences(String s, String part) {
        if (!s.contains(part)) return s;
        return removeOccurrences(s.replace(part, ""), part);
    }

    public static String clearDigits(String s) {
        Stack<Character> characters = new Stack<>();
        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                characters.pop();
            } else {
                characters.push(c);
            }
        }
        StringBuilder result = new StringBuilder();
        while (!characters.isEmpty()) {
            result.append(characters.pop());
        }
        return result.reverse().toString();
    }

    public static int maximumSum(int[] nums) {
        HashMap<Integer, Integer> occ = new HashMap<>();
        int max = -1;
        for (int n : nums) {
            int val = sumDigits(n);
            if (occ.containsKey(val)) {
                max = Math.max(max, occ.get(val) + n);
                if (occ.get(val) < n) {
                    occ.put(val, n);
                }
                continue;
            }
            occ.put(val, n);
        }
        return max;
    }

    public static int sumDigits(int n) {
        int result = 0;
        while (n > 0) {
            result += n % 10;
            n /= 10;
        }
        return result;
    }

    public static int lengthOfLongestSubstring(String s) {
        HashSet<Character> charSet = new HashSet<>();

        int l = 1;
        int res = 0;

        int len = s.length();
        for (int i = 0; i < len; i++) {
            while (charSet.contains(s.charAt(i))) {
                charSet.remove(s.charAt(l));
                l++;
            }
            charSet.add(s.charAt(i));
            res = Math.max(res, i - l + 1);
        }
        return res;
    }

    public static int minOperations(int[] nums, int k) {
        Queue<Long> queue = new PriorityQueue<>(Long::compareTo);
        for (var n : nums) {
            queue.add((long) n);
        }
        int steps = 0;
        while (!queue.isEmpty() && queue.size() > 1) {
            if (queue.peek() >= k) {
                break;
            }
            Long x = queue.poll();
            Long y = queue.poll();
            Long val = Math.min(x, y) * 2 + Math.max(x, y);
            queue.add(val);
            System.out.printf("Queue: %s\n", queue);
            steps++;
        }
        return steps;
    }

    public static int halveArray(int[] nums) {
        Queue<Double> queue = new PriorityQueue<>(Comparator.reverseOrder());
        double sum = 0;
        for (int num : nums) {
            queue.add((double) num);
            sum += num;
        }
        double track = sum;
        int steps = 0;
        while (!(track <= sum / 2)) {
            double element = queue.poll() / 2;
            track -= element;
            queue.add(element);
            steps++;
        }
        return steps;
    }


    public class LetterCombinations {
        public static List<String> letterCombinations(String digits) {
            if (digits.isEmpty()) return new ArrayList<>();

            Map<Character, String> digitToChar = new HashMap<>();
            digitToChar.put('2', "abc");
            digitToChar.put('3', "def");
            digitToChar.put('4', "ghi");
            digitToChar.put('5', "jkl");
            digitToChar.put('6', "mno");
            digitToChar.put('7', "pqrs");
            digitToChar.put('8', "tuv");
            digitToChar.put('9', "wxyz");

            List<String> res = new ArrayList<>();
            backtrack(0, new StringBuilder(), digits, res, digitToChar);
            return res;
        }

        private static void backtrack(int i, StringBuilder curStr, String digits, List<String> res, Map<Character, String> digitToChar) {
            if (curStr.length() == digits.length()) {
                res.add(curStr.toString());
                return;
            }

            for (char c : digitToChar.get(digits.charAt(i)).toCharArray()) {
                curStr.append(c);
                backtrack(i + 1, curStr, digits, res, digitToChar);
                curStr.deleteCharAt(curStr.length() - 1);
            }
        }
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        ArrayList<Integer> nums = new ArrayList<>();
        for (int i : nums1) {
            nums.add(i);
        }
        for (int i : nums2) {
            nums.add(i);
        }
        nums.sort(Integer::compareTo);

        int size = nums1.length + nums2.length;
        boolean isEven = size % 2 == 0;
        if (isEven) {
            int index1 = (int) Math.floor((double) (size - 1) / 2);
            int index2 = (int) Math.ceil((double) (size - 1) / 2);
            return (double) (nums.get(index1) + nums.get(index2)) / 2;
        }
        return nums.get((size - 1) / 2);
    }

    public static int longestConsecutive(int[] nums) {
        if (nums.length == 0) return 0;
        TreeSet<Integer> set = new TreeSet<>();
        for (int num : nums) {
            set.add(num);
        }

        int max = Integer.MIN_VALUE;
        int temp = 1;
        for (Integer i : set) {
            if (set.contains(i + 1)) {
                temp++;
            } else {
                max = Math.max(temp, max);
                temp = 1;
            }
        }
        return max;
    }

    public static String removeStars(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if(c == '*'){
                stack.pop();
                continue;
            }
            stack.push(c);
        }
        StringBuilder result = new StringBuilder();
        for (Character c : stack) {
            result.append(c);
        }
        return result.toString();
    }

    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> indexes = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if(indexes.containsKey(target - nums[i])){
                return new int[]{indexes.get(target - nums[i]), i};
            }
            indexes.put(nums[i], i);
        }
        return new int[]{};
    }
}
