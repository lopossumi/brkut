BRKUT: Executive summary on implementation
==========================================

The application is divided into three packages (main, engine and logic):
* Main handles file I/O and launches the game engine
* Engine handles the update-view-hold -loop, and includes the graphical user interface, keyboard input and sounds
* Logic handles collisions between objects, their hitpoints and existence.

![Class Diagram](https://github.com/lopossumi/brkut/blob/master/documentation/ClassDiagram-2014-12-04.png)

**Figure 1:** Class diagram with packages and connections.

Sequence diagrams
=================

Hit event
---------
A simplified sequence diagram of one frame where a hit event occurs is presented below. Points are added, a sound sample is played and the screen is updated with the game state before going on hold before the next frame.

![Sequence Diagram Hit](https://github.com/lopossumi/brkut/blob/master/documentation/SequenceDiagramHit.png)

**Figure 2:** Sequence diagram for a hit event (one frame where a ball hits a brick).

Logic: Collision detection
==========================

Collisions are detected between GameObjects. To determine where the ball should bounce, the function also returns whether the collision was vertical or horizontal (corner cases default to vertical collision).

![Collision testing](https://github.com/lopossumi/brkut/blob/master/images/collision.png)

**Figure 3:** Testing collision detection in Excel.

Collision between the ball and the paddle alters the launch angle of the ball.
