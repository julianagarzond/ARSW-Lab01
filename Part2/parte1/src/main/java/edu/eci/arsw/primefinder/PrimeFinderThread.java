package edu.eci.arsw.primefinder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;

public class PrimeFinderThread extends Thread {


    int a, b;
    boolean bandera;

    double seconds;

    private List<Integer> primes = new LinkedList<Integer>();

    public PrimeFinderThread(int a, int b) {
        super();
        this.a = a;
        this.b = b;
        bandera = true;

    }

    public PrimeFinderThread(int a, int b, int n) {
        super();
        int nt = n;
        int delta = (int) (b - a) / n;
        bandera = true;
        seconds = System.currentTimeMillis();

        ArrayList<PrimeFinderThread> primeThread = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            PrimeFinderThread primeT;
            //System.out.println(1);
            if (i != n - 1) {
                primeT = new PrimeFinderThread(a + (i * delta), a + (i + delta) + delta);
            } else {
                primeT = new PrimeFinderThread(a + (i * delta), b);
            }
            primeThread.add(primeT);
            primeT.start();
        }

        while (true) {
            if (System.currentTimeMillis() - seconds >= 5000) {
                for (int i = 0; i < primeThread.size(); i++) {
                    synchronized (this) {
                        try {
                            primeThread.get(i).wait();
                            primeThread.get(i).changeBandera();

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                break;
            }
        }


        for (PrimeFinderThread pt : primeThread) {
            for (int i = 0; i < pt.getPrimes().size(); i++) {
                primes.add(pt.getPrimes().get(i));
            }
        }
        System.out.println(primes);
    }

    public void run() {
        for (int i = a; i <= b; i++) {

            if (isPrime(i)) {
                primes.add(i);
                //System.out.println(2);
                //System.out.println(i);
            }
        }


    }

    boolean isPrime(int n) {
        if (n % 2 == 0) return false;
        for (int i = 3; i * i <= n; i += 2) {
            if (n % i == 0)
                return false;
        }
        return true;
    }

    public void changeBandera() {
        this.bandera = !this.bandera;
    }

    public List<Integer> getPrimes() {
        return primes;
    }


}
