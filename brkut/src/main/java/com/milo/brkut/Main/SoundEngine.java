package com.milo.brkut.Main;

import java.io.*;
import sun.audio.*;

/**
 *
 * @author milo
 */
public class SoundEngine {

    public SoundEngine(){
    }

    public void hit() throws Exception {
        String hit = "media/hit1.wav";
        InputStream in = new FileInputStream(hit);
        AudioStream audio = new AudioStream(in);
        AudioPlayer.player.start(audio);
    }
}
