package game;

import game.controllers.*;
import game.scenes.GameScene;
import game.scenes.Level1Scene;
import game.scenes.MenuScene;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;

/**
 * Created by ADMIN on 5/1/2017.
 */
public class GameWindow extends Frame {
    private BufferedImage bufferedImage;
    private Graphics backbufferGraphics;

    private GameScene currentScene;
    public static GameWindow istance;

    public GameWindow() {
        istance = this;
        setTitle("Game 1945");
        setVisible(true);
        setSize(600, 800);

        currentScene = new MenuScene();

        bufferedImage = new BufferedImage(600, 800, BufferedImage.TYPE_INT_ARGB);
        backbufferGraphics = bufferedImage.getGraphics();

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
                currentScene.keyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                currentScene.keyReleased(e);
            }
        });

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        repaint();
                        Thread.sleep(17);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    currentScene.update();
                  //  System.out.println(String.format("SIZE : %s", ControllerManager.istance.getControllers().size()));

                    ControllerManager.istance.update();
                    CollisionManger.istance.update();
                }
            }
        });

        thread.start();
    }

    @Override
    public void update(Graphics g) {
        currentScene.draw(backbufferGraphics);

        ControllerManager.istance.draw(backbufferGraphics);
        g.drawImage(bufferedImage, 0, 0, 600, 800, null);
    }
    public void setCurrentScene(GameScene currentScene) {
        this.currentScene = currentScene;
    }
}
