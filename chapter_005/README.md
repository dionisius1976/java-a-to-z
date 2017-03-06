# Chapter_005 "Collections"
1. Необходимо создать интератор для двухмерного массива.
 
int[][] value = {
   {1, 2}
   {3, 4}
};
 
метод next = должен вернуть последовательно 1, 2, 3, 4

2. Реализовать интерфейс с методом Iterator<Integer> convert(Iterator<Iterator<Integer>> it).

Что из себя представляет запись Iterator<Iterator<Integer>?.

Каждый итератор это последовательность.

Итератор 1 – 4 2 0 4 6 4 9

Итератор 2 – 0 9 8 7 5

Итератор 3 – 1 3 5 6 7 0 9 8 4

Если мы говорим о записи Итератор Итераторов. Значит итератор содержит не конечные значения, а сложенные итераторы.

Итератор - Итератор 1, Итератор 2, Итератор 3.

Метод convert должен принимать объект итератор итератор и возвращать Итератор чисел.

Iterator<Iterator<Integer> - ((4 2 0 4 6 4 9), (0 9 8 7 5), (1 3 5 6 7 0 9 8 4))




Метод должен возвращать

Iterator<Integer> - (4 2 0 4 6 4 9 0 9 8 7 5 1 3 5 6 7 0 9 8 4)

Метод не должен копировать данные. Нужно реализовать итератор, который будет пробегать по вложенными итераторам без копирования данных.

3. 




7_Additional_XML_Parsing:


«Order book» task

Before you start

Please follow these instructions super accurately.

Develop the solution in Java (you can use Maven as a build tool if you want)
The core of the solution should be written without any add-ons to the core of the programming language. Use only the defined language standard (e.g. you can use all features that you find in a standard JDK). This means that you can use external libraries, but your program should still produce the required output after removing those parts. For example, if you would decide to use a logging library then your program would still work correctly after removing all parts that use the logging library. You can also use JUnit.
Optimize for quality of development and execution speed.
-          In general execution time on PC with HDD should be around 6 seconds.

-          Be ready to explain and protect all performance optimizations (if there will be any).

Deliver your result in the best professional quality you can produce. Polish your solution. Make a master piece out of it.  It is part of this task to compare what different people consider to be a professional quality solution.
 
Task Description

Program should create Order Books according to orders from XML file:

https://yadi.sk/d/cciS7Dqy35uQjU

Two actions supported: new order, delete exiting order. And after processing the whole file, print to standard output all order books generated.

What is Order Book?

Order Book is basically a combination of bid ladder and ask ladder. Bid ladder consists from buy orders; ask ladder consists from sell orders. See example order book and format of output for your program:

Order book: ${order_book_name}

BID                  ASK

Volume@Price – Volume@Price

10@100.1  – 11@100.2

4@100.0    – 14@100.21

98@99.98  – 14@100.23

-----------      – 12@101.00

Bid ladder is sorted from the highest bid price at the top to lowest price at the bottom.

Ask ladder – visa versa, sorted from lowest at the top to highest price at the bottom.

Note: when printing out resulted order books - quantity for all orders with the same price should be aggregated. In other words – on each side (bid / ask) of order book can have only one level with particular price.

Note: You don’t need to check XML file for correctness and implement any safety checks.

Note: There are several different order books in XML file (see ‘book’ attribute)

Matching logic:

When new bid/ask order is added into order book you should check opposite book side for matching. Checking always starts from the top of opposite side. To get execution (matching) price of counter orders must overlap (bid>=ask). For example:

bid order at 4.4 matches ask order at 4.3
ask order at 3.7 matches bid order at 3.8
opposite side orders with same price matches as well.
 

Matching is a process of execution two counter orders. When orders are matched they current volume is decreased by minimum current volumes of these orders:  volume_executed=min(ask_order,bid_order).  So when there is a match you need to adjust orders quantities. There could be three cases:

Order existing in order book fully filled by incoming order à remove existing, adjust quantity of incoming order and add it
Incoming order fully filled by existing order à adjust quantity of existing order
Full quantity match à Remove existing order
 
Also

Describe do’s and don’ts of your solution, what could be done to speed it up further, improve, simplify?









8_Additional_Islands

* This program counts the sum of all cells with value 1 (earth) that bordering
 * each other at horizontal or at vertical in specified matrix (ocean). Cells with value 0
 * are "water".
