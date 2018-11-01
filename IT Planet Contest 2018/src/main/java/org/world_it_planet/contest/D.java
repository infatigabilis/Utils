package org.world_it_planet.contest;

import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class D {

    public static void main(String[] args) {
        int n = 6;
        Set<Integer> k = new HashSet<>(Arrays.asList(2));

//        List<String> list = new CorrectBranchSeq(n).get();
//        System.out.println("total: " + list.size());
//
//        List<String> l2 = new ArrayList<>();
//        for (String s : list) {
//            boolean b = true;
//            for (int i : k)
//                if (s.charAt(i) != '{') b = false;
//
//            if (b) l2.add(s);
//        }
//        System.out.println("test: " + l2.size());


        System.out.println("result: " + new CorrectBranchSeq(n).countWithSection(k));

        System.out.println("1: " + CatalanNumber.get(4));
        System.out.println("2: " + CatalanNumber.get(2));
    }

    static class CorrectBranchSeq {
        private final int n;

        private long count = 0;
        private List<String> list = new ArrayList<>();

        CorrectBranchSeq(int n) {
            this.n = n;
        }

        public List<String> get() {
            char[] str = new char[2*n];
            getParenthesis(str, 0, n, 0, 0);

            return list;
        }

        public long countWithSection(Set<Integer> testPos) {
            count = 0;
            countParenthesisWithSection(0, n, 0, 0, testPos);

            return count;
        }

        private void getParenthesis(char str[], int pos, int n, int open, int close) {
            if(close == n) {
                list.add(new String(str));
            } else {
                if(open > close) {
                    str[pos] = '}';
                    getParenthesis(str, pos+1, n, open, close+1);
                }
                if(open < n) {
                    str[pos] = '{';
                    getParenthesis(str, pos+1, n, open+1, close);
                }
            }
        }

        private void countParenthesisWithSection(int pos, int n, int open, int close, Set<Integer> testPos) {
            if(close == n) {
                count++;
            } else {
                if(open > close && !testPos.contains(pos)) {
                    countParenthesisWithSection(pos+1, n, open, close+1, testPos);
                }
                if(open < n) {
                    countParenthesisWithSection(pos+1, n, open+1, close, testPos);
                }
            }
        }
    }


    static class CatalanNumber {
        private static Map<Integer, Long> cache = new HashMap<>();

        public static long get(int n) {
            if (cache.get(n) != null) return cache.get(n);

            if (n == 1) return 1;
            if (n == 2) return 2;
            if (n == 3) return 5;

            long ans = PartialFactorial.get(2*n - 1, n+2).divide(PartialFactorial.get(n - 1, 3)).longValue();
            cache.put(n, ans);

            return ans;
        }
    }

    static class PartialFactorial {
        private static Map<Pair, BigInteger> cache = new HashMap<>();

        private static BigInteger get(int n, int k) {
            if (n == k) return BigInteger.valueOf(k);

            if (cache.get(new Pair(n, k)) != null) return cache.get(new Pair(n, k));
            else {
                BigInteger ans = BigInteger.valueOf(n).multiply(PartialFactorial.get(n-1, k));
                cache.put(new Pair(n, k), ans);

                return ans;
            }
        }
    }

    static class Pair {
        final int n;
        final int k;

        Pair(int n, int k) {
            this.n = n;
            this.k = k;
        }

        @Override
        public int hashCode() { return 31 * n + k; }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Pair)) return false;
            Pair obj = (Pair) o;
            return n == obj.n && k == obj.k;
        }
    }
}
