package ru.geekbrains.lessons.homework.lesson7;

// Коты создаются голодными (hunger = 100 %)
// Чувство голода (hunger) измеряется в процентах и зависит от количества употребленной еды
// appetite - количество единиц еды, необходимое для того, чтобы снизить hunger со 100 до 0

public class Cat {
    private String name;
    private int appetite;
    private int hunger;

    public Cat(String name, int appetite) {
        this.name = name;
        this.appetite = appetite;
        this.hunger = 100;
    }

    public String getName() {
        return name;
    }

    public void eat (Bowl bowl) {
        int needFeed = Math.round(hunger * 0.01f * appetite); // считаем сколько необходимо
        int wasEaten = bowl.decreaseFood(needFeed);
        if (wasEaten == needFeed) {
            hunger = 0;
        } else {
            hunger -= Math.round((1.0f * wasEaten/appetite)*100);
        }
    }

    public int getHunger() {
        return hunger;
    }
}

