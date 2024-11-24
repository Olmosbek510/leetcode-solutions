package uz.olmos.leetcode;

import java.util.*;

public class FourthMain {
    public static void main(String[] args) {
//        System.out.println(licenseKeyFormatting("2-4A0r7-4k", 4));
//        System.out.println(sortVowels("lEetcOde"));
//        System.out.println(compress(new char[]{'a', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b'}));
//        System.out.println(finalString("poiinter"));
//        System.out.println("Result: " + rle("11"));
//        System.out.println(countAndSay(4));
//        System.out.println(reverse(1534236469));
//        System.out.println(reverseStr("abcdefg", 3));
//        System.out.println("Result: " + longestPalindrome("abb"));
//        System.out.println(commonChars(new String[]{"bella", "label", "roller"}));
//        System.out.println(combinationSum3(3, 7));
//        System.out.println(Arrays.toString(findMissingAndRepeatedValues(new int[][]{{9, 1, 7}, {8, 9, 2}, {3, 4, 6}})));
//        System.out.println(maxFrequencyElements(new int[]{1, 2, 2, 3, 1, 4}));
//        System.out.println(canSortArray(new int[]{8, 4, 2, 30, 15}));
//        canFormArray(new int[]{91, 4, 64, 78}, new int[][]{{78}, {4, 64}, {91}});
//        System.out.println(maxRepeating("aaabaaaabaaabaaaabaaaabaaaabaaaaba", "aaaba"));
//        String s = "9001 discuss.leetcode.com";
//        subdomainVisits(new String[]{"9001 discuss.leetcode.com"});
//        System.out.println(caesarCipher("Always-Look-on-the-Bright-Side-of-Life", 5));

        System.out.println(findMost(new int[]{2, 3, 3, 7, 8, 7}));
    }

    public static String licenseKeyFormatting(String s, int k) {
        int start = s.indexOf("-");
        StringBuilder result = new StringBuilder(s.substring(0, start));
        StringBuilder temp = new StringBuilder("-");
        int indx = start;
        while (indx != s.length()) {
            System.out.println(s.charAt(indx));
            temp.append(s.charAt(indx) == '-' ? "" : String.valueOf(s.charAt(indx)).toUpperCase());
            if (temp.length() == k + 1) {
                result.append(temp);
                temp = new StringBuilder("-");
            }
            indx++;
        }
        return result.toString();
    }

    public static String sortVowels(String s) {
        LinkedList<Character> vowels = new LinkedList<>();
        String vows = "AOUEIaouei";
        for (char c : s.toCharArray()) {
            if (vows.contains(String.valueOf(c))) {
                vowels.add(c);
            }
        }
        vowels.sort(Character::compareTo);
        StringBuilder result = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (vows.contains(String.valueOf(c))) {
                result.append(vowels.poll());
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }

    public static int compress(char[] chars) {
        int n = chars.length;
        int temp = 1;
        int index = 0;
        int size = 0;
        for (int i = 0; i < n; i++) {
            if (i < n - 1 && chars[i] == chars[i + 1]) {
                temp++;
            } else {
                chars[index++] = chars[i];
                size++;
                if (temp > 1) {
                    for (char c : Integer.toString(temp).toCharArray()) {
                        chars[index++] = c;
                        size++;
                    }
                }
                temp = 1;
            }
        }
        return size;
    }

    public static int numOfStrings(String[] patterns, String word) {
        int n = 0;
        for (String pattern : patterns) {
            if (word.contains(pattern)) {
                n++;
            }
        }
        return n;
    }

    public static String finalString(String s) {
        StringBuilder res = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (c != 'i') {
                res.append(c);
            } else {
                res.reverse();
            }
        }
        return res.toString();
    }

    public static String countAndSay(int n) {
        if (n == 1) return "1";
        String result = "1";
        for (int i = 2; i <= n; i++) {
            result = rle(result);
        }
        return result;
    }

    public static String rle(String str) {
        StringBuilder result = new StringBuilder();
        int temp = 1;
        for (int i = 0; i < str.length(); i++) {
            if (i < str.length() - 1 && str.charAt(i) == str.charAt(i + 1)) {
                temp++;
            } else {
                result.append(temp).append(str.charAt(i));
                temp = 1;
            }
        }
        return result.toString();
    }

    public static int reverse(int x) {
        boolean neg = x < 0;
        if (x < 0) x *= -1;
        long res = 0;
        while (x > 0) {
            res *= 10;
            res += x % 10;
            x /= 10;
        }
        if (neg) res *= -1;
        if (res >= Integer.MAX_VALUE || res <= Integer.MIN_VALUE) {
            return 0;
        }
        System.out.println(Integer.MAX_VALUE);
        return (int) res;
    }

