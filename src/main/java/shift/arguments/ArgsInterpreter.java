package shift.arguments;

import shift.input.FileReadingInt;
import shift.input.FileReadingString;
import shift.input.Input;
import shift.output.FileWritingInt;
import shift.output.FileWritingString;
import shift.output.Output;
import shift.sorting.*;
import shift.validation.Validation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ArgsInterpreter implements Interpreter {

    /** Список для аргументов типа и режима сортировки.*/
    private final List<String> typeArguments = new ArrayList<>();
    /** Список для аргументов файлов.*/
    private final List<String> fileTxtArguments = new ArrayList<>();

    /** Проверяет список на наличие аргументов для сортировки целых чисел по возрастанию.
     * @param list Список аргументов программы.
     * @return {@code true} если аргумент присутствует в списке.*/
    @Override
    public boolean isIntAsc(List<String> list) {
        return list.contains("-i");
    }

    /** Проверяет список на наличие аргументов для сортировки целых чисел по убыванию.
     * @param list Список аргументов программы.
     * @return {@code true} если аргумент присутствует в списке.*/
    @Override
    public boolean isIntDesc(List<String> list) {
        return list.contains("-d") && list.contains("-i");
    }

    /** Проверяет список на наличие аргументов для сортировки строк по возрастанию.
     * @param list Список аргументов программы.
     * @return {@code true} если аргумент присутствует в списке.*/
    @Override
    public boolean isStringAsc(List<String> list) {
        return list.contains("-s");
    }

    /** Проверяет список на наличие аргументов для сортировки строк по убыванию.
     * @param list Список аргументов программы.
     * @return {@code true} если аргумент присутствует в списке.*/
    @Override
    public boolean isStringDesc(List<String> list) {
        return list.contains("-d") && list.contains("-s");
    }

    /** Выполняет разделение аргументов программы на аргументы типа и аргументы файлов.
     * Используется, в том числе, для предотвращения считывания аргументов в именах файлов.
     * @param args массив аргументов программы.*/
    private void interpret(String[] args) {
        new Validation().validate(args);
        for (String arg : args) {
            if (arg.startsWith("-") && arg.length() == 2) {
                typeArguments.add(arg);
            } else {
                fileTxtArguments.add(arg);
            }
        }
    }

    /** Сортирует данные, основываясь на заданных аргументах программы.
     * @param args массив аргументов программы.*/
    public void accomplish(String[] args) throws IOException {
        interpret(args);
        Input<String[]> stringInput = new FileReadingString(fileTxtArguments.subList(1, fileTxtArguments.size()));
        Input<int[]> integerInput = new FileReadingInt(fileTxtArguments.subList(1, fileTxtArguments.size()));
        Output<String[]> stringOutput = new FileWritingString(fileTxtArguments.get(0));
        Output<int[]> integerOutput = new FileWritingInt(fileTxtArguments.get(0));
        if (isIntAsc(typeArguments)) {
            Sort<int[]> integerSort = new MergeSortIntAsc();
            var array = integerSort.sortMerge(integerInput.readFile());
            integerOutput.printFile(array);
        }
        if (isStringAsc(typeArguments)) {
            Sort<String[]> stringSort = new MergeSortStringAsc();
            var array = stringSort.sortMerge(stringInput.readFile());
            stringOutput.printFile(array);
        }
        if (isStringDesc(typeArguments)) {
            Sort<String[]> stringSort = new MergeSortStringDesc();
            var array = stringSort.sortMerge(stringInput.readFile());
            stringOutput.printFile(array);
        }
        if (isIntDesc(typeArguments)) {
            Sort<int[]> integerSort = new MergeSortIntDesc();
            var array = integerSort.sortMerge(integerInput.readFile());
            integerOutput.printFile(array);
        }
    }
}
