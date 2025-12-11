public class WarmupDemo {

    static volatile long sink;

    static long work(int n) {
        long s = 0;
        for (int i = 1; i <= 10_000; i++) {
            s += (i % 3 == 0) ? (long) i * n : (long) i * (n + 1);
        }
        return s;
    }

    public static void main(String[] args) {
        final int ITERS = 100;

        for (int k = 0; k < ITERS; k++) {
            long t0 = System.nanoTime();

            for (int r = 0; r < 200; r++) {
                sink ^= work(k);
            }

            long us = (System.nanoTime() - t0) / 1_000;
            System.out.printf("%d,%d%n", k + 1, us);
        }
    }
}