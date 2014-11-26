/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milo.brkut.Logic;

import java.util.ArrayList;
import java.util.HashSet;

/**
 *
 * @author mikko
 */
class Collision {

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
	 * @param other the second object
	 * @return -1 for vertical collision, 1 for horizontal, 0 for no
	 * collision. For corner cases the default is vertical collision.
	 */
	static bounce collision(GameObject ego, GameObject other) {
		double xOverlapAmount = -Math.abs(ego.getX() - other.getX()) + (ego.getWidth() + other.getWidth()) / 2;
		double yOverlapAmount = -Math.abs(ego.getY() - other.getY()) + (ego.getHeight() + other.getHeight()) / 2;

		// No collision (overlap <= 0 on either axis)
//        if (xOverlapAmount <= 0 || yOverlapAmount <= 0) {
//            return bounce.NONE;
//        } else if (xOverlapAmount >= yOverlapAmount) {
//            return bounce.VERTICAL;
//        } else {
//            return bounce.HORIZONTAL;
//        }
		if (xOverlapAmount <= 0 || yOverlapAmount <= 0) {
			return bounce.NONE;
		} else if (xOverlapAmount > 0) {
			return bounce.VERTICAL;
		} else {
			return bounce.HORIZONTAL;

		}
	}

	static void clear(GameObject ego, GameObject other) {
		double xOverlapAmount = -Math.abs(ego.getX() - other.getX()) + (ego.getWidth() + other.getWidth()) / 2;
		double yOverlapAmount = -Math.abs(ego.getY() - other.getY()) + (ego.getHeight() + other.getHeight()) / 2;

		if (xOverlapAmount <= 0 || yOverlapAmount <= 0) {

		} else if (xOverlapAmount > 0) {
			if (ego.getY() < other.getY()) {
				ego.move(0, -yOverlapAmount);
			} else {
				ego.move(0, yOverlapAmount);
			}
		} else if (yOverlapAmount > 0) {
			if (ego.getX() < other.getX()) {
				ego.move(0, -xOverlapAmount);
			} else {
				ego.move(0, xOverlapAmount);
			}
		}
	}

	static enum bounce {

		NONE, VERTICAL, HORIZONTAL
	}
}