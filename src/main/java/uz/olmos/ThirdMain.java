package uz.olmos;

import java.util.*;

public class ThirdMain {
    public static void main(String[] args) {
        System.out.println(checkDistances("aa", new int[]{1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}));
        System.out.println(decodeMessage("eljuxhpwnyrdgtqkviszcfmabo", "zwx hnfx lqantp mnoeius ycgk vcnjrdb"));
        System.out.println(removeDigit("299858953917872714814599237991174513476623756395992135212546127959342974628712329595771672911914471", '3'));
        reverseString(new char[]{'h', 'e', 'l', 'l', 'o'});
        System.out.println(reverseVowels("race car"));
        System.out.println(countWords(new String[]{"leetcode", "is", "amazing", "as", "is"}, new String[]{"amazing", "leetcode", "is"}));
        System.out.println(kthDistinct(new String[]{"d", "b", "c", "b", "c", "a"}, 2));
        System.out.println(minimumAverage(new int[]{7, 8, 3, 4, 15, 13, 4, 1}));
        System.out.println(distributeCandies(new int[]{6, 6, 6, 6}));
        System.out.println(containsNearbyDuplicate(new int[]{1, 2, 3, 1}, 3));
        System.out.println(slowestKey(new int[]{19, 22, 28, 29, 66, 81, 93, 97}, "fnfaaxha"));
        System.out.println(restoreString("codeleet", new int[]{4, 5, 6, 7, 0, 2, 1, 3}));
        System.out.println(isArraySpecial(new int[]{2, 1, 4}));
    }

