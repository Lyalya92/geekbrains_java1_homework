package ru.geekbrains.lessons.homework.lesson6;

public class Animal {
    private String name;
    int maxRunLength;
    int maxSwimLength;
    private static int counter = 0;

    public Animal() {
        counter++;
    }

    public Animal (String name) {
        this();
        this.name = name;
    }

    public void run(int length) {
        if (length <= maxRunLength) {
            System.out.printf("%s пробежал %d метров!\n", name,length);
        } else {
            System.out.printf("%s не смог пробежать %d метров... :( \n", name,length);
        }
    }

    public void swim(int length) {
        if (length <= maxSwimLength) {
            System.out.printf("%s проплыл %d метров!\n", name,length);
        } else {
            System.out.printf("%s не смог проплыть %d метров... :( \n", name,length);
        }
    }

    public int getCounter () {
        return counter;
    }
}
