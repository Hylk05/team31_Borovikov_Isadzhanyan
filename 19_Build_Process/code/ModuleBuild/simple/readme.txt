Команды для сборки (из директории simple):

javac --module-source-path src -d out src\com.example.lib\module-info.java src\com.example.lib\com\example\lib\Utils.java
javac --module-source-path src -d out --module-path out src\com.example.app\module-info.java src\com.example.app\com\example\app\Main.java

Команда для запуска:

java --module-path out -m com.example.app/com.example.app.Main