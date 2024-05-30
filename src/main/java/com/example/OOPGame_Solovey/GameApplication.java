package com.example.OOPGame_Solovey;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import java.io.IOException;
/**
 * Класс представляет приложение игры.
 * Он загружает сцену игры из файла FXML, устанавливает обработчики событий нажатия и отпускания клавиш,
 * а также запускает приложение.
 */
public class GameApplication extends Application {

    /**
     * Метод вызывается при запуске приложения.
     * Он загружает сцену игры из файла FXML, устанавливает обработчики событий нажатия и отпускания клавиш,
     * а также показывает окно приложения.
     *
     * @param stage Stage - объект окна приложения
     * @throws IOException если файл сцены игры не может быть загружен
     */
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

    /**
     * Метод запускает приложение.
     *
     * @param args массив строк с параметрами командной строки
     */
    public static void main(String[] args) {
        launch();
    }
}