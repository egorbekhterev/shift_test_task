package shift.sorting;

/** Реализация сортировки слиянием по убыванию для строк. * */
public class MergeSortStringDesc implements Sort<String[]> {

    /** Выполняется сортировка слиянием данных, полученных из файлов
     * параметров программы.
     * @param array данные из файлов, в виде массива целых чисел.
     * @return отсортированный массив типа String.*/
    @Override
    public String[] sortMerge(String[] array) {
        return sort(array, 0, array.length - 1);
    }

    /** Рекурсивная часть сортировки слиянием. Входной массив циклически делится на "подмассивы".
     * После этого производится циклическое слияние, пока
     * не останется один отсортированный массив.
     * @param array входной массив.
     * @param from индекс первого элемента.
     * @param to индекс последнего элемента.
     * @return отсортированный массив.*/
    private String[] sort(String[] array, int from, int to) {
        if (from == to) {
            return new String[]{array[from]};
        }
        int mid = from + (to - from) / 2;
        return merge(
                sort(array, from, mid),
                sort(array, mid + 1, to)
        );
    }

    /** Алгоритм сортировки разделенных элементов массива.
     * @param left левый "подмассив".
     * @param right правый "подмассив".
     * @return отсортированный массив типа String.*/
    private String[] merge(String[] left, String[] right) {
        int l = 0, r = 0, rsl = 0;
        String[] result = new String[left.length + right.length];
        while (rsl != result.length) {
            if (l == left.length) {
                result[rsl++] = right[r++];
            } else if (r == right.length) {
                result[rsl++] = left[l++];
            } else if (left[l].compareTo(right[r]) > 0) {
                result[rsl++] = left[l++];
            } else {
                result[rsl++] = right[r++];
            }
        }
        return result;
    }
}
