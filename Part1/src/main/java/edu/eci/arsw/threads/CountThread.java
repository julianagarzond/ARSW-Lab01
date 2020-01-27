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
public class CountThread implements Runnable{

    int inicio;
    int fin;

    public CountThread(int inicio, int fin){
        this.inicio = inicio;
        this.fin = fin;
    }

    @Override
    public void run() {
        for (int i = inicio; i < fin+1; i++){
            System.out.println(i);
        }
    }


}
