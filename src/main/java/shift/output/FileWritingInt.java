package shift.output;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class FileWritingInt implements Output<int[]> {

    /** Строка, содержащая путь к файлу с результатом работы программы.*/
    private final String outputPath;

    /** Конструктор принимает имя выходного файла.*/
    public FileWritingInt(String outputPath) {
        this.outputPath = outputPath;
    }

    /** Выполняет запись данных в файл.
     * @param array массив целых чисел.*/
    @Override
    public void printFile(int[] array) {
        try (PrintWriter printWriter = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(outputPath)
                ))) {
            for (Integer i : array) {
                printWriter.println(i);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
