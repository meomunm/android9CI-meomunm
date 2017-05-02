package game.controllers;

import game.Collider;
import game.models.GameRect;

import java.util.ArrayList;

/**
 * Created by ADMIN on 5/2/2017.
 */
public class CollisionManger {
    public static final CollisionManger istance =  new CollisionManger();
    private ArrayList<Collider> colliders;

    private CollisionManger() {
        this.colliders = new ArrayList<>();
    }

    public void update(){

        for (int i = 0; i < colliders.size() - 1; i++){
            for (int j = i + 1; j < colliders.size(); j++  ){
                Collider ci = colliders.get(i);
                Collider cj = colliders.get(j);

                GameRect rectI = ci.getGameRect();
                GameRect rectJ = cj.getGameRect();

                if (rectI.intersects(rectJ)){
                    ci.onCollide(cj);
                    cj.onCollide(ci);
                }
            }
        }
    }

    public void add(Collider other){
        this.colliders.add(other);
    }

    public void remove(Collider other){
        this.colliders.remove(other);
    }
}
