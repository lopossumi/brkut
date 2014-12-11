package com.milo.brkut.main;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * High Score file handler.
 * @author milo
 */
public class HighscoreIO {

    private final static String FILENAME = Config.HIGHSCORE_FILENAME;
    
    public static void update(int i) {
        int oldScore = read();
        if (i > oldScore) {
            try (DataOutputStream os = new DataOutputStream(new FileOutputStream(FILENAME))) {
                os.writeInt(i);
                os.close();
            } catch (Exception ex) {
                System.out.println("Could not write to file " + FILENAME);
            }
        }
    }

    public static int read() {
        try (DataInputStream is = new DataInputStream(new FileInputStream(FILENAME))) {
            int score = is.readInt();
            is.close();
            return score;
        } catch (Exception ex) {
            return 0;
        }
    }
}
