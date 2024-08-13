package uz.olmos;

import java.lang.reflect.Array;
import java.math.BigInteger;
import java.util.*;

public class FourthMain {
    public static void main(String[] args) {
        System.out.println(licenseKeyFormatting("2-4A0r7-4k", 4));
        System.out.println(sortVowels("lEetcOde"));
        System.out.println(compress(new char[]{'a', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b'}));
        System.out.println(finalString("poiinter"));
        System.out.println("Result: " + rle("11"));
        System.out.println(countAndSay(4));
        System.out.println(reverse(1534236469));
        System.out.println(reverseStr("abcdefg", 3));
        System.out.println("Result: " + longestPalindrome("abb"));
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
                System.out.println(sub);
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
}
