import java.util.concurrent.*;

public class DeadlockDemo {
    static final Object LOCK_A = new Object();
    static final Object LOCK_B = new Object();

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            System.out.printf("T1: tentando adquirir LOCK_A\n");
            synchronized (LOCK_A) {
                System.out.printf("T1: adquiriu LOCK_A\n");
                dormir(50); // dá tempo para t2 adquirir LOCK_B
                System.out.printf("T1: tentando adquirir LOCK_B\n");
                synchronized (LOCK_B) {
                    System.out.printf("T1: adquiriu LOCK_B\n");
                    System.out.printf("T1: seção crítica completa\n");
                }
                System.out.printf("T1: liberou LOCK_B\n");
            }
            System.out.printf("T1: liberou LOCK_A\n");
        }, "T1\n");

        Thread t2 = new Thread(() -> {
            System.out.printf("T2: tentando adquirir LOCK_B\n");
            synchronized (LOCK_B) {
                System.out.printf("T2: adquiriu LOCK_B\n");
                dormir(50); // dá tempo para t1 adquirir LOCK_A
                System.out.printf("T2: tentando adquirir LOCK_A\n");
                synchronized (LOCK_A) {
                    System.out.printf("T2: adquiriu LOCK_A\n");
                    System.out.printf("T2: seção crítica completa\n");
                }
                System.out.printf("T2: liberou LOCK_A\n");
            }
            System.out.printf("T2: liberou LOCK_B\n");
        }, "T2\n");

        t1.start();
        t2.start();

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.printf("Main: se o programa ficou travado, ocorreu deadlock (espera circular).");
    }

    static void dormir(long ms) {
        try { Thread.sleep(ms); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }


}
