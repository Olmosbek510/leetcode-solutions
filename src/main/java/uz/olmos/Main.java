package uz.olmos;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntFunction;

public class Main {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(findWords(new String[]{"Hello", "Alaska", "Dad", "Peace"})));
        System.out.println(Arrays.toString(findWords(new String[]{"adsdf", "sfd"})));
        System.out.println(vowelStrings(new String[]{"are", "amy", "dhhdu"}, 0, 2));
        findRestaurant(new String[]{"happy", "sad", "good"}, new String[]{"sad", "happy", "good"});
        System.out.println(uniqueMorseRepresentations(new String[]{"gin", "zen", "gig", "msg"}));
        System.out.println(shortestCompletingWord("1s3 456", new String[]{"looks", "pest", "stew", "show"}));
        System.out.println(Arrays.toString(shortestToChar("loveleetcode", 'e')));
        System.out.println(isAnagram("art", "tar"));
        System.out.println(dominantIndex(new int[]{1, 2, 3, 4}));
        System.out.println(Arrays.toString(sortPeople(new String[]{"Mary", "Emma", "John"}, new int[]{180, 165, 170})));
        System.out.println(maximumValue(new String[]{"alic3", "bob", "3", "4", "00000"}));
        System.out.println(similarPairs(new String[]{"aba", "aabb", "abcd", "bac", "aabc"}));
        System.out.println(countConsistentStrings("abc", new String[]{"a", "b", "c", "ab", "ac", "bc", "abc"}));
        System.out.println(interpret("G()()()()(al)"));
        System.out.println(oddString(new String[]{"aaa","bob","ccc","ddd"}));
    }

    //    500. Keyboard Row
    public static String[] findWords(String[] words) {
        var rows = List.of("qwertyuiop", "asdfghjkl", "zxcvbnm");
        List<String> res = new ArrayList<>();
        int wordsCount = 0;
        for (String word : words) {
            int count = 0;
            for (String row : rows) {
                if (canWrite(row, word)) {
                    count++;
                }
            }
            if (count == 1) {
                res.add(word);
                wordsCount++;
            }
        }
        var result = new String[wordsCount];
        for (int i = 0; i < res.size(); i++) {
            result[i] = res.get(i);
        }
        return result;
    }

    //    500. Keyboard Row helper
    public static boolean canWrite(String row, String str) {
        Hashtable<Character, Integer> hashtable = new Hashtable<>();
        for (char c : str.toLowerCase().toCharArray()) {
            hashtable.put(c, 1);
        }
        for (char c : row.toCharArray()) {
            hashtable.remove(c);
        }
        return hashtable.isEmpty();
    }

    //  2586. Count the Number of Vowel Strings in Range
    public static int vowelStrings(String[] words, int left, int right) {
        int n = words.length;
        int count = 0;
        for (int i = 0; i < n; i++) {
            String current = words[i];
            if (i >= left && i <= right && isVowel(current)) {
                count++;
            }
        }
        return count;
    }

    //  2586. Count the Number of Vowel Strings in Range. Helper
    public static boolean isVowel(String word) {
        char first = word.charAt(0);
        char last = word.charAt(word.length() - 1);
        return (first == 'a' || first == 'e' || first == 'i' || first == 'o' || first == 'u') && (
                last == 'a' || last == 'e' || last == 'i' || last == 'o' || last == 'u'
        );
    }

    public static String[] findRestaurant(String[] list1, String[] list2) {
        int size = Integer.MAX_VALUE;
        Hashtable<String, Integer> hashtable = new Hashtable<>();
        for (int i = 0; i < list1.length; i++) {
            for (int j = 0; j < list2.length; j++) {
                if (list1[i].equals(list2[j])) {
                    hashtable.put(list1[i], i + j);
                    size = Math.min(size, i + j);
                }
            }
        }
        List<String> words = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : hashtable.entrySet()) {
            if (entry.getValue().equals(size)) {
                words.add(entry.getKey());
            }
        }
        var result = new String[words.size()];
        for (int i = 0; i < words.size(); i++) {
            result[i] = words.get(i);
        }
        return result;
    }

    //  804. Unique Morse Code Words
    public static int uniqueMorseRepresentations(String[] words) {
        HashSet<String> morses = new HashSet<>();
        for (String word : words) {
            String morse = convertToMorse(word);
            morses.add(morse);
        }
        return morses.size();
    }

    //  804. Unique Morse Code Words
    public static HashMap<Character, String> getMorsePairs() {
        HashMap<Character, String> morsePairs = new HashMap<>();
        var morse = new String[]{".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--.."};
        int ctr = 0;
        for (int i = 97; i < 123; i++) {
            morsePairs.put((char) i, morse[ctr++]);
        }
        return morsePairs;
    }

    //  804. Unique Morse Code Words
    static HashMap<Character, String> MORSE = getMorsePairs();

    //  804. Unique Morse Code Words
    public static String convertToMorse(String string) {
        StringBuilder result = new StringBuilder();
        for (char c : string.toCharArray()) {
            if (MORSE.containsKey(c)) {
                result.append(MORSE.get(c));
            }
        }
        return result.toString();
    }

    //  748. Shortest Completing Word
    public static String shortestCompletingWord(String licensePlate, String[] words) {
        StringBuilder filteredStr = new StringBuilder();
        String result = "";
        int min = Integer.MAX_VALUE;
        for (char c : licensePlate.toLowerCase().toCharArray()) {
            if (Character.isAlphabetic(c)) {
                filteredStr.append(c);
            }
        }
        for (String word : words) {
            if (isComplete(filteredStr.toString(), word) && word.length() < min) {
                min = word.length();
                result = word;
            }
        }
        return result;
    }

    //  748. Shortest Completing Word
    public static boolean isComplete(String lPlate, String word) {
        HashMap<Character, Integer> lMap = new HashMap<>();
        for (char c : lPlate.toCharArray()) {
            lMap.put(c, lMap.getOrDefault(c, 0) + 1);
        }
        for (char c : word.toCharArray()) {
            if (lMap.containsKey(c)) {
                lMap.put(c, lMap.get(c) - 1);
                if (lMap.get(c) == 0) {
                    lMap.remove(c);
                }
            }
        }
        return lMap.isEmpty();
    }

    //  821. Shortest Distance to a Character
    public static int[] shortestToChar(String s, char c) {
        int l = s.length();
        int[] res = new int[l];
        for (int i = 0; i < l; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < l; j++) {
                if (j == i) continue;
                if (s.charAt(j) == c) {
                    min = Math.min(Math.abs(i - j), min);
                }
                if (s.charAt(i) == c) min = 0;
            }
            res[i] = min;
        }
        return res;
    }

    //    747. Largest Number At Least Twice of Others
    public static int dominantIndex(int[] nums) {
        int maxIndex = -1;
        int count = 0;
        int max = Arrays.stream(nums).max().getAsInt();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == max) {
                for (int j = 0; j < nums.length; j++) {
                    if (j == i) continue;
                    if ((max / 2) >= nums[j]) {
                        count++;
                    }
                }
                if (count == nums.length - 1) {
                    maxIndex = i;
                }
            }
        }
        return maxIndex;
    }

    //    2418. Sort the People
    public static String[] sortPeople(String[] names, int[] heights) {
        TreeMap<Integer, String> nHeight = new TreeMap<>((o1, o2) -> o2 - o1);
        int n = names.length;
        for (int i = 0; i < n; i++) {
            nHeight.put(heights[i], names[i]);
        }
        AtomicInteger i = new AtomicInteger(0);
        nHeight.forEach((key, value) -> names[i.getAndIncrement()] = value);
        ;
        return names;
    }

    public static boolean isAnagram(String s, String t) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        for (char c : t.toCharArray()) {
            if (map.containsKey(c)) {
                map.put(c, map.get(c) - 1);
                if (map.get(c) == 0) {
                    map.remove(c);
                }
            }
        }
        return map.isEmpty();
    }

    //  2496. Maximum Value of a String in an Array
    public static int maximumValue(String[] strs) {
        int max = Integer.MIN_VALUE;
        for (String str : strs) {
            max = Math.max(max, maximumSize(str));
        }
        return max;
    }

    //  2496. Maximum Value of a String in an Array. Helper
    public static Integer maximumSize(String word) {
        try {
            return Integer.parseInt(word);
        } catch (NumberFormatException e) {
            return word.length();
        }
    }

    //  2506. Count Pairs Of Similar Strings
    public static int similarPairs(String[] words) {
        int n = words.length;
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (isPair(words[i], words[j])) {
                    count++;
                }
            }
        }
        return count;
    }

    //  2506. Count Pairs Of Similar Strings. Helper
    public static boolean isPair(String word, String word1) {
        return collectSet(word).equals(collectSet(word1));
    }

    //  2506. Count Pairs Of Similar Strings. Helper
    public static TreeSet<Character> collectSet(String word) {
        TreeSet<Character> set = new TreeSet<>();
        for (char c : word.toCharArray()) {
            set.add(c);
        }
        return set;
    }

    public static int countConsistentStrings(String allowed, String[] words) {
        int counter = 0;
        HashSet<Character> allowedSet = new HashSet<>();
        for (char c : allowed.toCharArray()) {
            allowedSet.add(c);
        }
        for (String word : words) {
            boolean flag = true;
            for (char c : word.toCharArray()) {
                if (!allowedSet.contains(c)) {
                    flag = false;
                    break;
                }
            }
            if (flag) counter++;
        }
        return counter;
    }

    //  1678. Goal Parser Interpretation
    public static String interpret(String command) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < command.length(); i++) {
            if (command.charAt(i) == 'G') {
                res.append(command.charAt(i));
            }
            if (command.charAt(i) == '(') {
                if (command.charAt(i + 1) == ')') {
                    res.append('o');
                } else {
                    res.append("al");
                }
            }
        }
        return res.toString();
    }

//  2451. Odd String Difference
    public static String oddString(String[] words) {
        HashMap<List<Integer>, Integer> hash = new HashMap<>();
        for (String word : words) {
            Integer[] diff = getDifference(word);
            hash.put(Arrays.stream(diff).toList(), hash.getOrDefault(Arrays.stream(diff).toList(), 0) + 1);
        }
        for (var word : words) {
            var diff = getDifference(word);
            System.out.println(Arrays.toString(diff));
            if (hash.get(Arrays.stream(diff).toList()) == 1) {
                return word;
            }
        }
        return "";
    }

//  2451. Odd String Difference. Helper
    public static Integer[] getDifference(String str) {
        var n = str.length();
        Integer[] arr = new Integer[n - 1];
        for (int i = 0; i < n - 1; i++) {
            arr[i] = (int) str.charAt(i + 1) - (int) str.charAt(i);
        }
        return arr;
    }


}