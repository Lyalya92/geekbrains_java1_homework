package ru.geekbrains.lessons.homework.lesson8;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class GameMap extends JPanel {
    public static final int MODE_VS_AI = 0;
    public static final int MODE_VS_HUMAN = 1;
    public static final Random RANDOM = new Random();
    private static final int DOT_PLAYER_1 = 1;
    private static final int DOT_PLAYER_2 = 2;
    private static final int DOT_EMPTY = 0;
    private static final int DOT_PADDING = 7;
    private static final int STATE_DRAW = 0;
    private static final int STATE_WIN_PLAYER1 = 1;
    private static final int STATE_WIN_PLAYER2 = 2;

    private int stateGameOver;
    private int[][] field;
    private int fieldSizeX;
    private int fieldSizeY;
    private int winLength;
    private int cellWidth;
    private int cellHeight;
    private boolean isGameOver;
    private boolean isInitialized;
    private int gameMode;
    private boolean player_1_turn;
    private String player1;
    private String player2;
    private int dotPlayer1;
    private int dotPlayer2;

    // Конструктор
    public GameMap() {
        isInitialized = false;
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                update(e);
            }
        });
    }

    private void update(MouseEvent e) {
        if (isGameOver || !isInitialized) {
            return;
        }
        if (!playerTurn(e)) { return; } // игрок делает ход

        int dotPlayer;
        int stateWin;
        if (player_1_turn) {  // определяем какой именно игрок ходил: 1 или 2
            dotPlayer = dotPlayer1;
            stateWin = STATE_WIN_PLAYER1;
        } else {
            dotPlayer = dotPlayer2;
            stateWin = STATE_WIN_PLAYER2;
        }
        if (gameCheck(dotPlayer, stateWin)) {  // проверяем победил ли игрок, сделавший ход
            return;
        }
        if  (this.gameMode == MODE_VS_HUMAN){ player_1_turn = !player_1_turn; } // меняем очередность игроков

        if  (this.gameMode == MODE_VS_AI) {  // если режим против AI
            aiTurn(dotPlayer2, dotPlayer1);
            repaint();
            if (gameCheck(dotPlayer2, STATE_WIN_PLAYER2)) {
                return;
            }
        }
    }

    private boolean playerTurn(MouseEvent event) {
             int dotPlayer;
             if (player_1_turn) {
                 dotPlayer = dotPlayer1;
             } else {
                 dotPlayer = dotPlayer2;
             }
             int cellX = event.getX() / cellWidth;
             int cellY = event.getY() / cellHeight;
             if (!isCellValid(cellY, cellX) || !isCellEmpty(cellY, cellX)) {
                return false;
                }
             field[cellY][cellX] = dotPlayer;
             repaint();

        return true;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        render(g);
    }

    private void render(Graphics g) {
        if (!isInitialized) {
            return;
        }
        int width = getWidth();
        int height = getHeight();
        cellWidth = width / fieldSizeX;
        cellHeight = height / fieldSizeY;
        g.setColor(Color.BLACK);

        for (int i = 0; i < fieldSizeY; i++) {
            int y = i * cellHeight;
            g.drawLine(0, y, width, y);
        }
        for (int i = 0; i < fieldSizeX; i++) {
            int x = i * cellHeight;
            g.drawLine(x, 0, x, height);
        }
        for (int y = 0; y < fieldSizeY; y++) {
            for (int x = 0; x < fieldSizeX; x++) {
                if (isCellEmpty(y, x)) {
                    continue;
                }
                if (field[y][x] == dotPlayer1) {
                    g.setColor(Color.GREEN);
                    g.fillOval(x * cellWidth + DOT_PADDING,
                            y * cellHeight + DOT_PADDING,
                            cellWidth - DOT_PADDING * 2,
                            cellHeight - DOT_PADDING * 2);
                } else /*if (field[y][x] == dotPlayer2) */ {
                    g.setColor(Color.RED);
                    g.fillRect(x * cellWidth + DOT_PADDING,
                            y * cellHeight + DOT_PADDING,
                            cellWidth - DOT_PADDING * 2,
                            cellHeight - DOT_PADDING * 2);
                }
            }
        }
        if (isGameOver) {
            showGameOverMessage(g);
        }
    }

    public void startNewGame(int gameMode, int fieldSize, int winLength) {
        this.gameMode = gameMode;
        fieldSizeX = fieldSize;
        fieldSizeY = fieldSize;
        this.winLength = winLength;
        player_1_turn = true;
        dotPlayer1 = DOT_PLAYER_1;
        dotPlayer2 = DOT_PLAYER_2;
        if (gameMode == MODE_VS_HUMAN) {
            player1 = "PLAYER 1";
            player2 = "PLAYER 2";
        } else {
            player1 = "HUMAN";
            player2 = "AI";
        }
        field = new int[fieldSizeY][fieldSizeX];
        isInitialized = true;
        isGameOver = false;
        repaint();
    }

    private void showGameOverMessage(Graphics g) {
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, getHeight() / 2 - 60, getWidth(), 120);
        g.setColor(Color.ORANGE);
        g.setFont(new Font("Comic Sans MS", Font.BOLD, 48));

        switch (stateGameOver) {
            case STATE_DRAW -> g.drawString("DRAW", getWidth() / 4 + 50, getHeight() / 2);
            case STATE_WIN_PLAYER1 -> g.drawString(player1 + " WIN", getWidth() / 4, getHeight() / 2);
            case STATE_WIN_PLAYER2 -> g.drawString(player2 + " WIN", getWidth() / 4, getHeight() / 2);
        }
    }

    private  boolean gameCheck(int dot, int stateGameOver) {
        if (checkWin(dot, winLength)) {
            this.stateGameOver = stateGameOver;
            isGameOver = true;
            repaint();
            return true;
        }
        if (checkDraw()) {
            this.stateGameOver = STATE_DRAW;
            isGameOver = true;
            repaint();
            return true;
        }

        return false;
    }

    private boolean checkDraw() {
        for (int y = 0; y < fieldSizeY; y++) {
            for (int x = 0; x < fieldSizeX; x++) {
                if (isCellEmpty(y, x)) return false;
            }
        }
        return true;
    }


    private void aiTurn(int dotAI, int dotHuman) {
        if (scanField(dotAI, winLength)) return;        // проверка выигрыша компа
        if (scanField(dotHuman, winLength)) return;    // проверка выигрыша игрока на след ходу
        if (scanField(dotAI, winLength - 1)) return;
        if (scanField(dotHuman, winLength - 1)) return;
        if (scanField(dotAI, winLength - 2)) return;
        if (scanField(dotHuman, winLength - 2)) return;
        aiTurnEasy(dotAI);
    }

    private void aiTurnEasy(int dotAI) {
        int x, y;
        do {
            x = RANDOM.nextInt(fieldSizeX);
            y = RANDOM.nextInt(fieldSizeY);
        } while (!isCellEmpty(x, y));
        field[y][x] = dotAI;
    }

    private boolean scanField(int dot, int length) {
        for (int y = 0; y < fieldSizeY; y++) {
            for (int x = 0; x < fieldSizeX; x++) {
                if (isCellEmpty(y, x)) {                // поставим фишку в каждую клетку поля по очереди
                    field[y][x] = dot;
                    if (checkWin(dot, length)) {
                        if (dot == DOT_PLAYER_2) return true;    // если комп выигрывает, то оставляем
                        if (dot == DOT_PLAYER_1) {
                            field[y][x] = DOT_PLAYER_2;            // Если выигрывает игрок ставим туда 0
                            return true;
                        }
                    }
                    field[y][x] = DOT_EMPTY;            // если никто ничего, то возвращаем как было
                }
            }
        }
        return false;
    }


    private boolean checkWin(int dot, int length) {
        for (int y = 0; y < fieldSizeY; y++) {            // проверяем всё поле
            for (int x = 0; x < fieldSizeX; x++) {
                if (checkLine(x, y, 1, 0, length, dot)) return true;    // проверка  по +х
                if (checkLine(x, y, 1, 1, length, dot)) return true;    // проверка по диагонали +х +у
                if (checkLine(x, y, 0, 1, length, dot)) return true;    // проверка линию по +у
                if (checkLine(x, y, 1, -1, length, dot)) return true;    // проверка по диагонали +х -у
            }
        }
        return false;
    }

    // проверка линии
    private boolean checkLine(int x, int y, int incrementX, int incrementY, int len, int dot) {
        int endXLine = x + (len - 1) * incrementX;            // конец линии по Х
        int endYLine = y + (len - 1) * incrementY;            // конец по У
        if (!isCellValid(endYLine, endXLine)) return false;    // Выход линии за пределы
        for (int i = 0; i < len; i++) {                    // идем по линии
            if (field[y + i * incrementY][x + i * incrementX] != dot) return false;    // символы одинаковые?
        }
        return true;
    }

    private boolean isCellValid(int y, int x) {
        return x >= 0 && y >= 0 && x < fieldSizeX && y < fieldSizeY;
    }

    private boolean isCellEmpty(int y, int x) {
        return field[y][x] == DOT_EMPTY;
    }

}