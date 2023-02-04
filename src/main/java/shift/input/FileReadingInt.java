package shift.input;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileReadingInt implements Input<int[]> {

    /** Список строк, содержащий пути к файлам.*/
    private final List<String> inputPath;

    /** Конструктор принимает имена входных файлов.*/
    public FileReadingInt(List<String> inputPath) {
        this.inputPath = inputPath;
    }

    /** Проверяет, что строка не пустая и не содержит пробелы.*/
    private static boolean isEmptyAndWithoutWhitespaces(String s) {
        return  (!s.isBlank() && !s.contains(" "));
    }

    /** Проверяет, что строка содержит ТОЛЬКО целые числа.*/
    private static boolean isInt(String s) {
        return s.matches("[0-9]+");
    }

    /** Выполняет чтение данных из файла.
     * @return массив данных типа int.*/
    @Override
    public int[] readFile() {
        List<Integer> list = new ArrayList<>();
        List<File> filesInFolder = new ArrayList<>();
        for (String input : inputPath) {
                filesInFolder.add(new File(input));
        }
        for (File file : filesInFolder) {
            try (BufferedReader in = new BufferedReader(new FileReader(file))) {
                in.lines()
                        .filter(FileReadingInt::isEmptyAndWithoutWhitespaces)
                        .filter(FileReadingInt::isInt)
                        .map(Integer::parseInt)
                        .forEach(list::add);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return list.stream().mapToInt(value -> value).toArray();
    }
}
