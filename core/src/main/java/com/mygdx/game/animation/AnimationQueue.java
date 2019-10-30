package com.mygdx.game.animation;

import com.mygdx.game.scenes.Scene;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * AnimationQueue
 *
 * This class handles an animation queue for entities
 *
 * @author  Aaron Chambers
 */
public class AnimationQueue {
    private LinkedList<Animation> animations;
    private ArrayList<Animation> currentAnimations;

    private ArrayList<Animation> removeList;

    /**
     * Animation constructor
     */
    public AnimationQueue() {
        animations = new LinkedList<Animation>();
        currentAnimations = new ArrayList<Animation>();

        removeList = new ArrayList<Animation>();
    }

    /**
     * update
     * iterates current animation(s) and updates each one
     */
    public void update(Scene scene) {
        if(!currentAnimations.isEmpty()) {
            for (Animation animation : currentAnimations) {
                animation.update(scene);
            }
        } else {
            if(!animations.isEmpty() && !animations.peek().isSimultaneous()) {
                currentAnimations.add(animations.pop());
            } else {
                while (!animations.isEmpty() && animations.peek().isSimultaneous()) {
                    currentAnimations.add(animations.pop());
                }
            }
        }

        for(Animation animation : currentAnimations){
            if(animation.done()) {
                removeList.add(animation);
            }
        }

        animations.removeAll(removeList);
        currentAnimations.removeAll(removeList);
        removeList.clear();
    }

    /**
     * add
     * adds animation to the queue
     * @param animation The animation to be added
     */
    public void add(Animation animation) {
        animations.add(animation);
    }

    /**
     * locked
     * @return Whether a current animation is locked
     */
    public boolean locked() {
        for(Animation animation : currentAnimations) {
            if(animation.locked())
                return true;
        }
        return false;
    }

    /**
     * inQueue
     * checks if an animation is in the queue by name
     * @param name Name of the animation to check for
     * @return Whether the spceified animation is in the queue
     */
    public boolean inQueue(String name) {
        for(Animation a : animations) {
            if(a.getName().equals(name))
                return true;
        }
        for(Animation a : currentAnimations) {
            if(a.getName().equals(name))
                return true;
        }
        return false;
    }
}
