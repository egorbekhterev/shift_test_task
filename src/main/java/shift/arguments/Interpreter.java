package shift.arguments;

import java.util.List;

public interface Interpreter {

    /** Проверяет список на наличие аргументов для сортировки целых чисел по возрастанию.
     * @param list Список аргументов программы.
     * @return {@code true} если аргумент присутствует в списке.*/
    boolean isIntAsc(List<String> list);

    /** Проверяет список на наличие аргументов для сортировки целых чисел по убыванию.
     * @param list Список аргументов программы.
     * @return {@code true} если аргумент присутствует в списке.*/
    boolean isIntDesc(List<String> list);

    /** Проверяет список на наличие аргументов для сортировки строк по возрастанию.
     * @param list Список аргументов программы.
     * @return {@code true} если аргумент присутствует в списке.*/
    boolean isStringAsc(List<String> list);

    /** Проверяет список на наличие аргументов для сортировки строк по убыванию.
     * @param list Список аргументов программы.
     * @return {@code true} если аргумент присутствует в списке.*/
    boolean isStringDesc(List<String> list);
}
