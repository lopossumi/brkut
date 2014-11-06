Бркут
======

BRKUT - Yet another breakout clone (a java course project).  

http://javalabra.github.io/Javalabra2014-2/#/pages/

Features: (TODO)
* High scores!
* Sound effects!
* Green-on-black graphics!
* Increasing difficulty level!

Additional features (pick some): (if the main features are finished too soon)
* Powerups!
* Multi-ball!
* Evolving music!
* Rotating paddle!
* Advanced graphics!
* Animated splash screen!
* Moving destructible objects!

About the project
=================
BRKUT is a simple pong-type game against a destructible brick wall. The bricks have different strengths (hit points). The ball launch angle varies as a function of the contact point on the paddle. The game proceeds to the next, more difficult level whenever all the bricks are destroyed. If the player misses the ball, one life is lost; when all lives are lost, the game is over.

Due to time constraints and personal preference, the game will feature oldskool lo-fi aesthetics: a square ball, limited amount of colours, 8-bit sounds and three-letter initials on the high score table.

![Concept art](https://github.com/lopossumi/brkut/blob/master/images/game.png)
**Figure 1:** Concept art. Actual game footage will differ.

TODO
====
**2nd deadline tasks:**
- [x] Start coding program logic
- [ ] Write unit tests [3/10+]
- [ ] Generate PIT report
- [x] Draw a preliminary class diagram 

**High order tasks:**
- [ ] Study testing
- [x] Concept art
- [ ] Sound design and sampling

**Coding tasks:**
- [x] Draw a blank canvas
- [x] Draw a rectangle on the canvas
- [x] Move a rectangle on the canvas
- [x] Bounce a square ball from < ^ > walls
- [ ] Move the rectangle (paddle) with user input (constant speed, no inertia)
- [ ] Add inertia
- [x] Add collision with paddle
- [ ] Launch angle function
- [x] Create bricks
- [x] Add collision with bricks
- [x] Destroyable bricks
- [ ] Score & lives & level counter
- [ ] Arena generation
- [ ] Levels
- [ ] Game over screen with stats
- [ ] High score table
- [ ] Launch screen (player press start)
- [ ] ...
