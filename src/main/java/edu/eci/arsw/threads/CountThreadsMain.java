/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.threads;

/**
 *
 * @author hcadavid
 */
public class CountThreadsMain {
    
    public static void main(String a[]){
        CountThread thread1 = new CountThread(0,99);
        CountThread thread2 = new CountThread(100,199);
        CountThread thread3 = new CountThread(200,299);
        Thread th1=new Thread(thread1);
        Thread th2=new Thread(thread2);
        Thread th3=new Thread(thread3);
        th1.start();
        th2.start();
        th3.start();

    }
    
}
