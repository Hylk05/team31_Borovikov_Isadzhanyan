Команды для сборки (из директории good):

javac -d out --module-source-path src -m com.example.lib
javac -d out --module-path out --module-source-path src -m com.example.app

Команда для запуска:

java --module-path out -m com.example.app/com.example.app.Main
