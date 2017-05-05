package game.controllers;

import game.models.GameRect;
import game.views.ImageRender;

import java.awt.*;

/**
 * Created by ADMIN on 5/5/2017.
 */
public class PowerController extends Controller implements Collider {

    public PowerController(int x, int y, Image image) {
        this.gameRect = new GameRect(x, y, image.getWidth(null), image.getHeight(null));
        this.imageRender = new ImageRender(image);

        CollisionManger.istance.add(this); //tao muon add chinh tao vao cai be va cham
    }

    @Override
    public void update() {
        this.gameRect.move(1,2);
    }

    @Override
    public void draw(Graphics graphics) {
        this.imageRender.render(graphics,this.gameRect);
    }

    @Override
    public void onCollide(Collider other) {
        if (other instanceof PlayerController){
            this.gameRect.setDead(true);
            CollisionManger.istance.remove(this);
        }
    }
}
