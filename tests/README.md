# Тесты к курсу «Введение в программирование»

[Условия домашних заданий](http://www.kgeorgiy.info/courses/prog-intro/homeworks.html)

## Домашнее задание 13. Обработка ошибок

Модификации
 * *Базовая*
    * Класс `ExpressionParser` должен реализовывать интерфейс
        [Parser](java/expression/exceptions/Parser.java)
    * Классы `CheckedAdd`, `CheckedSubtract`, `CheckedMultiply`,
        `CheckedDivide` и `CheckedNegate` должны реализовывать интерфейс
        [TripleExpression](java/expression/TripleExpression.java)
    * Нельзя использовать типы `long` и `double`
    * Нельзя использовать методы классов `Math` и `StrictMath`
    * [Исходный код тестов](java/expression/exceptions/ExceptionsTest.java)


## Домашнее задание 12. Разбор выражений

Модификации
 * *Базовая*
    * Класс `ExpressionParser` должен реализовывать интерфейс
        [Parser](java/expression/parser/Parser.java)
    * Результат разбора должен реализовывать интерфейс
        [TripleExpression](java/expression/TripleExpression.java)
    * [Исходный код тестов](java/expression/parser/ParserTest.java)
 * *Shifts*
    * Дополнительно реализуйте бинарные операции:
        * `<<` – сдвиг влево, минимальный приоритет (`1 << 5 + 3` равно `1 << (5 + 3)` равно 256);
        * `>>` – сдвиг вправо, минимальный приоритет (`1024 >> 5 + 3` равно `1024 >> (5 + 3)` равно 4);
    * [Исходный код тестов](java/expression/parser/ParserShiftsTest.java)
 * *ReverseDigits*
    * Реализуйте операции из модификации *Shifts*.
    * Дополнительно реализуйте унарные операции (приоритет как у унарного минуса):
        * `reverse` – число с переставленными цифрами, `reverse -12345` равно -54321;
        * `digits` – сумма цифр числа, `digits -12345` равно 15.
    * [Исходный код тестов](java/expression/parser/ParserReverseDigitsTest.java)
 * *Abs*
    * Реализуйте операции из модификации *Shifts*.
    * Дополнительно реализуйте унарные операции (приоритет как у унарного минуса):
        * `abs` – модуль числа, `abs -5` равно 5;
        * `square` – возведение в квадрат, `square -5` равно 25.
    * [Исходный код тестов](java/expression/parser/ParserAbsSquareTest.java)


## Домашнее задание 11. Выражения

Модификации
 * *Базовая*
    * Реализуйте интерфейс [Expression](java/expression/Expression.java)
    * [Исходный код тестов](java/expression/ExpressionTest.java)
        * Запускать c аргументом `easy` или `hard`
 * *Double*
    * Дополнительно реализуйте интерфейс [DoubleExpression](java/expression/DoubleExpression.java)
    * [Исходный код тестов](java/expression/DoubleExpressionTest.java)
 * *Triple*
    * Дополнительно реализуйте интерфейс [TripleExpression](java/expression/TripleExpression.java)
    * [Исходный код тестов](java/expression/TripleExpressionTest.java)


## Домашнее задание 10. Игра n,m,k

Модификации
 * *Турнир*
    * Добавьте поддержку кругового турнира из _c_ кругов
    * Выведите таблицу очков по схеме:
        * 3 очка за победу
        * 1 очко за ничью
        * 0 очков за поражение
 * *Multiplayer*
    * Добавьте поддержку значков `-` и `|`
    * Добавьте возможность игры для 3 и 4 игроков
 * *Ромб*
    * Добавить поддержку доски в форме ромба
 * *Матчи*
    * Добавьте поддержку матчей: последовательность игр указанного числа побед
    * Стороны в матче должны меняться каждую игру

## Домашнее задание 9. Markdown to HTML

Модификации
 * *Underline*
    * Добавьте поддержку `++подчеркивания++`: `<u>подчеркивания</u>`
    * [Исходный код тестов](java/md2html/Md2HtmlUnderlineTest.java)
    * [Откомпилированные тесты](artifacts/md2html/Md2HtmlUnderlineTest.jar)

 * *Link*
    * Добавьте поддержку ```[ссылок с _выделением_](https://kgeorgiy.info)```:
        ```&lt;a href='https://kgeorgiy.info'>ссылок с &lt;em>выделением&lt;/em>&lt;/a>```
    * [Исходный код тестов](java/md2html/Md2HtmlLinkTest.java)
    * [Откомпилированные тесты](artifacts/md2html/Md2HtmlLinkTest.jar)
 * *Mark*
    * Добавьте поддержку `~выделения цветом~`: `<mark>выделения цветом</mark>`
    * [Исходный код тестов](java/md2html/Md2HtmlMarkTest.java)
    * [Откомпилированные тесты](artifacts/md2html/Md2HtmlMarkTest.jar)
 * *Image*
    * Добавьте поддержку ```![картинок](http://www.ifmo.ru/images/menu/small/p10.jpg)```:
        ```&lt;img alt='картинок' src='http://www.ifmo.ru/images/menu/small/p10.jpg'&gt;```
    * [Исходный код тестов](java/md2html/Md2HtmlImageTest.java)
    * [Откомпилированные тесты](artifacts/md2html/Md2HtmlImageTest.jar)

Исходный код тестов: [Md2HtmlTest.java](java/md2html/Md2HtmlTest.java)

Откомпилированные тесты: [Md2HtmlTest.jar](artifacts/md2html/Md2HtmlTest.jar)


## Домашнее задание 7. Разметка

Модификации
 * *HTML*
    * Дополнительно реализуйте метод `toHtml`, генерирующий HTML-разметку:
      * выделеный текст окружается тегом `em`;
      * сильно выделеный текст окружается тегом `strong`;
      * зачеркнутый текст окружается тегом `s`.
    * [Исходный код тестов](java/markup/HtmlTest.java)
 * *HTML списки*
    * Добавьте поддержку:
      * Нумерованных списков (класс `OrderedList`, тег `ol`): последовательность элементов
      * Ненумерованных списков (класс `UnorderedList`, тег `ul`): последовательность элементов
      * Элементов списка (класс `ListItem`, тег `li`): последовательность абзацев и списков
    * Для новых классов поддержка Markdown не требуется
    * [Исходный код тестов](java/markup/HtmlListTest.java)
 * *TeX*
    * Дополнительно реализуйте метод `toTex`, генерирующий TeX-разметку:
      * выделеный текст заключается в `\emph{` и `}`;
      * сильно выделеный текст заключается в `\textbf{` и `}`;
      * зачеркнутый текст заключается в `\textst{` и `}`.
    * [Исходный код тестов](java/markup/TexTest.java)
 * *Tex списки*
    * Добавьте поддержку:
      * Нумерованных списков (класс `OrderedList`, окружение `enumerate`): последовательность элементов
      * Ненумерованных списков (класс `UnorderedList`, окружение `itemize`): последовательность элементов
      * Элементов списка (класс `ListItem`, тег `\item`: последовательность абзацев и списков
    * Для новых классов поддержка Markdown не требуется
    * [Исходный код тестов](java/markup/TexListTest.java)


Исходный код тестов:

 * [MarkdownTest.java](java/markup/MarkdownTest.java)
 * [AbstractTest.java](java/markup/AbstractTest.java)


## Домашнее задание 6. Подсчет слов++

Модификации
 * *LineIndex*
    * В выходном файле слова должны быть упорядочены в лексикографическом порядке
    * Вместо номеров вхождений во всем файле надо указывать
      `<номер строки>:<номер в строке>`
    * Класс должен иметь имя `WordStatLineIndex`
    * [Исходный код тестов](java/wordStat/WordStatLineIndexTest.java)
    * [Откомпилированные тесты](artifacts/wordStat/WordStatLineIndexTest.jar)
 * *FirstIndex*
    * Вместо номеров вхождений во всем файле надо указывать
      только первое вхождение в каждой строке
    * Класс должен иметь имя `WordStatFirstIndex`
    * [Исходный код тестов](java/wordStat/WordStatFirstIndexTest.java)
    * [Откомпилированные тесты](artifacts/wordStat/WordStatFirstIndexTest.jar)
 * *LastIndex*
    * Вместо номеров вхождений во всем файле надо указывать
      только последнее вхождение в каждой строке
    * Класс должен иметь имя `WordStatLastIndex`
    * [Исходный код тестов](java/wordStat/WordStatLastIndexTest.java)
    * [Откомпилированные тесты](artifacts/wordStat/WordStatLastIndexTest.jar)
 * *SortedLastIndex*
    * В выходном файле слова должны быть упорядочены в лексикографическом порядке
    * Вместо номеров вхождений во всем файле надо указывать
      только последнее вхождение в каждой строке
    * Класс должен иметь имя `WordStatSortedLastIndex`
    * [Исходный код тестов](java/wordStat/WordStatSortedLastIndexTest.java)
    * [Откомпилированные тесты](artifacts/wordStat/WordStatSortedLastIndexTest.jar)

Исходный код тестов:

* [WordStatIndexTest.java](java/wordStat/WordStatIndexTest.java)
* [WordStatIndexChecker.java](java/wordStat/WordStatIndexChecker.java)

Откомпилированные тесты: [WordStatIndexTest.jar](artifacts/wordStat/WordStatIndexTest.jar)


## Домашнее задание 5. Свой сканнер

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


## Домашнее задание 4. Подсчет слов

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


## Домашнее задание 3. Реверс

Модификации
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


## Домашнее задание 2. Сумма чисел

Модификации
  * *Long*
    * Входные данные являются 64-битными целыми числами
    * Класс должен иметь имя `SumLong`
    * [Исходный код тестов](java/sum/SumLongTest.java)
    * [Откомпилированные тесты](artifacts/sum/SumLongTest.jar)
 * *Hex*
    * Входные данные являются 32-битными целыми числами
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


## Домашнее задание 1. Запусти меня!

 1. Скачайте исходный код ([RunMe.java](java/RunMe.java))
 1. Откомпилируйте код (должен получиться `RunMe.class`)
 1. Запустите класс `RunMe` с выданными вам аргументами командной строки
 1. Следуйте выведенной инструкции
