package com.example.OOPGame_Solovey.Player.Shooting;

import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
/**
 * Класс, отвечающий за стрельбу врага.
 */
public class ShootEnemy extends Shooting{
    /**
     * Конструктор класса ShootEnemy.
     *
     * @param playerPane Панель игрока
     * @param LayerPane Панель слоев
     * @param parallelTransition Параллельный переход
     * @param multiply Коэффициент усиления
     * @param typeplayer Тип игрока
     * @param orient Ориентация
     * @param hue Цвет
     */
    public ShootEnemy(Pane playerPane, Pane LayerPane,ParallelTransition parallelTransition, double multiply, String typeplayer, int orient, double hue) {
        super(playerPane, parallelTransition, multiply, typeplayer, orient, hue);
        LayerPane.getChildren().add(ShootingPaneEnemy);
    }
    private double shooting_time = 2;
    private final int lvl = 1;
    private final Pane ShootingPaneEnemy = new Pane();

    /**
     * Метод, отвечающий за стрельбу врага.
     *
     * @param PlayerPane Панель игрока
     * @param lvl Уровень стрельбы
     * @param fire Флаг, указывающий, нужно ли стрелять
     */
    public void getShoot(Pane PlayerPane, int lvl, boolean fire) {

        double x = PlayerPane.getLayoutX() + PlayerPane.getTranslateX() + 150 * orient;
        double y = PlayerPane.getLayoutY() + PlayerPane.getTranslateY() ;
        if (lvl == 1) {
            this.shooting_time += 0.1;
            if (shooting_time >= 0.75) {
                TranslateTransition shoot_1 = setParams(x-50, y + 10,  shootlvl1, 1*multiply*orient,1,1, 350, ShootingPaneEnemy);
                TranslateTransition shoot_2 = setParams(x-50, y + 60,  shootlvl1, 1*multiply*orient,1, 1,350, ShootingPaneEnemy);

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
                TranslateTransition shoot_1_1 = setParams(x-30*orient, y - 10 - 10,  shootlvl2, 1*multiply*orient,0.5,0.5, 750, ShootingPaneEnemy);
                TranslateTransition shoot_1 = setParams(x-10*orient, y - 10 + 10,  shootlvl2, 1*multiply*orient,0.75,0.5, 750, ShootingPaneEnemy);
                TranslateTransition shoot_2 = setParams(x, y - 10 + 40,  shootlvl2, 2*multiply*orient,1, 1,750, ShootingPaneEnemy);
                TranslateTransition shoot_3 = setParams(x, y - 10 + 70,  shootlvl2, 2*multiply*orient,1, 1, 750, ShootingPaneEnemy);
                TranslateTransition shoot_4 = setParams(x-10*orient, y - 10 + 100, shootlvl2, 1*multiply*orient,0.75,0.5, 750, ShootingPaneEnemy);
                TranslateTransition shoot_4_1 = setParams(x-30*orient, y - 10 + 120, shootlvl2, 1*multiply*orient,0.5,0.5, 750, ShootingPaneEnemy);
                parallelTransition = new ParallelTransition(
                        shoot_1, shoot_2, shoot_3, shoot_4,shoot_1_1,shoot_4_1
                );
                this.shooting_time = 0;
                parallelTransition.setCycleCount(1);
                parallelTransition.play();

            }
        }
        if (lvl == 3) {
            this.shooting_time += 0.1;
            if (shooting_time >= 0.1) {
                TranslateTransition shoot_1 = setParams(x+80*orient, y + 25,  shootlvl3, 0.5*multiply*orient,2,1, 100, ShootingPaneEnemy);
                parallelTransition = new ParallelTransition(shoot_1);
                this.shooting_time = 0;
                parallelTransition.setCycleCount(1);
                parallelTransition.play();
            }
        }

    }
    /**
     * Метод, проверяющий наличие препятствий на пути стрелы.
     */
    public void checkWall(){
        if (ShootingPaneEnemy.getChildren() == null){return;}
        for (int count = 0; count < ShootingPaneEnemy.getChildren().toArray().length; count++){
            ImageView shoot_hero =  (ImageView) ShootingPaneEnemy.getChildren().get(count);
            if (Math.abs(shoot_hero.getTranslateX()) == 1200){
                ShootingPaneEnemy.getChildren().remove(count);
            }
        }

    }
    /**
     * Метод, проверяющий столкновение стрелы с врагом.
     *
     * @param enemy Панель врага
     * @return Урон, нанесенный врагу
     */
    public double checkCollision(Pane enemy) {
        double damage = 0;
        if (ShootingPaneEnemy.getChildren() == null){return 0;}
        for (int count = 0; count < ShootingPaneEnemy.getChildren().toArray().length; count++){
            ImageView shoot_hero =  (ImageView) ShootingPaneEnemy.getChildren().get(count);
            if (shoot_hero.getBoundsInParent().intersects(enemy.getBoundsInParent())){
                System.out.println(shoot_hero.getId()+" damage "+shoot_hero.getAccessibleText());
                damage+=(Double.parseDouble(shoot_hero.getAccessibleText()));
                ShootingPaneEnemy.getChildren().remove(count);


            }
        }
        return damage;
    }
}
