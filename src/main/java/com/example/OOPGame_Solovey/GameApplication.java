package com.example.OOPGame_Solovey;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import java.io.IOException;

public class GameApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GameApplication.class.getResource("gamescene.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 720);
        Controller controller = fxmlLoader.getController();

        stage.setScene(scene);
        scene.setOnKeyPressed(e -> {
            if(e.getCode() == KeyCode.A){
                controller.left = true;
            }
            if(e.getCode() == KeyCode.D){
                controller.right = true;
            }
            if(e.getCode() == KeyCode.S){
                controller.down = true;
            }
            if(e.getCode() == KeyCode.W){
                controller.up = true;
            }
            if(e.getCode() == KeyCode.SPACE){
                controller.fire = true;
            }
            if (e.getCode() == KeyCode.DIGIT1){
                controller.setLvl(1);
            }
            if (e.getCode() == KeyCode.DIGIT2){
                controller.setLvl(2);
            }
            if (e.getCode() == KeyCode.DIGIT3){
                controller.setLvl(3);
            }
            if (e.getCode() == KeyCode.ENTER){
                controller.isPause = !controller.isPause;
                if (controller.isWin){
                    controller.setScore(0);
                    controller.isWin = !controller.isWin;
                    controller.isPause = false;
                }
            }
        });
        scene.setOnKeyReleased(e ->{
            if(e.getCode() == KeyCode.A){
                controller.left = false;
            }
            if(e.getCode() == KeyCode.D){
                controller.right = false;
            }
            if(e.getCode() == KeyCode.S){
                controller.down = false;
            }
            if(e.getCode() == KeyCode.W){
                controller.up = false;
            }
            if(e.getCode() == KeyCode.SPACE){
                controller.fire = false;
            }
        });
        stage.show();
    }


    public static void main(String[] args) {
        launch();

    }
}