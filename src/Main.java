import java.util.Objects;
import java.util.Scanner;

public class Main {

    static String text; // Входящая строка
    static String textProof; // Необходима для проверки вхоядщей строки на содержание арабсикх чисел

    public static void main(String[] args) {

        System.out.println("""

                Input:""");
        try (Scanner scanner = new Scanner(System.in)) {
            text = scanner.nextLine();
        } catch (Exception e) {
            e.printStackTrace();
        }

        textProof = text;

        try {

//            Проверка ввода на валидность
            if (!valid(text)) {
                System.out.println("""

                        Output:""");
                throw new IllegalArgumentException("Не верный ввод.");
            }

//            Запуск калькулятора и присвоение результата вычислений
            String answer = calc(text);

//            Проверка на соответсвие входящщей строке
            if (text.equals(textProof)) {
                System.out.println("""

                                           Output:
                                           """ + answer);
            } else {
                try {

//                    Проверака результата для римскоих чисел
                    if (Integer.parseInt(answer) < 1) {
                        System.out.println("""

                                Output:""");
                        throw new IllegalArgumentException("Результатом работы калькулятора с римскими числами могут быть только числа больше нуля.");
                    } else {
                        System.out.println("""

                                                   Output:
                                                   """ + reverseConvert(answer));
                    }
                } catch (IllegalArgumentException e) {
                    System.err.println(e.getMessage());
                }
            }
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }

    }

    public static String calc(String input) {

        int firstElement;
        String secondElement;
        int thirdEElement;
        String[] arr;

        String solution = null;

        arr = input.split(" ");

        firstElement = Integer.parseInt(arr[0]);
        secondElement = arr[1];
        thirdEElement = Integer.parseInt(arr[2]);

        if (Objects.equals(secondElement, "+")) {
            solution = String.valueOf(firstElement + thirdEElement);
        } else if (Objects.equals(secondElement, "-")) {
            solution = String.valueOf(firstElement - thirdEElement);
        } else if (Objects.equals(secondElement, "*")) {
            solution = String.valueOf(firstElement * thirdEElement);
        } else if (Objects.equals(secondElement, "/")) {
            solution = String.valueOf(firstElement / thirdEElement);
        }

        return solution;
    } // Метод разбирает ввод на составные части и производит математическую операцию

    public static boolean valid(String input) {

        String firstElement;
        String thirdElement;

        String patternA = "^(1|2|3|4|5|6|7|8|9|10)+(\\s{1})+([+\\-*/])+(\\s{1})+(1|2|3|4|5|6|7|8|9|10)+$"; // Регулярное выражение для арабских чисел
        String patternR = "^([IVX])+(\\s{1})+([+\\-*/])+(\\s{1})+([IVX])+$"; // Регулярное выражение для римских чисел

        String[] arr = input.split(" ");

        if (input.matches(patternA)) {

            String[] arabicNumerals = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};

            firstElement = arr[0];
            thirdElement = arr[2];

            int num = 0;
            for (String s : arabicNumerals) {
                if (Objects.equals(firstElement, s)) {
                    num++;
                    break;
                }
            }
            for (String s : arabicNumerals) {
                if (Objects.equals(thirdElement, s)) {
                    num++;
                    break;
                }
            }
            if (num == 2) {
                return true;
            }
        }

        if (input.matches(patternR)) {

            String[] romanNumerals = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};

            firstElement = arr[0];
            thirdElement = arr[2];

