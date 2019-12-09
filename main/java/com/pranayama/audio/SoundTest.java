
package com.pranayama.audio;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class SoundTest implements Runnable {
    
    boolean start = false;
    int cnt = -1;

    @Override
    public void run() {
            while (start) {
                
                try {
                    cnt++;
                    Thread.currentThread().sleep(1000);
                    Sound.playSound("./src/main/recources/sound/snd1_ok.wav").join();
                    System.out.println("cnt:" + cnt);
                } catch (InterruptedException ex) {}
            }
      
    }
    
     public static void main(String[] args) throws IOException {
         
           SoundTest st = new SoundTest();
           st.start = true;
           Thread t = new Thread(st);
           t.start();
           
             
         // Sound.playSound("./src/main/recources/sound/snd1_ok.wav").join(); //ok
//          Sound.playSound("./src/main/java/com/sound_test/new_event.wav").join();
    }
    
}
