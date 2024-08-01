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



}
