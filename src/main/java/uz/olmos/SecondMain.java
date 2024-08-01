package uz.olmos;


import java.util.*;

public class SecondMain {
    public static void main(String[] args) {
        destCity(new ArrayList<>());
        System.out.println(countLargestGroup(264));
        System.out.println(findLucky(new int[]{8}));
        System.out.println(Arrays.toString(findIndices(new int[]{3}, 1, 1)));
        System.out.println(lastVisitedIntegers(new int[]{1, -1, 2, -1, -1}));
        System.out.println(sortSentence("is2 sentence4 This1 a3"));
        System.out.println(maxProductDifference(new int[]{2, 9, 5, 9, 1}));
        System.out.println(canBeIncreasing(new int[]{2, 3, 1, 2}));
        System.out.println(isSorted(List.of(2, 4, 20, 19)));
        containsDuplicate(new int[]{1, 2, 3, 4});
        System.out.println(maxSum(new int[]{2536, 1613, 3366, 162}));
        System.out.println(countBeautifulPairs(new int[]{2, 5, 1, 4}));
        System.out.println(diagonalPrime(new int[][]{{1, 2, 3}, {5, 6, 7}, {9, 10, 11}}));
        System.out.println(mySqrt(8));
        System.out.println(singleNumber(new int[]{2, 2, 1}));
        System.out.println(isHappy(25));
        System.out.println(isIsomorphic("badc", "baba"));
        System.out.println(Arrays.toString(decompressRLElist(new int[]{1, 2, 3, 4})));
    }

    //    1436. Destination City
    public static String destCity(List<List<String>> paths) {
        HashMap<String, String> dest = new HashMap<>();
        for (List<String> path : paths) {
            dest.put(path.get(0), path.get(1));
        }
        for (List<String> path : paths) {
            for (String s : path) {
                if (!dest.containsKey(s)) {
                    return s;
                }
            }
        }
        return "";
    }

    //    1399. Count Largest Group
    public static int countLargestGroup(int n) {
        HashMap<Integer, List<Integer>> groupss = new HashMap<>();
        int max = Integer.MIN_VALUE;
        for (int i = 1; i <= n; i++) {
            int sumDigit = sumDigit(i);
            if (!groupss.containsKey(sumDigit)) {
                groupss.put(sumDigit, new ArrayList<>());
            } else {
                groupss.get(sumDigit).add(i);
            }
            max = Math.max(max, groupss.get(sumDigit).size());
        }
        n = 0;
        for (Map.Entry<Integer, List<Integer>> entry : groupss.entrySet()) {
            if (entry.getValue().size() == max) n++;
        }
        return n;
    }

    //    1399. Count Largest Group
    public static int sumDigit(int n) {
        int sum = 0;
        while (n > 0) {
            sum += n % 10;
            n /= 10;
        }
        return sum;
    }

    //    1394. Find Lucky Integer in an Array
    public static int findLucky(int[] arr) {
        HashMap<Integer, Integer> freq = new HashMap<>();
        for (int i : arr) {
            freq.put(i, freq.getOrDefault(i, 0) + 1);
        }
        int max = Integer.MIN_VALUE;
        boolean isFound = false;
        for (int i : arr) {
            if (freq.get(i) == i) {
                max = Math.max(max, i);
                isFound = true;
            }
        }
        return isFound ? max : -1;
    }

    public static int[] findIndices(int[] nums, int indexDifference, int valueDifference) {
        int n = nums.length;
        ;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (Math.abs(i - j) >= indexDifference && Math.abs(nums[i] - nums[j]) >= valueDifference) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{-1, -1};
    }

