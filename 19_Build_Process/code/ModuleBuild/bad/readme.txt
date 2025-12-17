Пример для демонстрации ошибки package do not exist.

Команды для сборки (из директории bad):

javac --module-source-path src -d out src\com.example.lib\module-info.java src\com.example.lib\com\example\api\Calculator.java

javac --module-source-path src -d out src\com.example.lib\module-info.java src\com.example.lib\com\example\internal\CalculatorImpl.java

javac --module-source-path src -d out --module-path out src\com.example.app\module-info.java src\com.example.app\com\example\app\Main.java
Команда выше выдаст ошибку "package do not exist", т. к. com.example.internal не экспортируется.
Для корректной работы добавить в com.example.lib/module-info.java строку "exports com.example.internal;".
Альтернативная рабочая реализация в директории ModuleBuild/good

Команда для запуска (если исправлена ошибка):

java --module-path out -m com.example.app/com.example.app.Main