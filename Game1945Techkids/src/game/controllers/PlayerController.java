package game.controllers;

import game.GameWindow;
import game.models.GameRect;
import game.scenes.GameOverScene;
import game.utils.Util;
import game.views.ImageRender;

import java.awt.*;

/**
 * Created by ADMIN on 5/2/2017.
 */
public class PlayerController extends Controller implements Collider {

    private int dx, dy, lvPower, HP;
    private final int PLAYER_SPEED = 8;
    private int timeCountDown;
    private boolean shotDisable;

//    private boolean doubleBulletBehavior = false;

    public PlayerController(int x, int y, Image image) {
        this.gameRect = new GameRect(x, y, 70, 50);
        this.imageRender = new ImageRender(image);
        lvPower = 1;
        HP = 10;
        //  this.playerBulletControllers = new ArrayList<>();

        CollisionManger.istance.add(this);
    }

    public void processInput(boolean isUpPressed, boolean isDownPressed, boolean isLeftPressed, boolean isRightPressed, boolean isSpacePressed) {
        dx = 0;
        dy = 0;

        if (isUpPressed) {
            this.dy -= PLAYER_SPEED;
        }
        if (isDownPressed) {
            this.dy += PLAYER_SPEED;
        }
        if (isLeftPressed) {
            this.dx -= PLAYER_SPEED;
        }
        if (isRightPressed) {
            this.dx += PLAYER_SPEED;
        }
        if (isSpacePressed && !shotDisable) {
            shotDisable = true;
            if (this.lvPower == 1) {
                BulletController bulletController = new BulletController(this.gameRect.getX() + 29, this.gameRect.getY() - 15, Util.imageLoad("res/bullet-single.png"));
                ControllerManager.istance.add(bulletController);
            }
            if (this.lvPower == 2) {
                BulletController bulletController = new BulletController(this.gameRect.getX() + 29, this.gameRect.getY() - 15, Util.imageLoad("res/bullet-double.png"));
                ControllerManager.istance.add(bulletController);
            }
//            if (this.lvPower == 3){
//
//            }
//            switch (this.lvPower) {
//                case 1:
//                    BulletController bulletController = new BulletController(this.gameRect.getX() + 29, this.gameRect.getY() - 15, Util.imageLoad("res/bullet-single.png"));
//                    ControllerManager.istance.add(bulletController);
//                    break;
//                case 2:
//                    BulletController bulletController = new BulletController(this.gameRect.getX() + 29, this.gameRect.getY() - 15, Util.imageLoad("res/bullet-double.png"));
//                    ControllerManager.istance.add(bulletController);
//            }
        }
    }

    public void draw(Graphics graphics) {
        imageRender.render(graphics, gameRect);
    }

    public void update() {
        this.gameRect.move(dx, dy);
        if (shotDisable) {
            timeCountDown++;
            if (timeCountDown > 10) {
                shotDisable = false;
                timeCountDown = 0;
            }
        }
    }
//
//    public void setPlayerBulletControllers(ArrayList<BulletController> playerBulletControllers) {
//        this.playerBulletControllers = playerBulletControllers;
//    }

    @Override
    public void onCollide(Collider other) {
        if (other instanceof PowerController) {
            if (this.lvPower < 2) {
                this.lvPower += 1;
            }
        }
    }

    public void hitPoint(int hit) {
        HP -= hit;
        System.out.println(String.format("HP player: %s", this.HP));
        if (HP == 0) {
            System.out.println("GAME OVER");
            GameWindow.istance.setCurrentScene(new GameOverScene());
        }
    }
}