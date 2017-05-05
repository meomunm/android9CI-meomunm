package game.scenes;

import game.controllers.*;
import game.enemys.EnemyController;
import game.utils.Util;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Random;

/**
 * Created by ADMIN on 5/4/2017.
 */
public class Level1Scene implements GameScene {
    private int intTimeSpaw = 100; //time spaw enemy
    private Image background1;
    private Image background2;

    private Background bg1;
    private Background bg2; //Scrolling background -> contructor -> update -> set speed

    private PowerController powerController;
    private PlayerController playerController;
    private InputManager inputManager = new InputManager(); //Key controller of plane player
    private Random random;

    public Level1Scene() {
        random = new Random();
        powerController = new PowerController(0, -100, Util.imageLoad("res/power-up.png"));
        playerController = new PlayerController(275, 700, Util.imageLoad("res/plane3.png"));

        ControllerManager.istance.add(powerController);

        bg1 = new Background(0, 1600);
        bg2 = new Background(0, 0);
        bg1.setSpeedY(1);
        bg2.setSpeedY(1);

        background1 = Util.imageLoad("res/background1.png");
        background2 = Util.imageLoad("res/background2.png");
    }

    @Override
    public void update() {
        intTimeSpaw++;

        playerController.processInput(
                inputManager.isUpPressd(),
                inputManager.isDownPressd(),
                inputManager.isLeftPressd(),
                inputManager.isRightPressd(),
                inputManager.isSpacePressd());
        playerController.update();

        if (powerController.getGameRect().getY() <= 0) {
            powerController.getGameRect().move(0, -1);
        } else {
            powerController.update();
        }

        if (intTimeSpaw >= 100) {
            EnemyController enemyController = new EnemyController(random.nextInt(551), 0, Util.imageLoad("res/enemy-green-3.png"));
            ControllerManager.istance.add(enemyController);

            intTimeSpaw = 0;
        }
        bg1.update();
        bg2.update();
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(background1, bg1.getBgX(), bg1.getBgY(), background1.getWidth(null), background1.getHeight(null), null);
        g.drawImage(background2, bg2.getBgX(), bg2.getBgY(), background2.getWidth(null), background2.getHeight(null), null);

        playerController.draw(g);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                inputManager.setUpPressd(true);
                break;
            case KeyEvent.VK_DOWN:
                inputManager.setDownPressd(true);
                break;
            case KeyEvent.VK_LEFT:
                inputManager.setLeftPressd(true);
                break;
            case KeyEvent.VK_RIGHT:
                inputManager.setRightPressd(true);
                break;
            case KeyEvent.VK_SPACE:
                inputManager.setSpacePressd(true);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                inputManager.setUpPressd(false);
                break;
            case KeyEvent.VK_DOWN:
                inputManager.setDownPressd(false);
                break;
            case KeyEvent.VK_LEFT:
                inputManager.setLeftPressd(false);
                break;
            case KeyEvent.VK_RIGHT:
                inputManager.setRightPressd(false);
                break;
            case KeyEvent.VK_SPACE:
                inputManager.setSpacePressd(false);
                break;
        }
    }
}
