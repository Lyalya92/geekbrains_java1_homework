package ru.geekbrains.lessons.homework.lesson7;

public class HomeWork {

    public static void main(String[] args) {

        Cat [] cats = {
                new Cat("Matroskin",80),
                new Cat("Barsik",120),
                new Cat("Murzik",160),
        };

        System.out.println("Коты были созданы!");
        for (Cat cat: cats) {
            System.out.printf("%s Уровень голода: %d процентов\n", cat.getName(), cat.getHunger());

        }
        System.out.println();

        System.out.println("Пытаемся заполнить миски разных размеров");
        var littleBowl = new Bowl(100);
        var middleBowl = new Bowl(200);
        var bigBowl = new Bowl(400);

        littleBowl.putFood(100);
        System.out.printf("littleBowl: %d единиц еды\n ",littleBowl.getFoodAmount());

        middleBowl.putFood(250);
        System.out.printf("middleBowl: %d единиц еды\n ",middleBowl.getFoodAmount());

        bigBowl.putFood(380);
        System.out.printf("bigBowl: %d единиц еды\n ",bigBowl.getFoodAmount());
        System.out.println();
        System.out.println("Кормим котов...");

        cats[0].eat(middleBowl);
        cats[1].eat(littleBowl);
        cats[2].eat(bigBowl);

        System.out.println();
        for (Cat cat: cats) {
            System.out.printf("%s Уровень голода: %d процентов \n", cat.getName(), cat.getHunger());
        }
    }
}
