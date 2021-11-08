package ru.geekbrains.lessons.homework.lesson4;

import javax.naming.NameNotFoundException;
import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Random random = new Random();

    private static final char dotPlayer1 = 'X';
    private static final char dotPlayer2 = 'O';
    private static final char DOT_EMPTY = '_';

    private static char [][] field;
    private static int fieldSizeX;
    private static int fieldSizeY;
    private static int emptyCells;
    private static int winLength;

    private static String playerName;
    private static String player1;  // игрок, который ходит первым (X)
    private static String player2;  // игрок, который ходит вторым (O)
    private static int scoreHuman = 0;
    private static int scoreAI = 0;
    private static int coin;


    public static void main (String [] args) {

        System.out.println("Добро пожаловать в игру Крестики-нолики!");
        System.out.println("Введите своё имя: ");
        playerName = scanner.nextLine();

        while(true) {
            System.out.println("Введите размеры поля х и у: ");
            fieldSizeX = scanner.nextInt();
            fieldSizeY = scanner.nextInt();
            emptyCells = fieldSizeX * fieldSizeY;
            initField(fieldSizeX, fieldSizeY);
            printField();

            tossCoin();
            playRound();

            System.out.println("Score:");
            System.out.printf("%s: %d; PlayerAI : %d\n", playerName,scoreHuman,scoreAI);
            System.out.println();

            System.out.println("Сыграем ещё? (y/n)");
            if (!scanner.next().toLowerCase().equals("y")) {
                System.out.println("Всего хорошего!");
                break;
            }

        }
    }


    // Инициализация массива поля заданного размера
    private static void initField(int sizeX, int sizeY) {
        field = new char [sizeY][sizeX];
        for (int y = 0; y < sizeY; y++) {
            for (int x = 0; x < sizeX; x++) {
                field[y][x] = DOT_EMPTY;
            }

        }
    }


    // Вывод поля на экран
    private static void printField (){
        System.out.print("y/x ");
        for (int i = 0; i < fieldSizeX; i++) {
            System.out.printf("| %d ", i+1);
        }
        System.out.println();
        for (int i = 0; i < fieldSizeY; i++) {
            System.out.print(" " + (i+1) + "  ");
            for (int j = 0; j < fieldSizeX; j++) {
                System.out.printf("| %s ", field[i][j]);
            }
            System.out.println();
        }
    }


    // Определяет кто ходит первым, путем подбрасывания монетки
    private static void tossCoin () {
        int coinRand = random.nextInt(2);
        System.out.println("А теперь бросим монетку, чтобы определить кто ходит первым!");
        System.out.println("Выберите 0 (решка) или 1 (орел): ");
        coin = scanner.nextInt();
        if (coin == coinRand) {
            player1 = playerName;
            player2 = "PlayerAI";
        } else {
            player1 = "PlayerAI" ;
            player2 = playerName;
        }
        System.out.printf("Монетка: %d. %s ходит первым!\n", coinRand, player1);
    }


    // Сыграть один раунд
    private static void playRound () {
   while (true) {
       // Ход первого игрока
        playerTurn(player1, dotPlayer1);
        printField();
        emptyCells--;
        if (isEnd(player1, dotPlayer1))  {
            break;
        }

        // Ход второго игрока
        playerTurn(player2, dotPlayer2);
        printField();
        emptyCells--;
        if (isEnd(player2, dotPlayer2)) {
            break;
        }
         }
    }

    private static void playerTurn(String player, char dot) {
        if (player == "PlayerAI") {
            aiTurn(dot);
        } else {
            humanTurn(dot);
        }
    }

    // Ход ИИ
    private static void aiTurn (char aiDot) {
        System.out.println("Ход оппонента: ");
        int [] cell = findOptimalCell();
        field [cell[1]][cell[0]] = aiDot;
    }

    // Ход игрока
    private static void humanTurn (char humanDot) {
        int x;
        int y;
        do {
            System.out.println("Ваш ход. Введите координаты х и у выбранной клетки: ");
            x = scanner.nextInt() - 1;
            y = scanner.nextInt() - 1;
        } while (x < 0 || x >= fieldSizeX || y < 0 || y >= fieldSizeY || !isCellEmpty(x,y));

            field [y][x] = humanDot;
    }

    // Проверить является ли выбранная клетка пустой
    private static boolean isCellEmpty (int x, int y) {
        if (field[y][x] == DOT_EMPTY) return true;
        return false;
    }

    // Проверить не закончилась ли игра (победа одного из игроков или заполнение всех клеток)
    private static boolean isEnd(String player, char dotPlayer) {

        if (isPlayerWin(dotPlayer)) {
            System.out.printf("Игрок %s победил!", player);
            if (player == playerName) {
                scoreHuman++;
            } else {scoreAI++;}
            return true;
        }

        if (emptyCells == 0) {
            System.out.println("Ничья!");
            return true;
        }

        return false;
    }

    // Определяет победил ли один из игроков
    private static boolean isPlayerWin(char dot) {
        // считаем длину символов, необходимую для победы
        if (fieldSizeX > fieldSizeY) winLength = fieldSizeY;
        else winLength = fieldSizeX;

        for (int i = 0; i < fieldSizeY; i++) {
            for (int j = 0; j < fieldSizeX; j++) {
                if (field [i][j] == dot) {
                     if (checkAllLines(j, i)) { return true;};
                }
            }
        }
        return false;
    }

    // Методы, проверяющие линии по всем направлениям от исходящей точки
    private static boolean checkAllLines(int x, int y) {
        if (checkHorizontalLine (x,y)) return true;
        if (checkVerticalLine (x,y)) return true;
        if (checkDiagonalLines (x,y)) return true;
        return false;
    }

    private static boolean checkHorizontalLine(int x, int y) {

        // возвращаем false если последовательность нужного размера не вместится в строку.
        if (fieldSizeX - x < winLength) return false;

        char playerDot = field[y][x];
        int counter = 1;
        for (int i = x+1; i < fieldSizeX ; i++) {
            if (field[y][i] == playerDot) {
                counter++;
            if (counter == winLength) return true;
            } else { counter = 0;}
        }
        return false;
    }

    private static boolean checkVerticalLine(int x, int y) {

        // возвращаем false если последовательность нужного размера не вместится в строку.
        if (fieldSizeY - y < winLength) return false;

        char playerDot = field[y][x];
        int counter = 1;
        for (int i = y+1; i < fieldSizeY ; i++) {
            if (field[i][x] == playerDot) {
                counter++;
                if (counter == winLength) return true;
            }  else { counter = 0;}
        }

        return false;
    }

    private static boolean checkDiagonalLines(int x, int y) {
        char playerDot = field[y][x];
        int temp;
        int counter;
        boolean flag = false;
        // проверяем диагональ вправо/вниз
        if (fieldSizeX - x < winLength || fieldSizeY - y < winLength)  {
            flag = false;
        } else {
            temp = 1;

            do {
                x++;
                y++;
                if (field[y][x] == playerDot) {
                    temp++;
                } else {
                    counter = temp;
                    if (counter == winLength) flag = true;
                    temp = 0;
                }
            } while (x < (fieldSizeX - 1) && y < (fieldSizeY - 1));

            counter = temp;
            if (counter == winLength) {
                flag = true;
                return flag;
            }
        }

        // проверяем диагональ влево/вниз
        if (x+1 < winLength || fieldSizeY - y < winLength)  {
            flag = false; } else {
            temp = 1;

            do {
                x--;
                y++;
                if (field[y][x] == playerDot) {
                    temp++;
                } else {
                    counter = temp;
                    if (counter == winLength) flag = true;
                    temp = 0;
                }
            } while (x > 0 && y < fieldSizeY - 1);

            counter = temp;
            if (counter == winLength) flag = true;
        }
        return flag;
    }


    // ИИ выбирает наиболее оптимальную клетку для своего хода
    private static int [] findOptimalCell(){
        char dot1;
        char dot2;
        int [] place = new int [3];
        int [] cell = new int [2];

        if (player1 == "PlayerAI") {
            dot1 = dotPlayer1;
            dot2 = dotPlayer2;
        } else {
            dot1 = dotPlayer2;
            dot2 = dotPlayer1;
        }
            place = findSetPossibility(dot1); // проверяем можно ли победить
             if (place[0] == 1) {
                 cell[0] = place[1];
                 cell[1] = place[2];
                 return cell;
             }
             place = findSetPossibility(dot2); // проверяем нужно ли заблокировать
             if (place[0] == 1) {
                cell[0] = place[1];
                cell[1] = place[2];
                return cell;
             }

        // Если более оптимальный вариант не найден
        // ставим символ в произвольную свободную клетку
        return chooseRandomPlace();
    }

    private static int[] findSetPossibility(char dot) {
        // arr[0] будет указывать удалось ли найти клетку: 1 - true; 0 - false
        // arr[1] и arr[2] x и y координаты подходящей клетки
        int [] arr = {0,0,0};
        int counter = 0;

        for (int y = 0; y < fieldSizeY; y++) {
            for (int x = 0; x < fieldSizeX; x++) {
                if (field [y][x] == dot) {

                    // считаем количество символов в строке
                    for (int i = 0; i < fieldSizeX; i++) {
                        if (field[y][i] == dot) {counter ++;}
                    }
                    if (counter == winLength - 1) {
                        for (int i = 0; i < fieldSizeX; i++) {
                            if (field[y][i] == DOT_EMPTY) {
                                arr [0] = 1;
                                arr [1] = i;
                                arr [2] = y;
                                return arr;  }
                        }
                    }
                    counter = 0;

                    // считаем количество символов в столбце
                    for (int i = 0; i < fieldSizeY; i++) {
                        if (field[i][x] == dot) {counter ++;}
                    }
                    if (counter == winLength - 1) {
                        for (int i = 0; i < fieldSizeX; i++) {
                            if (field[i][x] == DOT_EMPTY) {
                                arr [0] = 1;
                                arr [1] = x;
                                arr [2] = i;
                                return arr;  }
                        }
                    }
                    counter = 0;

                    int y_temp;
                    int x_temp;
                    //диагональ вправо вниз
                    counter ++;
                    y_temp = y - 1;
                    x_temp = x - 1;
                    while (x_temp >= 0 && y_temp >= 0) {
                        if (field[y_temp][x_temp] == dot) { counter ++; }
                        x_temp--;
                        y_temp--;
                    }
                    y_temp = y + 1;
                    x_temp = x + 1;
                    while (x_temp < fieldSizeX && y_temp < fieldSizeY) {
                        if (field[y_temp][x_temp] == dot) { counter ++; }
                        x_temp++;
                        y_temp++;
                    }

                    if (counter == winLength - 1) {
                        y_temp = y - 1;
                        x_temp = x - 1;
                        while (x_temp >= 0 && y_temp >= 0) {
                            if (field[y_temp][x_temp] == DOT_EMPTY) {
                                arr[0] = 1;
                                arr[1] = x_temp;
                                arr[2] = y_temp;
                                return arr;
                            }
                            x_temp--;
                            y_temp--;
                        }

                        y_temp = y + 1;
                        x_temp = x + 1;
                        while (x_temp < fieldSizeX && y_temp < fieldSizeY) {
                            if (field[y_temp][x_temp] == DOT_EMPTY) {
                                arr[0] = 1;
                                arr[1] = x_temp;
                                arr[2] = y_temp;
                                return arr;
                            }
                            x_temp++;
                            y_temp++;
                        }
                    }
                    counter = 0;


                    //диагональ влево вниз
                    counter ++;
                    y_temp = y + 1;
                    x_temp = x - 1;
                    while (x_temp >=0 && y_temp < fieldSizeY) {
                        if (field[y_temp][x_temp] == dot) { counter ++; }
                        x_temp--;
                        y_temp++;
                    }
                    y_temp = y -1;
                    x_temp = x + 1;
                    while (x_temp < fieldSizeX && y_temp >= 0) {
                        if (field[y_temp][x_temp] == dot) { counter ++; }
                        x_temp++;
                        y_temp--;
                    }

                    if (counter == winLength - 1) {
                        y_temp = y + 1;
                        x_temp = x - 1;
                        while (x_temp >= 0 && y_temp < fieldSizeY) {
                            if (field[y_temp][x_temp] == DOT_EMPTY) {
                                arr[0] = 1;
                                arr[1] = x_temp;
                                arr[2] = y_temp;
                                return arr;
                            }
                            x_temp--;
                            y_temp++;
                        }

                        y_temp = y - 1;
                        x_temp = x + 1;
                        while (x_temp < fieldSizeX && y_temp >= 0) {
                            if (field[y_temp][x_temp] == DOT_EMPTY) {
                                arr[0] = 1;
                                arr[1] = x_temp;
                                arr[2] = y_temp;
                                return arr;
                            }
                            x_temp++;
                            y_temp--;
                        }
                    }
                    counter = 0;


                }
            }

        }
        return arr;
    }

    // Выбирает случайную пустую клетку
    private static int [] chooseRandomPlace () {
        int [] cell = new int [2];
        do {
            cell[0]  = random.nextInt(fieldSizeX);
            cell[1]  = random.nextInt(fieldSizeY);
        } while (!isCellEmpty(cell[0], cell [1]));

        return cell;
    }
}
