public class DeadlockCorrigido {
    static final Object LOCK_A = new Object();
    static final Object LOCK_B = new Object();

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            System.out.printf("T1: tentando adquirir LOCK_A\n");
            synchronized (LOCK_A) {
                System.out.printf("T1: adquiriu LOCK_A\n");
                dormir(100);
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
            System.out.printf("T2: tentando adquirir LOCK_A\n");
            synchronized (LOCK_A) {
                System.out.printf("T2: adquiriu LOCK_A\n");
                dormir(100);
                System.out.printf("T2: tentando adquirir LOCK_B\n");
                synchronized (LOCK_B) {
                    System.out.printf("T2: adquiriu LOCK_B\n");
                    System.out.printf("T2: seção crítica completa\n");
                }
                System.out.printf("T2: liberou LOCK_B\n");
            }
            System.out.printf("T2: liberou LOCK_A\n");
        }, "T2\n");

        t1.start();
        t2.start();
    }

    static void dormir(long ms) {
        try { Thread.sleep(ms); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }
}


