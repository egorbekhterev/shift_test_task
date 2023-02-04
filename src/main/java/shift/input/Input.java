package shift.input;

import java.io.IOException;

public interface Input<T> {

    /** Выполняет чтение данных из файла.
     * @return данные, в зависимости от конкретной реализации интерфейса.*/
    T readFile() throws IOException;
}
