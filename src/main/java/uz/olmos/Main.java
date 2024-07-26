package uz.olmos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(findWords(new String[]{"Hello", "Alaska", "Dad", "Peace"})));
        System.out.println(Arrays.toString(findWords(new String[]{"adsdf", "sfd"})));
        System.out.println(vowelStrings(new String[]{"are", "amy", "dhhdu"}, 0, 2));
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
}