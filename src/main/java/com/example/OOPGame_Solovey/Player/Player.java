package com.example.OOPGame_Solovey.Player;

import javafx.animation.ParallelTransition;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.File;

public abstract class Player {
    @FXML
    protected Pane playerpane;
    @FXML
    protected Pane LayerPane;
    private double anim_hero = 0;
    protected ParallelTransition parallelTransition;
    String typeplayer;
    double muliply;
    @FXML
    protected ImageView playerImage;
    public Player(Pane playerpane,Pane LayerPane, ParallelTransition parallelTransition, String typeplayer, double muliply){
        this.playerpane = playerpane;
        this.LayerPane = LayerPane;
        this.parallelTransition = parallelTransition;
        this.typeplayer = typeplayer;
        this.muliply = muliply;
    }

    public void setAnim_lvl(ImageView playerImage,int lvl_hero){
        anim_hero += 0.1;
        if (anim_hero == 0.5){
            playerImage.setImage(new Image(String.valueOf(new File("C:\\Универ\\Game_Kurs\\Game_kurs\\src\\main\\resources\\images\\Hero"+ lvl_hero +"\\1.png"))));
        }
        if (anim_hero == 1){
            playerImage.setImage(new Image(String.valueOf(new File("C:\\Универ\\Game_Kurs\\Game_kurs\\src\\main\\resources\\images\\Hero"+ lvl_hero +"\\2.png"))));
        }
        if (anim_hero >= 1.5){
            playerImage.setImage(new Image(String.valueOf(new File("C:\\Универ\\Game_Kurs\\Game_kurs\\src\\main\\resources\\images\\Hero"+ lvl_hero +"\\3.png"))));
            anim_hero = 0;
        }
    }

    public abstract void shoot(int lvl, boolean fire);
    public abstract double checkCollision(Pane enemyPane);
    public abstract void navigation();
    public void setNav(boolean left,boolean right,boolean up,boolean down) {}
    public void checkWall() {
    }
}


