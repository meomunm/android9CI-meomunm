package game.controllers;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by ADMIN on 5/3/2017.
 */
public class ControllerManager {
    private ArrayList<Controller> controllers;
    public static final ControllerManager istance = new ControllerManager();

    public ArrayList<Controller> getControllers() {
        return controllers;
    }

    private ControllerManager() {
        this.controllers = new ArrayList<>();
    }

    public void add(Controller controller) {
        controllers.add(controller);
    }

    public void update() {
//        Iterator<Controller> iterator = controllers.iterator();
//        while (iterator.hasNext()) {
//            Controller controller = iterator.next();
//            if (controller.gameRect.isDead()) {
//                controllers.remove(controller);
//            }
//        }
        for (int i = 0; i < controllers.size(); i++) {
            if (controllers.get(i).gameRect.isDead()) {
                controllers.remove(i); //khi va cham xoa doi tuong ra khoi man hinh
            }
        }
//        for (Controller controller: controllers){
//            controller.update();
//        }
//
        for (int i = 0; i < controllers.size(); i++) {
            controllers.get(i).update();    //quet qua cac controller va goi method update (move of object)
        }
    }

    public void draw(Graphics graphics) {
        for (Controller controller : controllers) {
            controller.draw(graphics);
        }
    }
}
