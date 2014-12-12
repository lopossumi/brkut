BRKUT: Manual - an executive summary on implementation
======================================================

Program Description
-------------------

When launching the program, a window is created (800x600). Game objects (the bricks, ball & player), effects and text elements are shown on the window: the game is now already running, and the paddle can be moved left and right.

The game engine determines the game state and draws pictures on screen, then proceeds to wait for 1/60 seconds. As the graphics are very simple, this results in a ~60 fps frame rate on a modern system.

The paddle accelerates rapidly and it has some inertia and friction. When the paddle and ball make contact, the ball angle changes relative to their positions.

Classes and packages
--------------------

The application is divided into three packages (main, engine and logic):
* Main handles file I/O and launches the game engine
* Engine handles the update-view-hold -loop, and includes the graphical user interface, keyboard input and sounds
* Logic handles collisions between objects, their hitpoints and existence.

![Class Diagram](https://raw.githubusercontent.com/lopossumi/brkut/master/documentation/Class%20diagrams/ClassDiagram-2014-12-04.png)

**Figure 1:** Class diagram with packages and connections.

Testing
-------

JUnit tests are written for the game logic package: PIT reports can be found on the PIT documents/PIT folder. Other packages were tested manually:
* **main**: Highscore is read from a file (highscore.dat). If no file is present, the high score is set to zero and the file is created on game over when a higher score is achieved.
* **engine**: Game starts, restarts and quits like it should. Keyboard events for a new game (Y/N) are not detected while the death animation is running, but it's hardly noticeable unless playing without sounds.
* **input**: Keyboard events were tested simply by playing the game. No problems were detected after using the boolean table to store key state.
* **gui**: Panel and GUI were also tested simply by running the game. All gameobjects report to correct places and the game runs smoothly on most systems. All game state dependent texts are shown correctly.
* **sound**: Sounds were tested by playing the game. Samples play correctly, but buffering would be needed to avoid lag on some systems. Most performance problems were fixed by creating threads for the audio.

Sequence diagrams
------------------

***Hit event***

A simplified sequence diagram of one frame where a hit event occurs is presented below. Points are added, a sound sample is played and the screen is updated with the game state before going on hold before the next frame.

![Sequence Diagram Hit](https://github.com/lopossumi/brkut/blob/master/documentation/Sequence%20diagrams/SequenceDiagramHit.png)

**Figure 2:** Sequence diagram for a hit event (one frame where a ball hits a brick).

***Move left***

Sequence for an event where player presses the LEFT key. The paddle accelerates left.
![Sequence Diagram Left](https://raw.githubusercontent.com/lopossumi/brkut/master/documentation/Sequence%20diagrams/SequenceDiagramMoveLeft.png)
**Figure 3:** Sequence diagram for a move left event (one frame).

Logic: Collision detection
--------------------------

Collisions are detected between GameObjects. To determine where the ball should bounce, the function also returns whether the collision was vertical or horizontal (corner cases default to vertical collision).

![Collision testing](https://github.com/lopossumi/brkut/blob/master/images/collision.png)

**Figure 4:** Testing collision detection in Excel.

Collision between the ball and the paddle alters the launch angle of the ball. Determining the launch angle is handled separately.

Sounds
======
I had some performance issues on this one, so now each played sample launches a thread. Sounds are played using sun.audio, and it's probably implemented poorly - but it's short, and it works.

Thanks for the sound samples go to Atom Splitter Audio & Mike Koenig.

Ideas on future development
---------------------------
I might rewrite the whole game on a proper game engine. The plan is to create a pong/breakout hybrid with two paddles, two balls and a brick wall in between.
