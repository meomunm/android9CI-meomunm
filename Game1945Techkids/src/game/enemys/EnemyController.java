package game.enemys;

import game.BulletController;
import game.Collider;
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
public class EnemyController extends Controller implements Collider {
    private int timeShootDelay = 50;
    private ArrayList<EnemyBulletController> enemyBulletControllers;

    public EnemyController(int x, int y, Image image) {
        this.gameRect = new GameRect(x, y, 70, 51);
        this.imageRender = new ImageRender(image);
        enemyBulletControllers = new ArrayList<>();

        CollisionManger.istance.add(this);
    }

    public void update() {
        gameRect.move(0, 2);
        timeShootDelay++;
        if (timeShootDelay >= 50) {
            EnemyBulletController enemyBulletController = new EnemyBulletController(this.gameRect.getX() + 19, this.gameRect.getY() + 30, Util.imageLoad("res/enemy_bullet.png"));
            enemyBulletControllers.add(enemyBulletController);
            timeShootDelay = 0;
        }
        if (this.gameRect.getX() >= 730) {
            this.gameRect.setDead(true);
        }
    }

    public void setEnemyBulletControllers(ArrayList<EnemyBulletController> enemyBulletControllers) {
        this.enemyBulletControllers = enemyBulletControllers;
    }

    @Override
    public void onCollide(Collider other) {
        if (other instanceof BulletController) {
            //Do nothing
            this.gameRect.setDead(true);
           // CollisionManger.istance.remove(this);
        }
    }
}
