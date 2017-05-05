package game.enemys;

import game.controllers.Collider;
import game.controllers.PlayerController;
import game.controllers.CollisionManger;
import game.controllers.Controller;
import game.models.GameRect;
import game.views.ImageRender;

import java.awt.*;

/**
 * Created by ADMIN on 5/2/2017.
 */
public class EnemyBulletController extends Controller implements Collider {

    public EnemyBulletController (int x, int y, Image image){
        this.gameRect = new GameRect(x,y,image.getWidth(null), image.getHeight(null));
        this.imageRender = new ImageRender(image);

        CollisionManger.istance.add(this);
    }

    @Override
    public void onCollide(Collider other) {
        if (other instanceof PlayerController){
            this.gameRect.setDead(true);
            ((PlayerController) other).hitPoint(1);

            CollisionManger.istance.remove(this);
        }
    }

    @Override
    public void update() {
        gameRect.move(0,7);
        if (this.gameRect.getY() >= 768){
            this.gameRect.setDead(true);

            CollisionManger.istance.remove(this);
        }
    }
}
