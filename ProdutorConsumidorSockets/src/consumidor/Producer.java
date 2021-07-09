/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consumidor;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Producer extends Thread {

    Programa a;
    boolean continuar;
    int contador;

    public Producer(Programa x) {

        a = x;
        contador = 0;
        continuar = true;
    }

    public void run() {

        while (continuar) {
            contador++;
            a.mutex.down();
            a.buffer.add(contador);
            a.mutex.up();
            a.items.up();
            System.out.println("producer: producing item " + contador);
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
