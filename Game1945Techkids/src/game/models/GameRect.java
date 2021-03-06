package game.models;

import java.awt.*;

/**
 * Created by ADMIN on 5/2/2017.
 */
public class GameRect {
    private int x;
    private int y;
    private int width;
    private int height;
    private boolean isDead;

    protected int HP;

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public GameRect(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public boolean isDead() {
        return isDead;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void move(int dx, int dy){
        this.x += dx;
        this.y += dy;
    }

    public boolean intersects(GameRect other){
        Rectangle rect1 = new Rectangle(this.x, this.y, this.width, this.height);
        Rectangle rect2 = new Rectangle(other.getX(), other.getY(), other.getWidth(), other.getHeight());
        return  rect1.intersects(rect2);
    }


    public void getHit(int damage){
        this.HP -= damage;
        if (this.HP == 0){
            this.setDead(true);
        }
    }
}
