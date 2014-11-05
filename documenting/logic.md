Collision detection
===================

Collisions are detected between GameObjects. To determine where the ball should bounce, the function also returns whether the collision was vertical or horizontal (corner cases default to vertical collision).

![alt tag](https://github.com/lopossumi/brkut/blob/master/images/collision.png)

**Figure 1:** Testing collision detection in Excel.

Extensions
----------

One possibility would be to alter the collision routine slightly so that corner hits would alter the angle of the ball.
