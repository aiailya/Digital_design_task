//Тестовое задание, Тимченко Илья
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        boolean result = true;
        int counter = 0;
        for (int i = 0; i < string.length(); i++) { //проверка на одинаковое количество открывающих и закрывающих скобок
            if (string.charAt(i) == '[') {
                counter++;
            }
            if (string.charAt(i) == ']') {
                counter--;
            }
        }
        if (counter != 0) {
            result = false;
        }
        Pattern pattern = Pattern.compile("[^A-z0-9\\[\\]]"); //проверка на разрешённые символы
        Matcher matcher = pattern.matcher(string);
        while (matcher.find()) {
            result = false;
        }
        return result;
    }
}