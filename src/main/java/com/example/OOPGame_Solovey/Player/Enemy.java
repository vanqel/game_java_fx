package com.example.OOPGame_Solovey.Player;

import com.example.OOPGame_Solovey.Player.Shooting.ShootEnemy;
import javafx.animation.Interpolator;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
/**
 * Класс представляет врага.
 * Он наследует класс Player и содержит методы для стрельбы и навигации.
 */
public class Enemy extends Player {

    /**
     * Конструктор инициализирует врага.
     *
     * @param playerImage ImageView - изображение врага
     * @param playerpane Pane - панель врага
     * @param LayerPane Pane - слой игры
     * @param parallelTransition ParallelTransition - параллельная анимация
     * @param typeplayer String - тип врага
     * @param muliply double - множитель для врага
     */
    public Enemy(ImageView playerImage, Pane playerpane, Pane LayerPane, ParallelTransition parallelTransition, String typeplayer, double muliply) {
        super(playerpane, LayerPane, parallelTransition, typeplayer, muliply);
    }

    /**
     * Переменная представляет цель врага.
     */
    private Pane target;

    /**
     * Переменная представляет таймер стрельбы.
     */
    private int timer = 250;

    /**
     * Переменная представляет таймер стрельбы.
     */
    private int timerShoot = 250;

    /**
     * Переменная представляет контроллер стрельбы врага.
     */
    protected ShootEnemy ShootEnemyController = new ShootEnemy(playerpane, LayerPane, parallelTransition, muliply, "Enemy Shoot", -1, 0.5);

    /**
     * Метод устанавливает цель для врага.
     *
     * @param target Pane - панель цели
     */
    public void setTarget(Pane target) {
        this.target = target;
    }

    /**
     * Метод выполняет стрельбу.
     *
     * @param lvl int - уровень игры
     * @param fire boolean - состояние стрельбы
     */
    @Override
    public void shoot(int lvl, boolean fire) {
        int randomShootTime = (int) (Math.random() * 5);
        this.timerShoot += randomShootTime;
        if (timerShoot >= 50) {
            ShootEnemyController.getShoot(playerpane, lvl, fire);
            if (timerShoot >= 100) {
                timerShoot = 0;
            }
        }
    }

    /**
     * Метод проверяет столкновение с другими врагами.
     *
     * @param enemyPane Pane - панель с другими врагами
     * @return double - результат проверки столкновения
     */
    @Override
    public double checkCollision(Pane enemyPane) {
        return ShootEnemyController.checkCollision(enemyPane);
    }

    /**
     * Метод выполняет навигацию.
     */
    @Override
    public void navigation() {
        timer++;
        if (timer >= 40){
            int x = (int) (Math.random() * 30 - 10) * 10;
            int y = (int) (Math.random() * 60 - 30) * 10;
            double avg = target.getLayoutY()- y;
            if (avg < 400 && avg > 200){
                double mill = 600;
                TranslateTransition enemyTranslate = new TranslateTransition(Duration.millis(mill),playerpane);
                enemyTranslate.setToX(x);
                enemyTranslate.setToY(y);
                enemyTranslate.setInterpolator(Interpolator.EASE_IN);
                ParallelTransition parallelTransition = new ParallelTransition( enemyTranslate );
                parallelTransition.setCycleCount(1);
                parallelTransition.play();
                timer = 0;
            }
        }
    }

    /**
     * Проверяет столкновение со стеной
     */
    @Override
    public void checkWall(){
        ShootEnemyController.checkWall();
    }

}
