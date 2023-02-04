package shift;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import shift.arguments.ArgsInterpreter;

import java.io.*;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class MainTest {

    /** Проверка выполнения сортировки слиянием для целых чисел по возрастанию.*/
    @Test
    void whenIntegerAscSort(@TempDir Path tempDir) throws IOException {
        File source = tempDir.resolve("source.txt").toFile();
        try (PrintWriter out = new PrintWriter(source)) {
            out.println("1");
            out.println("4");
            out.println("9");
        }
        File target  = tempDir.resolve("target.txt").toFile();
        ArgsInterpreter argsInterpreter = new ArgsInterpreter();
        String[] strArgs = {"-a", "-i", target.getAbsolutePath(), source.getAbsolutePath()};
        argsInterpreter.accomplish(strArgs);
        StringBuilder rsl = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            in.lines().forEach(rsl::append);
        }
        assertThat("149").isEqualTo(rsl.toString());
    }

    /** Проверка выполнения сортировки слиянием для целых чисел по убыванию.*/
    @Test
    void whenIntegerDescSort(@TempDir Path tempDir) throws IOException {
        File source = tempDir.resolve("source.txt").toFile();
        try (PrintWriter out = new PrintWriter(source)) {
            out.println("1");
            out.println("4");
            out.println("9");
        }
        File target  = tempDir.resolve("target.txt").toFile();
        ArgsInterpreter argsInterpreter = new ArgsInterpreter();
        String[] strArgs = {"-d", "-i", target.getAbsolutePath(), source.getAbsolutePath()};
        argsInterpreter.accomplish(strArgs);
        StringBuilder rsl = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            in.lines().forEach(rsl::append);
        }
        assertThat("941").isEqualTo(rsl.toString());
    }

    /** Проверка выполнения сортировки слиянием для строк по возрастанию.*/
    @Test
    void whenStringAscSort(@TempDir Path tempDir) throws IOException {
        File source = tempDir.resolve("source.txt").toFile();
        try (PrintWriter out = new PrintWriter(source)) {
            out.println("a");
            out.println("b");
            out.println("c");
        }
        File target  = tempDir.resolve("target.txt").toFile();
        ArgsInterpreter argsInterpreter = new ArgsInterpreter();
        String[] strArgs = {"-a", "-s", target.getAbsolutePath(), source.getAbsolutePath()};
        argsInterpreter.accomplish(strArgs);
        StringBuilder rsl = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            in.lines().forEach(rsl::append);
        }
        assertThat("abc").isEqualTo(rsl.toString());
    }

    /** Проверка выполнения сортировки слиянием для строк по убыванию.*/
    @Test
    void whenStringDescSort(@TempDir Path tempDir) throws IOException {
        File source = tempDir.resolve("source.txt").toFile();
        try (PrintWriter out = new PrintWriter(source)) {
            out.println("a");
            out.println("b");
            out.println("c");
        }
        File target  = tempDir.resolve("target.txt").toFile();
        ArgsInterpreter argsInterpreter = new ArgsInterpreter();
        String[] strArgs = {"-d", "-s", target.getAbsolutePath(), source.getAbsolutePath()};
        argsInterpreter.accomplish(strArgs);
        StringBuilder rsl = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            in.lines().forEach(rsl::append);
        }
        assertThat("cba").isEqualTo(rsl.toString());
    }

    /** Проверка обработки файла, содержащего пустую строку.*/
    @Test
    void whenSourceHasBlankLine(@TempDir Path tempDir) throws IOException {
        File source = tempDir.resolve("source.txt").toFile();
        try (PrintWriter out = new PrintWriter(source)) {
            out.println("    ");
            out.println("b");
            out.println("c");
        }
        File target  = tempDir.resolve("target.txt").toFile();
        ArgsInterpreter argsInterpreter = new ArgsInterpreter();
        String[] strArgs = {"-a", "-s", target.getAbsolutePath(), source.getAbsolutePath()};
        argsInterpreter.accomplish(strArgs);
        StringBuilder rsl = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            in.lines().forEach(rsl::append);
        }
        assertThat("bc").isEqualTo(rsl.toString());
    }

    /** Проверка обработки строки файла, содержащую пробелы.*/
    @Test
    void whenSourceHasWhitespaces(@TempDir Path tempDir) throws IOException {
        File source = tempDir.resolve("source.txt").toFile();
        try (PrintWriter out = new PrintWriter(source)) {
            out.println("a      ");
            out.println("b");
            out.println("c");
        }
        File target  = tempDir.resolve("target.txt").toFile();
        ArgsInterpreter argsInterpreter = new ArgsInterpreter();
        String[] strArgs = {"-a", "-s", target.getAbsolutePath(), source.getAbsolutePath()};
        argsInterpreter.accomplish(strArgs);
        StringBuilder rsl = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            in.lines().forEach(rsl::append);
        }
        assertThat("bc").isEqualTo(rsl.toString());
    }

    /** Для сортировки целых чисел производится проверка обработки строк файла,
     * содержащих нецелочисленные элементы.*/
    @Test
    void whenIntegerSortWithNotIntNumbersInLines(@TempDir Path tempDir) throws IOException {
        File source = tempDir.resolve("source.txt").toFile();
        try (PrintWriter out = new PrintWriter(source)) {
            out.println("1   aaaa");
            out.println("d 2");
            out.println("3");
        }
        File target  = tempDir.resolve("target.txt").toFile();
        ArgsInterpreter argsInterpreter = new ArgsInterpreter();
        String[] strArgs = {"-a", "-i", target.getAbsolutePath(), source.getAbsolutePath()};
        argsInterpreter.accomplish(strArgs);
        StringBuilder rsl = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            in.lines().forEach(rsl::append);
        }
        assertThat("3").isEqualTo(rsl.toString());
    }

    /** Проверка обработки исключения при наличии недостаточного количества имен текстовых файлов
     * в параметрах программы.*/
    @Test
    void whenLessThanTwoTxtFilesInTheParameters(@TempDir Path tempDir) throws IOException {
        File source = tempDir.resolve("source.txt").toFile();
        try (PrintWriter out = new PrintWriter(source)) {
            out.println("1");
            out.println("4");
            out.println("9");
        }
        ArgsInterpreter argsInterpreter = new ArgsInterpreter();
        String[] strArgs = {"-a", "-i", source.getAbsolutePath()};
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> argsInterpreter.accomplish(strArgs))
                .withMessageContaining("To run the application you need to specify");
    }

    /** Проверка обработки исключения при наличии недостаточного количества аргументов
     * в параметрах программы.*/
    @Test
    void whenLessThanThreeArguments(@TempDir Path tempDir) throws IOException {
        File source = tempDir.resolve("source.txt").toFile();
        try (PrintWriter out = new PrintWriter(source)) {
            out.println("1");
            out.println("4");
            out.println("9");
        }
        File target  = tempDir.resolve("target.txt").toFile();
        ArgsInterpreter argsInterpreter = new ArgsInterpreter();
        String[] strArgs = {source.getAbsolutePath(), target.getAbsolutePath()};
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> argsInterpreter.accomplish(strArgs))
                .withMessageContaining("Insufficient number of parameters to");
    }

    /** Проверка обработки исключения при некорректном вводе аргументов программы.*/
    @Test
    void whenTypeArgumentStartsWithoutMinus(@TempDir Path tempDir) throws IOException {
        File source = tempDir.resolve("source.txt").toFile();
        try (PrintWriter out = new PrintWriter(source)) {
            out.println("1");
            out.println("4");
            out.println("9");
        }
        File target  = tempDir.resolve("target.txt").toFile();
        ArgsInterpreter argsInterpreter = new ArgsInterpreter();
        String[] strArgs = {"i", source.getAbsolutePath(), target.getAbsolutePath()};
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> argsInterpreter.accomplish(strArgs))
                .withMessageContaining("Sorting mode and data type must consist of:");
    }

    /** Проверка обработки исключения при вводе нетекстового формата для файла.*/
    @Test
    void whenFileArgumentHasNonTxtFormat(@TempDir Path tempDir) throws IOException {
        File source = tempDir.resolve("source.png").toFile();
        try (PrintWriter out = new PrintWriter(source)) {
            out.println("1");
            out.println("4");
            out.println("9");
        }
        File target  = tempDir.resolve("target.txt").toFile();
        ArgsInterpreter argsInterpreter = new ArgsInterpreter();
        String[] strArgs = {"i", source.getAbsolutePath(), target.getAbsolutePath()};
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> argsInterpreter.accomplish(strArgs))
                .withMessageContaining("Sorting mode and data type must consist of:");
    }

    /** Проверка обработки исключения при вводе несуществующего аргумента.*/
    @Test
    void whenTypeArgumentIsUnacceptable(@TempDir Path tempDir) throws IOException {
        File source = tempDir.resolve("source.txt").toFile();
        try (PrintWriter out = new PrintWriter(source)) {
            out.println("1");
            out.println("4");
            out.println("9");
        }
        File target  = tempDir.resolve("target.txt").toFile();
        ArgsInterpreter argsInterpreter = new ArgsInterpreter();
        String[] strArgs = {"-k", source.getAbsolutePath(), target.getAbsolutePath()};
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> argsInterpreter.accomplish(strArgs))
                .withMessageContaining("Sorting mode and data type must consist of:");
    }

    /** Проверка обработки исключения при попытке записи результата программы в уже
     * существующий файл.*/
    @Test
    void whenOutputFileAlreadyExists(@TempDir Path tempDir) throws IOException {
        File source = tempDir.resolve("source.txt").toFile();
        try (PrintWriter out = new PrintWriter(source)) {
            out.println("1");
            out.println("4");
            out.println("9");
        }
        File target  = tempDir.resolve("target.txt").toFile();
        try (PrintWriter out = new PrintWriter(target)) {
            out.println("z");
            out.println("x");
            out.println("c");
        }
        ArgsInterpreter argsInterpreter = new ArgsInterpreter();
        String[] strArgs = {"-s", source.getAbsolutePath(), target.getAbsolutePath()};
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> argsInterpreter.accomplish(strArgs))
                .withMessageContaining("The output file must not exist");
    }
}
