package com.info7255.ebl.service;

import java.util.*;

public class Eratosthenes {

    public static List<Integer> findPrimesInRange(int start, int end) {

        boolean[] isPrime = new boolean[end + 1];
        Arrays.fill(isPrime, true);

        for (int i = 2; i * i <= end; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j <= end; j += i) {
                    isPrime[j] = false;
                }
            }
        }

        List<Integer> primes = new ArrayList<>();
        for (int i = Math.max(2, start); i <= end; i++) {
            if (isPrime[i]) {
                primes.add(i);
            }
        }

        return primes;
    }

    public static void main(String[] args) {
        int start = 10;
        int end = 50;

        List<Integer> primesInRange = findPrimesInRange(start, end);
        System.out.println("Prime numbers between " + start + " and " + end + ":");
        System.out.println(primesInRange);
    }

}
