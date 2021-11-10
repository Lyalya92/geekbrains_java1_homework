package ru.geekbrains.lessons.homework.lesson5;

public class Employee {
    private String name;
    private int age;
    private String position;
    private String email;
    private String telephoneNumber;
    private int salary;


    Employee (String name, int age, String position, String email, String telephoneNumber, int salary) {
        this.name = name;
        this.age = age;
        this.position = position;
        this.email = email;
        this.telephoneNumber = telephoneNumber;
        this.salary = salary;
    }

    public static void printInfo(Employee employee) {
        System.out.printf("ФИО сотрудника: %s \n",employee.name);
        System.out.printf("Возраст: %d \n",employee.age);
        System.out.printf("Должность: %s \n",employee.position);
        System.out.printf("E-mail: %s \n",employee.email);
        System.out.printf("Номер телефона: %s \n",employee.telephoneNumber);
        System.out.printf("Зарплата: %d \n",employee.salary);
    }


    public static void main(String[] args) {
        Employee [] employees = new Employee[5];
        employees[0] = new Employee("Ivanov Semen", 25, "Developer", "semen.ivanov@mail.ru", "892356874", 45000);
        employees[1] = new Employee("Kuznecova Anna", 51, "Administrator", "anna.kuznecova@mail.ru", "892311114", 30000);
        employees[2] = new Employee("Volkov Oleg", 43, "Engineer", "oleg.volkov@mail.ru", "892621414", 60000);
        employees[3] = new Employee("Klimova Svetlana", 36, "Manager", "svetlana.klimova@mail.ru", "892689004", 40000);
        employees[4] = new Employee("Grozniy Anatoliy", 29, "Chief Security Officer", "anatoliy.grozniy@mail.ru", "892629994", 50000);

        for (int i=0; i < employees.length; i++) {
            if (employees[i].age >= 40) {
                printInfo(employees[i]);
                System.out.println();
            }
        }
    }


}
