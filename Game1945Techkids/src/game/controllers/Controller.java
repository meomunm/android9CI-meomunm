package game.controllers;

import game.PlayerController;
import game.models.GameRect;
import game.views.ImageRender;

import java.awt.*;

/**
 * Created by ADMIN on 5/2/2017.
 */
public class Controller {
    protected GameRect gameRect;
    protected ImageRender imageRender;

    public Controller() {
    }

    public GameRect getGameRect() {
        return gameRect;
    }

    public Controller(GameRect gameRect, ImageRender imageRender) {
        this.gameRect = gameRect;
        this.imageRender = imageRender;
    }

    public void draw(Graphics graphics){
        imageRender.render(graphics, gameRect);
    }

    public void update(){

    }
}
