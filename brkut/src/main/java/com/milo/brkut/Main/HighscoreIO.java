/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milo.brkut.Main;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 *
 * @author milo
 */
public class HighscoreIO {

    private final static String filename = "highscore.dat";
    
    public static void update(int i) {
        int oldScore = read();
        if (i > oldScore) {
            try (DataOutputStream os = new DataOutputStream(new FileOutputStream(filename))) {
                os.writeInt(i);
                os.close();
            } catch (Exception ex) {
            }
        }
    }

    public static int read() {
        try (DataInputStream is = new DataInputStream(new FileInputStream(filename))) {
            int score = is.readInt();
            is.close();
            return score;
        } catch (Exception ex) {
            return 0;
        }
    }
}
