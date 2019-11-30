package com.mygdx.game.action;

import java.util.LinkedList;

/**
 * ActionQueue
 *
 * This class manages multiple actions
 * in order for them to be stacked and
 * be used consecutively
 *
 * @author  Aaron Chambers
 */
public class ActionQueue {

    private LinkedList<Action> queue;

    /**
     * ActionQueue constructor
     */
    public ActionQueue() {
        queue = new LinkedList<Action>();
    }

    /**
     * update
     * updates the first action and checks
     * if the action is finished, if it is finished
     * it gets popped
     */
    public void update() {
        if(!queue.isEmpty()) {
            queue.peek().update();
            if (queue.peek().finished()) {
                queue.pop();
            }
        }
    }

    /**
     * add
     * adds an action to the action queue
     * @param action The action being added
     */
    public void add(Action action) {
        queue.add(action);
    }

    /**
     * addNow
     * adds an action immediately to the action queue
     * @param action The action being added
     */
    public void addNow(Action action) {
        queue.addFirst(action);
    }
}
