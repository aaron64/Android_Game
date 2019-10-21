package com.mygdx.game.animation;

import com.mygdx.game.scenes.Scene;

import java.util.ArrayList;
import java.util.LinkedList;

public class AnimationQueue {
    private LinkedList<Animation> animations;
    private ArrayList<Animation> currentAnimations;

    private ArrayList<Animation> removeList;

    public AnimationQueue() {
        animations = new LinkedList<Animation>();
        currentAnimations = new ArrayList<Animation>();

        removeList = new ArrayList<Animation>();
    }

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

    public void add(Animation animation) {
        animations.add(animation);
    }

    public boolean locked() {
        for(Animation animation : currentAnimations) {
            if(animation.locked())
                return true;
        }
        return false;
    }

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
