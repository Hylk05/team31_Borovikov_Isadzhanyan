import java.util.*;
import java.util.stream.Collectors;

class Exmaple3 {
    static class Student {
        String name;
        double grade;
        String group;

        Student(String name, double grade, String group) {
            this.name = name;
            this.grade = grade;
            this.group = group;
        }
    }

    public static void main(String[] args) {
        List<Student> students = Arrays.asList(
                new Student("Анна", 4.5, "A"),
                new Student("Борис", 3.8, "B"),
                new Student("Сергей", 4.2, "A"),
                new Student("Дарья", 5.0, "B"),
                new Student("Евгений", 3.5, "A"),
                new Student("Ольга", 4.8, "B"),
                new Student("Иван", 4.0, "A")
        );

        System.out.println("ЗАДАЧА: Получить топ-2 студента по оценке по успеваемости, \n" +
                "        преобразовать имена в верхний регистр, \n" +
                "        отсортировать по имени");

        Map<String, List<String>> result = students.stream()
                .sorted((s1, s2) -> Double.compare(s2.grade, s1.grade)) // сортировка по убыванию оценки
                .collect(Collectors.groupingBy(
                        s -> {  // ← ИЗМЕНЕНИЕ (логика категоризации)
                            if (s.grade >= 4.5) return "Отличники";
                            if (s.grade >= 4.0) return "Хорошисты";
                            return "Удовлетворительно";
                        },

                        Collectors.collectingAndThen(
                                        Collectors.toList(),
                                        list -> list.stream()
                                                .limit(2)                           // топ-2 из группы
                                                .map(s -> s.name.toUpperCase())     // в верхний регистр
                                                .sorted()                      // сортировка по имени
                                                .collect(Collectors.toList())
                                )
                        )

                );

        result.forEach((group, names) ->
                System.out.println("Группа " + group + ": " + names));


    };

}