    public static List<Integer> lastVisitedIntegers(int[] nums) {
        List<Integer> seen = new ArrayList<>();
        List<Integer> ans = new ArrayList<>();
        int k = 0;
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (num > 0) {
                seen.addFirst(num);
            } else if (num == -1) {
                if (k < seen.size()) {
                    ans.add(seen.get(k));
                } else {
                    ans.add(-1);
                }
                if (i > 0 && nums[i - 1] > 0) {
                    k = 1;
                } else {
                    k++;
                }
            }
        }
        return ans;
    }

    public static String sortSentence(String s) {
        String[] strArr = s.split(" ");
        int n = strArr.length;
        StringJoiner result = new StringJoiner(" ");

        int min;
        for (int i = 0; i < n - 1; i++) {
            min = i;
            for (int j = i + 1; j < n; j++) {
                if (getDigit(strArr[j]) < getDigit(strArr[min])) {
                    min = j;
                }
            }
            if (min != i) {
                String temp = strArr[min];
                strArr[min] = strArr[i];
                strArr[i] = temp;
            }
            result.add(strArr[i].substring(0, strArr[i].length() - 1));
        }
        String last = strArr[strArr.length - 1];
        result.add(last.substring(0, last.length() - 1));
        return result.toString();
    }

    public static Integer getDigit(String str) {
        char c = str.charAt(str.length() - 1);
        System.out.println(c);
        return Integer.parseInt(String.valueOf(c));
    }

    //        1913. Maximum Product Difference Between Two Pairs
    public static int maxProductDifference(int[] nums) {
        Arrays.sort(nums);
        return (nums[nums.length - 1] * nums[nums.length - 2]) - (nums[0] * nums[1]);
    }

    public static boolean canBeIncreasing(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            List<Integer> temp = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    temp.add(nums[j]);
                }
            }
            if (isSorted(temp)) return true;
        }
        return false;
    }

    public static boolean isSorted(List<Integer> nums) {
        int ctr = 0;
        int n = nums.size();
        for (int i = 0; i < n - 1; i++) {
            if (nums.get(i) < nums.get(i + 1)) {
                ctr++;
            }
        }
        return ctr == n - 1;
    }

    public static boolean containsDuplicate(int[] nums) {
        HashSet<Integer> occ = new HashSet<>();
        for (int num : nums) {
            occ.add(num);
        }
        return nums.length != occ.size();
    }

    //    2815. Max Pair Sum in an Array
    public static int maxSum(int[] nums) {
        int max = -1;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int iMax = findMax(nums[i]);
                int jMax = findMax(nums[j]);
                if (iMax == jMax) {
                    max = Math.max(max, nums[i] + nums[j]);
                }
            }
        }
        return max;
    }

    //    2815. Max Pair Sum in an Array. Helper
    public static int findMax(int n) {
        int max = Integer.MIN_VALUE;
        while (n > 0) {
            max = Math.max(n % 10, max);
            n /= 10;
        }
        return max;
    }

    public static int countBeautifulPairs(int[] nums) {
        int n = nums.length;
        int ctr = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int first = (int) (nums[i] / Math.pow(10, String.valueOf(nums[i]).length() - 1));
                int last = nums[j] % 10;
                if (gcd(first, last) == 1) ctr++;
            }
        }
        return ctr;
    }

    public static int gcd(int x, int y) {
        int gcd = 1;
        for (int i = 1; i <= Math.min(x, y); i++) {
            if (x % i == 0 && y % i == 0) {
                gcd = i;
            }
        }
        return gcd;
    }

    public static int diagonalPrime(int[][] nums) {
        System.out.println("-----");
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums[i].length; j++) {
                if (i == j || i + j == nums[i].length - 1) {
                    int current = nums[i][j];
                    if (isPrime(current)) max = Math.max(max, current);
                }
            }
        }
        return max;
    }

    public static boolean isPrime(int n) {
        if (n <= 1) return false;
        if (n <= 3) return true;
        if (n % 2 == 0 || n % 3 == 0) return false;
        for (int i = 5; i * i <= n; i += 6) {
            if (n % i == 0 || n % (i + 2) == 0) return false;
        }
        return true;
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public static int mySqrt(int x) {
        return (int) Math.floor(Math.sqrt(x));
    }

    public static int singleNumber(int[] nums) {
        HashMap<Integer, Integer> occ = new HashMap<>();
        for (int num : nums) {
            occ.put(num, occ.getOrDefault(num, 0) + 1);
        }
        for (Map.Entry<Integer, Integer> entry : occ.entrySet()) {
            if (entry.getValue() == 1) {
                return entry.getKey();
            }
        }
        return 0;
    }

    public static int addDigits(int num) {
        if (num < 10) return num;
        int sum = 0;
        while (num > 0) {
            sum += num % 10;
            num /= 10;
        }
        return addDigits(sum);
    }

    public static boolean isHappy(int n) {
        HashSet<Integer> existingNums = new HashSet<>();
        while (n != 1) {
            if (existingNums.contains(n)) {
                return false;
            }
            existingNums.add(n);

            int sum = 0;
            while (n > 0) {
                sum += (int) Math.pow(n % 10, 2);
                n /= 10;
            }
            n = sum;
        }
        return true;
    }

    public static int sumDigitSqr(int n) {
        int sum = 0;
        while (n > 0) {
            sum += (int) Math.pow(n % 10, 2);
            n /= 10;
        }
        return sum;
    }

    //    205. Isomorphic Strings
    public static boolean isIsomorphic(String s, String t) {
        int n = s.length();
        if (n != t.length()) return false;
        HashMap<Character, Character> sMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            sMap.put(s.charAt(i), t.charAt(i));
        }
        HashMap<Character, Character> tMap = new HashMap<>();
        for (int i = n - 1; i >= 0; i--) {
            tMap.put(t.charAt(i), s.charAt(i));
        }
        if (sMap.size() != tMap.size()) {
            return false;
        }
        for (int i = 0; i < n; i++) {
            if (sMap.get(s.charAt(i)) != t.charAt(i)) return false;
        }
        return true;
    }

    public static int[] decompressRLElist(int[] nums) {
        int n = nums.length;
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < n - 1; i += 2) {
            List<Integer> list = genList(nums[i], nums[i + 1]);
            result.addAll(list);
        }
        int[] res = new int[result.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = result.get(i);
        }
        return res;
    }

    public static List<Integer> genList(int freq, int num) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < freq; i++) {
            result.add(num);
        }
        return result;
    }

    public static List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> groups = new HashMap<>();
        int n = strs.length;
        for (int i = 0; i < n; i++) {
            if (groups.containsKey(strs[i]))
               continue;
            groups.put(strs[i], new ArrayList<>());
            for (int j = i + 1; j < n; j++) {
                if (isAnagram(strs[i], strs[j])) {
                    groups.get(strs[i]).add(strs[j]);
                }
            }
        }
        groups.forEach((k, v) -> System.out.println(k + ": " + v));
        return new ArrayList<>();
    }

    public static boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;
        HashMap<Character, Integer> sHash = new HashMap<>();
        for (char c : s.toCharArray()) {
            sHash.put(c, sHash.getOrDefault(c, 0) + 1);
        }
        for (char c : t.toCharArray()) {
            if (sHash.containsKey(c)) {
                sHash.put(c, sHash.get(c) - 1);
                if (sHash.get(c) == 0) {
                    sHash.remove(c);
                }
            }
        }
        return sHash.isEmpty();
    }

}
