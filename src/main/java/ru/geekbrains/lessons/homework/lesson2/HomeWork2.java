package ru.geekbrains.lessons.homework.lesson2;

public class HomeWork2 {
    public static void main(String[] args) {
        System.out.println(checkSumInterval(-25,40));
        checkNumberSign(-120);
        System.out.println(isNegativeNumber(212));
        printString("Hello", 3);
        System.out.println(leapYear(2021));
    }

    public static boolean checkSumInterval (int a, int b) {
        if ((a+b)>=10 && (a+b) <=20) return true;
        else return false;
    }

    public static void checkNumberSign (int number) {
        if (number >= 0) System.out.println("Число положительное");
        else System.out.println("Число отрицательное");
    }

    public static boolean isNegativeNumber (int number) {
        if (number < 0) return true;
        else return false;
    }

    public static void printString (String s, int count) {
        for (int i=0; i < count; i++) {
            System.out.println(s);
        }
    }

    public static boolean leapYear (int year) {
        if (year % 4 == 0) {
            if (year % 100 == 0 && year % 400 != 0) return false;
            else return true;
        } else return false;
    }
}
