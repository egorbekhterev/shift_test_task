package shift.validation;

import java.nio.file.Files;
import java.nio.file.Path;

public class Validation implements ValidateInput {

    /** Поле для подсчета текстовых файлов, переданных в качестве аргументов.*/
    private int count = 0;

    /**
     * Определяет, что переданный аргумент является аргументом типа "-a",
     * "-d", "-s", "-i" или файлом с текстовым расширением.
     * @param arg аргумент программы.
     * @return {@code true} если является аргументом типа или файлом с текстовым расширением.
     * */
    @Override
    public boolean notAcceptableTypeOrFormatArgument(String arg) {
        return (!arg.matches("-a") && !arg.matches("-d"))
                && (!arg.matches("-i") && !arg.matches("-s")) && !arg.matches(".*\\.txt");
    }

    /**
     * Определяет количество текстовых файлов, переданных в качестве аргументов программы.
     * @param args аргументы программы.
     * @return {@code true} если передано более двух текстовых файлов.
     * */
    @Override
    public boolean notEnoughFilesDeclared(String[] args) {
        boolean rsl = false;
        for (String arg : args) {
            if (arg.matches(".*\\.txt")) {
                count++;
            }
        }
        if (count >= 2) {
            rsl = true;
        }
        return rsl;
    }

    /**
     * Реализует защиту от записи результата сортировки в уже существующий файл.
     * @param args аргументы программы.
     * @return {@code true} если выходной файл уже существует.
     * */
    @Override
    public boolean isOutputExist(String[] args) {
        return Files.exists(Path.of(args[1])) && Files.exists(Path.of(args[2]));
    }

    /**
     * Выполняет конечную валидацию входных параметров программы, включая проверку
     * на минимальное количество аргументов программы.
     * @param args принимает аргументы программы в виде массива.*/
    public void validate(String[] args) {
        if (args.length < 3) {
            throw new IllegalArgumentException(String.format("Insufficient number of parameters to "
                    + "realize the program, there should be at least 3 parameters. "
                    + "Current args length: %s.", args.length));
        }
        for (String arg : args) {
            if (notAcceptableTypeOrFormatArgument(arg)) {
                throw new IllegalArgumentException(String.format(
                        "Sorting mode and data type must consist of: "
                                + "\"-a\" as ASC(optional); \"-d\" as DESC; \"-i\" as int; "
                                + "\"-s\" as String. Fault point: %s.", arg
                ));
            }
        }
        if (!notEnoughFilesDeclared(args)) {
            throw new IllegalArgumentException(String.format("To run the application you need to specify output file "
                    + "and input file in the parameters. .txt files counter = %s.", count));
        }
        if (isOutputExist(args)) {
            throw new IllegalArgumentException("The output file must not exist"
                    + " to prevent writing to an already existing file.");
        }
    }
}
