package game.scenes;

import game.utils.Util;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by ADMIN on 5/5/2017.
 */
public class GameOverScene implements GameScene {
    private Image background;

    public GameOverScene() {
        background = Util.imageLoad("res/gameover.png");
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(background, 0,0, 600, 800, null);
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
