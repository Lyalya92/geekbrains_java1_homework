package ru.geekbrains.lessons.homework.lesson3;

public class HomeWork3 {

    public static void main (String [] args) {

        // Задание 1: Задать целочисленный массив, состоящий из элементов 0 и 1.
        // Например: [ 1, 1, 0, 0, 1, 0, 1, 1, 0, 0 ]. С помощью цикла и условия заменить 0 на 1, 1 на 0;
        int intArray [] = {1, 1, 0, 0, 1, 0, 1, 1, 0, 0};
        for (int i = 0; i < intArray.length; i++) {
            intArray[i] = Math.abs(intArray[i]-1);   // знаю, что надо было с if, но так мне нравится больше =)
        }
        System.out.println("Задание 1:");
        printArray (intArray);
        System.out.println();


        // Задание 2: Задать пустой целочисленный массив длиной 100.
        // С помощью цикла заполнить его значениями 1 2 3 4 5 6 7 8 … 100
        int intArray2 [] = new int [100];
        for (int i = 0; i < intArray2.length; i++) {
            intArray2 [i] = i+1;
        }
        System.out.println("Задание 2:");
        printArray (intArray2);
        System.out.println();


        // Задание 3: Задать массив [ 1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1 ]
        // Пройти по нему циклом, и числа меньшие 6 умножить на 2
        int intArray3 [] = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        for (int i = 0; i < intArray3.length; i++) {
            if (intArray3[i] < 6) intArray3[i] *= 2;
        }
        System.out.println("Задание 3:");
        printArray (intArray3);
        System.out.println();


        // Задание 4: Создать квадратный двумерный целочисленный массив
        // С помощью цикла(-ов) заполнить его диагональные элементы единицами
        int size = 5;
        int [][] intArray4  = new int [size][size];
        for (int i = 0; i < size; i ++) {
            intArray4[i][i] = 1;
            intArray4[i][(size-1) - i] = 1;
        }
        System.out.println("Задание 4:");
        printArray(intArray4);
        System.out.println();


        // Задание 5: Написать метод, принимающий на вход два аргумента: len и initialValue
        // и возвращающий одномерный массив типа int длиной len, каждая ячейка которого равна initialValue;
        int len = 10;
        int initValue = 2;
        System.out.println("Задание 5:");
        printArray(initArrayWithValue(len,initValue));
        System.out.println();


        // Задание 6*: Задать одномерный массив и найти в нем минимальный и максимальный элементы
        int [] intArray6 = {21, 15, -10, 5, 1, 12, 2, -30};
        int intMin = intArray6[0];
        int intMax = intArray[0];
        for (int i = 0; i < intArray6.length; i++) {
            if (intArray6 [i] > intMax) intMax = intArray6 [i];
            if (intArray6 [i] < intMin) intMin = intArray6 [i];
        }
        System.out.println("Задание 6:");
        System.out.print("Массив: ");
        printArray(intArray6);
        System.out.printf("Максимальный элемент: %d; Минимальный элемент: %d\n ", intMax, intMin);
        System.out.println();


        // Задание 7**: Написать метод, в который передается не пустой одномерный целочисленный массив
        // метод должен вернуть true, если в массиве есть место,
        // в котором сумма левой и правой части массива равны.
        int [] intArrayCheck = {11, 5, 0, -3, 6, 7};
        System.out.println("Задание 7:");
        System.out.printf("Массив: ");
        printArray(intArrayCheck);
        System.out.printf("check Balance : ");
        System.out.println(arrayChekBalance(intArrayCheck));
        System.out.println();


        // Задание 8***: Написать метод, которому на вход подается одномерный массив и число n
        // (может быть положительным, или отрицательным), при этом метод должен сместить
        // все элементы массива на n позиций. Элементы смещаются циклично.
        int [] intArray8 = {12, 0, 4, 3, 5, 18, 7};
        int shift = -2; // сдвиг на 2 влево
        System.out.println("Задание 8:");
        System.out.printf("Первоначальный массив: ");
        printArray(intArray8);
        System.out.printf("Массив после сдвига: ");
        printArray(shiftArray(intArray8, shift));


    }

    // Метод для задания 5: инициализация массива заданным числом
    private static int[] initArrayWithValue(int len, int initValue) {
        int [] arr = new int [len];
        for (int i = 0; i < len; i ++) {
            arr [i] = initValue;
        }
        return arr;
    }


    // Метод для задания 7: проверка есть ли в массиве место, когда сумма справа и слева от него будут равны
    private static boolean arrayChekBalance(int [] arr) {
        int sumTotal = 0;
        for (int i = 0; i < arr.length; i++) {
            sumTotal += arr[i];
        }
        int sumRight = sumTotal;
        int sumLeft = 0;

        for (int i = 0; i < arr.length; i++) {
            sumLeft += arr[i];
            sumRight -= arr[i];
            if (sumLeft == sumRight) { return true; }
        }
        return false;
    }


    // Метод для задания 8: сдвиг массива на заданное количество позиций
    private static int[] shiftArray(int[] arr, int n) { // Задание 8

        // Если число n равно длине массива или кратно ему, то сдвиг не требуется
        if (n % arr.length == 0) return arr;

        // Если n больше длины массива, преобразуем его:
        if (n > arr.length) {
            n -=  arr.length * (n / arr.length);
        }

        int temp = 0;
        // Если n > 0 -> сдвиг вправо
        if (n > 0) {
            for (int counter = 0; counter < n; counter ++) {
                temp = arr[arr.length - 1];
                for (int i = arr.length - 2; i >= 0; i--) {
                    arr[i + 1] = arr[i];
                }
                arr[0] = temp;
            }
        } else {  // Если n < 0 -> сдвиг влево
            for (int counter = 0; counter < Math.abs(n); counter ++) {
                temp = arr[0];
                for (int i = 1; i < arr.length; i++) {
                    arr[i - 1] = arr[i];
                }
                arr[arr.length-1] = temp;
            }
        }
        return arr;
    }


   // Вспомогательные методы для печати целочисленных массивов:
    private static void printArray(int[] arr) {
        System.out.print("[ ");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.print("]\n");
    }

    private static void printArray(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }

}

