package com.example.OOPGame_Solovey.Player;


import com.example.OOPGame_Solovey.Player.Shooting.ShootPlayer;
import javafx.animation.ParallelTransition;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;


public class Human extends Player{
    private boolean left = false;
    private boolean right = false;
    private boolean up = false;
    private boolean down = false;

    public void setNav(boolean left,boolean right,boolean up,boolean down) {
        this.down = down;
        this.left = left;
        this.up = up;
        this.right = right;
    }
    public Human(ImageView playerImage, Pane playerpane, Pane LayerPane, ParallelTransition parallelTransition, String typeplayer, double muliply) {
        super(playerpane, LayerPane, parallelTransition, typeplayer, muliply);
    }

    protected ShootPlayer ShootPlayerController = new ShootPlayer(playerpane,LayerPane,parallelTransition,muliply,typeplayer,1,0);
    @Override
    public void shoot(int lvl, boolean fire) {
        ShootPlayerController.getShoot(playerpane,lvl, fire);
    }
    @Override
    public double checkCollision(Pane enemyPane){
        return ShootPlayerController.checkCollision(enemyPane);

    }
    @Override
    public void checkWall(){
        ShootPlayerController.checkWall();
    }

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
