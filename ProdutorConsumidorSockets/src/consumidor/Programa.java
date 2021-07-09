/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consumidor;

import java.util.ArrayList;

public class Programa {

    ArrayList buffer;
    Semaphore mutex;
    Semaphore items;

    protected Programa() {
        buffer = new ArrayList();
        mutex = new Semaphore(1);
        items = new Semaphore();
    }

    int compartilhada;

    public static void main(String[] args) {
        Programa t = new Programa();
        t.run();
    }

    public void run() {
        Producer p = new Producer(this);
        Consumer c = new Consumer(this);

        p.start();
        c.start();

        Producer p1 = new Producer(this);
        Consumer c1 = new Consumer(this);

        p1.start();
        c1.start();

        Producer p2 = new Producer(this);
        Consumer c2 = new Consumer(this);

        p2.start();
        c2.start();

        Producer p3 = new Producer(this);
        Consumer c3 = new Consumer(this);

        p3.start();
        c3.start();

    }

}
