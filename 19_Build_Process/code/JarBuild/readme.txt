Последовательность команд для сборки (из директории JarBuild):

javac -d bin library/src/com/example/*.java

jar cf lib/stringtools.jar -C bin .

javac -cp lib/stringtools.jar -d app/bin app/src/com/example/Main.java


Команда для запуска:

java -cp "app/bin;lib/stringtools.jar" com.example.Main