package game;

import game.controllers.CollisionManger;
import game.controllers.Controller;
import game.enemys.EnemyController;
import game.models.GameRect;
import game.views.ImageRender;

import java.awt.*;

/**
 * Created by ADMIN on 5/2/2017.
 */
public class BulletController extends Controller implements Collider{

    public BulletController(int x, int y, Image image) {
        this.gameRect = new GameRect(x, y, 13,33);
        this.imageRender = new ImageRender(image);

        CollisionManger.istance.add(this);
    }

    public void update() {
        gameRect.move(0, -10);
        if (this.gameRect.getY() <= 0){
            this.gameRect.setDead(true);
        }
    }

    @Override
    public void onCollide(Collider other) {
        if (other instanceof EnemyController){
            this.gameRect.setDead(true);
            //CollisionManger.istance.remove(this);
        }
    }
}
