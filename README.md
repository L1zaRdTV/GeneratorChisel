# Генератор уникальных чисел

Android-приложение с экраном генерации уникальных случайных целых чисел.

## Функции

- ввод количества чисел от 1 до 10 000;
- ввод минимального и максимального значения диапазона;
- проверка, что минимум не больше максимума;
- проверка, что диапазон содержит достаточно уникальных значений;
- генерация через `HashSet`, чтобы проверка уникальности выполнялась за один шаг;
- вывод сообщения об ошибке или списка сгенерированных чисел.

## Проверка логики без Android SDK

```bash
gradle compileJava
javac -cp build/classes/java/main -d build/test-classes app/src/test/java/com/example/generatorchisel/UniqueNumberGeneratorTest.java
java -cp build/classes/java/main:build/test-classes com.example.generatorchisel.UniqueNumberGeneratorTest
```
