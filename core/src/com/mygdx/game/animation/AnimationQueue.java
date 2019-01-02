package com.mygdx.game.animation;

import com.mygdx.game.scenes.Scene;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class AnimationQueue {
    private LinkedList<Animation> animations;
    private ArrayList<Animation> currentAnimations;

    public AnimationQueue() {
        animations = new LinkedList<Animation>();
        currentAnimations = new ArrayList<Animation>();
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

        if(currentAnimationsFinished()) {
            animations.removeAll(currentAnimations);
            currentAnimations.clear();
        }
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

    public boolean currentAnimationsFinished() {
        for(Animation animation : currentAnimations) {
            if(!animation.done())
                return false;
        }
        return true;
    }
}
