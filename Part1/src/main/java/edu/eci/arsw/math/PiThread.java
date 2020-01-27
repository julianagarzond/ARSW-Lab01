package edu.eci.arsw.math;

import java.util.ArrayList;

public class PiThread implements Runnable {

    byte[] answer ;
    int start;
    int count;

    public PiThread(int start, int count){
        this.start=start;
        this.count=count;
    }

    @Override
    public void run() {
        answer=PiDigits.getDigits(start,count);
    }

    public byte[] getAnswer(){
        return answer;
    }


}
