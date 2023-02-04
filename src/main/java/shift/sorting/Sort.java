package shift.sorting;

import java.io.IOException;

public interface Sort<T> {

    /**
     * Выполняет сортировку данных методом слияния.
     * @param array данные.
     * @return данные после сортировки слиянием.
     */
    T sortMerge(T array) throws IOException;
}
