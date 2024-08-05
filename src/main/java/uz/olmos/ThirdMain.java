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
        System.out.println(Arrays.toString(convertTemperature(36.50)));
        System.out.println(sumOfEncryptedInt(new int[]{10, 21, 31}));
        System.out.println(divisorSubstrings(300, 2));
        System.out.println(largestGoodInteger("1221000"));
        System.out.println(isGood(new int[]{1, 3, 3, 2}));
        System.out.println(Arrays.toString(nextGreaterElement(new int[]{1, 3, 5, 2, 4}, new int[]{6, 5, 4, 3, 2, 1, 7})));
        System.out.println(Arrays.toString(getNoZeroIntegers(2)));
        System.out.println(hammingWeight(11));
        System.out.println(numberOfAlternatingGroups(new int[]{0, 1, 0, 0, 1}));
        System.out.println("Result = " + minDeletionSize(new String[]{"zyx", "wvu", "tsr"}));
        System.out.println(thirdMax(new int[]{3, 2, 1}));
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

    public static double[] convertTemperature(double celsius) {
        return new double[]{celsius + 273.15, celsius * 1.80 + 32.00};
    }

    public static int sumOfEncryptedInt(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += encrypt(num);
        }
        return sum;
    }

    public static int encrypt(int x) {
        if (x < 10) return x;
        int max = Integer.MIN_VALUE;
        int temp = x;
        int result = 0;
        while (x > 0) {
            max = Math.max(max, x % 10);
            x /= 10;
        }
        while (temp > 0) {
            result *= 10;
            int exp = temp % 10;
            result += Math.max(exp, max);
            temp /= 10;
        }
        return result;
    }

    public static int divisorSubstrings(int num, int k) {
        if (num < 10) {
            return k == 1 ? 1 : 0;
        }
        String numStr = String.valueOf(num);
        String substring = numStr.substring(numStr.length() - k);
        int parsed = Integer.parseInt(substring);
        int beauty = 0;
        if (parsed != 0)
            beauty = num % parsed == 0 ? 1 : 0;
        for (int i = 0; i < numStr.length(); i++) {
            if (i != numStr.length() - 1 && numStr.length() > i + k) {
                int temp = Integer.parseInt(numStr.substring(i, i + k));
                if (temp == 0) continue;
                if (num % temp == 0) beauty++;
            }
        }
        return beauty;
    }

    public static String largestGoodInteger(String num) {
        int max = Integer.MIN_VALUE;
        String maxStr = "";
        String numStr = String.valueOf(num);
        if (numStr.length() < 3) {
            return "";
        }
        String last3 = numStr.substring(numStr.length() - 3);
        if (isTheSame(last3)) {
            maxStr = last3;
            max = Integer.parseInt(last3);
        }
        for (int i = 0; i < numStr.length(); i++) {
            if (numStr.length() > i + 3) {
                String subNum = numStr.substring(i, i + 3);
                if (isTheSame(subNum) && Integer.parseInt(subNum) > max) {
                    max = Integer.parseInt(subNum);
                    maxStr = subNum;
                }
            }
        }
        return maxStr;
    }

    public static boolean isTheSame(String str) {
        char first = str.charAt(0);
        for (char c : str.toCharArray()) {
            if (first != c) {
                return false;
            }
        }
        return true;
    }

    public int addedInteger(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int asInt = Arrays.stream(nums1).min().getAsInt();
        int asInt1 = Arrays.stream(nums2).min().getAsInt();
        return asInt1 - asInt;
    }

    public static boolean isGood(int[] nums) {
        int max = Arrays.stream(nums).max().getAsInt();
        if (nums.length != max + 1) return false;
        int[] temp = new int[max + 1];
        for (int i = 0; i < max; i++) {
            temp[i] = i + 1;
        }
        temp[max] = max;
        Arrays.sort(nums);
        Arrays.sort(temp);
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != temp[i]) return false;
        }
        return true;
    }

    public boolean isPossibleToSplit(int[] nums) {
        HashMap<Integer, Integer> hash = new HashMap<>();
        for (int num : nums) {
            hash.put(num, hash.getOrDefault(num, 0) + 1);
        }
        int distinct_count = hash.size(); // Total number of distinct elements
        int half = nums.length / 2;
        return distinct_count >= half;
    }

    public static int[] nextGreaterElement(int[] nums1, int[] nums2) {
        HashMap<Integer, Integer> nums = new HashMap<>();
        for (int i = 0; i < nums2.length; i++) {
            nums.put(nums2[i], i);
        }
        int[] result = new int[nums1.length];
        int indx = 0;
        for (int i : nums1) {
            if (nums.containsKey(i)) {
                int i1 = nums.get(i) + 1;
                int temp = -1;
                while (i1 < nums2.length) {
                    if (nums2[i1] > i) {
                        temp = nums2[i1];
                        break;
                    }
                    i1++;
                }
                result[indx++] = temp;
            }
        }
        return result;
    }

    public static int[] getNoZeroIntegers(int n) {
        int left;
        int right;
        for (int i = 1; i < n; i++) {
            left = i;
            right = n - i;
            if (isNonZero(left) && isNonZero(right)) {
                return new int[]{left, right};
            }
        }
        return new int[]{};
    }

    public static boolean isNonZero(int n) {
        while (n > 0) {
            if (n % 10 == 0) return false;
            n /= 10;
        }
        return true;
    }

    public static int hammingWeight(int n) {
        int ctr = 0;
        while (n > 0) {
            int temp = n % 10;
            if (temp % 2 == 1) ctr++;
            n /= 2;
        }
        return ctr;
    }

    public static int numberOfAlternatingGroups(int[] colors) {
        int n = colors.length;
        int ctr = 0;
        for (int i = 0; i < n; i++) {
            int prev = colors[(i - 1 + n) % n];
            int curr = colors[i];
            int next = colors[(i + 1) % n];
            if (prev != curr && curr != next) {
                ctr++;
            }
        }
        return ctr;
    }

    public static int minDeletionSize(String[] strs) {
        int ctr = 0;
        int n = strs[0].length();
        for (int i = 0; i < n; i++) {
            boolean isSorted = true;
            for (int j = 1; j < strs.length; j++) {
                if (strs[j].charAt(i) < strs[j - 1].charAt(i)) {
                    isSorted = false;
                    break;
                }
            }
            if (!isSorted) ctr++;
        }
        return ctr;
    }

    public static int thirdMax(int[] nums) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int num : nums) {
            hashMap.put(num, hashMap.getOrDefault(num, 0) + 1);
        }
        int max = Integer.MIN_VALUE;
        int sMax = Integer.MIN_VALUE;
        int tMax = Integer.MIN_VALUE;
        for (Map.Entry<Integer, Integer> entry : hashMap.entrySet()) {
            if (entry.getValue() == 1) {
                Integer element = entry.getKey();
                System.out.println("Element: " + element);
                if (element > max) {
                    tMax = sMax;
                    sMax = max;
                    max = element;
                } else if (element > sMax && element != max) {
                    tMax = sMax;
                    sMax = element;
                } else if (element > tMax && element != max && element != sMax) {
                    tMax = element;
                }
            }
        }
        if (tMax == Integer.MIN_VALUE) {
            if(sMax == Integer.MIN_VALUE){
                return max;
            }
            return sMax;
        }
        return tMax;
    }


}
