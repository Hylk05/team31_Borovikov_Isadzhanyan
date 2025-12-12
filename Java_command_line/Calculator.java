public class Calculator {
    public static void main(String[] args) {
        if (args.length != 3) {
            System.err.println("Использование: java Calculator <число1> <операция> <число2>");
            System.err.println("Пример: java Calculator 10 + 5");
            return;
        }
        try {
            double a = Double.parseDouble(args[0]);
            String op = args[1];
            double b = Double.parseDouble(args[2]);
            double result = switch (op) {
                case "+" -> a + b;
                case "-" -> a - b;
                case "*" -> a * b;
                case "/" -> {
                    if (b == 0) {
                        throw new IllegalArgumentException("Деление на ноль запрещено");
                    }
                    yield a / b;
                }
                default -> throw new IllegalArgumentException("Неизвестная операция: " + op);
            };
            System.out.println("Результат: " + a + " " + op + " " + b + " = " + result);
        } catch (NumberFormatException e) {
            System.err.println("Ошибка: введены некорректные числа. Используйте формат, например: 3.14");
        } catch (IllegalArgumentException e) {
            System.err.println("Ошибка: " + e.getMessage());
        }
    }
}