            int num = 0;
            for (String s : romanNumerals) {
                if (Objects.equals(firstElement, s)) {
                    num++;
                    break;
                }

            }
            for (String s : romanNumerals) {
                if (Objects.equals(thirdElement, s)) {
                    num++;
                    break;
                }
            }
            if (num == 2) {
                text = convert(input);
                return true;
            }
        }

        return false;
    } // Метод проверяет ввод на валидность

    public static String convert(String input) {

        String firstElement;
        String secondElement;
        String thirdElement;

        String[] arr = input.split(" ");

        firstElement = arr[0];
        secondElement = arr[1];
        thirdElement = arr[2];

        StringBuilder resultFirst = new StringBuilder();
        StringBuilder resultThird = new StringBuilder();

        for (int i = 0; i < firstElement.length(); i++) {
            switch (firstElement.charAt(i)) {
                case 'I' -> resultFirst.append("1 ");
                case 'V' -> resultFirst.append("5 ");
                case 'X' -> resultFirst.append("10 ");
            }
        }
        for (int i = 0; i < thirdElement.length(); i++) {
            switch (thirdElement.charAt(i)) {
                case 'I' -> resultThird.append("1 ");
                case 'V' -> resultThird.append("5 ");
                case 'X' -> resultThird.append("10 ");
            }
        }

        String[] arrF = resultFirst.toString().split(" ");
        String[] arrT = resultThird.toString().split(" ");

        int newNumberF = 1;
        if (arrF.length == 1) {
            for (String s : arrF) {
                newNumberF = Integer.parseInt(s);
            }
        } else if (arrF.length >= 2) {
            for (int i = 0; i < arrF.length - 1; i++) {
                if (Integer.parseInt(arrF[i]) == Integer.parseInt(arrF[i + 1])) {
                    newNumberF += Integer.parseInt(arrF[i]);
                } else if (Integer.parseInt(arrF[i]) > Integer.parseInt(arrF[i + 1])) {
                    newNumberF = Integer.parseInt(arrF[i]) + Integer.parseInt(arrF[i + 1]);
                } else {
                    newNumberF = Integer.parseInt(arrF[i + 1]) - Integer.parseInt(arrF[i]);
                }
            }
        }

        int newNumberT = 1;
        if (arrT.length == 1) {
            for (String s : arrT) {
                newNumberT = Integer.parseInt(s);
            }
        } else if (arrT.length >= 2) {
            for (int i = 0; i < arrT.length - 1; i++) {
                if (Integer.parseInt(arrT[i]) == Integer.parseInt(arrT[i + 1])) {
                    newNumberT += Integer.parseInt(arrT[i]);
                } else if (Integer.parseInt(arrT[i]) > Integer.parseInt(arrT[i + 1])) {
                    newNumberT = Integer.parseInt(arrT[i]) + Integer.parseInt(arrT[i + 1]);
                } else {
                    newNumberT = Integer.parseInt(arrT[i + 1]) - Integer.parseInt(arrT[i]);
                }
            }
        }

        String convertInput = newNumberF + " " + secondElement + " " + newNumberT;

        return convertInput;
    } // Метод конвертирует римсике числа в арабские

    public static String reverseConvert(String input) {

        String[] romanNumerals = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "L", "C"};


        int numUnits = Integer.parseInt(input) - 1;
        if (Integer.parseInt(input) < 11) {
            return romanNumerals[numUnits];
        } else if (Integer.parseInt(input) == 50) {
            return romanNumerals[10];
        } else if (Integer.parseInt(input) == 100) {
            return romanNumerals[11];
        } else {
            String[] arr = input.split("");

            StringBuilder str = new StringBuilder();

//          от 11 до 19
            if (Integer.parseInt(arr[0]) < 2) {
                str.append(romanNumerals[9].repeat(Math.max(0, Integer.parseInt(arr[0]))));
            }
//          От 20 до 39
            if (Integer.parseInt(arr[0]) > 1 && Integer.parseInt(arr[0]) < 4) {
                str.append(romanNumerals[9].repeat(Math.max(0, Integer.parseInt(arr[0]))));
            }
//          от 40 до 49
            if (Integer.parseInt(arr[0]) > 3 && Integer.parseInt(arr[0]) < 5) {
                for (int j = 0; j < 1; j++) {
                    str.append(romanNumerals[9]).append(romanNumerals[10]);
                }
            }
//          от 50 до 59
            if (Integer.parseInt(arr[0]) > 4 && Integer.parseInt(arr[0]) < 6) {
                str.append(romanNumerals[10].repeat(1));
            }
//          от 60 до 89
            if (Integer.parseInt(arr[0]) > 5 && Integer.parseInt(arr[0]) < 9) {
                str.append(romanNumerals[10]);
                int numIter = 0;
                if (Objects.equals(arr[0], "6")) numIter = 1;
                if (Objects.equals(arr[0], "7")) numIter = 2;
                if (Objects.equals(arr[0], "8")) numIter = 3;
                str.append(romanNumerals[9].repeat(numIter));
            }
//          от 90 до 99
            if (Integer.parseInt(arr[0]) > 8 && Integer.parseInt(arr[0]) < 10) {
                str.append(romanNumerals[9]);
                str.append(romanNumerals[11].repeat(1));
            }
//          если единицы числа больше нуля
            if (Integer.parseInt(arr[1]) > 0) {
                for (int i = 0; i < 1; i++) {
                    str.append(reverseConvert(arr[1]));
                }
            }
            return str.toString();
        }
    } // Метод конвертирует арфбские числа в римские
}