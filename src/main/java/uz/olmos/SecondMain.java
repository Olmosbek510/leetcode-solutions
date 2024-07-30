package uz.olmos;


import java.util.*;

public class SecondMain {
    public static void main(String[] args) {
        destCity(new ArrayList<>());
        System.out.println(countLargestGroup(264));
        System.out.println(findLucky(new int[]{8}));
        System.out.println(Arrays.toString(findIndices(new int[]{3}, 1, 1)));
        System.out.println(lastVisitedIntegers(new int[]{1, -1, 2, -1, -1}));
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

}
