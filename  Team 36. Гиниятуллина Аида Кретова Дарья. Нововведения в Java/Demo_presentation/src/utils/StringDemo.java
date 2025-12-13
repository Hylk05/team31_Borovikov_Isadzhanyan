package utils;

public class StringDemo {
    public static void main(String[] args) {
        System.out.println("НОВЫЕ МЕТОДЫ ДЛЯ РАБОТЫ СО СТРОКАМИ:");
        System.out.println("-".repeat(35));

        // 1. isBlank() vs isEmpty()
        String empty = "";
        String spaces = "   ";
        String text = "Java";

        System.out.println("1. isBlank() проверяет пустоту ИЛИ только пробелы:");
        System.out.println("   \"\".isEmpty() = " + empty.isEmpty());
        System.out.println("   \"\".isBlank() = " + empty.isBlank());
        System.out.println("   \"   \".isEmpty() = " + spaces.isEmpty());
        System.out.println("   \"   \".isBlank() = " + spaces.isBlank());

        // 2. lines() - разбиение на поток строк
        System.out.println("\n2. lines() - разбивает на поток строк:");
        String multiline = "Первая строка\nВторая строка\nТретья строка";
        System.out.println("   Исходный текст:");
        System.out.println("   \"" + multiline.replace("\n", "\\n") + "\"");

        System.out.println("   Результат lines().count(): " +
                multiline.lines().count());

        // 3. Text Blocks (Java 15+)
        System.out.println("\n3. TEXT BLOCKS (многострочные строки):");

        String json = """
            {
                "name": "Анна",
                "age": 25,
                "city": "Москва"
            }
            """;

        System.out.println("   JSON сохраняет форматирование:");
        System.out.println(json);

        // 4. repeat()
        System.out.println("4. repeat() - повторение строк:");
        System.out.println("   \"Java \".repeat(3) = \"" + "Java ".repeat(3) + "\"");
    }
}