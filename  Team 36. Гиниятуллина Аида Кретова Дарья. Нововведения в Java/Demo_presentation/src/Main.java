public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("=== ДЕМОНСТРАЦИЯ ВОЗМОЖНОСТЕЙ ИЗ ПРЕЗЕНТАЦИИ ===\n");

        // 1. Records (Слайды 6-8)
        System.out.println("1. ДЕМОНСТРАЦИЯ RECORDS (JEP 395)");
        System.out.println("================================");
        demo.records.OrderDemo.main(new String[]{});

        // 2. Pattern Matching (Слайды 9-11)
        System.out.println("\n\n2. PATTERN MATCHING И SEALED CLASSES");
        System.out.println("===================================");
        demo.pattern.PatternMatchingDemo.main(new String[]{});

        // 3. Virtual Threads (Слайды 12-14)
        System.out.println("\n\n3. ВИРТУАЛЬНЫЕ ПОТОКИ (JEP 444)");
        System.out.println("==============================");
        demo.threads.VirtualThreadsDemo.main(new String[]{});

        // 4. Новые строковые методы (Слайд 15)
        System.out.println("\n\n4. НОВЫЕ МЕТОДЫ ДЛЯ СТРОК");
        System.out.println("========================");
        utils.StringDemo.main(new String[]{});

        System.out.println("\n=== ВСЕ ДЕМОНСТРАЦИИ ЗАВЕРШЕНЫ ===");
    }
}