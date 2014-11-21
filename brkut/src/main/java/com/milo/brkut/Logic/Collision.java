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

    static ArrayList<GameObject> checkCollisions(GameObject ego, HashSet<GameObject> others) {
        ArrayList<GameObject> collisions = new ArrayList<>();
        for (GameObject other:others){
            if (ego.collision(other) != 0)
                collisions.add(other);
        }
        return collisions;
    }
    
}
