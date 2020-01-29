package edu.eci.arsw.primefinder;

import java.util.LinkedList;
import java.util.List;

public class PrimeFinderThread extends Thread{

	
	int a,b;
	boolean active;
	
	private List<Integer> primes=new LinkedList<Integer>();
	
	public PrimeFinderThread(int a, int b) {

		super();
		this.a = a;
		this.b = b;
		active = true;
	}

	public void run(){
		for (int i=a;i<=b;i++){
			synchronized (this) {
				if (!active) {
					try {
						wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
			if (isPrime(i)){
				primes.add(i);
			}
		}
	}
	
	boolean isPrime(int n) {
	    if (n%2==0) return false;
	    for(int i=3;i*i<=n;i+=2) {
	        if(n%i==0)
	            return false;
	    }
	    return true;
	}

	public void changeActive(){
		this.active = !this.active;
	}

	public synchronized void restart(){
		this.active = true;
		notify();
	}

	public List<Integer> getPrimes() {
		return primes;
	}
	
	
	
	
}
