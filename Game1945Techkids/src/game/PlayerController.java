package game;

import game.controllers.CollisionManger;
import game.controllers.Controller;
import game.models.GameRect;
import game.utils.Util;
import game.views.ImageRender;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by ADMIN on 5/2/2017.
 */
public class PlayerController extends Controller implements Collider{
    public static final PlayerController instance = new PlayerController(275,700,Util.imageLoad("res/plane3.png"));
    private ArrayList<BulletController> playerBulletControllers;

    private int dx;
    private int dy;
    private final int PLAYER_SPEED = 7;
    private int timeCountDown;
    private boolean shotDisable;
    private boolean isGameOver = false;
    private int HP = 10;

    private PlayerController(int x, int y, Image image) {
        this.gameRect = new GameRect(x, y, 70, 50);
        this.imageRender = new ImageRender(image);
        this.playerBulletControllers = new ArrayList<>();

        CollisionManger.istance.add(this);
    }

    public boolean isGameOver() {
        return isGameOver;
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
            BulletController bulletController = new BulletController(this.gameRect.getX() + 29, this.gameRect.getY() - 15, Util.imageLoad("res/bullet.png"));
            playerBulletControllers.add(bulletController);
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

    public void setPlayerBulletControllers(ArrayList<BulletController> playerBulletControllers) {
        this.playerBulletControllers = playerBulletControllers;
    }

    @Override
    public void onCollide(Collider other) {

    }

    public void hitPoint(int hit){
        HP -= hit;
        System.out.println(String.format("HP player: %s", this.HP));
        if (HP == 0){
            this.isGameOver = true;
            System.out.println("GAME OVER");
        }
    }
}
