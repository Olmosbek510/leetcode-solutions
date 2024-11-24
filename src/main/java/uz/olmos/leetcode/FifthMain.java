package uz.olmos.leetcode;

import java.util.LinkedList;
import java.util.List;

public class FifthMain {
    public static void main(String[] args) {
        camelMatch(new String[]{"FooBar", "FooBarTest", "FootBall", "FrameBuffer", "ForceFeedBack"},
                "FoBa");
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
}