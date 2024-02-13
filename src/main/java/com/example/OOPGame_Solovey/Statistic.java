package com.example.OOPGame_Solovey;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class Statistic implements Stats {
    private final double score_target = 100;
    private double startPoint = 0;
    private double score = 0;
    private String randLose;
    private String randWin;
    private Rectangle rect;
    private VBox stats;
    private Label stats_text;
    public boolean isWin = false;
    public void setStartPoint(double damage){
        startPoint = damage;
    }
    public void Damage(double damage){
        score += damage;
        setScoreIndicator();
        checkStop();
    }
    public void setStatsParams(Rectangle rect, VBox stats, Label stats_text){
        this.rect = rect;
        this.stats = stats;
        this.stats_text = stats_text;
    }
    public void setScoreIndicator(){
        if (Math.abs(score) <= score_target){
            stats_text.setText(String.valueOf((int)Math.abs(score)));
            stats.setLayoutX(startPoint+score*rect.getWidth()/(score_target*2));
        }
        if (Math.abs(score) >= score_target){
            stats_text.setText("100");
        }
    }
    public double getScore(){
        return score;
    }
    public void setScore(double s){
        score = s;
    }

    public void checkStop(){
        if (score >= score_target || score <= score_target * -1){
            isWin = true;
            randLose = randStrLose();
            randWin = randStrWin();
        }
    }
    public String checkWinner(){
        if (score >= score_target){
            return randWin;
        }
        if (score <= score_target * -1){
            return randLose;
        }
        return null;
    }

    private String randStrLose(){
        ArrayList list = new ArrayList();
        list.add("Ты держался достойно");
        list.add("Иди в свою дотку");
        list.add("Ты проиграл");
        list.add("Нужно больше пота");
        list.add("Ну ты и чушпан");
        return (String) list.get((int) (Math.random() * list.size()));
    }
    private String randStrWin(){
        ArrayList list = new ArrayList();
        list.add("Ты достоин");
        list.add("Молодцом");
        list.add("Ты выйграл");

        return (String) list.get((int) (Math.random() * list.size()));
    }
}
