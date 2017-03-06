package track.lessons.lesson1;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileReader;

/**
 * Задание 1: Реализовать два метода
 * <p>
 * Формат файла: текстовый, на каждой его строке есть (или/или)
 * - целое число (int)
 * - текстовая строка
 * - пустая строка (пробелы)
 * <p>
 * <p>
 * Пример файла - words.txt в корне проекта
 * <p>
 * ******************************************************************************************
 * Пожалуйста, не меняйте сигнатуры методов! (название, аргументы, возвращаемое значение)
 * <p>
 * Можно дописывать новый код - вспомогательные методы, конструкторы, поля
 * <p>
 * ******************************************************************************************
 */
public class CountWords {

    /**
     * Метод на вход принимает объект File, изначально сумма = 0
     * Нужно пройти по всем строкам файла, и если в строке стоит целое число,
     * то надо добавить это число к сумме
     *
     * @param file - файл с данными
     * @return - целое число - сумма всех чисел из файла
     */
    public long countNumbers(File file) throws Exception {
        long sum = 0;
        if (file.exists()) {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            long nextNumber;
            String nextString;

            while (null != (nextString = bufferedReader.readLine())) {
                if (nextString.length() > 0) {
                    try {
                        nextNumber = Long.valueOf(nextString);
                    } catch (NumberFormatException e) {
                        continue;
                    }
                    sum += nextNumber;
                }
            }
        }
        return sum;
    }


    /**
     * Метод на вход принимает объект File, изначально результат= ""
     * Нужно пройти по всем строкам файла, и если в строка не пустая и не число
     * то надо присоединить ее к результату через пробел
     *
     * @param file - файл с данными
     * @return - результирующая строка
     */
    public String concatWords(File file) throws Exception {
        if (file.exists()) {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            StringBuilder stringBuilder = new StringBuilder();
            String nextString;

            while (null != (nextString = bufferedReader.readLine())) {
                if (nextString.length() > 0) {
                    try {
                        Long.valueOf(nextString);
                    } catch (NumberFormatException e) {
                        if (0 < nextString.replace(" ", "").length()) {
                            if (0 < stringBuilder.length()) {
                                stringBuilder.append(' ');
                            }
                            stringBuilder.append(nextString);
                        }
                    }
                }
            }
            return stringBuilder.toString();
        } else {
            return null;
        }
    }

}
