package ru.geekbrains.lessons.homework.lesson7;

// У каждой миски есть свой размер bowlSize, измеряемый в условных единицах
// Можно добавить только такое количество единиц еды, которое не будет превышать размер миски.

public class Bowl {
    private int bowlSize;
    private int foodAmount;

    public Bowl(int bowlSize) {
        this.bowlSize = bowlSize;
    }

    public int getFoodAmount() {
        return foodAmount;
    }

    public void putFood (int amount) {
        if (amount <= bowlSize) {
            foodAmount = amount;
        } else {
            System.out.printf("Миска слишком мала! Свободно: %d единиц\n",bowlSize-foodAmount);
        }
    }

    // метод возвращает количество съеденной еды
    public int decreaseFood (int amount) {
        int temp;

        if (amount <= foodAmount) {
            foodAmount -= amount;
            return amount;
        } else {
            temp = foodAmount;
            foodAmount = 0;
            return temp;
        }
    }
}
