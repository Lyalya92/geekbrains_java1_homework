package ru.geekbrains.lessons.homework.lesson6;

public class Cat extends Animal{
    private final int MAX_RUN = 600;
    private final int MAX_SWIM = 5;

    public Cat (String name) {
        super(name);
        this.maxRunLength = MAX_RUN;
        this.maxSwimLength = MAX_SWIM;
    }
}
