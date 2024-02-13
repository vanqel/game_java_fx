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

public class Controller extends Statistic {
    @FXML
    public  boolean left = false;
    @FXML
    public  boolean right = false;
    @FXML
    public  boolean up = false;
    @FXML
    public  boolean down = false;
    @FXML
    public  boolean fire = false;
    @FXML
    public  boolean isPause = false;

    private int lvl = 1;

    @FXML
    public Label textBlackScreen;
    @FXML
    private Pane BlackScreen;
    @FXML
    private Pane enemyPane;
    @FXML
    private Pane playerPane;
    @FXML
    private Pane LayerPane;
    @FXML
    private ImageView enemy;
    @FXML
    private ImageView Hero;

    @FXML
    public VBox stats;
    @FXML
    public Label stats_text;
    @FXML
    private Rectangle stats_rect;
    private ParallelTransition parallelTransition;
    public Player human;
    public Enemy opponent;
    public void setHumanNav(){
        human.setNav(left,right,up,down);
    }
    public void setIsBlackScreenText(boolean VisibleScreen, String textScreen){
        BlackScreen.setVisible(VisibleScreen);
        textBlackScreen.setText(textScreen);
    }
    public void setIsBlackScreenText(boolean VisibleScreen){
        BlackScreen.setVisible(VisibleScreen);
    }
    public void setLvl(int level){
        lvl = level;
    }

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