    public static String reverseStr(String s, int k) {
        var n = s.length();
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < n; i++) {
            var sub = i + k * 2 > n ? s.substring(i) : s.substring(i, i + k * 2);
            k = Math.min(k, sub.length());
            var firstPart = new StringBuilder(sub.substring(0, k));
            firstPart.reverse();
            var secondPart = sub.substring(k);
            result.append(firstPart).append(secondPart);
            i += k * 2 - 1;
        }
        return result.toString();
    }

    public static String longestPalindrome(String s) {
        if (isPalindrome(s)) return s;
        int n = s.length();
        int max = Integer.MIN_VALUE;
        String res = s;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j <= n; j++) {
                String sub = s.substring(i, j);
                if (isPalindrome(sub)) {
                    if (max < sub.length()) {
                        max = sub.length();
                        res = sub;
                    }
                }
            }
        }
        return res;
    }

    private static boolean isPalindrome(String s) {
        int n = s.length();
        int left = n - 1;
        for (int i = 0; i < n / 2; i++) {
            if (s.charAt(i) != s.charAt(left)) return false;
            left--;
        }
        return true;
    }

    public static int lengthOfLongestSubstring(String s) {
        int maxLen = Integer.MIN_VALUE;
        int n = s.length();
        for (int i = 0; i < n; i++) {
            int right = n - 1;
            while (i < right) {
                if (right - i + 1 <= maxLen) break;
                String temp = s.substring(i, right + 1);
                if (!isRepeated(temp)) {
                    maxLen = Math.max(maxLen, temp.length());
                    break;
                }
                right--;
            }
        }
        return maxLen;
    }

    public static boolean isRepeated(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
            if (map.get(c) == 2) return true;
        }
        return false;
    }


    public static List<String> commonChars(String[] words) {
        HashMap<Character, Integer> hash = getFreq(words[0]);
        for (int i = 1; i < words.length; i++) {
            var temp = getFreq(words[i]);
            for (char c : words[i].toCharArray()) {
                if (hash.containsKey(c)) {
                    hash.put(c, Math.min(hash.get(c), temp.getOrDefault(c, 0)));
                }
                for (Map.Entry<Character, Integer> entry : hash.entrySet()) {
                    if (!temp.containsKey(entry.getKey())) {
                        hash.put(entry.getKey(), 0);
                    }
                }
            }
        }
        List<String> res = new LinkedList<>();
        for (Map.Entry<Character, Integer> entry : hash.entrySet()) {
            Integer n = entry.getValue();
            if (n > 0) {
                Character key = entry.getKey();
                for (int i = 0; i < n; i++) {
                    res.add(String.valueOf(key));
                }
            }
        }
        return res;
    }

    private static HashMap<Character, Integer> getFreq(String word) {
        HashMap<Character, Integer> hash = new HashMap<>();
        for (char c : word.toCharArray()) {
            hash.put(c, hash.getOrDefault(c, 0) + 1);
        }
        return hash;
    }

    public static List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> result = new ArrayList<>();
        combinations(1, k, n, new LinkedList<>(), result);
        return result;
    }

    private static void combinations(int start, int k, int n, LinkedList<Integer> ll, List<List<Integer>> result) {
        if (k < 0 || n < 0) return;
        if (n == 0 && k == 0) {
            result.add(new ArrayList<>(ll));
            return;
        }
        for (int i = start; i <= 9; i++) {
            ll.add(i);
            combinations(i + 1, k - 1, n - i, ll, result);
            ll.removeLast(); //backtrack
        }
    }

    public static int[] findMissingAndRepeatedValues(int[][] grid) {
        int last = (int) Math.pow(grid[0].length, 2);
        List<Integer> list = new ArrayList<>();
        for (int[] ints : grid) {
            for (int anInt : ints) {
                list.add(anInt);
            }
        }
        list.sort(Comparator.naturalOrder());
        System.out.println(list);
        int duplicate;
        int[] res = new int[2];
        int sum = 0;
        for (int i = 0; i < list.size(); i++) {
            Integer current = list.get(i);
            System.out.println("Current: " + current);
            if (i < list.size() - 1 && Objects.equals(current, list.get(i + 1))) {
                duplicate = current;
                res[0] = duplicate;
                sum -= current;
            }
            sum += current;
        }
        int missing = (last * (last + 1)) / 2 - sum;
        res[1] = missing;
        return res;
    }

    public static int maxFrequencyElements(int[] nums) {
        int max = Integer.MIN_VALUE;
        HashMap<Integer, Integer> occ = new HashMap<>();
        for (int n : nums) {
            occ.put(n, occ.getOrDefault(n, 0) + 1);
        }
        for (Map.Entry<Integer, Integer> entry : occ.entrySet()) {
            max = Math.max(entry.getValue(), max);
        }
        int sum = 0;
        for (var entry : occ.entrySet()) {
            if (entry.getValue() == max) {
                sum += max;
            }
        }
        return sum;
    }

    public static boolean canSortArray(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n - 1; i++) {
            if ((nums[i] ^ nums[i + 1]) == 0) {
                nums[i] += nums[i + 1];
                nums[i + 1] = nums[i] - nums[i + 1];
                nums[i] = nums[i] - nums[i + 1];
                if (isSorted(nums)) return true;
            }
        }
        return false;
    }

    public static boolean isSorted(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] > nums[i + 1]) return false;
        }
        return true;
    }

    public static boolean canFormArray(int[] arr, int[][] pieces) {
        HashMap<Integer, List<Integer>> pairs = new HashMap<>();
        int n = arr.length;
        for (int[] piece : pieces) {
            List<Integer> temp = new LinkedList<>();
            for (int i : piece) {
                temp.add(i);
            }
            pairs.put(piece[0], temp);
        }
        int index = 0;
        int[] check = new int[n];
        for (int j : arr) {
            if (pairs.containsKey(j)) {
                for (Integer integer : pairs.get(j)) {
                    check[index++] = integer;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            if (arr[i] != check[i]) return false;
        }
        return true;
    }

    public static int minSubArrayLen(int target, int[] nums) {
        int left = 0;
        int sum = 0;
        int n = nums.length;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            sum += nums[i];
            while (sum >= target) {
                min = Math.min(min, i - left + 1);
                sum -= nums[left];
                left++;
            }
        }
        return min != Integer.MAX_VALUE ? 0 : min;
    }

    public static int maxRepeating(String sequence, String word) {
        int n = 0;
        StringBuilder str = new StringBuilder();
        while (str.length() < sequence.length()) {
            str.append(word);
            if (sequence.contains(str)) n++;
        }
        return n;
    }

    public List<List<String>> displayTable(List<List<String>> orders) {
        List<List<String>> result = new LinkedList<>();
        TreeMap<Integer, TreeMap<String, Integer>> res = new TreeMap<>(Integer::compare);
        List<String> first = new LinkedList<>();
        TreeSet<String> sortedFoods = new TreeSet<>(String::compareTo);
        first.add("Table");
        for (List<String> order : orders) {
            sortedFoods.add(order.get(2));
            Integer table = Integer.valueOf(order.get(1));
            res.computeIfAbsent(table, k -> new TreeMap<>(String::compareTo));
            TreeMap<String, Integer> currentMap = res.get(table);
            currentMap.put(order.get(2), currentMap.getOrDefault(order.get(2), 0) + 1);
            res.put(table, currentMap);
        }
        first.addAll(sortedFoods);
        result.add(first);
        for (var entry : res.entrySet()) {
            List<String> temp = new LinkedList<>();
            temp.add(entry.getKey().toString());
            temp.add(entry.getValue().toString());
            result.add(temp);
        }
        System.out.println(result);
        return new LinkedList<>();
    }

    public String replaceWords(List<String> dictionary, String sentence) {
        StringJoiner result = new StringJoiner(" ");
        var strArr = sentence.split(" ");
        HashMap<String, String> flag = new LinkedHashMap<>();
        for (int i = 0; i < strArr.length; i++) {
            var element = strArr[i];
            boolean isAdded = false;
            for (var dic : dictionary) {
                if (element.startsWith(dic) && !element.equals(dic)) {
                    isAdded = true;
                    if (flag.containsKey(element) &&
                            flag.get(element).length() < dic.length()) {
                        continue;
                    }
                    flag.put(element, dic);
                    strArr[i] = flag.get(element);
                }
            }
            if (!isAdded) {
                strArr[i] = element;
            }
            result.add(strArr[i]);
        }
        return result.toString();
    }

    public static List<String> subdomainVisits(String[] cpdomains) {
        HashMap<String, Integer> flag = new HashMap<>();
        for (String cpdomain : cpdomains) {
            Integer count = Integer.parseInt(cpdomain.substring(0, cpdomain.indexOf(" ")));
            String temp = cpdomain.substring(cpdomain.indexOf(" ") + 1);
            do {
                flag.put(temp, flag.getOrDefault(temp, 0) + count);
                if (!temp.contains(".")) break;
                temp = temp.substring(temp.indexOf(".") + 1);
            } while (true);
        }
        List<String> result = new LinkedList<>();
        for (var entry : flag.entrySet()) {
            result.add("%s %s".formatted(entry.getValue(), entry.getKey()));
        }
        return result;
    }

    public static String reverseWords(String s) {
        StringJoiner res = new StringJoiner(" ");
        StringBuilder temp = new StringBuilder();
        for (var c : s.toCharArray()) {
            if (c != ' ') {
                temp.append(c);
            } else if (!temp.isEmpty()) {
                res.add(temp.toString());
                temp = new StringBuilder();
            }
        }
        return res.toString();
    }

    public static String caesarCipher(String s, int k) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (Character.isLetter(s.charAt(i))) {
                var temp = (s.charAt(i) + k) % 122;
                result.append((char) temp);
            } else {
                result.append(s.charAt(i));
            }
        }
        return result.toString();
    }

    public static int findMost(int[] arr) {
        int max = 0;
        int maxCount = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            int count = 0;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] == arr[j]) {
                    count++;
                }
            }
            if (maxCount <= count) {
                maxCount = count;
                max = arr[i];
            }
        }
        return max;
    }
}
