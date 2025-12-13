// Java 7: Анонимный класс
Comparator<Integer> oldWay = new Comparator<Integer>() {
    @Override
    public int compare(Integer a, Integer b) {
        return a.compareTo(b);
    }
};

// Java 8+: Лямбда-выражение
Comparator<Integer> newWay = (a, b) -> a.compareTo(b);

Function<String, Integer> parser = Integer::parseInt;

String prefix = "Mr. ";
Function<String, String> addPrefix = prefix::concat;

Function<String, String> upper = String::toUpperCase;

Supplier<List<String>> listSupplier = ArrayList::new;

List<String> result = transactions.stream()
    .filter(t -> t.getType() == Transaction.Type.CREDIT)
    .filter(t -> t.getAmount() > 1000)
    .sorted(comparing(Transaction::getDate).reversed())
    .map(Transaction::getDescription)
    .distinct()
    .limit(10)
    .collect(Collectors.toList());

.collect(Collectors.toList())
.collect(Collectors.toSet())
.collect(Collectors.toMap(keyMapper, valueMapper))
.collect(Collectors.toCollection(ArrayList::new))

.collect(Collectors.groupingBy(Product::getCategory))
.collect(Collectors.groupingBy(
    Product::getCategory,
    Collectors.counting()
))

.collect(Collectors.partitioningBy(p -> p.getPrice() > 100))

.collect(Collectors.summingInt(Product::getQuantity))
.collect(Collectors.averagingDouble(Product::getPrice))
.collect(Collectors.summarizingInt(Product::getQuantity))

.collect(Collectors.joining(", ", "[", "]"))


// Вместо иерархии классов
Validator numericValidator = s -> s.matches("\\d+");
Validator emailValidator = s -> s.matches("^[A-Za-z0-9+_.-]+@(.+)$");

// Использование
validate(input, numericValidator);


Function<String, String> pipeline = ((Function<String, String>) String::trim)
    .andThen(String::toLowerCase)
    .andThen(s -> s.replace(" ", "_"));

String result = pipeline.apply("  Hello World  ");



Supplier<ExpensiveObject> lazySupplier = () -> {
    ExpensiveObject obj = new ExpensiveObject();
    // тяжелая инициализация
    return obj;
};

// Объект создается только при первом вызове get()
Lazy<ExpensiveObject> lazy = Lazy.of(lazySupplier);



