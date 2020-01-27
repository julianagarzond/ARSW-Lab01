package edu.eci.arsw.primefinder;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class PrimeFinderThread extends Thread{

	
	int a,b;
	
	private List<Integer> primes=new LinkedList<Integer>();
	
	public PrimeFinderThread(int a, int b) {
		super();
		this.a = a;
		this.b = b;

	}

	public PrimeFinderThread(int a, int b,int n) {
		super();
		int nt = n ;
		int delta=(int)(b-a)/n;


		ArrayList<PrimeFinderThread> primeThread =new ArrayList<>();
		ArrayList<Thread> threads =new ArrayList<>();

		for(int i=0 ; i< n;i++){
			PrimeFinderThread primet;
			//System.out.println(1);
			if(i!=n-1){
				primet =new PrimeFinderThread(a+(i*delta),a+(i+delta)+delta);
			}else{
				primet=new PrimeFinderThread(a+(i*delta),b);
			}
			primeThread.add(primet);
			primet.start();
		}
		 for(Thread th:threads){
			 try {
				 th.join();
			 } catch (InterruptedException e) {
				 e.printStackTrace();
			 }
		 }

		 for(PrimeFinderThread pt:primeThread) {
			 for (int i = 0; i < pt.getPrimes().size(); i++) {
				 primes.add(pt.getPrimes().get(i));
			 }
		 }
	}

	public void run(){
		for (int i=a;i<=b;i++){						
			if (isPrime(i)){
				primes.add(i);
				//System.out.println(2);
				System.out.println(i);
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

	public void keyPressed(KeyEvent e){

		int key =e.getKeyCode();
		if(key==KeyEvent.VK_ENTER){
			if(!currentThread().isAlive()){

			}
		}
	}

	public List<Integer> getPrimes() {
		return primes;
	}
	
	
	
	
}
