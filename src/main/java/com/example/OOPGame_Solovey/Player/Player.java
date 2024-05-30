package com.example.OOPGame_Solovey.Player;

import javafx.animation.ParallelTransition;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.File;
/**
 * Абстрактный класс представляет игрока.
 * Он содержит абстрактные методы для стрельбы, проверки столкновения и навигации.
 */
public abstract class Player {

    /**
     * Pane представляет панель игрока.
     */
    @FXML
    protected Pane playerpane;

    /**
     * Pane представляет слой игры.
     */
    @FXML
    protected Pane LayerPane;

    /**
     * Переменная представляет анимацию игрока.
     */
    private double anim_hero = 0;

    /**
     * ParallelTransition представляет параллельную анимацию.
     */
    protected ParallelTransition parallelTransition;

    /**
     * Строка представляет тип игрока.
     */
    String typeplayer;

    /**
     * Двойное число представляет множитель для игрока.
     */
    double muliply;

    /**
     * ImageView представляет изображение игрока.
     */
    @FXML
    protected ImageView playerImage;

    /**
     * Конструктор инициализирует игрока.
     *
     * @param playerpane Pane - панель игрока
     * @param LayerPane Pane - слой игры
     * @param parallelTransition ParallelTransition - параллельная анимация
     * @param typeplayer String - тип игрока
     * @param muliply double - множитель для игрока
     */
    public Player(Pane playerpane, Pane LayerPane, ParallelTransition parallelTransition, String typeplayer, double muliply) {
        this.playerpane = playerpane;
        this.LayerPane = LayerPane;
        this.parallelTransition = parallelTransition;
        this.typeplayer = typeplayer;
        this.muliply = muliply;
    }

    /**
     * Метод устанавливает анимацию игрока.
     *
     * @param playerImage ImageView - изображение игрока
     * @param lvl_hero int - уровень игрока
     */
    public void setAnim_lvl(ImageView playerImage, int lvl_hero) {
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

    /**
     * Абстрактный метод стреляет игроком.
     *
     * @param lvl int - уровень игры
     * @param fire boolean - состояние стрельбы
     */
    public abstract void shoot(int lvl, boolean fire);

    /**
     * Абстрактный метод проверяет столкновение игрока с врагами.
     *
     * @param enemyPane Pane - панель с врагами
     * @return double - результат проверки столкновения
     */
    public abstract double checkCollision(Pane enemyPane);

    /**
     * Абстрактный метод выполняет навигацию игрока.
     */
    public abstract void navigation();
    /**
     * Метод выполняет установку местоположения.
     */
    public void setNav(boolean left,boolean right,boolean up,boolean down) {}

    /**
     * Метод проверяющий столкновение со стеной
     */
    public void checkWall() {}
}


