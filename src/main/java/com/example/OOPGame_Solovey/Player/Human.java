package com.example.OOPGame_Solovey.Player;


import com.example.OOPGame_Solovey.Player.Shooting.ShootPlayer;
import javafx.animation.ParallelTransition;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/**
 * Класс представляет игрока-человека.
 * Он наследует класс Player и содержит методы для навигации и стрельбы.
 */
public class Human extends Player {

    /**
     * Переменная представляет состояние нажатия клавиши "A".
     */
    private boolean left = false;

    /**
     * Переменная представляет состояние нажатия клавиши "D".
     */
    private boolean right = false;

    /**
     * Переменная представляет состояние нажатия клавиши "S".
     */
    private boolean up = false;

    /**
     * Переменная представляет состояние нажатия клавиши "W".
     */
    private boolean down = false;

    /**
     * Конструктор инициализирует игрока-человека.
     *
     * @param playerImage ImageView - изображение игрока
     * @param playerpane Pane - панель игрока
     * @param LayerPane Pane - слой игры
     * @param parallelTransition ParallelTransition - параллельная анимация
     * @param typeplayer String - тип игрока
     * @param muliply double - множитель для игрока
     */
    public Human(ImageView playerImage, Pane playerpane, Pane LayerPane, ParallelTransition parallelTransition, String typeplayer, double muliply) {
        super(playerpane, LayerPane, parallelTransition, typeplayer, muliply);
    }

    /**
     * Метод устанавливает состояние навигации.
     *
     * @param left boolean - состояние нажатия клавиши "A"
     * @param right boolean - состояние нажатия клавиши "D"
     * @param up boolean - состояние нажатия клавиши "W"
     * @param down boolean - состояние нажатия клавиши "S"
     */
    public void setNav(boolean left, boolean right, boolean up, boolean down) {
        this.down = down;
        this.left = left;
        this.up = up;
        this.right = right;
    }

    /**
     * Переменная представляет контроллер стрельбы игрока.
     */
    protected ShootPlayer ShootPlayerController = new ShootPlayer(playerpane, LayerPane, parallelTransition, muliply, typeplayer, 1, 0);

    /**
     * Метод выполняет стрельбу.
     *
     * @param lvl int - уровень игры
     * @param fire boolean - состояние стрельбы
     */
    @Override
    public void shoot(int lvl, boolean fire) {
        ShootPlayerController.getShoot(playerpane, lvl, fire);
    }

    /**
     * Метод проверяет столкновение с врагами.
     *
     * @param enemyPane Pane - панель с врагами
     * @return double - результат проверки столкновения
     */
    @Override
    public double checkCollision(Pane enemyPane) {
        return ShootPlayerController.checkCollision(enemyPane);
    }

    /**
     * Метод проверяет столкновение с стеной.
     */
    @Override
    public void checkWall() {
        ShootPlayerController.checkWall();
    }

    /**
     * Метод выполняет навигацию.
     */
    @Override
    public void navigation() {
        int herospeed = 18;
        if(right && super.playerpane.getLayoutX() < 500f){
            playerpane.setLayoutX(playerpane.getLayoutX()+ herospeed);
        }
        if(left && playerpane.getLayoutX() > 28f){
            playerpane.setLayoutX(playerpane.getLayoutX()- herospeed);
        }
        if(down && playerpane.getLayoutY() < 600f){
            playerpane.setLayoutY(playerpane.getLayoutY()+ herospeed);
        }
        if(up && playerpane.getLayoutY() > 0f){
            playerpane.setLayoutY(playerpane.getLayoutY()- herospeed);
        }
    }
}