    public static boolean checkDistances(String s, int[] distance) {
        int n = s.length();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    if (distance[(int) s.charAt(i) - 97] != (j - i - 1)) return false;
                }
            }
        }
        return true;
    }

    public static String decodeMessage(String key, String message) {
        StringBuilder result = new StringBuilder();
        HashMap<Character, Character> pairs = new HashMap<>();
        int indx = 0;
        int i = 0;
        while (pairs.size() != 26) {
            if (Character.isAlphabetic(key.charAt(i))) {
                if (!pairs.containsKey(key.charAt(i))) {
                    pairs.put(key.charAt(i), (char) ('a' + indx++));
                }
            }
            i++;
        }
        for (char c : message.toCharArray()) {
            if (pairs.containsKey(c))
                result.append(pairs.get(c));
            else result.append(' ');
        }
        return result.toString();
    }

    public static String removeDigit(String number, char digit) {
        int n = number.length();
        Set<Integer> numbs = new HashSet<>();
        for (int i = 0; i < n; i++) {
            if (digit == number.charAt(i)) {
                numbs.add(i);
            }
        }
        var max = "";
        for (int i = 0; i < n; i++) {
            StringBuilder tempNum = new StringBuilder();
            if (numbs.contains(i)) {
                for (int j = 0; j < n; j++) {
                    if (j == i) continue;
                    if (number.charAt(i) != '0')
                        tempNum.append(number.charAt(j));
                }
            }
            max = findMax(max, tempNum.toString());
        }
        return max;
    }

    public static String findMax(String num, String num1) {
        int compareTo = num.compareTo(num1);
        if (compareTo > 0) return num;
        else if (compareTo < 0) {
            return num1;
        }
        return num;
    }

    public static void reverseString(char[] s) {
        int indx = s.length - 1;
        for (int i = 0; i < s.length / 2; i++) {
            char temp = s[i];
            s[i] = s[indx];
            s[indx] = temp;
            indx--;
        }
        System.out.println("After changing: " + Arrays.toString(s));
    }

    public static String reverseVowels(String s) {
        Queue<Character> vowels = new ArrayDeque<>();
        char[] chars = s.toCharArray();
        int n = chars.length;
        for (int i = n - 1; i >= 0; i--) {
            if (isVowel(String.valueOf(chars[i]))) {
                vowels.add(chars[i]);
            }
        }
        StringBuilder result = new StringBuilder();
        for (char aChar : chars) {
            if (isVowel(String.valueOf(aChar))) {
                result.append(vowels.poll());
            } else {
                result.append(aChar);
            }
        }
        return result.toString();
    }

    public static boolean isVowel(String s) {
        s = s.toLowerCase();
        return s.equals("a") || s.equals("e") || s.equals("i") || s.equals("u") || s.equals("o");
    }

    public static int countWords(String[] words1, String[] words2) {
        HashMap<String, Integer> word1Hash = new HashMap<>();
        HashMap<String, Integer> word2Hash = new HashMap<>();
        for (int i = 0; i < Math.max(words1.length, words2.length); i++) {
            if (i < words1.length)
                word1Hash.put(words1[i], word1Hash.getOrDefault(words1[i], 0) + 1);
            if (i < words2.length)
                word2Hash.put(words2[i], word2Hash.getOrDefault(words2[i], 0) + 1);
        }
        int ctr = 0;
        for (Map.Entry<String, Integer> entry : word1Hash.entrySet()) {
            if (word2Hash.containsKey(entry.getKey()) && entry.getValue() == 1) {
                if (entry.getValue().equals(word2Hash.get(entry.getKey()))) {
                    ctr++;
                }
            }
        }
        return ctr;
    }

    public static String kthDistinct(String[] arr, int k) {
        LinkedHashMap<String, Integer> map = new LinkedHashMap<>();
        for (String s : arr) {
            map.put(s, map.getOrDefault(s, 0) + 1);
        }
        List<String> singles = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 1) {
                singles.add(entry.getKey());
            }
        }
        if (singles.size() > k - 1) {
            return singles.get(k - 1);
        }
        return "";
    }

    public int countOperations(int num1, int num2) {
        int ctr = 0;
        while (num1 != 0 && num2 != 0) {
            if (num1 > num2) num1 -= num2;
            else num2 -= num1;
            ctr++;
        }
        return ctr;
    }

    public static double minimumAverage(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        double[] averages = new double[n / 2];
        double min = Double.MAX_VALUE;
        for (int i = 0; i < n / 2; i++) {
            averages[i] = (double) (nums[i] + nums[n - i - 1]) / 2;
            min = Math.min(min, (double) (nums[i] + nums[n - i - 1]) / 2);
        }
        System.out.println(Arrays.toString(averages));
        return min;
    }

    public static int distributeCandies(int[] candyType) {
        int limit = candyType.length / 2;
        HashSet<Integer> types = new HashSet<>();
        for (int i : candyType) {
            types.add(i);
        }
        return Math.min(types.size(), limit);
    }

    public static boolean containsNearbyDuplicate(int[] nums, int k) {
        HashMap<Integer, Integer> ind = new HashMap<>();
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            ind.put(nums[i], Math.abs(ind.getOrDefault(nums[i], 0) - i));
        }
        for (Map.Entry<Integer, Integer> entry : ind.entrySet()) {
            if (entry.getValue() <= k) {
                return true;
            }
        }
        return false;
    }

    public static char slowestKey(int[] releaseTimes, String keysPressed) {
        int n = keysPressed.length();
        int max = releaseTimes[0];
        char c = keysPressed.charAt(0);
        for (int i = 1; i < n; i++) {
            int interval = releaseTimes[i] - releaseTimes[i - 1];
            if (max < interval) {
                max = interval;
                c = keysPressed.charAt(i);
            } else if (max == interval && c < keysPressed.charAt(i)) {
                c = keysPressed.charAt(i);
            }
        }
        return c;
    }

    public static String restoreString(String s, int[] indices) {
        char[] result = new char[indices.length];
        for (int i = 0; i < indices.length; i++) {
            result[indices[i]] = s.charAt(i);
        }
        return new String(result);
    }

    public int smallestEqual(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (i % 10 == nums[i]) return i;
        }
        return -1;
    }

    public boolean wordBreak(String s, List<String> wordDict) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String string : wordDict) {
            stringBuilder.append(string);
        }
        return stringBuilder.toString().contains(s);
    }

    public static boolean isArraySpecial(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            if (i != 0 && !didParity(nums[i], nums[i + 1])) return false;
        }
        return true;
    }

    public static boolean didParity(int n, int n1) {
        return n % 2 == 0 ? n1 % 2 == 1 : n1 % 2 == 0;
    }

    public static boolean canAliceWin(int[] nums) {
        int doubleSum = 0;
        int singleSum = 0;
        for (int num : nums) {
            if (String.valueOf(num).length() == 1) {
                singleSum += num;
                continue;
            }
            doubleSum += num;
        }
        return singleSum != doubleSum;
    }

    public static boolean isSingle(int n) {
        int sum = 0;
        while (n > 0) {
            sum++;
            n /= 10;
        }
        return sum == 1;
    }
}
