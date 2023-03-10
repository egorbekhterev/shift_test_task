## Сортировка слиянием нескольких файлов.
### Описание
Программа выполняет сортировку методом слияния объединенного содержимого
входных файлов по возрастанию или убыванию и записывает результат в новый файл.
Входные данные могут быть строкового или целочисленного типа.

### Запуск через терминал

<p>1. Собрать jar через Maven</p>

```bash
mvn package
```
<p>2. Запустить jar файл</p>

```bash
java -jar target/shift_test_task-1.0-SNAPSHOT.jar
```

### Запуск через IDE

1. Перейти к папке ``src/main/java`` и файлу ``shift/Main.java``
2. Нажать на кнопку запуска метода ``main`` в IDE

### Установка параметров запуска программы
Параметры программы задаются при запуске через аргументы командной строки, по порядку:
1. режим сортировки (-a или -d), необязательный, по умолчанию сортируем по возрастанию;
2. тип данных (-s или -i), обязательный;
3. имя выходного файла, обязательное;
4. остальные параметры – имена входных файлов, не менее одного.
Для всех файлов необходимо указывать полный путь, либо запускать .jar через командную строку
из одной папки.

### Особенности реализации
Java-17.0.2
Maven-3.8.1
