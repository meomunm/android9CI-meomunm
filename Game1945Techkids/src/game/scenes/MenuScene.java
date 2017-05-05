package game.scenes;

import game.GameWindow;
import game.utils.Util;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by ADMIN on 5/4/2017.
 */
public class MenuScene implements GameScene {
    private Image background;
    private Image button;
    private int buttonX = 100;
    private int buttonY = 545;
    private int numb = 1;

    public MenuScene() {
        background = Util.imageLoad("res/gamemenu.png");
        button = Util.imageLoad("res/button.png");
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(background, 0, 0, 600, 800, null);
        g.drawImage(button, this.buttonX, this.buttonY, 50, 50, null);
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            GameWindow.istance.setCurrentScene(new Level1Scene());
        }
//        if (e.getKeyCode() == KeyEvent.VK_DOWN){
//            numb += 1;
//
//        }
//        if (e.getKeyCode() == KeyEvent.VK_UP)
//            numb -= 1;
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                if(numb > 1) {
                    numb -= 1;
                }
                selectMenu(numb);
                break;

            case KeyEvent.VK_DOWN:
                if (numb < 3){
                    numb += 1;
                }
                selectMenu(numb);
                break;
        }
    }

    public void selectMenu(int selectPoint) {
        if (selectPoint == 1) {
            this.buttonX = 100;
            this.buttonY = 545;
        }
        if (selectPoint == 2){
            this.buttonX = 180;
            this.buttonY = 595;
        }
        if (selectPoint == 3){
            this.buttonX = 170;
            this.buttonY = 643;
        }
    }
}
