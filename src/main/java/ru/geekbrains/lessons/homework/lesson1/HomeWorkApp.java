package ru.geekbrains.lessons.homework.lesson1;

public class HomeWorkApp {
    public static void main (String[] args) {

        printThreeWords();
        checkSumSign();
        printColor();
        compareNumbers();

    }

    private static void printThreeWords() {
        System.out.println("Orange");
        System.out.println("Banana");
        System.out.println("Apple");
    }

    private static void checkSumSign() {
        int a = 15;
        int b = -15;
        if ((a+b) >=0) System.out.println("Сумма положительная");
        else System.out.println("Сумма отрицательная");

    }

    private static void printColor () {
        int value = 120;
        if (value <= 0) System.out.println("Красный");
        else if (value > 0 && value <=100) System.out.println("Жёлтый");
        else System.out.println("Зелёный");
    }

    private static void compareNumbers() {
        int a = -46;
        int b = -11;
        if (a >= b) System.out.println("a >= b");
        else System.out.println("a < b");
    }
}
