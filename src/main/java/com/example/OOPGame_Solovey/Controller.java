package com.example.OOPGame_Solovey;

import com.example.OOPGame_Solovey.Player.Enemy;
import com.example.OOPGame_Solovey.Player.Human;
import com.example.OOPGame_Solovey.Player.Player;
import javafx.animation.AnimationTimer;
import javafx.animation.ParallelTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
/**
 * Класс представляет контроллер игры.
 * Он обрабатывает нажатия и отпускания клавиш, а также анимацию игры.
 */
public class Controller extends Statistic {

    /**
     * Переменная представляет состояние нажатия клавиши "A".
     */
    @FXML
    public boolean left = false;

    /**
     * Переменная представляет состояние нажатия клавиши "D".
     */
    @FXML
    public boolean right = false;

    /**
     * Переменная представляет состояние нажатия клавиши "S".
     */
    @FXML
    public boolean up = false;

    /**
     * Переменная представляет состояние нажатия клавиши "W".
     */
    @FXML
    public boolean down = false;

    /**
     * Переменная представляет состояние нажатия клавиши "SPACE".
     */
    @FXML
    public boolean fire = false;

    /**
     * Переменная представляет состояние паузы игры.
     */
    @FXML
    public boolean isPause = false;

    /**
     * Переменная представляет уровень игры.
     */
    private int lvl = 1;

    /**
     * Label представляет текст на черном экране.
     */
    @FXML
    public Label textBlackScreen;

    /**
     * Pane представляет черный экран.
     */
    @FXML
    private Pane BlackScreen;

    /**
     * Pane представляет панель с врагами.
     */
    @FXML
    private Pane enemyPane;

    /**
     * Pane представляет панель с игроком.
     */
    @FXML
    private Pane playerPane;

    /**
     * Pane представляет панель с игровыми объектами.
     */
    @FXML
    private Pane LayerPane;

    /**
     * ImageView представляет изображение врага.
     */
    @FXML
    private ImageView enemy;

    /**
     * ImageView представляет изображение игрока.
     */
    @FXML
    private ImageView Hero;

    /**
     * VBox представляет панель со статистикой.
     */
    @FXML
    public VBox stats;

    /**
     * Label представляет текст в панели со статистикой.
     */
    @FXML
    public Label stats_text;

    /**
     * Rectangle представляет прямоугольник в панели со статистикой.
     */
    @FXML
    private Rectangle stats_rect;

    /**
     * ParallelTransition представляет анимацию параллельных переходов.
     */
    private ParallelTransition parallelTransition;

    /**
     * Player представляет игрока.
     */
    public Player human;

    /**
     * Enemy представляет врага.
     */
    public Enemy opponent;

    /**
     * Метод устанавливает навигацию для игрока.
     */
    public void setHumanNav(){
        human.setNav(left,right,up,down);
    }

    /**
     * Метод устанавливает текст и видимость черного экрана.
     *
     * @param VisibleScreen boolean - видимость черного экрана
     * @param textScreen String - текст на черном экране
     */
    public void setIsBlackScreenText(boolean VisibleScreen, String textScreen){
        BlackScreen.setVisible(VisibleScreen);
        textBlackScreen.setText(textScreen);
    }

    /**
     * Метод устанавливает текст и видимость черного экрана.
     *
     * @param VisibleScreen boolean - видимость черного экрана
     */
    public void setIsBlackScreenText(boolean VisibleScreen){
        BlackScreen.setVisible(VisibleScreen);
    }
    /**
     * Метод устанавливает уровень игры.
     *
     * @param level int - уровень игры
     */
    public void setLvl(int level){
        lvl = level;
    }
    /**
     * Метод обрабатывает анимацию игры.
     */
    private final AnimationTimer animationTimer = new AnimationTimer() {
        @Override
        public void handle(long now) {
            if (!isPause && !isWin){
                setIsBlackScreenText(false);
                human.setAnim_lvl(Hero,lvl);
                opponent.setAnim_lvl(enemy,lvl);

                setHumanNav();
                human.navigation();
                opponent.navigation();

                human.shoot(lvl, fire);
                opponent.shoot(lvl,fire);

                human.checkWall();
                opponent.checkWall();

                Damage(human.checkCollision(enemyPane));
                Damage(opponent.checkCollision(playerPane));
            }
            else {
                if (isWin){
                    setIsBlackScreenText(isWin,checkWinner());
                }
                else {
                    setIsBlackScreenText(isPause, "Pause");
                }
            }
        }
    };
    /**
     * Метод инициализирует контроллер и конфигурацию игры.
     */
    @FXML
    void initialize(){
        animationTimer.start();
        setStatsParams(stats_rect, stats, stats_text);
        human = new Human(Hero,playerPane, LayerPane,parallelTransition,"Player Shoot",1);
        opponent = new Enemy(enemy, enemyPane, LayerPane, parallelTransition, "Enemy Shoot", 2);
        opponent.setTarget(playerPane);
        setStartPoint(stats.getLayoutX());
    }

}
