public class JitDemo {

    // Небольшой метод
    private static int exampleArithOperator(int x, int y) {
        int r = x + y;

        // Ветвление, чтобы был профиль веток
        if ((r & 1) == 0) {
            r *= 2;
        } else {
            r -= 3;
        }

        return r;
    }

    // Горячий метод, который JIT будет компилировать и
    // внутрь которого заинлайнит exampleArithOperator
    private static int hotLoop(int n) {
        int acc = 0;
        for (int i = 0; i < n; i++) {
            acc += exampleArithOperator(i, 42);
        }
        return acc;
    }

    // volatile, чтобы результат не выкинули как "лишний"
    private static volatile int sink;

    public static void main(String[] args) {
        final int WARMUP_ITERS   = 50_000;  // прогрев

        // Прогрев: тут JIT поймёт, что hotLoop и exampleArithOperator "горячие"
        for (int k = 0; k < WARMUP_ITERS; k++) {
            sink = hotLoop(200);
        }

        System.out.println("Done, sink = " + sink);
    }
}



