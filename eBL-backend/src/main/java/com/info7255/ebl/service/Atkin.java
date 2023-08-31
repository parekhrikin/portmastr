package com.info7255.ebl.service;

import java.util.*;

public class Atkin {

    public static List<Integer> findPrimes(int limit) {
        boolean[] isPrime = new boolean[limit + 1];
        Arrays.fill(isPrime, false);

        // 2 and 3 are known primes
        isPrime[2] = true;
        isPrime[3] = true;

        for (int x = 1; x * x <= limit; x++) {
            for (int y = 1; y * y <= limit; y++) {
                int n = (4 * x * x) + (y * y);
                if (n <= limit && (n % 12 == 1 || n % 12 == 5)) {
                    isPrime[n] = !isPrime[n];
                }

                n = (3 * x * x) + (y * y);
                if (n <= limit && n % 12 == 7) {
                    isPrime[n] = !isPrime[n];
                }

                n = (3 * x * x) - (y * y);
                if (x > y && n <= limit && n % 12 == 11) {
                    isPrime[n] = !isPrime[n];
                }
            }
        }

        for (int n = 5; n * n <= limit; n++) {
            if (isPrime[n]) {
                for (int k = n * n; k <= limit; k += n * n) {
                    isPrime[k] = false;
                }
            }
        }

        List<Integer> primes = new ArrayList<>();
        for (int i = 2; i <= limit; i++) {
            if (isPrime[i]) {
                primes.add(i);
            }
        }

        return primes;
    }

    public static void main(String[] args) {
        int limit = 50;

        List<Integer> primes = findPrimes(limit);
        System.out.println("Prime numbers up to " + limit + ":");
        System.out.println(primes);
    }

}
