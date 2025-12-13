package demo.threads;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class VirtualThreadsDemo {

    // Имитация I/O операции (запрос к БД, HTTP-запрос и т.д.)
    private static void simulateIO(int taskId) {
        try {
            // Имитация блокирующей операции
            Thread.sleep(ThreadLocalRandom.current().nextInt(50, 150));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) throws Exception {
        System.out.println("ДЕМОНСТРАЦИЯ ВИРТУАЛЬНЫХ ПОТОКОВ:");
        System.out.println("-".repeat(35));

        int taskCount = 10_000;
        System.out.println("Запускаем " + taskCount + " I/O задач...\n");

        // 1. Виртуальные потоки
        System.out.println("1. ВИРТУАЛЬНЫЕ ПОТОКИ (Virtual Threads):");
        long startTime = System.currentTimeMillis();
        AtomicInteger virtualCompleted = new AtomicInteger();

        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            for (int i = 0; i < taskCount; i++) {
                int taskId = i;
                executor.submit(() -> {
                    simulateIO(taskId);
                    virtualCompleted.incrementAndGet();
                });
            }
        } // Все потоки завершаются автоматически

        long virtualTime = System.currentTimeMillis() - startTime;
        System.out.println("   Завершено задач: " + virtualCompleted.get());
        System.out.println("   Время выполнения: " + virtualTime + " мс");
        System.out.println("   Потоков создано: " + taskCount + " (легковесные)");

        // 2. Платформенные потоки (для сравнения)
        System.out.println("\n2. ПЛАТФОРМЕННЫЕ ПОТОКИ (Platform Threads):");
        startTime = System.currentTimeMillis();
        AtomicInteger platformCompleted = new AtomicInteger();

        // Ограниченный пул - типичная настройка для платформенных потоков
        ExecutorService platformExecutor = Executors.newFixedThreadPool(200);

        try {
            for (int i = 0; i < taskCount; i++) {
                int taskId = i;
                platformExecutor.submit(() -> {
                    simulateIO(taskId);
                    platformCompleted.incrementAndGet();
                });
            }

            platformExecutor.shutdown();
            platformExecutor.awaitTermination(30, TimeUnit.SECONDS);

        } catch (Exception e) {
            System.err.println("Ошибка: " + e.getMessage());
        }

        long platformTime = System.currentTimeMillis() - startTime;
        System.out.println("   Завершено задач: " + platformCompleted.get());
        System.out.println("   Время выполнения: " + platformTime + " мс");
        System.out.println("   Потоков создано: 200 (пул фиксированного размера)");

        // 3. Сравнение результатов
        System.out.println("\n3. СРАВНЕНИЕ:");
        System.out.println("   Виртуальные потоки быстрее в " +
                String.format("%.1f", (double)platformTime / virtualTime) + " раз");

        System.out.println("\nКлючевое отличие:");
        System.out.println("- Виртуальные потоки: 1 задача = 1 поток (легковесный)");
        System.out.println("- Платформенные потоки: N задач = M потоков (M << N)");
        System.out.println("  => задачи ждут в очереди на свободный поток");
    }
}