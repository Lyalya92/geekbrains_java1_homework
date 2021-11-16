package ru.geekbrains.lessons.homework.lesson6;


public class Dog extends Animal {
    private final int MAX_RUN = 800;
    private final int MAX_SWIM = 200;

    public Dog (String name) {
        super(name);
        this.maxRunLength = MAX_RUN;
        this.maxSwimLength = MAX_SWIM;
    }
}
