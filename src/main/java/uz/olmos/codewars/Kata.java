package uz.olmos.codewars;

import java.util.*;
import java.util.stream.Collectors;

public class Kata {

    public static void main(String[] args) {
        filterList(List.of(1, 2, "a", "b"));
    }

    public static List<Object> filterList(final List<Object> list) {
        LinkedHashSet<Integer> flag = new LinkedHashSet<>();
        for (Object o : list) {
            if (isDigit(String.valueOf(o)))
                flag.add((Integer) o);
        }
        var result = new LinkedList<>();
        for (Integer i : flag) {
            result.add((Object) i);
        }
        return result;
    }

    public static boolean isDigit(String str) {
        System.out.println(str);
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) return false;
        }
        return true;
    }
}


