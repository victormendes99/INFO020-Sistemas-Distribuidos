/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consumidor;

public class Semaphore {

    public Semaphore() {
        count = 0;
    }

    public Semaphore(int i) {
        count = i;
    }

    public synchronized void down() {
        while (count == 0) {
            try {
                wait();
            } catch (InterruptedException _ex) {
            }
        }
        count--;
    }

    public synchronized void up() {
        count++;
        notify();
    }

    protected int count;
}
