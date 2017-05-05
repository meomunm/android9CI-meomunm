package game.controllers;

/**
 * Created by ADMIN on 5/5/2017.
 */
public class Background {
    private int bgX, bgY, speedY;

    public Background(int bgX, int bgY) {
        this.bgX = bgX;
        this.bgY = bgY;
        this.speedY = 0;
    }

    public int getBgX() {
        return bgX;
    }

    public void setBgX(int bgX) {
        this.bgX = bgX;
    }

    public int getBgY() {
        return bgY;
    }

    public void setBgY(int bgY) {
        this.bgY = bgY;
    }

    public int getSpeedY() {
        return speedY;
    }

    public void setSpeedY(int speedY) {
        this.speedY = speedY;
    }

    public void update (){
        this.bgY += speedY;
        if (this.bgY >= 1600){
            this.bgY -= 3200;
        }
    }
}
