package shift.output;

public interface Output<T> {

    /** Выполняет запись данных в файл.
     * @param array данные, в зависимости от конкретной реализации интерфейса.*/
    void printFile(T array);
}
