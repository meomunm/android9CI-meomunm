package game;

import game.controllers.CollisionManger;
import game.enemys.EnemyBulletController;
import game.enemys.EnemyController;
import game.utils.Util;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 * Created by ADMIN on 5/1/2017.
 */
public class GameWindow extends Frame {
    private int intTimeSpaw = 100;
    private InputManager inputManager = new InputManager();

    private BufferedImage bufferedImage;
    private Graphics backbufferGraphics;

    private Image background;

  //  private PlayerController playerController;
    private ArrayList<BulletController> playerBulletControllers;
    private ArrayList<EnemyController>enemyControllers;
    private ArrayList<EnemyBulletController>enemyBulletControllers;

    private Random random;

    public GameWindow() {
        setTitle("Game 1945");
        setVisible(true);
        setSize(600, 800);

        random = new Random();

        background = Util.imageLoad("res/background.png");
        bufferedImage = new BufferedImage(600, 800, BufferedImage.TYPE_INT_ARGB);
        backbufferGraphics = bufferedImage.getGraphics();

        enemyBulletControllers = new ArrayList<>();
        playerBulletControllers = new ArrayList<>();
        enemyControllers = new ArrayList<>();

        PlayerController.instance.setPlayerBulletControllers(playerBulletControllers);

        addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
                System.out.println("window opened");
            }

            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("window closing");
                System.exit(0);
            }

            @Override
            public void windowClosed(WindowEvent e) {
                System.out.println("window closed");
            }

            @Override
            public void windowIconified(WindowEvent e) {
                System.out.println("window  iconified");
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
                System.out.println("window deiconified");
            }

            @Override
            public void windowActivated(WindowEvent e) {
                System.out.println("window activated");
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
                System.out.println("window deactivated");
            }
        });
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                //Do nothing
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
        });

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true && !PlayerController.instance.isGameOver()) {
                    try {
                        intTimeSpaw++;
                        repaint();
                        Thread.sleep(17);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                        PlayerController.instance.processInput(
                                inputManager.isUpPressd(),
                                inputManager.isDownPressd(),
                                inputManager.isLeftPressd(),
                                inputManager.isRightPressd(),
                                inputManager.isSpacePressd());
                        PlayerController.instance.update();

                   //SPAWN ENEMY
                    if (intTimeSpaw >= 100){
                        EnemyController enemyController = new EnemyController(random.nextInt(551), 0, Util.imageLoad("res/enemy-green-3.png"));
                        enemyController.setEnemyBulletControllers(enemyBulletControllers);
                        enemyControllers.add(enemyController);
                        intTimeSpaw = 0;
                    }

                    for (BulletController bullet : playerBulletControllers) {
                        bullet.update();
                    }
                    //  UPDATE ENEMY
                    //                      enemyController.update();
                    for (EnemyController enemyController: enemyControllers){
                        enemyController.update();
                    }

                    for (EnemyBulletController enemyBulletController: enemyBulletControllers){
                        enemyBulletController.update();
                    }

                    CollisionManger.istance.update();
                }
            }
        });

        thread.start();
    }

    @Override
    public void update(Graphics g) {
        backbufferGraphics.drawImage(background, 0, 0, 600, 800, null);
        PlayerController.instance.draw(backbufferGraphics);
        for (BulletController bulletController : playerBulletControllers) {
            bulletController.draw(backbufferGraphics);
        }
        //enemyController.draw(backbufferGraphics);
        for (EnemyController enemyController: enemyControllers){
            enemyController.draw(backbufferGraphics);
        }

        for (EnemyBulletController enemyBulletController: enemyBulletControllers){
            enemyBulletController.draw(backbufferGraphics);
        }

        Iterator<EnemyController> enemyIterator = enemyControllers.iterator();
        while(enemyIterator.hasNext()){
            EnemyController enemyController = enemyIterator.next();
            if (enemyController.getGameRect().isDead()){
                enemyIterator.remove();
                CollisionManger.istance.remove(enemyController);
            }
        }

        Iterator<EnemyBulletController> enemyBulletControllerIterator = enemyBulletControllers.iterator();
        while(enemyBulletControllerIterator.hasNext()){
            EnemyBulletController enemyBulletController = enemyBulletControllerIterator.next();
            if (enemyBulletController.getGameRect().isDead()){
                enemyBulletControllerIterator.remove();
                CollisionManger.istance.remove(enemyBulletController);
            }
        }

        Iterator<BulletController> bulletControllerIterator = playerBulletControllers.iterator();
        while(bulletControllerIterator.hasNext()){
            BulletController bulletController = bulletControllerIterator.next();
            if (bulletController.getGameRect().isDead()){
                bulletControllerIterator.remove();
                CollisionManger.istance.remove(bulletController);
            }
        }

        g.drawImage(bufferedImage, 0, 0, 600, 800, null);
    }
}
