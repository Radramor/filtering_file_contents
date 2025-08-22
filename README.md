# Утилита фильтрации файлов

Программа на Java для фильтрации данных из входных файлов в отдельные выходные файлы по типам данных (целые числа, числа с плавающей точкой, строки) с возможностью подсчёта статистики.

## Требования
- **Java**: версия 17 или выше  
- **Система сборки**: отсутствует   
- **Сторонние зависимости**: отсутствуют (используются только стандартные библиотеки Java)

## Структура проекта
- `src/base/Main.java` — точка входа (основной класс программы)  
- `src/base/Classifier.java` — определение типа данных (int, float, string)  
- `src/statistics/IntStats.java` — статистика по целым числам  
- `src/statistics/FloatStats.java` — статистика по числам с плавающей точкой  
- `src/statistics/StringStats.java` — статистика по строкам  
- `src/cmd/ArgParser.java` — разбор аргументов командной строки  

## Установка
1. Установите **Java 17 или выше**.
2. Проверьте установку:
   ```bash
   java -version
   ```
3. Склонируйте репозиторий
   
## Сборка
Перейдите в корень проекта и выполните команду для компиляции:  
```bash
javac -d out src/base/*.java src/statistics/*.java src/cmd/*.java
```

## Запуск
### Запуск напрямую из скомпилированных классов
```bash
java -cp out base.Main [опции] input1.txt input2.txt ...
```

## Опции
- `-f` — вывод полной статистики (минимум, максимум, сумма, среднее)  
- `-s` — вывод краткой статистики (только количество элементов)  
- `-o <dir>` — указать директорию для выходных файлов (по умолчанию текущая)  
- `-p <prefix>` — префикс для имён выходных файлов  
- `-a` — дозапись в выходные файлы вместо перезаписи  

## Примеры
### Фильтрация без запросов дополнительных опций 
```bash
java -cp out base.Main input.txt
```
<img width="828" height="50" alt="image" src="https://github.com/user-attachments/assets/431e914e-0d5f-48a6-9fd7-505a067d951f" />

### Фильтрация с краткой статистикой
```bash
java -cp out base.Main -s input.txt
```
<img width="853" height="116" alt="image" src="https://github.com/user-attachments/assets/87caf833-b0ba-4e34-8db2-8b760ed6baa2" />

### Фильтрация с полной статистикой
```bash
java -cp out base.Main -f input.txt
```
<img width="948" height="117" alt="image" src="https://github.com/user-attachments/assets/fa3f095b-4c1f-4985-b487-7483348cfcbd" />

### Фильтрация с указанием директории и префикса
```bash
java -cp out base.Main -o results -f -p data input.txt input2.txt
```
<img width="1196" height="93" alt="image" src="https://github.com/user-attachments/assets/bc1e80b0-0296-4add-b2ba-ed9c9946b94d" />

