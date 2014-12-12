Бркут
======

BRKUT - Yet another breakout clone ([a java course project](http://javalabra.github.io/Javalabra2014-2/#/pages/)).

Features:
* High score!
* Sound effects!
* Green-on-black graphics!
* Animations!

About the project
=================
BRKUT is a Breakout clone, a simple pong-type game against a destructible brick wall. Each brick takes two hits to destroy, and each hit scores 100 points. The ball launch angle varies as a function of the contact point on the paddle. If the player misses the ball, one life is lost; when all lives are lost, the game is over. 

The bonus multiplier is the key to high scores: every time the ball hits a brick, multiplier increases by 0.1. When the ball hits the paddle, the multiplier resets back to 1.0x. In order to get a high score, aim for the gaps and try to get multiple consecutive hits on bricks. The starry wave effect in the middle of the screen shows the multiplier as its amplitude.

![Screenshot](https://github.com/lopossumi/brkut/blob/master/images/screenshot.gif)

**Figure 1:** Actual game footage.

How to play
===========
Press space to launch, press left to move the paddle to the left, and press right to move the paddle to the right. Aim for the high score - that's about it. :)

Documentation
=============
See [manual](/documentation/manual.md) for notes on project implementation, including sequence diagrams, manual testing and ideas for future development. Mutation test and checkstyle reports can be found on the documentation folder.

[Diary](https://github.com/lopossumi/brkut/blob/master/documentation/diary.md) contains the project schedule.
