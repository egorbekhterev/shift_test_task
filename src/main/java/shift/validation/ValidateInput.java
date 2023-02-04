package shift.validation;

public interface ValidateInput {

    /**
     * Определяет, что переданный аргумент является аргументом типа "-a",
     * "-d", "-s", "-i" или файлом с текстовым расширением.
     * @param arg аргумент программы.
     * @return {@code true} если является аргументом типа или файлом с текстовым расширением.
     * */
    boolean notAcceptableTypeOrFormatArgument(String arg);

    /**
     * Определяет количество текстовых файлов, переданных в качестве аргументов программы.
     * @param args аргументы программы.
     * @return {@code true} если передано более двух текстовых файлов.
     * */
    boolean notEnoughFilesDeclared(String[] args);

    /**
     * Реализует защиту от записи результата сортировки в уже существующий файл.
     * @param args аргументы программы.
     * @return {@code true} если выходной файл уже существует.
     * */
    boolean isOutputExist(String[] args);
}
