import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    //Создаем переменные
    static int a;
    static int b;
    static int result;
    static char operation;


    public static void main(String[] args) {
        //считываем введенную строку
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        calc(input);
    }

    public static String calc(String input) {



//проверка на наличие римских и арабских символов


        //проверка на количество символов, не менее 3х
        if(input.length()<=1){
            System.out.println("Input: " + input);
            System.out.println("Output: ");
            try {
                throw new Exception("Cтрока не является математической операцией");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        char[] sings = new char[100];

        //заносим строку в массив и делаем проверку на операцию, присвоим знак операции char operation
        for (int i = 0; i<input.length(); i++) {
            sings[i] = input.charAt(i);
            switch (sings[i]) {
                case ('+') -> operation = '+';
                case ('-') -> operation = '-';
                case ('*') -> operation = '*';
                case ('/') -> operation = '/';
            }
        }
        //проверка на количество операндов
        int plusCount = 0, minusCount = 0, divideCount = 0, multiplyCount = 0;
        for (char c : sings) {
            if (c == '+') {
                plusCount++;
            } else if (c == '-') {
                minusCount++;
            } else if (c == '/') {
                divideCount++;
            } else if (c == '*') {
                multiplyCount++;
            }
        }
        if (plusCount > 1 || minusCount > 1 || divideCount > 1 || multiplyCount > 1) {
            try {
                throw new Exception("Формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *) ");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }


        String chars = String.valueOf(sings);

        ///убираем операнды
        String[] splits = chars.split("[+-/*]");

        String position0 = splits[0];
        String position1 = splits[1];
        String position3 = position1.trim();


        String[] roman = {"O", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
        String[] arab = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};

        int count1 = 0;
        int count2 = 0;

        for (int i = 0; i < roman.length; i++) {
            if (roman[i].equals(position0) || roman[i].equals(position3)) {
                count1++;
            }
            if (arab[i].equals(position3) || arab[i].equals(position0)) {
                count2++;
            }
        }
        if (count1 >= 1 && count2 >= 1) {
            try {
                throw new IllegalArgumentException("Используются одновременно разные системы счисления");
            } catch (IllegalArgumentException e) {
                throw new RuntimeException(e);
            }
        }


        a = romanToNumber(position0);
        b = romanToNumber(position3);
//Римские
        if (a < 0 && b < 0) {
            result = 0;
        } else {
            result = calculated(a, b, operation);
            ///проверка на отрицательные символы
            if(result < 0){
                try {
                    throw new Exception("В римской системе нет отрицательных чисел");
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
            String resultRoman = convertToRoman(result);

            System.out.println("Input: " + position0 + " " + operation + " " + position3);
            System.out.println("Output: " + position0 + " " + operation + " " + position3 + " = " + resultRoman);

            return String.valueOf(result);
        }

///Арабские
        a = Integer.parseInt(position0);
        b = Integer.parseInt(position3);
        System.out.println("Input: " + a + " " + operation + " " + b);
        result = calculated(a, b, operation);
        System.out.println("Output: " + a + " " + operation + " " + b + " = " + result);
        return String.valueOf(result);
    }
///конвертируем арабские в римские
    private static String convertToRoman(int arabNums) {
        String[] romanian = {"O", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX",
                "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL",
                "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX",
                "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX",
                "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX",
                "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC",
                "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C",};

        final String s = romanian[arabNums];
        return s;
    }
//думаю здесь все понятно
    private static int calculated(int a, int b, char operation) {
        return switch (operation) {
            case '+' -> a + b;
            case '-' -> a - b;
            case '*' -> a * b;
            case '/' -> a / b;
            default -> 0;
        };
    }

    //присваеваем римским арабские
    private static int romanToNumber(String roman) {
        return switch (roman) {
            case ("I") -> 1;
            case ("II") -> 2;
            case ("III") -> 3;
            case ("IV") -> 4;
            case ("V") -> 5;
            case ("VI") -> 6;
            case ("VII") -> 7;
            case ("VIII") -> 8;
            case ("IX") -> 9;
            case ("X") -> 10;
            default -> -1;
        };
    }
}

