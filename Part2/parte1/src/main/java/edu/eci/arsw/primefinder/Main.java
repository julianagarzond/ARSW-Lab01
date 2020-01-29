package edu.eci.arsw.primefinder;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		int start = 0;
		int primes = 0;

		long time = System.currentTimeMillis();
		ArrayList<PrimeFinderThread> threads = new ArrayList<>();

		for (int i = 0; i < 3 ; i++){
			PrimeFinderThread pft=new PrimeFinderThread(start + (10000000 * i), start + (10000000 * i) + 9999999);
			pft.start();
			threads.add(pft);
		}

		while(true){
			if (System.currentTimeMillis() - time >= 5000){
				for(PrimeFinderThread th: threads){
					th.changeActive();
					primes += th.getPrimes().size();
				}
				System.out.println("The amount of prime numbers are: "+ Integer.toString(primes));

				Scanner entrada = new Scanner(System.in);
				entrada.nextLine();
				entrada.close();
				primes = 0;

				for(PrimeFinderThread th: threads){
					th.restart();
				}

				System.out.println("The program finish");
				for(PrimeFinderThread th: threads){
					primes += th.getPrimes().size();
				}
				System.out.println("The amount of prime numbers are: "+ Integer.toString(primes));

				break;

			}
		}


		
	}
	
}
