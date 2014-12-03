package com.milo.brkut.Logic;

import java.util.ArrayList;
import java.util.HashSet;

/**
 *
 * @author mikko
 */
class Collision {

    /**
     * Gets a list of collisions between one GameObject and a set of other
     * objects.
     *
     * @param ego The first object (e.g. ball)
     * @param others Set of other objects (e.g. bricks)
     * @return List of objects in collision with the first parameter.
     */
    static ArrayList<GameObject> getColliders(GameObject ego, HashSet<GameObject> others) {
        ArrayList<GameObject> colliders = new ArrayList<>();
        for (GameObject other : others) {
            if (collision(ego, other) != bounce.NONE) {
                colliders.add(other);
            }
        }
        return colliders;
    }

    /**
     * Checks collision between two objects (this and the other).
     *
     * @param ego the first object
     * @param other the second object
     * @return VERTICAL for vertical collision, HORIZONTAL for horizontal, NONE
     * for no collision. For corner cases the default is vertical collision.
     */
    static bounce collision(GameObject ego, GameObject other) {
        double xOverlapAmount = xOverlap(ego, other);
        double yOverlapAmount = yOverlap(ego, other);

        if (xOverlapAmount <= 0 || yOverlapAmount <= 0) {
            return bounce.NONE;
        } else if (xOverlapAmount > yOverlapAmount) {
            return bounce.VERTICAL;
        } else {
            return bounce.HORIZONTAL;

        }
    }

    /**
     * Moves an object so that collision state is no longer active.
     *
     * @param ego Object to be moved
     * @param other Possibly colliding object
     */
    static void clear(GameObject ego, GameObject other) {
        double xOverlapAmount = xOverlap(ego, other);
        double yOverlapAmount = yOverlap(ego, other);

        if (xOverlapAmount <= 0 || yOverlapAmount <= 0) {

        } else if (xOverlapAmount > yOverlapAmount) {
            if (ego.getY() < other.getY()) {
                ego.move(0, -yOverlapAmount);
            } else {
                ego.move(0, yOverlapAmount);
            }
        } else {
            if (ego.getX() < other.getX()) {
                ego.move(-xOverlapAmount,0);
            } else {
                ego.move(xOverlapAmount,0);
            }
        }
    }

    /**
     * Returns the amount of overlap between objects (e.g. positive value of 50 means that the objects must be moved 50 units to be clear on the X axis).
     * @param ego First object
     * @param other Second object
     * @return Amount of overlap
     */
    static double xOverlap(GameObject ego, GameObject other) {
        return -Math.abs(ego.getX() - other.getX()) + (ego.getWidth() + other.getWidth()) / 2;
    }
    
    /**
     * Returns the amount of overlap between objects (e.g. positive value of 50 means that the objects must be moved 50 units to be clear on the Y axis).
     * @param ego First object
     * @param other Second object
     * @return Amount of overlap
     */
    static double yOverlap(GameObject ego, GameObject other) {
        return -Math.abs(ego.getY() - other.getY()) + (ego.getHeight() + other.getHeight()) / 2;
    }

    /**
     * Bounce types.
     */
    static enum bounce {
        NONE, VERTICAL, HORIZONTAL
    }
}
