# Домашние работы к курсу «Введение в программирование»

[Условия домашних заданий](http://www.kgeorgiy.info/courses/prog-intro/homeworks.html)


Домашнее задание 6. Подсчет слов++
----

Исходный код тестов:

* [WordStatIndexTest.java](java/wordStat/WordStatIndexTest.java)
* [WordStatIndexChecker.java](java/wordStat/WordStatIndexChecker.java)

Откомпилированные тесты: [WordStatIndexTest.jar](artifacts/wordStat/WordStatIndexTest.jar)


[Домашнее задание 5. Свой сканнер](hw5)
----

Модификации
 * *Transpose*
    * Рассмотрим входные данные как (не полностью определенную) матрицу,
      выведите ее в транспонированном виде
    * Класс должен иметь имя `ReverseTranspose`
    * [Исходный код тестов](java/reverse/FastReverseTransposeTest.java)
    * [Откомпилированные тесты](artifacts/reverse/FastReverseTransposeTest.jar)
 * *Sort*
    * Строки должны быть отсортированы по сумме в обратном порядке
      (при равенстве сумм – в порядке обратном следованию во входе).
      Числа в строке так же должны быть отсортированы в обратном порядке.
    * [Исходный код тестов](java/reverse/FastReverseSortTest.java)
    * [Откомпилированные тесты](artifacts/reverse/FastReverseSortTest.jar)
 * *Min*
    * Рассмотрим входные данные как (не полностью определенную) матрицу,
      вместо каждого числа выведите минимум из чисел в его столбце и строке
    * Класс должен иметь имя `ReverseMin`
    * [Исходный код тестов](java/reverse/FastReverseMinTest.java)
    * [Откомпилированные тесты](artifacts/reverse/FastReverseMinTest.jar)

Исходный код тестов:

* [FastReverseTest.java](java/reverse/FastReverseTest.java)

Откомпилированные тесты: [FastReverseTest.jar](artifacts/reverse/FastReverseTest.jar)


[Домашнее задание 4. Подсчет слов](hw4)
----

Модификации
 * *Words*
    * В выходном файле слова должны быть упорядочены в лексикографическом порядке
    * Класс должен иметь имя `WordStatWords`
    * [Исходный код тестов](java/wordStat/WordStatWordsTest.java)
    * [Откомпилированные тесты](artifacts/wordStat/WordStatWordsTest.jar)
 * *Sort*
    * Пусть _n_ – число слов во входном файле,
      тогда программа должна работать за O(_n_ log _n_).
 * *Count*
    * В выходном файле слова должны быть упорядочены по возрастанию числа
      вхождений, а при равном числе вхождений – по порядку первого вхождения
      во входном файле
    * Класс должен иметь имя `WordStatCount`
    * [Исходный код тестов](java/wordStat/WordStatCountTest.java)
    * [Откомпилированные тесты](artifacts/wordStat/WordStatCountTest.jar)

Исходный код тестов:

* [WordStatInputTest.java](java/wordStat/WordStatInputTest.java)
* [WordStatChecker.java](java/wordStat/WordStatChecker.java)

Откомпилированные тесты: [WordStatInputTest.jar](artifacts/wordStat/WordStatInputTest.jar)


[Домашнее задание 3. Реверс](hw3)
----

Модификации:
 * *Even*
    * Выведите только четные числа (в реверсивном порядке)
    * Класс должен иметь имя `ReverseEven`
    * [Исходный код тестов](java/reverse/ReverseEvenTest.java)
    * [Откомпилированные тесты](artifacts/reverse/ReverseEvenTest.jar)
 * *Linear*
    * Пусть _n_ – сумма числа чисел и строк во входе,
      тогда программе разрешается потратить не более 5_n_+O(1) памяти
 * *Sum*
    * Рассмотрим входные данные как (не полностью определенную) матрицу,
      вместо каждого числа выведите сумму чисел в его столбце и строке
    * Класс должен иметь имя `ReverseSum`
    * [Исходный код тестов](java/reverse/ReverseSumTest.java)
    * [Откомпилированные тесты](artifacts/reverse/ReverseSumTest.jar)

Исходный код тестов:

* [ReverseTest.java](java/reverse/ReverseTest.java)
* [ReverseChecker.java](java/reverse/ReverseChecker.java)

Откомпилированные тесты: [ReverseTest.jar](artifacts/reverse/ReverseTest.jar)


[Домашнее задание 2. Сумма чисел](hw2)
----

Модификации
  * *Long*
    * Входные данные являются 64-битными целыми числами
    * Класс должен иметь имя `SumLong`
    * [Исходный код тестов](java/sum/SumLongTest.java)
    * [Откомпилированные тесты](artifacts/sum/SumLongTest.jar)
 * *Hex*
    * Шестнадцатеричные числа имеют префикс `0x`
    * Класс должен иметь имя `SumHex`
    * [Исходный код тестов](java/sum/SumHexTest.java)
    * [Откомпилированные тесты](artifacts/sum/SumHexTest.jar)
 * *Double*
    * Входные данные являются 64-битными числами с формате с плавающей точкой
    * Класс должен иметь имя `SumDouble`
    * [Исходный код тестов](java/sum/SumDoubleTest.java)
    * [Откомпилированные тесты](artifacts/sum/SumDoubleTest.jar)
 * *LongHex*
    * Входные данные являются 64-битными целыми числами
    * Шестнадцатеричные числа имеют префикс `0x`
    * Класс должен иметь имя `SumLongHex`
    * [Исходный код тестов](java/sum/SumLongHexTest.java)
    * [Откомпилированные тесты](artifacts/sum/SumLongHexTest.jar)

Для того, чтобы протестировать исходную программу:

 1. Скачайте откомпилированные тесты ([SumTest.jar](artifacts/sum/SumTest.jar))
 * Откомпилируйте `Sum.java`
 * Проверьте, что создался `Sum.class`
 * В каталоге, в котором находится `Sum.class` выполните команду
    ```
       java -jar <путь к SumTest.jar>
    ```
    * Например, если `SumTest.jar` находится в текущем каталоге, выполните команду
    ```
        java -jar SumTest.jar
    ```

Исходный код тестов:

* [SumTest.java](java/sum/SumTest.java)
* [SumChecker.java](java/sum/SumChecker.java)
* [Базовые классы](java/base/)


[Домашнее задание 1. Запусти меня!](hw1)
----

 1. Скачайте исходный код ([RunMe.java](java/RunMe.java))
 1. Откомпилируйте код (должен получиться `RunMe.class`)
 1. Запустите класс `RunMe` с выданными вам аргументами командной строки
 1. Следуйте выведенной инструкции
