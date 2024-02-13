package com.example.OOPGame_Solovey.Player.Shooting;


import com.example.OOPGame_Solovey.Statistic;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.Objects;

public class ShootPlayer extends Shooting {
    private double shooting_time = 2;
    private final int lvl = 1;
    private final Pane ShootingPanePlayer = new Pane();

    public ShootPlayer(Pane playerPane, Pane LayerPane, ParallelTransition parallelTransition, double multiply, String typeplayer, int orient, double hue) {
        super(playerPane, parallelTransition, multiply, typeplayer, orient, hue);
        LayerPane.getChildren().add(ShootingPanePlayer);
    }

    public void getShoot(Pane PlayerPane, int lvl, boolean fire) {
        if (!fire){
            shooting_time =1;
            return;
        }
        double x = PlayerPane.getLayoutX() + PlayerPane.getTranslateX() + 150 * orient;
        double y = PlayerPane.getLayoutY() + PlayerPane.getTranslateY() ;
        if (lvl == 1) {
            this.shooting_time += 0.1;
            if (shooting_time >= 0.75) {
                TranslateTransition shoot_1 = setParams(x, y + 10,  shootlvl1, 1*multiply*orient,1,1, 350, ShootingPanePlayer);
                TranslateTransition shoot_2 = setParams(x, y + 60,  shootlvl1, 1*multiply*orient,1, 1,350, ShootingPanePlayer);

                parallelTransition = new ParallelTransition(
                        shoot_1,shoot_2
                );
                this.shooting_time = 0;
                parallelTransition.setCycleCount(1);
                parallelTransition.play();
            }
        }
        if (lvl == 2) {
            this.shooting_time += 0.1;
            if (shooting_time >= 2) {
                TranslateTransition shoot_1 = setParams(x-10, y - 20 + 10,  shootlvl2, 1*multiply*orient,0.75,0.5, 750, ShootingPanePlayer);
                TranslateTransition shoot_2 = setParams(x, y - 20 + 40,  shootlvl2, 2*multiply*orient,1, 1,750, ShootingPanePlayer);
                TranslateTransition shoot_3 = setParams(x, y - 20 + 70,  shootlvl2, 2*multiply*orient,1, 1, 750, ShootingPanePlayer);
                TranslateTransition shoot_4 = setParams(x-10, y - 20 + 100, shootlvl2, 1*multiply*orient,0.75,0.5, 750, ShootingPanePlayer);
                parallelTransition = new ParallelTransition(
                      shoot_1, shoot_2, shoot_3, shoot_4
                );
                this.shooting_time = 0;
                parallelTransition.setCycleCount(1);
                parallelTransition.play();

            }
        }
        if (lvl == 3) {
            this.shooting_time += 0.1;
            if (shooting_time >= 0.2) {
                TranslateTransition shoot_1 = setParams(x+80, y + 35,  shootlvl3, 0.5*multiply*orient,2,1, 250, ShootingPanePlayer);
                parallelTransition = new ParallelTransition(shoot_1);
                this.shooting_time = 0;
                parallelTransition.setCycleCount(1);
                parallelTransition.play();
            }
        }

    }

    public void checkWall(){
        if (ShootingPanePlayer.getChildren() == null){return;}
        for (int count = 0; count < ShootingPanePlayer.getChildren().toArray().length; count++){
                ImageView shoot_hero =  (ImageView) ShootingPanePlayer.getChildren().get(count);
                if (Math.abs(shoot_hero.getTranslateX()) == 1200){
                    ShootingPanePlayer.getChildren().remove(count);
                }
            }
    }

    public double checkCollision(Pane enemy) {
        double damage = 0;
        if (ShootingPanePlayer.getChildren() == null){return 0;}
        for (int count = 0; count < ShootingPanePlayer.getChildren().toArray().length; count++){
            ImageView shoot_hero =  (ImageView) ShootingPanePlayer.getChildren().get(count);
            if (shoot_hero.getBoundsInParent().intersects(enemy.getBoundsInParent())){
                System.out.println(shoot_hero.getId()+" damage "+shoot_hero.getAccessibleText());
                damage += (Double.parseDouble(shoot_hero.getAccessibleText()));
                ShootingPanePlayer.getChildren().remove(count);
            }
        }
        return damage;
    }
}
