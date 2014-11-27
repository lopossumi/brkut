package com.milo.brkut.Main;

import java.io.*;
import sun.audio.*;

/**
 *
 * @author milo
 */
public class SoundEngine {

    public SoundEngine() {
    }

    public void hit() {
        play("media/hit1.wav");
    }

    public void died() {
        play("media/died.wav");
    }

    public void play(String sample) {
        try {
            InputStream in = new FileInputStream(sample);
            AudioStream audio = new AudioStream(in);
            AudioPlayer.player.start(audio);
        } catch (Exception ex) {
        }
    }
}
