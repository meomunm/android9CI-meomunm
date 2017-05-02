package game;

import game.models.GameRect;

/**
 * Created by ADMIN on 5/2/2017.
 */
public interface Collider {
    GameRect getGameRect();
    void onCollide(Collider other);
}
