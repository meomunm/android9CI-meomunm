package game.scenes;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by ADMIN on 5/4/2017.
 */
public interface GameScene {
    void update();

    void draw(Graphics g);

    void keyPressed(KeyEvent e);

    void keyReleased(KeyEvent e);
}
