package com.example.OOPGame_Solovey;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class Statistic implements Stats {
    /**
     * Целевая точка для победы в игре.
     */
    private final double score_target = 100;

    /**
     * Начальная позиция статистики.
     */
    private double startPoint = 0;

    /**
     * Текущий счет игры.
     */
    private double score = 0;

    /**
     * Строка с сообщением об утере.
     */
    private String randLose;

    /**
     * Строка с сообщением о победе.
     */
    private String randWin;

    /**
     * Прямоугольник для статистики.
     */
    private Rectangle rect;

    /**
     * Панель для статистики.
     */
    private VBox stats;

    /**
     * Метка для отображения счета.
     */
    private Label stats_text;

    /**
     * Флаг, указывающий на победу или поражение.
     */
    public boolean isWin = false;

    /**
     * Устанавливает начальную позицию статистики.
     *
     * @param damage урон, наносимый игроку при старте игры
     */
    public void setStartPoint(double damage){
        startPoint = damage;
    }

    /**
     * Наносит урон игроку и обновляет индикатор счета.
     *
     * @param damage урон, наносимый игроку
     */
    public void Damage(double damage){
        score += damage;
        setScoreIndicator();
        checkStop();
    }

    /**
     * Устанавливает параметры панели статистики.
     *
     * @param rect прямоугольник для статистики
     * @param stats панель для статистики
     * @param stats_text метка для отображения счета
     */
    public void setStatsParams(Rectangle rect, VBox stats, Label stats_text){
        this.rect = rect;
        this.stats = stats;
        this.stats_text = stats_text;
    }

    /**
     * Обновляет индикатор счета.
     */
    public void setScoreIndicator(){
        if (Math.abs(score) <= score_target){
            stats_text.setText(String.valueOf((int)Math.abs(score)));
            stats.setLayoutX(startPoint+score*rect.getWidth()/(score_target*2));
        }
        if (Math.abs(score) >= score_target){
            stats_text.setText("100");
        }
    }

    /**
     * Возвращает текущий счет игры.
     *
     * @return текущий счет игры
     */
    public double getScore(){
        return score;
    }

    /**
     * Устанавливает текущий счет игры.
     *
     * @param s новый счет игры
     */
    public void setScore(double s){
        score = s;
    }

    /**
     * Проверяет, достигнута ли победа или поражение.
     */
    public void checkStop(){
        if (score >= score_target || score <= score_target * -1){
            isWin = true;
            randLose = randStrLose();
            randWin = randStrWin();
        }
    }

    /**
     * Возвращает сообщение об утере, если игрок проиграл, иначе null.
     */
    public String checkWinner(){
        if (score >= score_target){
            return randWin;
        }
        if (score <= score_target * -1){
            return randLose;
        }
        return null;
    }
    /**
     * Возвращает случайную строку из списка возможных сообщений об поражении.
     *
     * @return случайная строка из списка возможных сообщений об поражении
     */
    private String randStrLose(){
        ArrayList list = new ArrayList();
        list.add("Ты держался достойно");
        list.add("Иди в свою дотку");
        list.add("Ты проиграл");
        list.add("Нужно больше пота");
        list.add("Ну ты и чушпан");
        return (String) list.get((int) (Math.random() * list.size()));
    }

    /**
     * Возвращает случайную строку из списка возможных сообщений об победе.
     *
     * @return случайная строка из списка возможных сообщений об победе
     */
    private String randStrWin(){
        ArrayList list = new ArrayList();
        list.add("Ты достоин");
        list.add("Молодцом");
        list.add("Ты выйграл");

        return (String) list.get((int) (Math.random() * list.size()));
    }
}
