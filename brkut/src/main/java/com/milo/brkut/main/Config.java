package com.milo.brkut.main;

import java.awt.Color;

/**
 * Global configuration of game rules.
 * @author milo
 */

public class Config {
    
    public static final String HIGHSCORE_FILENAME = "highscore.dat";
    public static final boolean SOUNDS = false;
    public static final int ARENA_WIDTH = 800;
    public static final int ARENA_HEIGHT = 600;

    public static final double PLAYER_START_X = 100;
    public static final double PLAYER_START_Y = 500;
    public static final double PLAYER_WIDTH = 100;
    public static final double PLAYER_HEIGHT = 15;
    public static final double PLAYER_MAXSPEED = 10;
    public static final double PLAYER_ACCELERATION = 1;
    public static final int PLAYER_LIVES = 3;
    public static final Color PLAYER_COLOR = Color.WHITE;
    
    public static final double BALL_START_X = 110;
    public static final double BALL_START_Y = 480;
    public static final double BALL_WIDTH = 10;
    public static final double BALL_HEIGHT = 10;
    public static final double BALL_MAXSPEED = 10;
    public static final Color BALL_COLOR = Color.WHITE;
    
    public static final int BRICK_HITPOINTS = 2;
    
}
