package com.example.OOPGame_Solovey.Player.Shooting;


import javafx.animation.Interpolator;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.io.File;

public abstract class Shooting {
    private final double hue;
    protected int orient;
    protected Pane PlayerPane;
    protected ParallelTransition parallelTransition;
    protected double multiply;
    protected String typeplayer;
    protected String shootlvl3 = "C:\\Универ\\Game_Kurs\\Game_kurs\\src\\main\\resources\\images\\shoot_lvl_3.png";
    protected String shootlvl2 = "C:\\Универ\\Game_Kurs\\Game_kurs\\src\\main\\resources\\images\\shoot_lvl_2.png";
    protected String shootlvl1 = "C:\\Универ\\Game_Kurs\\Game_kurs\\src\\main\\resources\\images\\shoot_lvl_1.png";

    public Shooting(Pane playerPane,ParallelTransition parallelTransition,double multiply, String typeplayer, int orient, double hue){
        this.PlayerPane = playerPane;
        this.parallelTransition = parallelTransition;
        this.multiply = multiply;
        this.typeplayer = typeplayer;
        this.orient = orient;
        this.hue = hue;
    }
    public TranslateTransition setParams(double X, double Y, String image_path, double damage, double ScaleX,double ScaleY, int mill, Pane LayerPane){
        ImageView newshoot = new ImageView();
        newshoot.setLayoutX(X);
        newshoot.setLayoutY(Y);
        newshoot.setScaleY(orient*ScaleY*0.5);
        newshoot.setScaleX(orient*ScaleX);
        newshoot.setImage(new Image(String.valueOf(new File(image_path))));
        newshoot.setAccessibleText(String.valueOf(damage));
        newshoot.setId(String.valueOf(typeplayer));
        newshoot.setEffect(new ColorAdjust(hue,0,0,0));
        LayerPane.getChildren().add(newshoot);
        TranslateTransition shootTranslate = new TranslateTransition(Duration.millis(mill),newshoot);
        shootTranslate.setToX(1200*orient);
        shootTranslate.setInterpolator(Interpolator.LINEAR);
        return shootTranslate;
    }
}
