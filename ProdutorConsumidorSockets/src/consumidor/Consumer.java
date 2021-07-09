/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consumidor;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Consumer extends Thread {

    Programa a;
    boolean continuar;

    public Consumer(Programa x) {

        a = x;
        continuar = true;

    }

    public void run() {

        while (continuar) {
            int item;
            a.items.down();
            a.mutex.down();
            item = (Integer) a.buffer.get(0);
            a.buffer.remove(0);
            a.mutex.up();
            System.out.println("consumer: consuming item " + item);
            for (int i = 0; i < 100000000; i++);
//            try {
//                Thread.sleep(50000);
//                continuar = false;
//            } catch (InterruptedException ex) {
//                Logger.getLogger(Consumer.class.getName()).log(Level.SEVERE, null, ex);
//            }
        }

    }

}
