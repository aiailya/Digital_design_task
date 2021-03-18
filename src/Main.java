import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        try (BufferedReader input = new BufferedReader(new InputStreamReader(System.in))) {
            String string = input.readLine(); //считываем введённые данные
            if (isValid(string)) { //проверка на валидность
                while (string.contains("[")) { //выполняем распаковывание строки
                    string = unpacking(string);
                }
                System.out.println(string); //выводим результат в консоль
            }
            else {
                throw new Exception();
            }
        }
        catch (Exception e) { //обрабатываем возможное исключение
            System.out.println("Строка введена неверно! Попробуйте ещё раз!");
        }
    }

    public static String unpacking (String string) {
        int startIndex = string.lastIndexOf('[') + 1; //начало обрабатываемой подстроки
        int finishIndex = string.length(); //конец обрабатываемой подстроки (изначально - конец строки)
        int numIndex = 0;
        for (int i = startIndex-2; i > 0; i--) { //на тот случай, если число состоит из нескольких цифр
            if (!Character.isDigit(string.charAt(i))) {
                numIndex = i+1;
                break;
            }
        }
        int howMuch = Integer.parseInt(string.substring(numIndex, startIndex - 1)); //сколько раз повторяется строка
        for (int i = startIndex; i < string.length(); i++) { //корректируем значение finishIndex
            if (string.charAt(i) == ']') {
                finishIndex = i;
                break;
            }
        }
        StringBuffer insert = new StringBuffer();
        for (int i = 0; i < howMuch; i++) { //создаём подстроку
            insert.append(string.substring(startIndex, finishIndex));
        }
        String result = string.substring(0, numIndex)  + insert + string.substring(finishIndex + 1); //результат
        return result;
    }

    public static boolean isValid(String string) {
        if (!string.contains("[") || !Character.isDigit(string.charAt(0)) || string.contains(" ")) {
            return false;
        }
        return true;
    }
}
