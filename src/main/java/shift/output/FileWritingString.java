package shift.output;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class FileWritingString implements Output<String[]> {

    /** Строка, содержащая путь к файлу с результатом работы программы.*/
    private final String outputPath;

    /** Конструктор принимает имя выходного файла.*/

    public FileWritingString(String outputPath) {
        this.outputPath = outputPath;
    }

    /** Выполняет запись данных в файл.
     * @param array массив строк.*/
    @Override
    public void printFile(String[] array) {
        try (PrintWriter printWriter = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(outputPath)
                ))) {
            for (String s : array) {
                printWriter.println(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
