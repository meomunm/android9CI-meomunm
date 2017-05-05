package game.enemys;

import game.controllers.*;
import game.models.GameRect;
import game.utils.Util;
import game.views.ImageRender;

import java.awt.*;

/**
 * Created by ADMIN on 5/2/2017.
 */
public class EnemyController extends Controller implements Collider {

    private int timeShootDelay = 50;
    //ArrayList<EnemyBulletController> enemyBulletControllers;

    public EnemyController(int x, int y, Image image) {
        this.gameRect = new GameRect(x, y, 70, 51);
        this.imageRender = new ImageRender(image);
   //     enemyBulletControllers = new ArrayList<>();
        this.gameRect.setHP(2);
        CollisionManger.istance.add(this);
    }

    public void update() {
        gameRect.move(0, 2);
        timeShootDelay++;
        if (timeShootDelay >= 50) {
            EnemyBulletController enemyBulletController = new EnemyBulletController(this.gameRect.getX() + 19, this.gameRect.getY() + 30, Util.imageLoad("res/enemy_bullet.png"));
            ControllerManager.istance.add(enemyBulletController);
            timeShootDelay = 0;
        }
        if (this.gameRect.getX() >= 730) {
            this.gameRect.setDead(true);

            CollisionManger.istance.remove(this);
        }
    }

//    public void setEnemyBulletControllers(ArrayList<EnemyBulletController> enemyBulletControllers) {
//        this.enemyBulletControllers = enemyBulletControllers;
//    }

    @Override
    public void onCollide(Collider other) {
        if (other instanceof BulletController) {
            //Do nothing
            this.gameRect.getHit(1);
            if (this.gameRect.isDead()) {
                this.gameRect.setDead(true);
                CollisionManger.istance.remove(this);
            }
        }
    }
}
