package game.views;

import game.models.GameRect;

import java.awt.*;

/**
 * Created by ADMIN on 5/2/2017.
 */
public class ImageRender {
    private Image image;

    public ImageRender(Image image) {
        this.image = image;
    }

    public void render(Graphics graphics, GameRect gameRect){
        graphics.drawImage(image, gameRect.getX(), gameRect.getY(), gameRect.getWidth(), gameRect.getHeight(), null);
    }
}
