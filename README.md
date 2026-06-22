# Генератор уникальных чисел

Android-приложение для Android Studio с экраном генерации уникальных случайных целых чисел.

## Функции

- ввод количества чисел от 1 до 10 000;
- ввод минимального и максимального значения диапазона;
- проверка, что минимум не больше максимума;
- проверка, что диапазон содержит достаточно уникальных значений;
- генерация через `HashSet`, чтобы проверка уникальности выполнялась за один шаг;
- вывод сообщения об ошибке или списка сгенерированных чисел.

## Открытие в Android Studio

1. Откройте Android Studio.
2. Выберите **File → Open** и укажите папку проекта `GeneratorChisel`.
3. Дождитесь синхронизации Gradle.
4. Запустите приложение через конфигурацию модуля `app`.

Проект содержит модуль `:app` с Android-плагином Gradle, `AndroidManifest.xml`, Java-кодом активности и ресурсами, поэтому его можно открыть и собрать в Android Studio как обычное Android-приложение.

## Проверка логики без Android SDK

```bash
gradle compileJava
javac -cp build/classes/java/main -d build/test-classes app/src/test/java/com/example/generatorchisel/UniqueNumberGeneratorTest.java
java -cp build/classes/java/main:build/test-classes com.example.generatorchisel.UniqueNumberGeneratorTest
```

## Сборка Android-приложения

```bash
gradle :app:assembleDebug
```
