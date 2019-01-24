package com.mygdx.game.action;

import java.util.LinkedList;

public class ActionQueue {

    private LinkedList<Action> queue;
    public ActionQueue() {
        queue = new LinkedList<Action>();
    }

    public void update() {
        if(!queue.isEmpty()) {
            queue.peek().update();
            if (queue.peek().finished()) {
                queue.pop();
            }
        }
    }

    public void add(Action action) {
        queue.add(action);
    }
}
