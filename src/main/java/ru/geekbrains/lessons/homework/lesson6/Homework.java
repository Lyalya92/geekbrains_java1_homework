package ru.geekbrains.lessons.homework.lesson6;

public class Homework {

    public static void main(String[] args) {
        Cat cat1 = new Cat ("Василий");
        Dog dog1 = new Dog ("Шарик");
        Cat cat2 = new Cat ("Мурзик");

        cat1.run(200);
        cat2.run(900);
        dog1.run(700);

        cat1.swim(20);
        cat2.swim(5);
        dog1.swim(300);
        System.out.println();
        System.out.println("Всего создано животных: " + cat1.getCounter());

    }

}
